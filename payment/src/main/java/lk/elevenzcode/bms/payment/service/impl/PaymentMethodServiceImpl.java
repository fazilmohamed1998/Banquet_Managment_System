package lk.elevenzcode.bms.payment.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.payment.dao.PaymentMethodDao;
import lk.elevenzcode.bms.payment.model.PaymentMethod;
import lk.elevenzcode.bms.payment.service.PaymentMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author HaShaN on 8/28/2019 11:21 PM.
 */
@Service
public class PaymentMethodServiceImpl extends GenericServiceImpl<PaymentMethod> implements PaymentMethodService {
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

  @Autowired
  private PaymentMethodDao payMethodDao;

  @PostConstruct
  void init() {
    init(payMethodDao);
  }
}
