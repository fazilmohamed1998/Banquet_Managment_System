package lk.elevenzcode.bms.reservation.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.reservation.dao.ReservationTypeDao;
import lk.elevenzcode.bms.reservation.model.ReservationType;
import lk.elevenzcode.bms.reservation.service.ReservationTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Harin on 8/30/2019
 */
@Service
public class ReservationTypeServiceImpl extends GenericServiceImpl<ReservationType> implements ReservationTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationTypeDao reservationTypeDao;

    @PostConstruct
    void init() {
        init(reservationTypeDao);
    }
}
