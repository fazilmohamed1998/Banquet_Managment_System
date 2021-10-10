package lk.elevenzcode.bms.banquethall.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author HaShaN on 8/24/2019 8:40 PM.
 */
@Entity
@Table(name = "banquet_hall_type", schema = "sch_banquet_hall_mgmt")
@NamedQueries({
        //TODO add HQL queries here
})
public class BanquetHallType extends EntityBase implements Serializable {
  @Column(name = "type", nullable = false, unique = true, length = 50)
  private String type;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
