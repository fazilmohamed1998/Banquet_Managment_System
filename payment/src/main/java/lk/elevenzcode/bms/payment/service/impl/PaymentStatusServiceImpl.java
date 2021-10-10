package lk.elevenzcode.bms.payment.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.payment.dao.PaymentStatusDao;
import lk.elevenzcode.bms.payment.model.PaymentStatus;
import lk.elevenzcode.bms.payment.service.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author HaShaN on 10/4/2019 9:43 PM.
 */
@Service
public class PaymentStatusServiceImpl extends GenericServiceImpl<PaymentStatus> implements PaymentStatusService {
  @Autowired
  private PaymentStatusDao paymentStatusDao;

  @PostConstruct
  void init() {
    init(paymentStatusDao);
  }
}
