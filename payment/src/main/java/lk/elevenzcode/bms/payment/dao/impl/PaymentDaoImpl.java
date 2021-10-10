package lk.elevenzcode.bms.payment.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.dto.ResultAdditionalData;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.util.ConversionUtil;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.payment.dao.PaymentDao;
import lk.elevenzcode.bms.payment.dto.PaymentDataDto;
import lk.elevenzcode.bms.payment.dto.ReportCriteriaDto;
import lk.elevenzcode.bms.payment.model.Payment;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDaoImpl extends GenericDaoImpl<Payment> implements PaymentDao {
  public static final String CRITERIA_FORMAT = "%%%s%%";
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDaoImpl.class);

  public PaymentDaoImpl() {
    super(Payment.class);
  }

  @Override
  public List<Payment> getList(Pageable pageable, ResultAdditionalData additionalData, String criteria)
    throws DataAccessException {
    final StringBuffer querySb = new StringBuffer(getCurrentSession().getNamedQuery("Payment.getList")
      .getQueryString());
    //get where clause data
    final WhereClauseData whereClauseData = getWhereClause(criteria);
    querySb.append(whereClauseData.getWhereClause());
    querySb.append("ORDER BY t.").append(PaymentDataDto.sortMap.get(pageable.getSort())).append(" ")
      .append(pageable.getOrder());

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Query : {}", querySb);
    }

    Query query = getCurrentSession().createQuery(querySb.toString());

    if (StringUtils.isNotEmpty(criteria)) {
      query.setParameter("criteria", String.format("%%%s%%", criteria));
      if (whereClauseData.getCriteriaAmt() != null) {
        query.setParameter("criteriaAmt", whereClauseData.getCriteriaAmt());
      }
      if (whereClauseData.getCriteriaDate() != null) {
        query.setParameter("criteriaDate", whereClauseData.getCriteriaDate());
      }
    }

    //get count
    additionalData.setCount(getCount(criteria, whereClauseData));

    return query.setFirstResult(pageable.getOffset())
      .setMaxResults(pageable.getLimit())
      .list();
  }

  @Override
  public long getCount(String criteria, WhereClauseData whereClauseData) throws DataAccessException {
    final StringBuffer querySb = new StringBuffer(getCurrentSession().getNamedQuery("Payment.getCount")
      .getQueryString());
    if (whereClauseData == null) {
      whereClauseData = getWhereClause(criteria);
    }
    querySb.append(whereClauseData.getWhereClause());

    Query query = getCurrentSession().createQuery(querySb.toString());

    if (StringUtils.isNotEmpty(criteria)) {
      query.setParameter("criteria", String.format(CRITERIA_FORMAT, criteria));
      if (whereClauseData.getCriteriaAmt() != null) {
        query.setParameter("criteriaAmt", whereClauseData.getCriteriaAmt());
      }
      if (whereClauseData.getCriteriaDate() != null) {
        query.setParameter("criteriaDate", whereClauseData.getCriteriaDate());
      }
    }
    return (long) query.uniqueResult();
  }

  @Override
  public List<Payment> getForReport(ReportCriteriaDto reportCriteria) throws DataAccessException {
    final StringBuffer querySb = new StringBuffer(getCurrentSession().getNamedQuery("Payment.getList")
      .getQueryString());
    StringBuffer whereClauseSb = null;
    if (reportCriteria != null) {
      if (reportCriteria.getFrom() != null || reportCriteria.getTo() != null || reportCriteria.getPayMethodId() != -1
        || reportCriteria.getStatusId() != -1) {
        whereClauseSb = new StringBuffer();
        if (reportCriteria.getFrom() != null) {
          if (whereClauseSb.length() > 0) {
            whereClauseSb.append("AND ");
          }
          whereClauseSb.append("DATE(t.paidOn)>=:from ");
        }

        if (reportCriteria.getTo() != null) {
          if (whereClauseSb.length() > 0) {
            whereClauseSb.append("AND ");
          }
          whereClauseSb.append("DATE(t.paidOn)<=:to ");
        }

        if (reportCriteria.getPayMethodId() != -1) {
          if (whereClauseSb.length() > 0) {
            whereClauseSb.append("AND ");
          }
          whereClauseSb.append("m.id=:payMethodId ");
        }

        if (reportCriteria.getStatusId() != -1) {
          if (whereClauseSb.length() > 0) {
            whereClauseSb.append("AND ");
          }
          whereClauseSb.append("s.id=:statusId ");
        }
        querySb.append("WHERE ").append(whereClauseSb);
      }
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Query : {}", querySb);
    }
    Query query = getCurrentSession().createQuery(querySb.toString());
    if (whereClauseSb != null) {
      if (reportCriteria.getFrom() != null) {
        query.setParameter("from", reportCriteria.getFrom());
      }
      if (reportCriteria.getTo() != null) {
        query.setParameter("to", reportCriteria.getTo());
      }
      if (reportCriteria.getPayMethodId() != -1) {
        query.setParameter("payMethodId", reportCriteria.getPayMethodId());
      }
      if (reportCriteria.getStatusId() != -1) {
        query.setParameter("statusId", reportCriteria.getStatusId());
      }
    }
    return query.list();
  }

  private WhereClauseData getWhereClause(String criteria) {
    final WhereClauseData whereClauseData = new WhereClauseData();
    final StringBuffer whereClauseSb = new StringBuffer();
    if (StringUtils.isNotEmpty(criteria)) {
      whereClauseSb.append("WHERE LOWER(t.reference) LIKE :criteria OR LOWER(c.name) ")
        .append("LIKE :criteria OR LOWER(m.type) LIKE :criteria OR LOWER(u.name) ")
        .append("LIKE :criteria OR LOWER(s.name) LIKE :criteria ");

      if (NumberUtils.isCreatable(criteria.replaceAll(",", ""))) {
        whereClauseData.setCriteriaAmt(ConversionUtil.getMoney(NumberUtils.createBigDecimal(criteria)));
        whereClauseSb.append("OR t.amount=:criteriaAmt ");
      }

      boolean isDate = false;
      if (GenericValidator.isDate(criteria, DateUtil.PATTERN_DATE, false)) {
        whereClauseData.setCriteriaDate(DateUtil.parseToWebDate(criteria));
        isDate = true;
      } else if (GenericValidator.isDate(criteria, DateUtil.PATTERN_DATE_TIME, false)) {
        whereClauseData.setCriteriaDate(DateUtil.parseToWebDateTime(criteria));
        isDate = true;
      } else if (GenericValidator.isDate(criteria, DateUtil.PATTERN_DATE_MMM_TIME_12HRS, false)) {
        whereClauseData.setCriteriaDate(DateUtil.parseToWebDateTimeMMM(criteria));
        isDate = true;
      }

      if (isDate) {
        whereClauseSb.append("OR t.paidOn=:criteriaDate ");
      }
    }
    whereClauseData.setWhereClause(whereClauseSb.toString());
    return whereClauseData;
  }
}
