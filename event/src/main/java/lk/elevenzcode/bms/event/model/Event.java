package lk.elevenzcode.bms.event.model;

import lk.elevenzcode.bms.banquethall.model.BanquetHall;
import lk.elevenzcode.bms.common.model.EntityBase;
import lk.elevenzcode.bms.customer.model.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "event", schema = "sch_event_mgmt")
@NamedQueries({
        //TODO add HQL queries here
})
public class Event extends EntityBase implements Serializable {
  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private EventType eventType;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @Column(name = "description", nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  private BanquetHall location;

  @Column(name = "event_from", nullable = false)
  private Date from;

  @Column(name = "event_to", nullable = false)
  private Date to;

  @ManyToOne
  @JoinColumn(name = "status", nullable = false)
  private EventStatus status;

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BanquetHall getLocation() {
    return location;
  }

  public void setLocation(BanquetHall location) {
    this.location = location;
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

  public EventStatus getStatus() {
    return status;
  }

  public void setStatus(EventStatus status) {
    this.status = status;
  }
}
