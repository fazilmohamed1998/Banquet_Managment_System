package lk.elevenzcode.bms.restaurant.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Nilan on 9/1/2019 4:23 PM.
 */
@Entity
@Table(name = "restaurant_status", schema = "sch_restaurant_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class RestaurantStatus extends EntityBase implements Serializable {
  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
