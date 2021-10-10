package lk.elevenzcode.bms.reservation.dto;

import java.util.Date;

/**
 * @author Harin on 10/4/2019 10:00 PM.
 */
public class ReportCriteriaDto {

  private Date from, to;


  public ReportCriteriaDto(Date from, Date to) {
    this.from = from;
    this.to = to;
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
