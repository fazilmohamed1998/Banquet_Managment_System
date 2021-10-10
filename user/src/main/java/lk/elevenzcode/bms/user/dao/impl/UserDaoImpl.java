package lk.elevenzcode.bms.user.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.user.dao.UserDao;
import lk.elevenzcode.bms.user.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

  public UserDaoImpl() {
    super(User.class);
  }

  @Override
  public List<User> search(SearchCriteriaDto searchCriteriaDto) throws DataAccessException {
    final StringBuffer querySb = new StringBuffer(getCurrentSession()
      .getNamedQuery("User.search").getQueryString());

    StringBuffer whereClauseSb = null;
    if (searchCriteriaDto != null && (searchCriteriaDto.getStatusId() != null || searchCriteriaDto.getRoleId() != null)) {
      whereClauseSb = new StringBuffer();
      if (searchCriteriaDto.getRoleId() != null) {
        if (StringUtils.isNotEmpty(whereClauseSb.toString())) {
          whereClauseSb.append(" AND ");
        }
        whereClauseSb.append("r.id=:roleId");
      }
      if (searchCriteriaDto.getStatusId() != null) {
        if (StringUtils.isNotEmpty(whereClauseSb.toString())) {
          whereClauseSb.append(" AND ");
        }
        whereClauseSb.append("s.id=:statusId");
      }
    }

    if (whereClauseSb != null) {
      querySb.append(" WHERE ").append(whereClauseSb.toString());
    }

    final Query query = getCurrentSession().createQuery(querySb.toString());
    if (whereClauseSb != null) {
      if (searchCriteriaDto.getRoleId() != null) {
        query.setParameter("roleId", searchCriteriaDto.getRoleId());
      }

      if (searchCriteriaDto.getStatusId() != null) {
        query.setParameter("statusId", searchCriteriaDto.getStatusId());
      }
    }
    return query.list();
  }
}
