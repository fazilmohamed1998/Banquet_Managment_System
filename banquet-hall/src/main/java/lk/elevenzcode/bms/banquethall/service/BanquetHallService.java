package lk.elevenzcode.bms.banquethall.service;

import lk.elevenzcode.bms.banquethall.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.banquethall.model.BanquetHall;
import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;

public interface BanquetHallService extends GenericService<BanquetHall> {
  List<BanquetHall> getList(Pageable pageable) throws ServiceException;

  long getCount() throws ServiceException;

  void save(BanquetHall banquetHall) throws ServiceException;

  List<BanquetHall> search(SearchCriteriaDto searchCriteriaDto) throws ServiceException;

  JasperPrint generateReport(List<BanquetHall> banquetHalls, SearchCriteriaDto criteriaDto) throws ServiceException;
}
