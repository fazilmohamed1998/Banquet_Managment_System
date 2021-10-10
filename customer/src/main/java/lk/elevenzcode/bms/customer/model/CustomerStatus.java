package lk.elevenzcode.bms.customer.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Gayan on 9/1/2019 3:41 PM.
 */
@Entity
@Table(name = "customer_status", schema = "sch_customer_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class CustomerStatus extends EntityBase implements Serializable {
  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;

  public CustomerStatus() {
  }

  public CustomerStatus(Integer id) {
    super(id);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
