package lk.elevenzcode.bms.event.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.event.dao.EventTypeDao;
import lk.elevenzcode.bms.event.model.EventType;
import lk.elevenzcode.bms.event.service.EventTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by hashan on 9/10/19 10:45 AM
 */
@Service
public class EventTypeServiceImpl extends GenericServiceImpl<EventType> implements EventTypeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EventTypeServiceImpl.class);
  @Autowired
  private EventTypeDao eventTypeDao;

  @PostConstruct
  void init() {
    init(eventTypeDao);
  }
}
