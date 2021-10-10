package lk.elevenzcode.bms.banquethall.dao;

import lk.elevenzcode.bms.banquethall.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.banquethall.model.BanquetHall;
import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.exception.DataAccessException;

import java.util.List;

public interface BanquetHallDao extends GenericDao<BanquetHall> {
  List<BanquetHall> search(SearchCriteriaDto searchCriteriaDto) throws DataAccessException;
}
