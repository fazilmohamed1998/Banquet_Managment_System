package lk.elevenzcode.bms.banquethall.service.impl;

import lk.elevenzcode.bms.banquethall.dao.BanquetHallDao;
import lk.elevenzcode.bms.banquethall.dto.BanquetHallReportData;
import lk.elevenzcode.bms.banquethall.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.banquethall.model.BanquetHall;
import lk.elevenzcode.bms.banquethall.service.BanquetHallService;
import lk.elevenzcode.bms.banquethall.service.BanquetHallStatusService;
import lk.elevenzcode.bms.banquethall.service.BanquetHallTypeService;
import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.common.util.JasperUtil;
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
public class BanquetHallServiceImpl extends GenericServiceImpl<BanquetHall> implements BanquetHallService {
  private static final Logger LOGGER = LoggerFactory.getLogger(BanquetHallServiceImpl.class);
  private static final String ALL = "All";

  @Autowired
  private BanquetHallDao banquetHallDao;

  @Autowired
  private ReportService reportService;

  @Autowired
  private BanquetHallTypeService hallTypeService;

  @Autowired
  private BanquetHallStatusService hallStatusService;

  @PostConstruct
  void init() {
    init(banquetHallDao);
  }

  @Override
  public List<BanquetHall> getList(Pageable pageable) throws ServiceException {
    return null;
  }

  @Override
  public long getCount() throws ServiceException {
    return 0;
  }

  @Override
  public void save(BanquetHall banquetHall) throws ServiceException {
    if (banquetHall != null) {
      if (banquetHall.getId() != null && banquetHall.getId() > 0) {
        update(banquetHall);
      } else {
        insert(banquetHall);
      }
    } else {
      //TODO throw exception
    }
  }

  @Override
  public List<BanquetHall> search(SearchCriteriaDto searchCriteriaDto) throws ServiceException {
    try {
      return banquetHallDao.search(searchCriteriaDto);
    } catch (DataAccessException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Override
  public JasperPrint generateReport(List<BanquetHall> banquetHalls,
                                    SearchCriteriaDto criteriaDto) throws ServiceException {
    if (CollectionUtils.isNotEmpty(banquetHalls)) {
      final List<BanquetHallReportData> reportDataList = BanquetHallReportData.parse(banquetHalls);
      final Map<String, Object> param = new HashMap<>();

      String type;
      if (criteriaDto.getTypeId() != null && criteriaDto.getTypeId() > 0) {
        type = hallTypeService.get(criteriaDto.getTypeId()).getType();
      } else {
        type = ALL;
      }
      String status;
      if (criteriaDto.getStatusId() != null && criteriaDto.getStatusId() > 0) {
        status = hallStatusService.get(criteriaDto.getStatusId()).getName();
      } else {
        status = ALL;
      }
      param.put("type", type);
      param.put("status", status);
      return reportService.generateReport(JasperUtil.getTemplate("banquet_hall_report_template", this.getClass()),
        reportDataList, param, "banquet-hall-messages");
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.common.no.result");
    }
  }
}
