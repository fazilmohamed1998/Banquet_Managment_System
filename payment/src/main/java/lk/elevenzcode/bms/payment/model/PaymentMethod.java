package lk.elevenzcode.bms.payment.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "payment_method", schema = "sch_payment")
@NamedQueries({
  //TODO add HQL queries here
})
public class PaymentMethod extends EntityBase implements Serializable {
  @Column(name = "type", nullable = false, unique = true, length = 50)
  private String type;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
