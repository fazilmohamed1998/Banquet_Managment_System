package lk.elevenzcode.bms.reservation.service;

import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.dto.ResultAdditionalData;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.reservation.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.reservation.dto.ReportCriteriaDto;
import lk.elevenzcode.bms.reservation.model.Reservation;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;

public interface ReservationService extends GenericService<Reservation> {
    List<Reservation> getList(Pageable pageable, ResultAdditionalData additionalData, String criteria) throws ServiceException;

    long getCount(String criteria) throws ServiceException;

    void save(Reservation payment) throws ServiceException;

    List<Reservation> getByStatuses(Integer... statuses) throws ServiceException;

    void cancel(int id) throws ServiceException;

    List<Reservation> search(SearchCriteriaDto searchCriteriaDto) throws ServiceException;

    JasperPrint generateReport(ReportCriteriaDto reportCriteria) throws ServiceException;
}
