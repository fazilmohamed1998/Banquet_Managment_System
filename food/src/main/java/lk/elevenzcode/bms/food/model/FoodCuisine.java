package lk.elevenzcode.bms.food.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author HaShaN on 8/27/2019 9:03 PM.
 */
@Entity
@Table(name = "food_cuisine", schema = "sch_food_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class FoodCuisine extends EntityBase implements Serializable {
  @Column(name = "type", nullable = false, unique = true, length = 50)
  private String type;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
