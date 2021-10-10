package lk.elevenzcode.bms.banquethall.dao.impl;

import lk.elevenzcode.bms.banquethall.dao.BanquetHallDao;
import lk.elevenzcode.bms.banquethall.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.banquethall.model.BanquetHall;
import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BanquetHallDaoImpl extends GenericDaoImpl<BanquetHall> implements BanquetHallDao {
  private static final Logger LOGGER = LoggerFactory.getLogger(BanquetHallDaoImpl.class);

  public BanquetHallDaoImpl() {
    super(BanquetHall.class);
  }

  @Override
  public List<BanquetHall> search(SearchCriteriaDto searchCriteriaDto) throws DataAccessException {
    final StringBuffer querySb = new StringBuffer(getCurrentSession()
        .getNamedQuery("BanquetHall.search").getQueryString());

    StringBuffer whereClauseSb = null;
    if (searchCriteriaDto != null && (searchCriteriaDto.getStatusId() != null || searchCriteriaDto.getTypeId() != null)) {
      whereClauseSb = new StringBuffer();
      if (searchCriteriaDto.getTypeId() != null) {
        if (StringUtils.isNotEmpty(whereClauseSb.toString())) {
          whereClauseSb.append(" AND ");
        }
        whereClauseSb.append("t.id=:typeId");
      }
      if (searchCriteriaDto.getStatusId() != null) {
        if (StringUtils.isNotEmpty(whereClauseSb.toString())) {
          whereClauseSb.append(" AND ");
        }
        whereClauseSb.append("s.id=:statusId");
      }
    }

    if (whereClauseSb != null) {
      querySb.append(" WHERE ").append(whereClauseSb);
    }

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("query : {}", querySb);
    }

    final Query query = getCurrentSession().createQuery(querySb.toString());
    if (searchCriteriaDto != null && (searchCriteriaDto.getStatusId() != null || searchCriteriaDto.getTypeId() != null)) {
      if (searchCriteriaDto.getTypeId() != null) {
        query.setParameter("typeId", searchCriteriaDto.getTypeId());
      }
      if (searchCriteriaDto.getStatusId() != null) {
        query.setParameter("statusId", searchCriteriaDto.getStatusId());
      }
    }
    return query.list();
  }
}





