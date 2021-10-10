package lk.elevenzcode.bms.banquethall.service.impl;

import lk.elevenzcode.bms.banquethall.dao.BanquetHallTypeDao;
import lk.elevenzcode.bms.banquethall.model.BanquetHallType;
import lk.elevenzcode.bms.banquethall.service.BanquetHallTypeService;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by hashan on 9/10/19 10:45 AM
 */
@Service
public class BanquetHallTypeServiceImpl extends GenericServiceImpl<BanquetHallType> implements BanquetHallTypeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(BanquetHallTypeServiceImpl.class);
  @Autowired
  private BanquetHallTypeDao banquetHallTypeDao;

  @PostConstruct
  void init() {
    init(banquetHallTypeDao);
  }
}
