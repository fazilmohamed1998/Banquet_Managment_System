package lk.elevenzcode.bms.restaurant.service;

import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.restaurant.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.restaurant.model.Restaurant;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;

public interface RestaurantService extends GenericService<Restaurant> {

  List<Restaurant> getList(Pageable pageable) throws ServiceException;

  long getCount() throws ServiceException;

  void save(Restaurant restaurant) throws ServiceException;

  List<Restaurant> search(SearchCriteriaDto searchCriteriaDto) throws ServiceException;

  JasperPrint generateReport(List<Restaurant> restaurants, SearchCriteriaDto criteriaDto) throws ServiceException;
}
