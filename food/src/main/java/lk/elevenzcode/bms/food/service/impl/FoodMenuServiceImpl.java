package lk.elevenzcode.bms.food.service.impl;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.food.dao.FoodMenuDao;
import lk.elevenzcode.bms.food.model.FoodMenu;
import lk.elevenzcode.bms.food.service.FoodMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FoodMenuServiceImpl extends GenericServiceImpl<FoodMenu> implements FoodMenuService {
  @Autowired
  private FoodMenuDao foodMenuDao;

  @PostConstruct
  void init() {
    init(foodMenuDao);
  }

  @Override
  public void save(FoodMenu foodMenu) throws ServiceException {
    if (foodMenu != null) {
      /*check food object has an id then it will update, otherwise will add*/
      if (foodMenu.getId() != null && foodMenu.getId() > 0) {
        update(foodMenu);
      } else {
        insert(foodMenu);
      }
    } else {
      //TODO throw exception
    }
  }
}
