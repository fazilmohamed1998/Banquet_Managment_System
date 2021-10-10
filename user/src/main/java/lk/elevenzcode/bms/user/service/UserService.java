package lk.elevenzcode.bms.user.service;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.user.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.user.model.User;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;

public interface UserService extends GenericService<User> {
  void save(User user) throws ServiceException;

  List<User> search(SearchCriteriaDto searchCriteriaDto) throws ServiceException;

  JasperPrint generateReport(List<User> users, SearchCriteriaDto criteriaDto) throws ServiceException;
}
