package lk.elevenzcode.bms.entity.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.entity.dao.EntityStatusDao;
import lk.elevenzcode.bms.entity.model.EntityStatus;
import lk.elevenzcode.bms.entity.service.EntityStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EntityStatusServiceImpl extends GenericServiceImpl<EntityStatus> implements EntityStatusService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EntityStatusServiceImpl.class);
  @Autowired
  private EntityStatusDao entityStatusDao;

  @PostConstruct
  void init() {
    init(entityStatusDao);
  }
}
