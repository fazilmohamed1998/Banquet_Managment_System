package lk.elevenzcode.bms.banquethall.dao.impl;

import lk.elevenzcode.bms.banquethall.dao.BanquetHallTypeDao;
import lk.elevenzcode.bms.banquethall.model.BanquetHallType;
import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by hashan on 9/10/19 10:47 AM
 */
@Repository
public class BanquetHallTypeDaoImpl extends GenericDaoImpl<BanquetHallType> implements BanquetHallTypeDao {
  public BanquetHallTypeDaoImpl() {
    super(BanquetHallType.class);
  }
}
