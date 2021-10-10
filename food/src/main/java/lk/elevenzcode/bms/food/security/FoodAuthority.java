package lk.elevenzcode.bms.food.security;

import lk.elevenzcode.bms.common.security.CommonAuthority;

public interface FoodAuthority extends CommonAuthority {
  String MODULE_PREFIX = "FOOD_";
  String ROLE_FOOD_MGMT = ROLE_PREFIX + MODULE_PREFIX + "MGMT";
  String ROLE_FOOD_MENU_MGMT = ROLE_PREFIX + MODULE_PREFIX + "MENU_MGMT";
}