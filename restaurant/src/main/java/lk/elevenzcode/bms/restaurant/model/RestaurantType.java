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
@Table(name = "restaurant_type", schema = "sch_restaurant_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class RestaurantType extends EntityBase implements Serializable {
  @Column(name = "type", nullable = false, unique = true, length = 50)
  private String type;

  public RestaurantType() {
  }

  public RestaurantType(Integer id) {
    super(id);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
