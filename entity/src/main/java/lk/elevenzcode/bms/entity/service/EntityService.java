package lk.elevenzcode.bms.entity.service;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.entity.model.Entity;

/**
 * Created by hashan on 3/8/19 12:03 PM
 */
public interface EntityService extends GenericService<Entity> {
  Entity getActiveEntity(String userName) throws ServiceException;
}
