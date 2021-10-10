package lk.elevenzcode.bms.food.service.impl;

import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.common.util.JasperUtil;
import lk.elevenzcode.bms.food.dao.FoodDao;
import lk.elevenzcode.bms.food.dto.FoodReportData;
import lk.elevenzcode.bms.food.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.food.model.Food;
import lk.elevenzcode.bms.food.service.FoodService;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FoodServiceImpl extends GenericServiceImpl<Food> implements FoodService {
  @Autowired
  private FoodDao foodDao;

  @Autowired
  private ReportService reportService;

  @PostConstruct
  void init() {
    init(foodDao);
  }

  @Override
  public void save(Food food) throws ServiceException {
    if (food != null) {
      /*check food object has an id then it will update, otherwize will add*/
      if (food.getId() != null && food.getId() > 0) {
        update(food);
      } else {
        insert(food);
      }
    } else {
      //TODO throw exception
    }
  }

  @Override
  public List<Food> search(SearchCriteriaDto criteria) throws ServiceException {
    try {
      return foodDao.search(criteria);
    } catch (DataAccessException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Override
  public JasperPrint generateReport(List<Food> foods, SearchCriteriaDto criteriaDto) throws ServiceException {
    if (CollectionUtils.isNotEmpty(foods)) {
      final List<FoodReportData> foodDataList = FoodReportData.parse(foods);
      return reportService.generateReport(JasperUtil.getTemplate("food_report", this.getClass()),
        foodDataList, null, "food-messages");
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.common.no.result");
    }
  }
}
