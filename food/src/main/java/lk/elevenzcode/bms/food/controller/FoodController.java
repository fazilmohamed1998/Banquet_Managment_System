package lk.elevenzcode.bms.food.controller;

import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.common.dto.Response;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import lk.elevenzcode.bms.food.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.food.model.Food;
import lk.elevenzcode.bms.food.security.FoodAuthority;
import lk.elevenzcode.bms.food.service.FoodService;
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
import java.util.List;

import static lk.elevenzcode.bms.common.util.Constant.REQ_HEADER_ACCEPT_JSON;

@Controller
@RequestMapping("/app/food")
@Secured(FoodAuthority.ROLE_FOOD_MGMT)
public class FoodController extends BaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

  @Autowired
  private FoodService foodService;

  @Autowired
  private ReportService reportService;

  @RequestMapping(value = "home", method = RequestMethod.GET)
  public String getFoodHomePage(HttpServletRequest request, Model model) {
    final EntityDetail loggedEntity = getLoggedEntity();
    model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
    return "/food/home";
  }

  @RequestMapping(method = RequestMethod.GET)
  public String getFoodPage(Model model) {
    final EntityDetail loggedEntity = getLoggedEntity();
    model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
    model.addAttribute("food", new Food());
    try {
      model.addAttribute("foods", foodService.getAll());
    } catch (ServiceException e) {
      e.printStackTrace();
    }
    return "/food/food";
  }

  /*when user submit the food add form all form values will comes here and bind to food object,
   so then we just pass the food object to the service which will modify/persist food object to database*/
  @RequestMapping(method = RequestMethod.POST, headers = REQ_HEADER_ACCEPT_JSON)
  @ResponseBody
  public Response<Void> save(@ModelAttribute("food") Food food) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@save(food:{})", food);
    }
    final Response<Void> response = new Response();
    try {
      foodService.save(food);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
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
      final List<Food> foods = foodService.search(criteriaDto);
      if (CollectionUtils.isNotEmpty(foods)) {
        reportService.exportReport(response, foodService.generateReport(foods,
          criteriaDto), "Food Report");
      } else {
        throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.common.no.result");
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  @ResponseBody
  public Response<Food> getFood(@PathVariable(value = "id") int id) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getFood(id:{})", id);
    }
    final Response<Food> response = new Response();
    try {
      response.setResult(foodService.get(id));
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response.addErrors(getMessage(e.getMessage()));
      } else {
        response.addErrors(e.getMessage());
      }
    }
    return response;
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public Response<Void> deleteFood(@PathVariable(value = "id") int id) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@deleteFood(id:{})", id);
    }
    final Response<Void> response = new Response();
    try {
      foodService.delete(foodService.get(id));
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response.addErrors(getMessage(e.getMessage()));
      } else {
        response.addErrors(e.getMessage());
      }
    }
    return response;
  }
}