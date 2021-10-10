package lk.elevenzcode.bms.entity.dao;

import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.entity.model.RolePermission;

import java.util.List;

public interface RolePermissionDao extends GenericDao<RolePermission> {
  List<RolePermission> getByRole(int roleId) throws DataAccessException;
}
