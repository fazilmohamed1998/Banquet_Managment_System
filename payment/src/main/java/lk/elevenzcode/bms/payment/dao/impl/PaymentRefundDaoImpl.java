package lk.elevenzcode.bms.payment.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.payment.dao.PaymentRefundDao;
import lk.elevenzcode.bms.payment.model.PaymentRefund;
import org.springframework.stereotype.Repository;

/**
 * @author HaShaN on 8/31/2019 10:59 AM.
 */
@Repository
public class PaymentRefundDaoImpl extends GenericDaoImpl<PaymentRefund> implements PaymentRefundDao {
  public PaymentRefundDaoImpl() {
    super(PaymentRefund.class);
  }
}
