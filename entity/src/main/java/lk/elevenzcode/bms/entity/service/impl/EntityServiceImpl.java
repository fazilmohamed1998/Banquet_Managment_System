package lk.elevenzcode.bms.entity.service.impl;

import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.entity.dao.EntityDao;
import lk.elevenzcode.bms.entity.model.Entity;
import lk.elevenzcode.bms.entity.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by hashan on 3/8/19 12:04 PM
 */
@Service
public class EntityServiceImpl extends GenericServiceImpl<Entity> implements EntityService {
  @Autowired
  private EntityDao entityDao;

  @PostConstruct
  void init() {
    init(entityDao);
  }

  @Override
  public Entity getActiveEntity(String userName) throws ServiceException {
    try {
      return entityDao.getActiveEntity(userName);
    } catch (DataAccessException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }
}
