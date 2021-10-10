package lk.elevenzcode.bms.food.service;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.food.model.FoodMenu;

public interface FoodMenuService extends GenericService<FoodMenu> {
  void save(FoodMenu foodMenu) throws ServiceException;
}
