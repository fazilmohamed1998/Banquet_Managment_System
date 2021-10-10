package lk.elevenzcode.bms.reservation.dao;

import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.dto.ResultAdditionalData;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.reservation.dto.ReportCriteriaDto;
import lk.elevenzcode.bms.reservation.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.reservation.model.Reservation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ReservationDao extends GenericDao<Reservation> {
    List<Reservation> getList(Pageable pageable, ResultAdditionalData additionalData, String criteria) throws DataAccessException;

    List<Reservation> getByStatuses(Integer... statuses) throws DataAccessException;

    List<Reservation> search(SearchCriteriaDto searchCriteriaDto) throws DataAccessException;

    long getCount(String search, WhereClauseData whereClauseData) throws DataAccessException;

    List<Reservation> getForReport(ReportCriteriaDto reportCriteria) throws DataAccessException;

    class WhereClauseData {
        private String whereClause;
        private BigDecimal criteriaAmt = null;
        private Date criteriaDate = null;

        public String getWhereClause() {
            return whereClause;
        }

        public void setWhereClause(String whereClause) {
            this.whereClause = whereClause;
        }

        public BigDecimal getCriteriaAmt() {
            return criteriaAmt;
        }

        public void setCriteriaAmt(BigDecimal criteriaAmt) {
            this.criteriaAmt = criteriaAmt;
        }

        public Date getCriteriaDate() {
            return criteriaDate;
        }

        public void setCriteriaDate(Date criteriaDate) {
            this.criteriaDate = criteriaDate;
        }
    }


}
