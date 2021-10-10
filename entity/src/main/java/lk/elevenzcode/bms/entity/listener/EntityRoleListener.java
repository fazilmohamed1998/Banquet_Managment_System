package lk.elevenzcode.bms.entity.listener;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.entity.model.Role;

public interface EntityRoleListener {
  Role getRole(int entityId) throws ServiceException;
}
