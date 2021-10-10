package lk.elevenzcode.bms.event.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.event.dao.EventTypeDao;
import lk.elevenzcode.bms.event.model.EventType;
import org.springframework.stereotype.Repository;

/**
 * Created by hashan on 9/10/19 10:47 AM
 */
@Repository
public class EventTypeDaoImpl extends GenericDaoImpl<EventType> implements EventTypeDao {
  public EventTypeDaoImpl() {
    super(EventType.class);
  }
}
