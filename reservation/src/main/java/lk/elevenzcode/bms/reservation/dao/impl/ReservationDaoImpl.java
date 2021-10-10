package lk.elevenzcode.bms.reservation.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.reservation.dto.ReportCriteriaDto;
import lk.elevenzcode.bms.reservation.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.dto.ResultAdditionalData;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.reservation.dao.ReservationDao;
import lk.elevenzcode.bms.reservation.dto.ReservationDataDto;
import lk.elevenzcode.bms.reservation.model.Reservation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDaoImpl extends GenericDaoImpl<Reservation> implements ReservationDao {
    public static final String CRITERIA_FORMAT = "%%%s%%";
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDaoImpl.class);

    public ReservationDaoImpl() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> getList(Pageable pageable, ResultAdditionalData additionalData, String criteria) throws DataAccessException {
        final StringBuffer querySb = new StringBuffer(getCurrentSession().getNamedQuery("Reservation.getList")
                .getQueryString());
        //get where clause data
        final WhereClauseData whereClauseData = getWhereClause(criteria);
        querySb.append(whereClauseData.getWhereClause());
        querySb.append("ORDER BY r.").append(ReservationDataDto.sortMap.get(pageable.getSort())).append(" ")
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
        final StringBuffer querySb = new StringBuffer(getCurrentSession().getNamedQuery("Reservation.getCount")
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
    public List<Reservation> getForReport(ReportCriteriaDto reportCriteria) throws DataAccessException {
        final StringBuffer querySb = new StringBuffer(getCurrentSession().getNamedQuery("Reservation.getList")
                .getQueryString());
        StringBuffer whereClauseSb = null;
        if (reportCriteria != null) {
            if (reportCriteria.getFrom() != null || reportCriteria.getTo() != null) {
                whereClauseSb = new StringBuffer();
                if (reportCriteria.getFrom() != null) {
                    if (whereClauseSb.length() > 0) {
                        whereClauseSb.append("AND ");
                    }
                    whereClauseSb.append("DATE(r.reservedOn)>=:from ");
                }

                if (reportCriteria.getTo() != null) {
                    if (whereClauseSb.length() > 0) {
                        whereClauseSb.append("AND ");
                    }
                    whereClauseSb.append("DATE(r.reservedOn)<=:to ");
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
        }
        return query.list();
    }

    @Override
    public List<Reservation> getByStatuses(Integer... statuses) throws DataAccessException {
        return getCurrentSession().getNamedQuery("Reservation.getByStatuses")
                .setParameterList("statuses", statuses)
                .list();
    }

    private WhereClauseData getWhereClause(String criteria) {
        final WhereClauseData whereClauseData = new WhereClauseData();
        final StringBuffer whereClauseSb = new StringBuffer();
        if (StringUtils.isNotEmpty(criteria)) {
            whereClauseSb.append("WHERE LOWER(r.reservationNo) LIKE :criteria OR LOWER(c.name) ")
                    .append("LIKE :criteria OR LOWER(t.type) LIKE :criteria OR LOWER(s.name) ")
                    .append("LIKE :criteria OR LOWER(e.eventType.type) LIKE :criteria ");

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
                whereClauseSb.append("OR r.reservedOn=:criteriaDate OR e.from=:criteriaDate"); // What should be done here
            }
        }
        whereClauseData.setWhereClause(whereClauseSb.toString());
        return whereClauseData;
    }

    @Override
    public List<Reservation> search(SearchCriteriaDto searchCriteriaDto) throws DataAccessException {
        final StringBuffer querySb = new StringBuffer(getCurrentSession()
                .getNamedQuery("Reservation.search").getQueryString());

        StringBuffer whereClauseSb = null;
        if (searchCriteriaDto != null && (searchCriteriaDto.getFrom() != null || searchCriteriaDto.getTo() != null)) {
            whereClauseSb = new StringBuffer();
            if (searchCriteriaDto.getFrom() != null) {
                if (StringUtils.isNotEmpty(whereClauseSb.toString())) {
                    whereClauseSb.append(" AND ");
                }
                whereClauseSb.append("e.from=:from");
            }
            if (searchCriteriaDto.getTo() != null) {
                if (StringUtils.isNotEmpty(whereClauseSb.toString())) {
                    whereClauseSb.append(" AND ");
                }
                whereClauseSb.append("e.to=:to");
            }
        }

        if (whereClauseSb != null) {
            querySb.append(" WHERE ").append(whereClauseSb);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("query : {}", querySb);
        }

        final Query query = getCurrentSession().createQuery(querySb.toString());
        if (searchCriteriaDto != null && (searchCriteriaDto.getFrom() != null || searchCriteriaDto.getTo() != null)) {
            if (searchCriteriaDto.getFrom() != null) {
                query.setParameter("from", searchCriteriaDto.getFrom());
            }
            if (searchCriteriaDto.getTo() != null) {
                query.setParameter("to", searchCriteriaDto.getTo());
            }
        }
        return query.list();
    }
}
