package lk.elevenzcode.bms.app.security;

import lk.elevenzcode.bms.common.security.CommonAuthority;

public interface AppAuthority extends CommonAuthority {
  String MODULE_PREFIX = "APP";
  String ROLE_APP = ROLE_PREFIX + MODULE_PREFIX;
}
