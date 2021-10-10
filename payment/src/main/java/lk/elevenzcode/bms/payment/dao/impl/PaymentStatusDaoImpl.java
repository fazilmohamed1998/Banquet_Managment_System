package lk.elevenzcode.bms.payment.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.payment.dao.PaymentStatusDao;
import lk.elevenzcode.bms.payment.model.PaymentStatus;
import org.springframework.stereotype.Repository;

/**
 * @author HaShaN on 10/4/2019 9:44 PM.
 */
@Repository
public class PaymentStatusDaoImpl extends GenericDaoImpl<PaymentStatus> implements PaymentStatusDao {
  public PaymentStatusDaoImpl() {
    super(PaymentStatus.class);
  }
}
