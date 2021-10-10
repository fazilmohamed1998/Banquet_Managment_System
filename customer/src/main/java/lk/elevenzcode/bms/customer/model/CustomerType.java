package lk.elevenzcode.bms.customer.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Gayan on 9/1/2019 3:53 PM.
 */
@Entity
@Table(name = "customer_type", schema = "sch_customer_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class CustomerType extends EntityBase implements Serializable {
  @Column(name = "type", nullable = false, unique = true, length = 50)
  private String type;

  public CustomerType() {
  }

  public CustomerType(Integer id) {
    super(id);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
