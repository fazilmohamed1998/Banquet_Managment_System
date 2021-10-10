package lk.elevenzcode.bms.food.dao;

import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.food.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.food.model.Food;

import java.util.List;

public interface FoodDao extends GenericDao<Food> {
  List<Food> search(SearchCriteriaDto criteria) throws DataAccessException;
}
