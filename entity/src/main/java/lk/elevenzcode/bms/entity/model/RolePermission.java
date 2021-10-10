package lk.elevenzcode.bms.entity.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role_permission", schema = "sch_entity")
@NamedQueries({
        @NamedQuery(
                name = "RolePermission.getByRole",
                query = "SELECT rp FROM RolePermission rp WHERE rp.role.id=:roleId")
})
public class RolePermission implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private Role role;

  @ManyToOne
  @JoinColumn(name = "permission_id", referencedColumnName = "id")
  private Permission permission;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }


  public Permission getPermission() {
    return permission;
  }

  public void setPermission(Permission permission) {
    this.permission = permission;
  }

}
