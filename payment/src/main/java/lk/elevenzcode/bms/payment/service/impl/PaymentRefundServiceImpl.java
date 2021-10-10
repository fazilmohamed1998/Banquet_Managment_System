package lk.elevenzcode.bms.payment.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.payment.dao.PaymentRefundDao;
import lk.elevenzcode.bms.payment.model.PaymentRefund;
import lk.elevenzcode.bms.payment.service.PaymentRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author HaShaN on 8/31/2019 10:58 AM.
 */
@Service
public class PaymentRefundServiceImpl extends GenericServiceImpl<PaymentRefund> implements PaymentRefundService {
  @Autowired
  private PaymentRefundDao refundDao;

  @PostConstruct
  void init() {
    init(refundDao);
  }
}
