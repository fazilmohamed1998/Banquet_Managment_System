package lk.elevenzcode.bms.restaurant.security;

import lk.elevenzcode.bms.common.security.CommonAuthority;

public interface RestaurantAuthority extends CommonAuthority {
  String MODULE_PREFIX = "RESTAURANT_";
  String ROLE_RESERVATION_MGMT = ROLE_PREFIX + MODULE_PREFIX + "MGMT";
}