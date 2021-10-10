package lk.elevenzcode.bms.food.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.food.dao.FoodMenuDao;
import lk.elevenzcode.bms.food.model.FoodMenu;
import org.springframework.stereotype.Repository;

@Repository
public class FoodMenuDaoImpl extends GenericDaoImpl<FoodMenu> implements FoodMenuDao {
  public FoodMenuDaoImpl() {
    super(FoodMenu.class);
  }
}
