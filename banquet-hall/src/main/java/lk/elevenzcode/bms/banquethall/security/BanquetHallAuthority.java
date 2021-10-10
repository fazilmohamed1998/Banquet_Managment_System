package lk.elevenzcode.bms.banquethall.security;

import lk.elevenzcode.bms.common.security.CommonAuthority;

public interface BanquetHallAuthority extends CommonAuthority {
  String MODULE_PREFIX = "BANQUET_HALL_";
  String ROLE_BANQUET_HALL_MGMT = ROLE_PREFIX + MODULE_PREFIX + "MGMT";
}