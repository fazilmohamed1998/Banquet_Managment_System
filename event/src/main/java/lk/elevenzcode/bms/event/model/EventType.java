package lk.elevenzcode.bms.event.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author HaShaN on 8/31/2019 1:00 PM.
 */
@Entity
@Table(name = "event_type", schema = "sch_event_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class EventType extends EntityBase implements Serializable {
  @Column(name = "type", nullable = false, unique = true, length = 50)
  private String type;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
