package lk.elevenzcode.bms.payment.security;

import lk.elevenzcode.bms.common.security.CommonAuthority;

public interface PaymentAuthority extends CommonAuthority {
  String MODULE_PREFIX = "PAYMENT";
  String ROLE_PAYMENT = ROLE_PREFIX + MODULE_PREFIX;
}