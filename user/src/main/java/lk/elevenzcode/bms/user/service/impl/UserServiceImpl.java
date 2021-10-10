package lk.elevenzcode.bms.user.service.impl;

import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.common.util.JasperUtil;
import lk.elevenzcode.bms.user.dao.UserDao;
import lk.elevenzcode.bms.user.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.user.dto.UserReportData;
import lk.elevenzcode.bms.user.model.User;
import lk.elevenzcode.bms.user.service.UserService;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
  private static final String ALL = "All";

  @Autowired
  private UserDao userDao;

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private ReportService reportService;

  @PostConstruct
  void init() {
    init(userDao);
  }

  @Override
  public void save(User user) throws ServiceException {
    if (user != null) {
      if (user.getId() != null && user.getId() > 0) {
        final User eixstingUser = get(user.getId());
        eixstingUser.setName(user.getName());
        eixstingUser.setNic(user.getNic());
        eixstingUser.setEpfNo(user.getEpfNo());
        eixstingUser.setEmail(user.getEmail());
        eixstingUser.setContactNo(user.getContactNo());
        eixstingUser.setAddress(user.getAddress());
        eixstingUser.setUserName(user.getUserName());

        if (StringUtils.isNotEmpty(user.getPassword())) {
          eixstingUser.setPassword(encoder.encode(user.getPassword()));
        }

        eixstingUser.setUserRole(user.getUserRole());
        eixstingUser.setStatus(user.getStatus());
        update(eixstingUser);
      } else {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setJoinDate(new Date());
        insert(user);
      }
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.user.details.required");
    }
  }

  @Override
  public List<User> search(SearchCriteriaDto searchCriteriaDto) throws ServiceException {
    try {
      return userDao.search(searchCriteriaDto);
    } catch (DataAccessException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }

  @Override
  public JasperPrint generateReport(List<User> users, SearchCriteriaDto criteriaDto) throws ServiceException {
    final List<UserReportData> reportDataList = UserReportData.parse(users);
    return reportService.generateReport(JasperUtil.getTemplate("user_report_template", this.getClass()),
      reportDataList, null, "user-messages");
  }
}
