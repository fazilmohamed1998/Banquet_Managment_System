package lk.elevenzcode.bms.food.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Naufan Hassen on 30/08/19 8:58 p.m..
 */
@Entity
@Table(name = "food_menu_status", schema = "sch_food_mgmt")
@NamedQueries({
        //TODO add HQL queries here
})

public class FoodMenuStatus extends EntityBase implements Serializable {
  @Column(name = "status", nullable = false, unique = true, length = 50)
  private String status;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
