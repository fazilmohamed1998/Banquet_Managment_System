package lk.elevenzcode.bms.banquethall.dao.impl;

import lk.elevenzcode.bms.banquethall.dao.BanquetHallStatusDao;
import lk.elevenzcode.bms.banquethall.model.BanquetHallStatus;
import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by hashan on 9/10/19 10:51 AM
 */
@Repository
public class BanquetHallStatusDaoImpl extends GenericDaoImpl<BanquetHallStatus> implements BanquetHallStatusDao {
  public BanquetHallStatusDaoImpl() {
    super(BanquetHallStatus.class);
  }
}
