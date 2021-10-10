package lk.elevenzcode.bms.payment.dto;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.util.ConversionUtil;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.payment.model.Payment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HaShaN on 10/4/2019 11:54 PM.
 */
public class ReportData {
  private String ref, customer, amount, payMethod, paidOn, status;

  public static List<ReportData> parse(List<Payment> payments) throws ServiceException {
    final List<ReportData> reportDataList = new ArrayList<>();
    ReportData reportData;
    for (Payment payment : payments) {
      reportData = new ReportData();
      reportData.setRef(payment.getReference());
      reportData.setCustomer(payment.getReservation().getCustomer().getName());
      reportData.setAmount(ConversionUtil.getMoneyWithThousandSeparator(payment.getAmount()));
      reportData.setPayMethod(payment.getPayMethod().getType());
      reportData.setPaidOn(DateUtil.formatToWebDateTimeMMM(payment.getPaidOn()));
      reportData.setStatus(payment.getStatus().getName());
      reportDataList.add(reportData);
    }
    return reportDataList;
  }

  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
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

  public String getPaidOn() {
    return paidOn;
  }

  public void setPaidOn(String paidOn) {
    this.paidOn = paidOn;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
