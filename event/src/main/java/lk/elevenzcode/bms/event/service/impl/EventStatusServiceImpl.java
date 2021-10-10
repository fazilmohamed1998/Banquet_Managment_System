package lk.elevenzcode.bms.event.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.event.dao.EventStatusDao;
import lk.elevenzcode.bms.event.model.EventStatus;
import lk.elevenzcode.bms.event.service.EventStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by hashan on 9/10/19 10:50 AM
 */
@Service
public class EventStatusServiceImpl extends GenericServiceImpl<EventStatus> implements EventStatusService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EventStatusServiceImpl.class);
  @Autowired
  private EventStatusDao eventStatusDao;

  @PostConstruct
  void init() {
    init(eventStatusDao);
  }
}
