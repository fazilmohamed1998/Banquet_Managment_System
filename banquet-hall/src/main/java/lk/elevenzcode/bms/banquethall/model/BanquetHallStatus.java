package lk.elevenzcode.bms.banquethall.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author HaShaN on 8/24/2019 8:42 PM.
 */
@Entity
@Table(name = "banquet_hall_status", schema = "sch_banquet_hall_mgmt")
@NamedQueries({
        //TODO add HQL queries here
})
public class BanquetHallStatus extends EntityBase implements Serializable {
  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
