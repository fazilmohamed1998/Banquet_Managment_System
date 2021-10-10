package lk.elevenzcode.bms.event.security;

import lk.elevenzcode.bms.common.security.CommonAuthority;

public interface EventAuthority extends CommonAuthority {
  String MODULE_PREFIX = "EVENT_";
  String ROLE_EVENT_MGMT = ROLE_PREFIX + MODULE_PREFIX + "MGMT";
}