package lk.elevenzcode.bms.event.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "event_status", schema = "sch_event_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class EventStatus extends EntityBase implements Serializable {
  public static final int STATUS_DELETED = 4;

  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;

  public EventStatus() {
  }

  public EventStatus(Integer id) {
    super(id);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
