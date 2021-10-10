package lk.elevenzcode.bms.reservation.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.reservation.dao.ReservationTypeDao;
import lk.elevenzcode.bms.reservation.model.ReservationType;
import org.springframework.stereotype.Repository;

/**
 * @author Harin on 8/30/2019.
 */
@Repository
public class ReservationTypeDaoImpl extends GenericDaoImpl<ReservationType> implements ReservationTypeDao {
    public ReservationTypeDaoImpl() {
        super(ReservationType.class);
    }
}
