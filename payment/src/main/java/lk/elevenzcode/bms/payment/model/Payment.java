package lk.elevenzcode.bms.payment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lk.elevenzcode.bms.common.model.EntityBase;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.common.util.MoneySerializer;
import lk.elevenzcode.bms.reservation.model.Reservation;
import lk.elevenzcode.bms.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payment", schema = "sch_payment")
@NamedQueries({
  @NamedQuery(
    name = "Payment.getList",
    query = "SELECT t FROM Payment t INNER JOIN t.reservation r INNER JOIN r.customer c INNER JOIN t.payMethod m " +
      "INNER JOIN t.acceptBy u INNER JOIN t.status s "
  ),
  @NamedQuery(
    name = "Payment.getCount",
    query = "SELECT COUNT(t) FROM Payment t INNER JOIN t.reservation r INNER JOIN r.customer c INNER JOIN t.payMethod m " +
      "INNER JOIN t.acceptBy u INNER JOIN t.status s "
  )
})
public class Payment extends EntityBase implements Serializable {
  public static final String REFERENCE_PREFIX = "INV";
  public static final int REFERENCE_LENGTH = 13;
  public static final int SERIAL_NO_LENGTH = 4;

  @ManyToOne
  @JoinColumn(name = "reservation_id", nullable = false)
  private Reservation reservation;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtil.PATTERN_DATE_MMM_TIME_12HRS)
  @Column(name = "paid_on", nullable = false)
  private Date paidOn = new Date();

  @ManyToOne
  @JoinColumn(name = "pay_method", nullable = false)
  private PaymentMethod payMethod;

  @JsonSerialize(using = MoneySerializer.class)
  @Column(name = "amount", nullable = false)
  @Digits(integer = 11, fraction = 2)
  private BigDecimal amount;

  @Column(name = "reference", nullable = false, unique = true, length = REFERENCE_LENGTH)
  private String reference;

  @ManyToOne
  @JoinColumn(name = "accept_by", nullable = false)
  private User acceptBy;

  @ManyToOne
  @JoinColumn(name = "status", nullable = false)
  private PaymentStatus status;

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }

  public Date getPaidOn() {
    return paidOn;
  }

  public void setPaidOn(Date paidOn) {
    this.paidOn = paidOn;
  }

  public PaymentMethod getPayMethod() {
    return payMethod;
  }

  public void setPayMethod(PaymentMethod payMethod) {
    this.payMethod = payMethod;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public User getAcceptBy() {
    return acceptBy;
  }

  public void setAcceptBy(User acceptBy) {
    this.acceptBy = acceptBy;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Payment{" +
      "reservation=" + reservation +
      ", paidOn=" + paidOn +
      ", payMethod=" + payMethod +
      ", amount=" + amount +
      ", reference='" + reference + '\'' +
      ", acceptBy=" + acceptBy +
      ", status=" + status +
      '}';
  }
}
