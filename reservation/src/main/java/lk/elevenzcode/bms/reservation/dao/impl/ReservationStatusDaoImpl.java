package lk.elevenzcode.bms.reservation.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.reservation.dao.ReservationStatusDao;
import lk.elevenzcode.bms.reservation.model.ReservationStatus;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationStatusDaoImpl extends GenericDaoImpl<ReservationStatus> implements ReservationStatusDao {
    public ReservationStatusDaoImpl() {
        super(ReservationStatus.class);
    }

}
