package lk.elevenzcode.bms.entity.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "entity_status", schema = "sch_entity")
public class EntityStatus implements Serializable {
  public static final int STATUS_ACTIVE = 1;
  public static final int STATUS_INACTIVE = 2;
  public static final int STATUS_BLOCKED = 3;
  public static final int STATUS_DELETED = 4;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, unique = true, length = 10)
  private String name;

  public EntityStatus() {
  }

  public EntityStatus(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
