package lk.elevenzcode.bms.user.listener;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.entity.listener.EntityRoleListener;
import lk.elevenzcode.bms.entity.model.Role;
import lk.elevenzcode.bms.user.model.User;
import lk.elevenzcode.bms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleListener implements EntityRoleListener {
  @Autowired
  private UserService userService;

  @Override
  public Role getRole(int entityId) throws ServiceException {
    final User user = userService.get(entityId);
    return user != null ? user.getUserRole() : null;
  }
}
