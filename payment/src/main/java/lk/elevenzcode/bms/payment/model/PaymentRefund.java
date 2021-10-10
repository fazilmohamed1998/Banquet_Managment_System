package lk.elevenzcode.bms.payment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author HaShaN on 8/31/2019 10:24 AM.
 */
@Entity
@Table(name = "payment_refund", schema = "sch_payment")
public class PaymentRefund implements Serializable {
  @Id
  @OneToOne
  @JoinColumn(name = "payment_id", nullable = false, unique = true)
  private Payment payment;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtil.PATTERN_DATE_MMM_TIME_12HRS)
  @Column(name = "refund_on", nullable = false)
  private Date refundOn = new Date();

  @ManyToOne
  @JoinColumn(name = "refund_by", nullable = false)
  private User refundBy;

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public Date getRefundOn() {
    return refundOn;
  }

  public void setRefundOn(Date refundOn) {
    this.refundOn = refundOn;
  }

  public User getRefundBy() {
    return refundBy;
  }

  public void setRefundBy(User refundBy) {
    this.refundBy = refundBy;
  }
}
