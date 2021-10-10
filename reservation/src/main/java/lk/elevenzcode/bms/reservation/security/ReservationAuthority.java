package lk.elevenzcode.bms.reservation.security;

import lk.elevenzcode.bms.common.security.CommonAuthority;

public interface ReservationAuthority extends CommonAuthority {
  String MODULE_PREFIX = "RESERVATION";
  String ROLE_RESERVATION = ROLE_PREFIX + MODULE_PREFIX;
}