package lk.elevenzcode.bms.payment.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.payment.dao.PaymentMethodDao;
import lk.elevenzcode.bms.payment.model.PaymentMethod;
import org.springframework.stereotype.Repository;

/**
 * @author HaShaN on 8/28/2019 11:21 PM.
 */
@Repository
public class PaymentMethodDaoImpl extends GenericDaoImpl<PaymentMethod> implements PaymentMethodDao {
  public PaymentMethodDaoImpl() {
    super(PaymentMethod.class);
  }
}
