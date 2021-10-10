package lk.elevenzcode.bms.user.security;

import lk.elevenzcode.bms.common.security.CommonAuthority;

public interface UserAuthority extends CommonAuthority {
  String MODULE_PREFIX = "USER_";
  String ROLE_ADMIN = ROLE_PREFIX + "ADMIN";
  String ROLE_USER_MGMT = ROLE_PREFIX + MODULE_PREFIX + "MGMT";
}
