package lk.elevenzcode.bms.entity.service.impl;

import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.entity.dao.RolePermissionDao;
import lk.elevenzcode.bms.entity.model.RolePermission;
import lk.elevenzcode.bms.entity.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RolePermissionServiceImpl extends GenericServiceImpl<RolePermission> implements RolePermissionService {
  @Autowired
  private RolePermissionDao rolePermissionDao;

  @PostConstruct
  void init() {
    init(rolePermissionDao);
  }

  @Override
  public List<RolePermission> getByRole(int roleId) throws ServiceException {
    try {
      return rolePermissionDao.getByRole(roleId);
    } catch (DataAccessException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }
}
