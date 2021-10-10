package lk.elevenzcode.bms.payment.dto;

import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.payment.model.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HaShaN on 8/26/2019 9:56 PM.
 */
public class PaymentDataDto {
  public static final Map<String, String> sortMap = new HashMap<>();

  static {
    sortMap.put("id", "id");
    sortMap.put("payMethod", "payMethod.id");
    sortMap.put("payOn", "paidOn");
    sortMap.put("status", "status.id");
  }

  private int id;
  private String reference, customer;
  private BigDecimal amount;
  private String payMethod, payOn, acceptBy, status;

  public static List<PaymentDataDto> parse(List<Payment> payments) {
    final List<PaymentDataDto> paymentDataDtoList = new ArrayList<>();
    PaymentDataDto paymentDataDto;
    for (Payment payment : payments) {
      paymentDataDto = new PaymentDataDto();
      paymentDataDto.setId(payment.getId());
      paymentDataDto.setReference(payment.getReference());
      paymentDataDto.setCustomer(payment.getReservation().getCustomer().getName());
      paymentDataDto.setAmount(payment.getAmount());
      paymentDataDto.setPayMethod(payment.getPayMethod().getType());
      paymentDataDto.setPayOn(DateUtil.formatToWebDateTimeMMM(payment.getPaidOn()));
      paymentDataDto.setAcceptBy(payment.getAcceptBy().getName());
      paymentDataDto.setStatus(payment.getStatus().getName());
      paymentDataDtoList.add(paymentDataDto);
    }
    return paymentDataDtoList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getPayMethod() {
    return payMethod;
  }

  public void setPayMethod(String payMethod) {
    this.payMethod = payMethod;
  }

  public String getPayOn() {
    return payOn;
  }

  public void setPayOn(String payOn) {
    this.payOn = payOn;
  }

  public String getAcceptBy() {
    return acceptBy;
  }

  public void setAcceptBy(String acceptBy) {
    this.acceptBy = acceptBy;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
