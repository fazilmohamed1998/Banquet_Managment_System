package lk.elevenzcode.bms.restaurant.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.restaurant.dao.RestaurantTypeDao;
import lk.elevenzcode.bms.restaurant.model.RestaurantType;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantTypeDaoImpl extends GenericDaoImpl<RestaurantType> implements RestaurantTypeDao {
    public RestaurantTypeDaoImpl() {
        super(RestaurantType.class);
    }
}
