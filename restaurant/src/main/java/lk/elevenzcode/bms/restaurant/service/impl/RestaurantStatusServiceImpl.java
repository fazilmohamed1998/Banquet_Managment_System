package lk.elevenzcode.bms.restaurant.service.impl;

import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.restaurant.dao.RestaurantStatusDao;
import lk.elevenzcode.bms.restaurant.model.RestaurantStatus;
import lk.elevenzcode.bms.restaurant.service.RestaurantStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RestaurantStatusServiceImpl extends GenericServiceImpl<RestaurantStatus> implements RestaurantStatusService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantStatusServiceImpl.class);
    @Autowired
    private RestaurantStatusDao restaurantStatusDao;

    @PostConstruct
    void init() {
        init(restaurantStatusDao);
    }
}
