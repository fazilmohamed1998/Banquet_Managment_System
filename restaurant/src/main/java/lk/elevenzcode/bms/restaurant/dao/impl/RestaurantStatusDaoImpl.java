package lk.elevenzcode.bms.restaurant.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.restaurant.dao.RestaurantStatusDao;
import lk.elevenzcode.bms.restaurant.model.Restaurant;
import lk.elevenzcode.bms.restaurant.model.RestaurantStatus;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantStatusDaoImpl extends GenericDaoImpl<RestaurantStatus> implements RestaurantStatusDao {
    public RestaurantStatusDaoImpl() { super(RestaurantStatus.class);
    }
}
