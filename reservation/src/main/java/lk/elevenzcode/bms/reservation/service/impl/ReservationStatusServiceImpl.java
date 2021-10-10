package lk.elevenzcode.bms.reservation.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.reservation.dao.ReservationStatusDao;
import lk.elevenzcode.bms.reservation.model.ReservationStatus;
import lk.elevenzcode.bms.reservation.service.ReservationStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ReservationStatusServiceImpl extends GenericServiceImpl<ReservationStatus> implements ReservationStatusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationStatusServiceImpl.class);

    @Autowired
    private ReservationStatusDao reservationStatusDao;

    @PostConstruct
    void init() {
        init(reservationStatusDao);
    }


}
