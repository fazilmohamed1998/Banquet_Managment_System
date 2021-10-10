package lk.elevenzcode.bms.payment.dto;

import java.util.Date;

/**
 * @author HaShaN on 10/4/2019 9:37 PM.
 */
public class ReportCriteriaDto {
  private int payMethodId, statusId;
  private Date from, to;

  public ReportCriteriaDto() {
  }

  public ReportCriteriaDto(int payMethodId, int statusId, Date from, Date to) {
    this.payMethodId = payMethodId;
    this.statusId = statusId;
    this.from = from;
    this.to = to;
  }

  public int getPayMethodId() {
    return payMethodId;
  }

  public void setPayMethodId(int payMethodId) {
    this.payMethodId = payMethodId;
  }

  public int getStatusId() {
    return statusId;
  }

  public void setStatusId(int statusId) {
    this.statusId = statusId;
  }

  public Date getFrom() {
    return from;
  }

  public void setFrom(Date from) {
    this.from = from;
  }

  public Date getTo() {
    return to;
  }

  public void setTo(Date to) {
    this.to = to;
  }
}
