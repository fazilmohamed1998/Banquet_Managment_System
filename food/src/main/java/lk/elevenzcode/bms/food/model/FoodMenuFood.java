package lk.elevenzcode.bms.food.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

/**
 * Created by hashan on 9/30/19 7:15 PM
 */
@Entity
@Table(name = "food_menu_food", schema = "sch_food_mgmt")
@NamedQueries( {
    //TODO add HQL queries here
})
public class FoodMenuFood extends EntityBase implements Serializable {
  @ManyToOne
  @JoinColumn(name = "menu_id", nullable = false)
  private FoodMenu menu;

  @ManyToOne
  @JoinColumn(name = "food_id", nullable = false)
  private Food food;

  public FoodMenu getMenu() {
    return menu;
  }

  public void setMenu(FoodMenu menu) {
    this.menu = menu;
  }

  public Food getFood() {
    return food;
  }

  public void setFood(Food food) {
    this.food = food;
  }
}
