package lk.elevenzcode.bms.common.model;


import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass()
public abstract class EntityBase implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  public EntityBase() {
  }

  public EntityBase(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof EntityBase)) {
      return false;
    }
    EntityBase other = (EntityBase) object;
    return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other
            .id)));
  }

  @Override
  public String toString() {
    return "EntityBase{"
            + "id=" + id
            + '}';
  }
}
