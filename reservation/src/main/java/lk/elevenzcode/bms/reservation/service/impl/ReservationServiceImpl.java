package lk.elevenzcode.bms.reservation.service.impl;

import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.dto.ResultAdditionalData;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.MailService;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.common.util.Constant;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.common.util.JasperUtil;
import lk.elevenzcode.bms.event.model.Event;
import lk.elevenzcode.bms.event.model.EventStatus;
import lk.elevenzcode.bms.event.service.EventService;
import lk.elevenzcode.bms.reservation.dao.ReservationDao;
import lk.elevenzcode.bms.reservation.dto.ReportCriteriaDto;
import lk.elevenzcode.bms.reservation.dto.ReservationReportData;
import lk.elevenzcode.bms.reservation.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.reservation.model.Reservation;
import lk.elevenzcode.bms.reservation.model.ReservationStatus;
import lk.elevenzcode.bms.reservation.model.ReservationType;
import lk.elevenzcode.bms.reservation.service.ReservationService;
import lk.elevenzcode.bms.user.model.User;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.springframework.web.cors.CorsConfiguration.ALL;

@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation> implements ReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private ReportService reportService;

    @Autowired
    private EventService eventService;
    @Value("${app.base.url}")
    private String baseUrl;
    @Value("${app.company.name}")
    private String companyName;
    @Value("${app.company.address}")
    private String companyAddress;
    @Value("${app.company.tel}")
    private String companyTel;
    @Value("${app.company.fax}")
    private String companyFax;
    @Value("${app.company.email}")
    private String companyEmail;
    @Value("${app.company.web}")
    private String companyWeb;

    @PostConstruct
    void init() {
        init(reservationDao);
    }

    @Override
    public List<Reservation> getList(Pageable pageable, ResultAdditionalData additionalData, String criteria) throws ServiceException {
        try {
            return reservationDao.getList(pageable, additionalData, StringUtils.lowerCase(criteria));
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public long getCount(String criteria) throws ServiceException {
        try {
            return reservationDao.getCount(StringUtils.lowerCase(criteria), null);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(value = Constant.TRANSACTION_MANAGER, rollbackFor = ServiceException.class)
    public void save(Reservation reservation) throws ServiceException {
        if (reservation != null) {
            if (reservation.getId() != null && reservation.getId() > 0) {
                final Reservation existingReserv = get(reservation.getId());
                if (reservation.getEvent().getFrom() != null || reservation.getEvent().getTo() != null) {
                    Event event = existingReserv.getEvent();
                    if (reservation.getEvent().getFrom() != null) {
                        event.setFrom(reservation.getEvent().getFrom());
                    }
                    if (reservation.getEvent().getTo() != null) {
                        event.setTo(reservation.getEvent().getTo());
                    }
                    eventService.update(event);
                }
                existingReserv.setStatus(reservation.getStatus());
                update(existingReserv);
            } else {
                Event event = reservation.getEvent();
                event.setCustomer(reservation.getCustomer());
                event.setStatus(new EventStatus(lk.elevenzcode.bms.event.model.enums.EventStatus.PENDING.getId()));
                eventService.insert(event);
                reservation.setType(new ReservationType(lk.elevenzcode.bms.reservation.model.enums.ReservationType.BANQUET_HALL.getId()));
                reservation.setStatus(new ReservationStatus(lk.elevenzcode.bms.reservation.model.enums.ReservationStatus.RESEREVED.getId()));
                reservation.setUser(new User(getLoggedEntity().getId()));
                reservation.setReservedOn(new Date());
                insert(reservation);
            }
        } else {
            throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.reservation.details.required");
        }
    }

    @Override
    public List<Reservation> getByStatuses(Integer... statuses) throws ServiceException {
        try {
            return reservationDao.getByStatuses(statuses);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(value = Constant.TRANSACTION_MANAGER, rollbackFor = ServiceException.class)
    public void cancel(int id) throws ServiceException {
        final Reservation reservation = get(id);
        if (reservation != null) {
            reservation.setStatus(new ReservationStatus(lk.elevenzcode.bms.reservation.model.enums.ReservationStatus.CANCELED.getId()));
            update(reservation);
        } else {
            throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.reservation.details.required");
        }
    }

    @Override
    public List<Reservation> search(SearchCriteriaDto searchCriteriaDto) throws ServiceException {
        try {
            return reservationDao.search(searchCriteriaDto);
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public JasperPrint generateReport(ReportCriteriaDto reportCriteria) throws ServiceException {
        try {
            final List<Reservation> reservations = reservationDao.getForReport(reportCriteria);
            final List<ReservationReportData> reportDataList = ReservationReportData.parse(reservations);
            final Map<String, Object> param = new HashMap<>();
            param.put("from", reportCriteria.getFrom() != null ? DateUtil.formatToWebDateMMM(reportCriteria.getFrom()) : "-");
            param.put("to", reportCriteria.getTo() != null ? DateUtil.formatToWebDateMMM(reportCriteria.getTo()) : "-");


            return reportService.generateReport(JasperUtil.getTemplate("reservation_report_template", this.getClass()),
                    reportDataList, param, "reservation-messages");
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
