package lk.elevenzcode.bms.user.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.user.dao.UserRoleDao;
import lk.elevenzcode.bms.user.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements UserRoleDao {
  public UserRoleDaoImpl() {
    super(UserRole.class);
  }
}

