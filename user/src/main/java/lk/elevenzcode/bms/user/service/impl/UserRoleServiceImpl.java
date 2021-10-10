package lk.elevenzcode.bms.user.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.user.dao.UserRoleDao;
import lk.elevenzcode.bms.user.model.UserRole;
import lk.elevenzcode.bms.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserRoleServiceImpl extends GenericServiceImpl<UserRole> implements UserRoleService {
  @Autowired
  private UserRoleDao userRoleDao;

  @PostConstruct
  void init() {
    init(userRoleDao);
  }
}
