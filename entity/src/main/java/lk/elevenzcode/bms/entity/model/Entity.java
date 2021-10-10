package lk.elevenzcode.bms.entity.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name = "entity", schema = "sch_entity")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(
                name = "Entity.getActiveEntity",
                query = "SELECT e FROM Entity e WHERE e.userName=:userName AND e.status.id=:active")
})
public class Entity extends EntityBase implements Serializable {
  @Column(name = "nic", nullable = false, length = 12)
  private String nic;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @Column(name = "address", length = 250)
  private String address;

  @Column(name = "user_name", nullable = false, length = 50)
  private String userName;

  @Column(name = "password", nullable = false, length = 60)
  private String password;

  @ManyToOne
  @JoinColumn(name = "status", referencedColumnName = "id")
  private EntityStatus status;

  public Entity() {
  }

  public Entity(Integer id) {
    super(id);
  }

  public String getNic() {
    return nic;
  }

  public void setNic(String nic) {
    this.nic = nic;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public EntityStatus getStatus() {
    return status;
  }

  public void setStatus(EntityStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Entity{" +
            "nic='" + nic + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", status='" + status + '\'' +
            '}';
  }
}
