package lk.elevenzcode.bms.reservation.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lk.elevenzcode.bms.common.model.EntityBase;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.customer.model.Customer;
import lk.elevenzcode.bms.event.model.Event;
import lk.elevenzcode.bms.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reservation", schema = "sch_reservation")
@NamedQueries({
        @NamedQuery(
                name = "Reservation.getList",
                query = "SELECT r FROM Reservation r JOIN r.customer c JOIN r.event e JOIN r.status s JOIN r.type t "
        ),
        @NamedQuery(
                name = "Reservation.getCount",
                query = "SELECT COUNT(r) FROM Reservation r JOIN r.customer c JOIN r.event e JOIN r.status s JOIN r.type t "
        ),
        @NamedQuery(
                name = "Reservation.getByStatuses",
                query = "SELECT r FROM Reservation r INNER JOIN r.status s WHERE s.id IN (:statuses)"
        )
})
public class Reservation extends EntityBase implements Serializable {
    public static final String REFERENCE_PREFIX = "RES";
    public static final int REFERENCE_LENGTH = 13;

    @Column(name = "reservation_no", nullable = false, unique = true, length = 13)
    private String reservationNo;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtil.PATTERN_DATE_MMM_TIME_12HRS)
    @Column(name = "reserved_on", nullable = false)
    private Date reservedOn = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ReservationType type;

  public String getReservationNo() {
    return reservationNo;
  }

  public void setReservationNo(String reservationNo) {
    this.reservationNo = reservationNo;
  }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getReservedOn() {
        return reservedOn;
    }

    public void setReservedOn(Date reservedOn) {
        this.reservedOn = reservedOn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
    }


}
