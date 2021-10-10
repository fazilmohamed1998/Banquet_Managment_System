package lk.elevenzcode.bms.entity.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.entity.dao.EntityDao;
import lk.elevenzcode.bms.entity.model.Entity;
import lk.elevenzcode.bms.entity.model.EntityStatus;
import org.springframework.stereotype.Repository;

@Repository
public class EntityDaoImpl extends GenericDaoImpl<Entity> implements EntityDao {
  public EntityDaoImpl() {
    super(Entity.class);
  }

  @Override
  public Entity getActiveEntity(String userName) throws DataAccessException {
    final Object eOb = getCurrentSession().getNamedQuery("Entity.getActiveEntity")
            .setParameter("userName", userName)
            .setParameter("active", EntityStatus.STATUS_ACTIVE)
            .uniqueResult();
    return eOb != null ? (Entity) eOb : null;
  }
}
