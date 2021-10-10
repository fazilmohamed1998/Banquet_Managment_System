package lk.elevenzcode.bms.event.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.event.dao.EventStatusDao;
import lk.elevenzcode.bms.event.model.EventStatus;
import org.springframework.stereotype.Repository;

/**
 * Created by hashan on 9/10/19 10:51 AM
 */
@Repository
public class EventStatusDaoImpl extends GenericDaoImpl<EventStatus> implements EventStatusDao {
  public EventStatusDaoImpl() {
    super(EventStatus.class);
  }
}
