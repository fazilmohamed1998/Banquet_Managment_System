package lk.elevenzcode.bms.payment.service.impl;

import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.dto.ResultAdditionalData;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.MailService;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.common.util.*;
import lk.elevenzcode.bms.payment.dao.PaymentDao;
import lk.elevenzcode.bms.payment.dto.ReceiptData;
import lk.elevenzcode.bms.payment.dto.ReportCriteriaDto;
import lk.elevenzcode.bms.payment.dto.ReportData;
import lk.elevenzcode.bms.payment.model.Payment;
import lk.elevenzcode.bms.payment.model.PaymentMethod;
import lk.elevenzcode.bms.payment.model.PaymentRefund;
import lk.elevenzcode.bms.payment.model.PaymentStatus;
import lk.elevenzcode.bms.payment.service.PaymentMethodService;
import lk.elevenzcode.bms.payment.service.PaymentRefundService;
import lk.elevenzcode.bms.payment.service.PaymentService;
import lk.elevenzcode.bms.payment.service.PaymentStatusService;
import lk.elevenzcode.bms.reservation.model.Reservation;
import lk.elevenzcode.bms.reservation.model.enums.ReservationStatus;
import lk.elevenzcode.bms.reservation.service.ReservationService;
import lk.elevenzcode.bms.user.model.User;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PaymentServiceImpl extends GenericServiceImpl<Payment> implements PaymentService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

  @Autowired
  private PaymentDao paymentDao;

  @Autowired
  private PaymentRefundService refundService;

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private PaymentMethodService paymentMethodService;

  @Autowired
  private PaymentStatusService paymentStatusService;

  @Autowired
  private MailService mailService;

  @Autowired
  private ReportService reportService;

  @PostConstruct
  void init() {
    init(paymentDao);
  }

  @Value("${app.base.url}")
  private String baseUrl;

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

  @Value("${app.demo.nrecords}")
  private int nRecords;

  @Override
  public List<Payment> getList(Pageable pageable, ResultAdditionalData additionalData, String criteria) throws ServiceException {
    try {
      return paymentDao.getList(pageable, additionalData, StringUtils.lowerCase(criteria));
    } catch (DataAccessException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Override
  public long getCount(String criteria) throws ServiceException {
    try {
      return paymentDao.getCount(StringUtils.lowerCase(criteria), null);
    } catch (DataAccessException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, rollbackFor = ServiceException.class)
  public void save(Payment payment) throws ServiceException {
    if (payment != null) {
      if (payment.getId() != null && payment.getId() > 0) {
        Payment existingPayment = get(payment.getId());
        boolean isModified = false;
        if (!existingPayment.getPayMethod().getId().equals(payment.getPayMethod().getId())) {
          existingPayment.setPayMethod(payment.getPayMethod());
          isModified = true;
        }
        if (existingPayment.getAmount().compareTo(payment.getAmount()) != 0) {
          existingPayment.setAmount(payment.getAmount());
          isModified = true;
        }

        if (isModified) {
          update(existingPayment);
        }
      } else {
        payment.setPaidOn(new Date());
        payment.setAcceptBy(new User(getLoggedEntity().getId()));
        payment.setStatus(new PaymentStatus(lk.elevenzcode.bms.payment.model.enums.PaymentStatus.SUCCESS.getId()));
        insert(payment);

      }
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.payment.details.required");
    }
  }

  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, rollbackFor = ServiceException.class)
  public void saveWithNotif(Payment payment) throws ServiceException {
    try {
      save(payment);
      paymentDao.detach(payment);
      sendReceipt(get(payment.getId()));
    } catch (DataAccessException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, rollbackFor = ServiceException.class)
  public void refund(int id) throws ServiceException {
    final Payment payment = get(id);
    if (payment != null) {
      payment.setStatus(new PaymentStatus(lk.elevenzcode.bms.payment.model.enums.PaymentStatus.REFUND.getId()));

      update(payment);

      //refund details
      final PaymentRefund refund = new PaymentRefund();
      refund.setPayment(payment);
      refund.setRefundOn(new Date());
      refund.setRefundBy(new User(getLoggedEntity().getId()));
      refundService.insert(refund);

      //paymentDao.detach(payment);
      sendReceipt(get(payment.getId()));
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.payment.details.required");
    }
  }

  @Override
  public JasperPrint generateReceipt(Payment payment) throws ServiceException {
    if (payment != null) {
      final ReceiptData receiptData = ReceiptData.parse(payment);
      final Map<String, Object> param = new HashMap<>();
      param.put("companyName", StringUtils.upperCase(companyName));
      param.put("companyAddress", companyAddress);
      final String lblTel = getMessage("lbl.payment.receipt.tel");
      final String lblFax = getMessage("lbl.payment.receipt.fax");
      final String lblEmail = getMessage("lbl.payment.receipt.email");
      final String lblWeb = getMessage("lbl.payment.receipt.web");
      param.put("companyTelFax", String.format("%s : %s, %s : %s", lblTel, companyTel, lblFax, companyFax));
      param.put("companyEmailWeb", String.format("%s : %s, %s : %s", lblEmail, companyEmail, lblWeb, companyWeb));
      param.put("isRefund", payment.getStatus().getId().intValue() == lk.elevenzcode.bms.payment.model.enums
        .PaymentStatus.REFUND.getId());
      final InputStream resourceAsStream = this.getClass().getResourceAsStream(Constant.REFUND_IMG_PATH);
      param.put("refundImg", resourceAsStream);
      return reportService.generateReport(JasperUtil.getTemplate("payment_receipt_template", this.getClass()), Arrays.asList(receiptData), param,
        "payment-messages");
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.payment.details.required");
    }
  }

  @Override
  public JasperPrint generateReport(ReportCriteriaDto reportCriteria) throws ServiceException {
    try {
      final List<Payment> payments = paymentDao.getForReport(reportCriteria);
      final List<ReportData> reportDataList = ReportData.parse(payments);
      final Map<String, Object> param = new HashMap<>();
      param.put("logo", this.getClass().getResourceAsStream(lk.elevenzcode.bms.common.util.Constant.LOGO_PATH));
      param.put("from", reportCriteria.getFrom() != null ? DateUtil.formatToWebDateMMM(reportCriteria.getFrom()) : "-");
      param.put("to", reportCriteria.getTo() != null ? DateUtil.formatToWebDateMMM(reportCriteria.getTo()) : "-");
      param.put("payMethod", reportCriteria.getPayMethodId() != -1 ? paymentMethodService.get(reportCriteria
        .getPayMethodId()).getType() : "All");
      param.put("status", reportCriteria.getStatusId() != -1 ? paymentStatusService.get(reportCriteria
        .getStatusId()).getName() : "All");
      final BigDecimal totRefunded = getTotFor(lk.elevenzcode.bms.payment.model.enums.PaymentStatus.REFUND, payments);
      final BigDecimal totReceived = getTotFor(lk.elevenzcode.bms.payment.model.enums.PaymentStatus.SUCCESS, payments)
        .add(totRefunded);
      param.put("totReceived", ConversionUtil.getMoneyWithThousandSeparator(totReceived));
      param.put("totRefunded", ConversionUtil.getMoneyWithThousandSeparator(totRefunded));
      param.put("grandTot", ConversionUtil.getMoneyWithThousandSeparator(totReceived.subtract(totRefunded)));

      return reportService.generateReport(JasperUtil.getTemplate("payment_report_template", this.getClass()),
        reportDataList, param, "payment-messages");
    } catch (DataAccessException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(value = Constant.TRANSACTION_MANAGER, rollbackFor = ServiceException.class)
  public void insertDemoData() throws ServiceException {
    final List<Reservation> reservations = reservationService.getByStatuses(ReservationStatus.RESEREVED.getId(),
      ReservationStatus.CONFIRM.getId());
    final List<PaymentMethod> payMethods = paymentMethodService.getAll();
    Payment payment;
    for (int i = 0; i < nRecords; i++) {
      payment = new Payment();
      payment.setReference(RandomIdUtil.generateNumber(Payment.REFERENCE_LENGTH, Payment.REFERENCE_PREFIX));
      payment.setReservation(reservations.get(new Random().nextInt(reservations.size())));
      payment.setPayMethod(payMethods.get(new Random().nextInt(payMethods.size())));
      payment.setAmount(ConversionUtil.getMoney(new Random().nextInt(99999999) / 100));
      save(payment);
    }
  }

  @Override
  public void sendReceipt(Payment payment) throws ServiceException {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@sendReceipt");
    }
    List<String> recipients = Arrays.asList(StringUtils.split(payment.getReservation().getCustomer().getEmail(), ","));
    if (CollectionUtils.isNotEmpty(recipients)) {
      final String subject = getMessage("lbl.payment.notif.subject");
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("subject:{}", subject);
      }
      final Map<String, String> parameters = new HashMap<>();
      parameters.put("baseUrl", baseUrl);
      parameters.put("customerName", payment.getReservation().getCustomer().getName());
      parameters.put("amount", String.format("%s %s", getMessage("lbl.payment.receipt.currency.symbol"),
        ConversionUtil.getMoneyWithThousandSeparator(payment.getAmount())));
      parameters.put("payRef", payment.getReference());
      parameters.put("event", payment.getReservation().getEvent().getDescription());
      parameters.put("eventDate", DateUtil.formatToWebDateMMM(payment.getReservation().getEvent().getFrom()));
      parameters.put("tel", companyTel);
      parameters.put("email", companyEmail);
      parameters.put("isRefund", String.valueOf(payment.getStatus().getId().intValue() == lk.elevenzcode.bms.payment.model.enums
        .PaymentStatus.REFUND.getId()));
      parameters.put("disclaimer", getMessage("app.email.disclaimer"));

      //Attachments
      final Map<String, InputStreamSource> attachments = new HashMap<>();
      try {
        attachments.put("Payment Reciept.pdf", new ByteArrayResource(JasperExportManager.exportReportToPdf(generateReceipt(payment))));
      } catch (JRException e) {
        e.printStackTrace();
      }
      //Resources
      final Map<String, Resource> resources = new HashMap<>();
      resources.put("pay-vector", new ClassPathResource("email/res/pay-vector.png"));
      resources.put("tel", new ClassPathResource("email/res/tel.png"));
      resources.put("mail", new ClassPathResource("email/res/mail.png"));
      mailService.sendIS(StringUtils.join(recipients, ";"), subject, "email/payment-notif.vm",
        parameters, attachments, resources);
    } else {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.error("customer:{} has no email address.", String.format("%s(%s)", payment.getReservation().getCustomer()
          .getName(), payment.getReservation().getCustomer().getNic()));
      }
    }
  }

  private BigDecimal getTotFor(lk.elevenzcode.bms.payment.model.enums.PaymentStatus status, List<Payment> payments) {
    BigDecimal tot = new BigDecimal(0.00);
    for (Payment payment : payments) {
      if (payment.getStatus().getId().intValue() == status.getId()) {
        tot = tot.add(payment.getAmount());
      }
    }
    return tot;
  }
}
