package lk.elevenzcode.bms.entity.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.entity.dao.RolePermissionDao;
import lk.elevenzcode.bms.entity.model.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolePermissionDaoImpl extends GenericDaoImpl<RolePermission> implements RolePermissionDao {
  public RolePermissionDaoImpl() {
    super(RolePermission.class);
  }

  @Override
  public List<RolePermission> getByRole(int roleId) throws DataAccessException {
    return getCurrentSession().getNamedQuery("RolePermission.getByRole")
            .setParameter("roleId", roleId)
            .list();
  }
}
