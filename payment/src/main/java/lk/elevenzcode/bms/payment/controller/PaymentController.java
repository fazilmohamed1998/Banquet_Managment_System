package lk.elevenzcode.bms.payment.controller;

import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.common.dto.*;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.common.util.RandomIdUtil;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import lk.elevenzcode.bms.payment.dto.PaymentDataDto;
import lk.elevenzcode.bms.payment.dto.PaymentRefundInfoDto;
import lk.elevenzcode.bms.payment.dto.ReportCriteriaDto;
import lk.elevenzcode.bms.payment.model.Payment;
import lk.elevenzcode.bms.payment.model.PaymentMethod;
import lk.elevenzcode.bms.payment.model.enums.PaymentStatus;
import lk.elevenzcode.bms.payment.security.PaymentAuthority;
import lk.elevenzcode.bms.payment.service.PaymentMethodService;
import lk.elevenzcode.bms.payment.service.PaymentRefundService;
import lk.elevenzcode.bms.payment.service.PaymentService;
import lk.elevenzcode.bms.payment.service.PaymentStatusService;
import lk.elevenzcode.bms.reservation.model.Reservation;
import lk.elevenzcode.bms.reservation.model.enums.ReservationStatus;
import lk.elevenzcode.bms.reservation.service.ReservationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static lk.elevenzcode.bms.common.util.Constant.REQ_HEADER_ACCEPT_JSON;

@Controller
@RequestMapping("/app/payment")
@Secured(PaymentAuthority.ROLE_PAYMENT)
public class PaymentController extends BaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

  @Autowired
  private PaymentService paymentService;

  @Autowired
  private PaymentMethodService payMethodService;

  @Autowired
  private PaymentStatusService payStatusService;

  @Autowired
  private PaymentRefundService paymentRefundService;

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private ReportService reportService;

  @Value("${default.currency.symbol}")
  private String currencySymb;

  @Value("${app.company.name}")
  private String companyName;

  @Value("${app.company.address}")
  private String companyAddress;

  @Value("${app.company.tel}")
  private String companyTel;

  @Value("${app.company.fax}")
  private String companyFax;

  @Value("${app.company.email}")
  private String companyEmail;

  @Value("${app.company.web}")
  private String companyWeb;

  @Value("${app.demo.mode.enabled}")
  private boolean isDemoModeEnabled;

  @RequestMapping(value = "home", method = RequestMethod.GET)
  public String getPaymentHomePage(Model model) {
    final EntityDetail loggedEntity = getLoggedEntity();
    model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
    model.addAttribute("payment", new Payment());
    model.addAttribute("payMethods", getPayMethods());
    model.addAttribute("statuses", getStatuses());
    model.addAttribute("currencySymb", currencySymb);
    model.addAttribute("statusRefund", PaymentStatus.REFUND.name());
    model.addAttribute("reservations", getReservations());
    model.addAttribute("isDemoModeEnabled", isDemoModeEnabled);
    return "/payment/home";
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public GridData<PaymentDataDto> getGridData(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                              @RequestParam(value = "limit", defaultValue = "15") int limit,
                                              @RequestParam(value = "sort", defaultValue = "id") String sort,
                                              @RequestParam(value = "order", defaultValue = "desc") String order,
                                              @RequestParam(value = "search", defaultValue = "") String search) {
    LOGGER.debug("@getGridData(offset:{}, limit:{}, order:{})", offset, limit, order);
    final GridData<PaymentDataDto> gridData = new GridData<>();
    final Pageable pageable = new Pageable(sort, order, offset, limit);
    final ResultAdditionalData additionalData = new ResultAdditionalData();
    try {
      List<Payment> payments = paymentService.getList(pageable, additionalData, search);
      LOGGER.debug("payments:{}", payments);
      gridData.setRows(PaymentDataDto.parse(payments));
      gridData.setTotal(additionalData.getCount());
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return gridData;
  }

  @RequestMapping(method = RequestMethod.POST, headers = REQ_HEADER_ACCEPT_JSON)
  @ResponseBody
  public Response<Void> save(@ModelAttribute("payment") Payment payment) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@save(payment:{})", payment);
    }
    final Response<Void> response = new Response();
    try {
      paymentService.saveWithNotif(payment);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response.addErrors(getMessage(e.getMessage()));
      } else {
        response.addErrors(e.getMessage());
      }
    }
    return response;
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  @ResponseBody
  public Response<Payment> getPayment(@PathVariable(value = "id") int id) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getPayment(id:{})", id);
    }
    final Response<Payment> response = new Response();
    try {
      response.setResult(paymentService.get(id));
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response.addErrors(getMessage(e.getMessage()));
      } else {
        response.addErrors(e.getMessage());
      }
    }
    return response;
  }

  @RequestMapping(value = "{id}/receipt", method = RequestMethod.GET)
  public void getReceipt(@PathVariable(value = "id") int id,
                         HttpServletResponse response) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getReceipt(id:{})", id);
    }
    try {
      Payment payment = paymentService.get(id);
      if (payment != null) {
        reportService.exportReport(response, paymentService.generateReceipt(payment), String.format("Receipt of %s",
          payment.getReference()));
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.payment.details.required");
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  @RequestMapping(value = "{id}/receipt/resend", method = RequestMethod.GET)
  @ResponseBody
  public Response<Void> resendReceipt(@PathVariable(value = "id") int id,
                                      @RequestParam(value = "email") String email) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@resendReceipt(id:{})", id);
    }
    final Response<Void> response = new Response();
    try {
      Payment payment = paymentService.get(id);
      if (payment != null) {
        payment.getReservation().getCustomer().setEmail(email);
        paymentService.sendReceipt(payment);
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.payment.details.not.found");
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response.addErrors(getMessage(e.getMessage()));
      } else {
        response.addErrors(e.getMessage());
      }
    }
    return response;
  }

  @RequestMapping(value = "{id}/refund/info", method = RequestMethod.GET)
  @ResponseBody
  public Response<PaymentRefundInfoDto> getPaymentRefundInfo(@PathVariable(value = "id") int id) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getPaymentRefundInfo(id:{})", id);
    }
    final Response<PaymentRefundInfoDto> response = new Response();
    try {
      response.setResult(PaymentRefundInfoDto.parse(paymentRefundService.get(id)));
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response.addErrors(getMessage(e.getMessage()));
      } else {
        response.addErrors(e.getMessage());
      }
    }
    return response;
  }

  @RequestMapping(value = "reference", method = RequestMethod.GET)
  @ResponseBody
  public Response<String> getPayReference() {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getPayReference()");
    }
    final Response<String> response = new Response();
    response.setResult(RandomIdUtil.generateNumber(Payment.REFERENCE_LENGTH, Payment.REFERENCE_PREFIX));
    return response;
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public Response<Void> refundPayment(@PathVariable(value = "id") int id) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@refundPayment(id:{})", id);
    }
    final Response<Void> response = new Response();
    try {
      paymentService.refund(id);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response.addErrors(getMessage(e.getMessage()));
      } else {
        response.addErrors(e.getMessage());
      }
    }
    return response;
  }

  @RequestMapping(value = "{id}/customer-email", method = RequestMethod.GET)
  @ResponseBody
  public Response<String> getCustomerEmail(@PathVariable(value = "id") int id) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getCustomerEmail(id:{})", id);
    }
    final Response<String> response = new Response();
    try {
      final Payment payment = paymentService.get(id);
      if (payment != null) {
        response.setResult(payment.getReservation().getCustomer().getEmail());
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.payment.details.not.found");
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response.addErrors(getMessage(e.getMessage()));
      } else {
        response.addErrors(e.getMessage());
      }
    }
    return response;
  }

  @RequestMapping(value = "report", method = RequestMethod.GET)
  public void getReport(HttpServletResponse response,
                        @RequestParam(value = "from", required = false)
                        @DateTimeFormat(pattern = DateUtil.PATTERN_DATE_MMM) Date from,
                        @RequestParam(value = "to", required = false)
                        @DateTimeFormat(pattern = DateUtil.PATTERN_DATE_MMM) Date to,
                        @RequestParam(value = "payMethodId", required = false, defaultValue = "-1") Integer payMethodId,
                        @RequestParam(value = "statusId", required = false, defaultValue = "-1") Integer statusId) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getReport(from:{}, to:{}, payMethodId:{}, statusId:{})", from, to, payMethodId, statusId);
    }
    try {
      reportService.exportReport(response, paymentService.generateReport(new ReportCriteriaDto(payMethodId, statusId,
        from, to)), "Payment Report");
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  @RequestMapping(value = "demo/data", method = RequestMethod.POST)
  @ResponseBody
  public Response<Void> insertDemoData(HttpServletResponse servletResponse) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@insertDemoData()....");
    }
    final Response<Void> response = new Response();
    if (isDemoModeEnabled) {
      try {
        paymentService.insertDemoData();
      } catch (ServiceException e) {
        LOGGER.error(e.getMessage(), e);
        if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
          response.addErrors(getMessage(e.getMessage()));
        } else {
          response.addErrors(e.getMessage());
        }
      }
    } else {
      servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
    return response;
  }

  private List<ComboboxData> getPayMethods() {
    final List<ComboboxData> cbDataList = new ArrayList<>();
    try {
      for (PaymentMethod paymentMethod : payMethodService.getAll()) {
        cbDataList.add(new ComboboxData(paymentMethod.getId(), paymentMethod.getType()));
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return cbDataList;
  }

  private List<ComboboxData> getStatuses() {
    final List<ComboboxData> cbDataList = new ArrayList<>();
    try {
      for (lk.elevenzcode.bms.payment.model.PaymentStatus paymentStatus : payStatusService.getAll()) {
        cbDataList.add(new ComboboxData(paymentStatus.getId(), paymentStatus.getName()));
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return cbDataList;
  }

  private List<ComboboxData> getReservations() {
    final List<ComboboxData> cbDataList = new ArrayList<>();
    try {
      for (Reservation reservation : reservationService.getByStatuses(ReservationStatus.RESEREVED.getId(),
        ReservationStatus.CONFIRM.getId())) {
        cbDataList.add(new ComboboxData(reservation.getId(), String.format(StringUtils
          .repeat("%s", " - ", 3), reservation.getReservationNo(), reservation.getEvent()
          .getEventType().getType(), CollectionUtils.isNotEmpty(reservation.getCustomer().getContactList()) ?
          reservation.getCustomer().getContactList().get(0).getContactNumber() : reservation.getCustomer()
          .getName())));
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return cbDataList;
  }
}
