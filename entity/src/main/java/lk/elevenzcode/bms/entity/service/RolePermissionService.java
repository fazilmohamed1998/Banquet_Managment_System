package lk.elevenzcode.bms.entity.service;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.entity.model.RolePermission;

import java.util.List;

public interface RolePermissionService extends GenericService<RolePermission> {
  List<RolePermission> getByRole(int roleId) throws ServiceException;
}
