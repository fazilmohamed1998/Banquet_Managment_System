package lk.elevenzcode.bms.restaurant.controller;

import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.common.dto.ComboboxData;
import lk.elevenzcode.bms.common.dto.Response;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import lk.elevenzcode.bms.restaurant.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.restaurant.model.Restaurant;
import lk.elevenzcode.bms.restaurant.model.RestaurantStatus;
import lk.elevenzcode.bms.restaurant.model.RestaurantType;
import lk.elevenzcode.bms.restaurant.security.RestaurantAuthority;
import lk.elevenzcode.bms.restaurant.service.RestaurantService;
import lk.elevenzcode.bms.restaurant.service.RestaurantStatusService;
import lk.elevenzcode.bms.restaurant.service.RestaurantTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static lk.elevenzcode.bms.common.util.Constant.REQ_HEADER_ACCEPT_JSON;

@Controller
@RequestMapping("/app/restaurant")
@Secured(RestaurantAuthority.ROLE_RESERVATION_MGMT)
public class RestaurantController extends BaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);

  @Autowired
  private RestaurantService restaurantService;

  @Autowired
  private RestaurantTypeService restaurantTypeService;

    @Autowired
    private RestaurantStatusService restaurantStatusService;

  @Autowired
  private ReportService reportService;

  @RequestMapping(value = "home", method = RequestMethod.GET)
  public String getRestaurantHomePage(HttpServletRequest request, Model model) {
    final EntityDetail loggedEntity = getLoggedEntity();
    model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
    model.addAttribute("restaurant", new Restaurant());
    try {
      model.addAttribute("types", getTypes());
      model.addAttribute("statuses", getStatuses());
      model.addAttribute("restaurants", restaurantService.getAll());
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return "/restaurant/home";
  }

  @RequestMapping(method = RequestMethod.POST, headers = REQ_HEADER_ACCEPT_JSON)
  @ResponseBody
  public Response<Void> save(@ModelAttribute("table") Restaurant restaurant) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@save(restaurant:{})", restaurant);
    }
    final Response<Void> response = new Response();
    try {
      restaurantService.save(restaurant);
    } catch (ServiceException e) {
      LOGGER  .error(e.getMessage(), e);
      response.addErrors(e.getMessage());
    }
    return response;
  }


  @RequestMapping(value = "report", method = RequestMethod.POST)
  public void getReport(@ModelAttribute("criteria") SearchCriteriaDto criteriaDto,
                        HttpServletResponse response) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getReport(criteriaDto:{})", criteriaDto);
    }
    try {
      final List<Restaurant> restaurants = restaurantService.search(criteriaDto);
      if (CollectionUtils.isNotEmpty(restaurants)) {
        reportService.exportReport(response, restaurantService.generateReport(restaurants,
                criteriaDto),
                "Restaurant Report");
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.common.no.result");
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  private List<ComboboxData> getTypes() {
    final List<ComboboxData> cbDataList = new ArrayList<>();
    try {
      for (RestaurantType eventType : restaurantTypeService.getAll()) {
        cbDataList.add(new ComboboxData(eventType.getId(), eventType.getType()));
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("cbDataList:{}", cbDataList);
    }
    return cbDataList;
  }

  private List<ComboboxData> getStatuses() {
    final List<ComboboxData> cbDataList = new ArrayList<>();
    try {
      for (RestaurantStatus eventstatus : restaurantStatusService.getAll()) {
        cbDataList.add(new ComboboxData(eventstatus.getId(), eventstatus.getName()));
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("cbDataList:{}", cbDataList);
    }
    return cbDataList;
  }
}

