package lk.elevenzcode.bms.restaurant.dao;

import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.restaurant.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.restaurant.model.Restaurant;

import java.util.List;

public interface RestaurantDao extends GenericDao<Restaurant> {
    List<Restaurant> search(SearchCriteriaDto searchCriteriaDto) throws DataAccessException;

}
