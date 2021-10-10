package lk.elevenzcode.bms.restaurant.service.impl;

import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.common.util.JasperUtil;
import lk.elevenzcode.bms.restaurant.dao.RestaurantDao;
import lk.elevenzcode.bms.restaurant.dto.RestaurantReportData;
import lk.elevenzcode.bms.restaurant.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.restaurant.model.Restaurant;
import lk.elevenzcode.bms.restaurant.service.RestaurantService;
import lk.elevenzcode.bms.restaurant.service.RestaurantStatusService;
import lk.elevenzcode.bms.restaurant.service.RestaurantTypeService;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantServiceImpl extends GenericServiceImpl<Restaurant> implements RestaurantService {
  private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImpl.class);
  private static final String ALL = "All";

  @Autowired
  private RestaurantDao restaurantDao;

  @Autowired
  private ReportService reportService;

  @Autowired
  private RestaurantTypeService tableTypeService;

  @Autowired
  private RestaurantStatusService tableStatusService;

  @PostConstruct
  void init() {
    init(restaurantDao);
  }

  @Override
  public List<Restaurant> getList(Pageable pageable) throws ServiceException {
    return null;
  }

  @Override
  public long getCount() throws ServiceException {
    return 0;
  }

  @Override
  public void save(Restaurant restaurant) throws ServiceException {
    if (restaurant != null) {
      if (restaurant.getId() != null && restaurant.getId() > 0) {
        update(restaurant);
      } else {
        insert(restaurant);
      }
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.customer.details.required");
    }
  }

  @Override
  public List<Restaurant> search(SearchCriteriaDto searchCriteriaDto) throws ServiceException {
    return null;
  }

  @Override
  public JasperPrint generateReport(List<Restaurant> restaurants,
                                    SearchCriteriaDto criteriaDto) throws ServiceException {
    if (CollectionUtils.isNotEmpty(restaurants)) {
      final List<RestaurantReportData> reportDataList = RestaurantReportData.parse(restaurants);
      final Map<String, Object> param = new HashMap<>();

      String type;
      if (criteriaDto.getTypeId() != null && criteriaDto.getTypeId() > 0) {
        type = tableTypeService.get(criteriaDto.getTypeId()).getType();
      } else {
        type = ALL;
      }
      String status;
      if (criteriaDto.getStatusId() != null && criteriaDto.getStatusId() > 0) {
        status = tableStatusService.get(criteriaDto.getStatusId()).getName();
      } else {
        status = ALL;
      }
      param.put("type", type);
      param.put("status", status);
      return reportService.generateReport(JasperUtil.getTemplate("restaurant_report_template", this.getClass()),
              reportDataList, param, "restaurant-messages");
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.common.no.result");
    }
  }
}
