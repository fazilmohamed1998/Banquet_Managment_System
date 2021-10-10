package lk.elevenzcode.bms.user.model;

import lk.elevenzcode.bms.entity.model.Role;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_role", schema = "sch_user")
@PrimaryKeyJoinColumn(name = "role_id", referencedColumnName = "id")

public class UserRole extends Role implements Serializable {
  public UserRole() {
  }

  public UserRole(Integer id) {
    super(id);
  }
}

