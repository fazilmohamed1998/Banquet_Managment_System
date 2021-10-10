package lk.elevenzcode.bms.banquethall.service.impl;

import lk.elevenzcode.bms.banquethall.dao.BanquetHallStatusDao;
import lk.elevenzcode.bms.banquethall.model.BanquetHallStatus;
import lk.elevenzcode.bms.banquethall.service.BanquetHallStatusService;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by hashan on 9/10/19 10:50 AM
 */
@Service
public class BanquetHallStatusServiceImpl extends GenericServiceImpl<BanquetHallStatus> implements BanquetHallStatusService {
  private static final Logger LOGGER = LoggerFactory.getLogger(BanquetHallStatusServiceImpl.class);
  @Autowired
  private BanquetHallStatusDao banquetHallStatusDao;

  @PostConstruct
  void init() {
    init(banquetHallStatusDao);
  }
}
