package lk.elevenzcode.bms.entity.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role", schema = "sch_entity")
@Inheritance(strategy = InheritanceType.JOINED)
public class Role extends EntityBase implements Serializable {
  @Column(name = "name", nullable = false, unique = true, length = 12)
  private String name;

  public String getName() {
    return name;
  }

  public Role() {
  }

  public Role(Integer id) {
    super(id);
  }

  public void setName(String name) {
    this.name = name;
  }
}