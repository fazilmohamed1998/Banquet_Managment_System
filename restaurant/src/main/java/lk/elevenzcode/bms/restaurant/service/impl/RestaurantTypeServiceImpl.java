package lk.elevenzcode.bms.restaurant.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.restaurant.dao.RestaurantTypeDao;
import lk.elevenzcode.bms.restaurant.model.Restaurant;
import lk.elevenzcode.bms.restaurant.model.RestaurantType;
import lk.elevenzcode.bms.restaurant.service.RestaurantTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RestaurantTypeServiceImpl extends GenericServiceImpl<RestaurantType> implements RestaurantTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantTypeServiceImpl.class);
    @Autowired
    private RestaurantTypeDao banquetHallTypeDao;

    @PostConstruct
    void init() {
        init(banquetHallTypeDao);
    }
}
