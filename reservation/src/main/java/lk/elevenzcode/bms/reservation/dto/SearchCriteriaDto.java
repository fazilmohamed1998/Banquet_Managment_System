package lk.elevenzcode.bms.reservation.dto;

import org.hibernate.internal.jaxb.mapping.hbm.IdBagPluralAttributeElementAdapter;

import java.util.Date;

/**
 * Created by harin on 10/3/19 4:00 PM
 */
public class SearchCriteriaDto {
  private Date from, to;

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



  @Override
  public String toString() {
    return "SearchCriteriaDto{" +
        "from=" + from +
        ", to=" + to +
        '}';
  }
}
