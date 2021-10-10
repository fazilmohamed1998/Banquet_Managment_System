package lk.elevenzcode.bms.event.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.event.dao.EventDao;
import lk.elevenzcode.bms.event.model.Event;
import lk.elevenzcode.bms.event.model.EventStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventDaoImpl extends GenericDaoImpl<Event> implements EventDao {
  public EventDaoImpl() {
    super(Event.class);
  }

  @Override
  public List<Event> getList(Pageable pageable, String criterial) throws DataAccessException {
    return null;
  }

  @Override
  public List<Event> getList(int excludeStatus) throws DataAccessException {
    return getCurrentSession().createQuery("FROM Event e WHERE e.status.id<>:deletedStatus")
            .setParameter("deletedStatus", EventStatus.STATUS_DELETED)
            .list();
  }
}
