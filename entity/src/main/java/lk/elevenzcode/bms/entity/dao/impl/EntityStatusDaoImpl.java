package lk.elevenzcode.bms.entity.dao.impl;


import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.entity.dao.EntityStatusDao;
import lk.elevenzcode.bms.entity.model.EntityStatus;
import org.springframework.stereotype.Repository;

@Repository
public class EntityStatusDaoImpl extends GenericDaoImpl<EntityStatus> implements EntityStatusDao {
  public EntityStatusDaoImpl() {
    super(EntityStatus.class);
  }
}
