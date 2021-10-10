package lk.elevenzcode.bms.payment.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "payment_status", schema = "sch_payment")
@NamedQueries({
  //TODO add HQL queries here
})
public class PaymentStatus extends EntityBase implements Serializable {
  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;

  public PaymentStatus() {
  }

  public PaymentStatus(Integer id) {
    super(id);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
