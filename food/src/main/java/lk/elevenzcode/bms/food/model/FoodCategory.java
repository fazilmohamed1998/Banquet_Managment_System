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
@Table(name = "food_category", schema = "sch_food_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class FoodCategory extends EntityBase implements Serializable {
  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
