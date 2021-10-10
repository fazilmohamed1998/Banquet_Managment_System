package lk.elevenzcode.bms.event.service.impl;

import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.event.dao.EventDao;
import lk.elevenzcode.bms.event.model.Event;
import lk.elevenzcode.bms.event.model.EventStatus;
import lk.elevenzcode.bms.event.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class EventServiceImpl extends GenericServiceImpl<Event> implements EventService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);
  @Autowired
  private EventDao eventDao;

  @PostConstruct
  void init() {
    init(eventDao);
  }

  @Override
  public List<Event> getList(Pageable pageable, String criterial) throws ServiceException {
    try {
      return eventDao.getList(pageable, criterial);
    } catch (DataAccessException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Override
  public void delete(int id) throws ServiceException {
    if (id > 0){
      final Event event = get(id);
      event.setStatus(new EventStatus(EventStatus.STATUS_DELETED));
      update(event);
    }
  }

  @Override
  public List<Event> getList(int excludeStatus) throws ServiceException {
    try {
      return eventDao.getList(excludeStatus);
    } catch (DataAccessException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(e.getMessage(), e);
    }
  }
}
