package lk.elevenzcode.bms.customer.security;

import lk.elevenzcode.bms.common.security.CommonAuthority;

public interface CustomerAuthority extends CommonAuthority {
  String MODULE_PREFIX = "CUSTOMER_";
  String ROLE_CUSTOMER_MGMT = ROLE_PREFIX + MODULE_PREFIX + "MGMT";
}