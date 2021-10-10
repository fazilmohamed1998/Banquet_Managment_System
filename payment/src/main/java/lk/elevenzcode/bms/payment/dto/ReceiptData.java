package lk.elevenzcode.bms.payment.dto;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.util.ConversionUtil;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.common.util.RandomIdUtil;
import lk.elevenzcode.bms.event.model.Event;
import lk.elevenzcode.bms.payment.model.Payment;
import lk.elevenzcode.bms.common.util.Constant;
import lk.elevenzcode.bms.reservation.model.Reservation;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;

/**
 * @author HaShaN on 9/3/2019 9:26 PM.
 */
public class ReceiptData {

  private String serialNo;
  private String receiptNo;
  private String date;
  private String receivedFrom;
  private String payFor;
  private String amount;
  private String payMethod;
  private InputStream logo;

  public static ReceiptData parse(Payment payment) throws ServiceException {
    if (payment != null) {
      final ReceiptData receiptData = new ReceiptData();
      Reservation reservation = payment.getReservation();
      Event event = reservation.getEvent();
      receiptData.setSerialNo(RandomIdUtil.getWithPaddingZero(payment.getId(), Payment.SERIAL_NO_LENGTH));
      receiptData.setReceiptNo(payment.getReference());
      receiptData.setDate(DateUtil.formatToWebDateMMM(payment.getPaidOn()));
      receiptData.setReceivedFrom(reservation.getCustomer().getName());
      receiptData.setPayFor(StringUtils.containsIgnoreCase(event.getDescription(), event.getEventType().getType())
        ? event.getDescription() : String.format("%s (%s)", event.getDescription(), event.getEventType().getType()));
      receiptData.setAmount(ConversionUtil.getMoneyWithThousandSeparator(payment.getAmount()));
      receiptData.setPayMethod(payment.getPayMethod().getType());
      receiptData.setLogo(ReceiptData.class.getResourceAsStream(Constant.LOGO_PATH));
      return receiptData;
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.payment.details.required");
    }
  }


  public String getSerialNo() {
    return serialNo;
  }

  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }

  public String getReceiptNo() {
    return receiptNo;
  }

  public void setReceiptNo(String receiptNo) {
    this.receiptNo = receiptNo;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getReceivedFrom() {
    return receivedFrom;
  }

  public void setReceivedFrom(String receivedFrom) {
    this.receivedFrom = receivedFrom;
  }

  public String getPayFor() {
    return payFor;
  }

  public void setPayFor(String payFor) {
    this.payFor = payFor;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getPayMethod() {
    return payMethod;
  }

  public void setPayMethod(String payMethod) {
    this.payMethod = payMethod;
  }

  public InputStream getLogo() {
    return logo;
  }

  public void setLogo(InputStream logo) {
    this.logo = logo;
  }
}
