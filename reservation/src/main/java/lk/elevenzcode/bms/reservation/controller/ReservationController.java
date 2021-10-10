package lk.elevenzcode.bms.reservation.controller;

import lk.elevenzcode.bms.banquethall.model.BanquetHall;
import lk.elevenzcode.bms.banquethall.service.BanquetHallService;
import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.common.dto.*;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.common.util.RandomIdUtil;
import lk.elevenzcode.bms.customer.model.Customer;
import lk.elevenzcode.bms.customer.service.CustomerService;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import lk.elevenzcode.bms.event.model.EventType;
import lk.elevenzcode.bms.event.service.EventTypeService;
import lk.elevenzcode.bms.reservation.dto.ReportCriteriaDto;
import lk.elevenzcode.bms.reservation.dto.ReservationDataDto;
import lk.elevenzcode.bms.reservation.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.reservation.model.Reservation;
import lk.elevenzcode.bms.reservation.model.ReservationStatus;
import lk.elevenzcode.bms.reservation.security.ReservationAuthority;
import lk.elevenzcode.bms.reservation.service.ReservationService;
import lk.elevenzcode.bms.reservation.service.ReservationStatusService;
import lk.elevenzcode.bms.reservation.service.ReservationTypeService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static lk.elevenzcode.bms.common.util.Constant.REQ_HEADER_ACCEPT_JSON;

@Controller
@RequestMapping("/app/reservation")
@Secured(ReservationAuthority.ROLE_RESERVATION)
public class ReservationController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationTypeService reservationTypeService;

    @Autowired
    private ReservationStatusService reservationStatusService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EventTypeService eventTypeService;

    @Autowired
    private BanquetHallService banquetHallService;

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

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String getReservationHomePage(HttpServletRequest request, Model model) {
        final EntityDetail loggedEntity = getLoggedEntity();
        model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
        model.addAttribute("reservation", new Reservation());
        //model.addAttribute("types", getReservationTypes());
        model.addAttribute("statuses", getStatuses());
        model.addAttribute("customers", getCustomers());
        model.addAttribute("events", getEvents());
        model.addAttribute("locations", getBanquetHalls());
        //TODO: here you need to put those parameters
        return "/reservation/home";
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public GridData<ReservationDataDto> getGridData(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                    @RequestParam(value = "limit", defaultValue = "15") int limit,
                                                    @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                    @RequestParam(value = "order", defaultValue = "desc") String order,
                                                    @RequestParam(value = "search", defaultValue = "") String search) {
        LOGGER.debug("@getGridData(offset:{}, limit:{}, order:{})", offset, limit, order);
        final GridData<ReservationDataDto> gridData = new GridData<>();
        final Pageable pageable = new Pageable(sort, order, offset, limit);
        final ResultAdditionalData additionalData = new ResultAdditionalData();
        try {
            List<Reservation> reservation = reservationService.getList(pageable, additionalData, search);
            LOGGER.debug("reservation:{}", reservation);
            gridData.setRows(ReservationDataDto.parse(reservation));
            gridData.setTotal(additionalData.getCount());
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return gridData;
    }

    //request will hit to below api
    @RequestMapping(method = RequestMethod.POST, headers = REQ_HEADER_ACCEPT_JSON)
    @ResponseBody
    public Response<Void> save(@ModelAttribute("reservation") Reservation reservation) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("@save(reservation:{})", reservation);
        }
        final Response<Void> response = new Response();
        try {
            reservationService.save(reservation);
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

    @RequestMapping(value = "{id}", method = RequestMethod.GET, headers = REQ_HEADER_ACCEPT_JSON)
    @ResponseBody
    public Response<Reservation> getReservation(@PathVariable(value = "id") int id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("@getReservation(id:{})", id);
        }
        final Response<Reservation> response = new Response();
        try {
            response.setResult(reservationService.get(id));
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

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response<Void> cancelReservation(@PathVariable(value = "id") int id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("@cancelReservation(id:{})", id);
        }
        final Response<Void> response = new Response();
        try {
            reservationService.cancel(id);
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
    public Response<String> getReservationReference() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("@getReservationReference()");
        }
        final Response<String> response = new Response();
        response.setResult(RandomIdUtil.generateNumber(Reservation.REFERENCE_LENGTH, Reservation.REFERENCE_PREFIX));
        return response;
    }

    @RequestMapping(value = "report", method = RequestMethod.GET)
    public void getReport(HttpServletResponse response,
                          @RequestParam(value = "from", required = false)
                          @DateTimeFormat(pattern = DateUtil.PATTERN_DATE_MMM) Date from,
                          @RequestParam(value = "to", required = false)
                          @DateTimeFormat(pattern = DateUtil.PATTERN_DATE_MMM) Date to) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("@getReport(from:{}, to:{})", from, to);
        }
        try {
            reportService.exportReport(response, reservationService.generateReport(new ReportCriteriaDto(from, to)), "Reservation Report");
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /*private List<ComboboxData> getReservationTypes() {
        final List<ComboboxData> cbDataList = new ArrayList<>();
        try {
            for (ReservationType reservationType : reservationTypeService.getAll()) {
                cbDataList.add(new ComboboxData(reservationType.getId(), reservationType.getType()));
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cbDataList;
    }*/

    private List<ComboboxData> getStatuses() {
        final List<ComboboxData> cbDataList = new ArrayList<>();
        try {
            for (ReservationStatus status : reservationStatusService.getAll()) {
                cbDataList.add(new ComboboxData(status.getId(), status.getName()));
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cbDataList;
    }

    private List<ComboboxData> getCustomers() {
        final List<ComboboxData> cbDataList = new ArrayList<>();
        try {
            for (Customer customer : customerService.getAll()) {
                cbDataList.add(new ComboboxData(customer.getId(), String.format(StringUtils
                        .repeat("%s", " - ", 2), customer.getName(), customer.getNic())
                ));
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cbDataList;
    }

    private List<ComboboxData> getEvents() {
        final List<ComboboxData> cbDataList = new ArrayList<>();
        try {
            for (EventType eventType : eventTypeService.getAll()) {
                cbDataList.add(new ComboboxData(eventType.getId(), eventType.getType()));
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cbDataList;
    }

    private List<ComboboxData> getBanquetHalls() {
        final List<ComboboxData> cbDataList = new ArrayList<>();
        try {
            for (BanquetHall banquetHall : banquetHallService.getAll()) {
                cbDataList.add(new ComboboxData(banquetHall.getId(), String.format(StringUtils
                        .repeat("%s", " - ", 2), banquetHall.getName(), banquetHall
                        .getCapacity())));
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cbDataList;
    }

}
