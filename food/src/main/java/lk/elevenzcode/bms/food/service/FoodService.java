package lk.elevenzcode.bms.food.service;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.food.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.food.model.Food;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;

public interface FoodService extends GenericService<Food> {
  void save(Food food) throws ServiceException;

  List<Food> search(SearchCriteriaDto criteria) throws ServiceException;

  JasperPrint generateReport(List<Food> foods, SearchCriteriaDto criteriaDto) throws ServiceException;
}