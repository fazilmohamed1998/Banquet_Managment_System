package lk.elevenzcode.bms.food.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.food.dao.FoodDao;
import lk.elevenzcode.bms.food.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.food.model.Food;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodDaoImpl extends GenericDaoImpl<Food> implements FoodDao {
  private static final Logger LOGGER = LoggerFactory.getLogger(FoodDaoImpl.class);

  public FoodDaoImpl() {
    super(Food.class);
  }

  @Override
  public List<Food> search(SearchCriteriaDto criteria) throws DataAccessException {
    final StringBuffer querySb = new StringBuffer(getCurrentSession()
      .getNamedQuery("Food.search").getQueryString());

    StringBuffer whereClauseSb = null;
    if (criteria != null && (criteria.getCategoryId() != null || criteria.getCuisineId() != null)) {
      whereClauseSb = new StringBuffer();
      if (criteria.getCategoryId() != null) {
        if (StringUtils.isNotEmpty(whereClauseSb.toString())) {
          whereClauseSb.append(" AND ");
        }
        whereClauseSb.append("c.id=:categoryId");
      }
      if (criteria.getCuisineId() != null) {
        if (StringUtils.isNotEmpty(whereClauseSb.toString())) {
          whereClauseSb.append(" AND ");
        }
        whereClauseSb.append("cu.id=:cuisineId");
      }
    }

    if (whereClauseSb != null) {
      querySb.append(" WHERE ").append(whereClauseSb);
    }

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("query : {}", querySb);
    }

    final Query query = getCurrentSession().createQuery(querySb.toString());
    if (criteria != null && (criteria.getCategoryId() != null || criteria.getCuisineId() != null)) {
      if (criteria.getCategoryId() != null) {
        query.setParameter("categoryId", criteria.getCategoryId());
      }
      if (criteria.getCuisineId() != null) {
        query.setParameter("cuisineId", criteria.getCuisineId());
      }
    }
    return query.list();
  }
}
