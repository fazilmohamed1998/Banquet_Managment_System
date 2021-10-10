package lk.elevenzcode.bms.entity.dao;

import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.entity.model.Entity;

public interface EntityDao extends GenericDao<Entity> {
  Entity getActiveEntity(String userName) throws DataAccessException;
}
