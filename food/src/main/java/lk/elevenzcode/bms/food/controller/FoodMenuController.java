package lk.elevenzcode.bms.food.controller;

import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.common.dto.Response;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import lk.elevenzcode.bms.food.model.FoodMenu;
import lk.elevenzcode.bms.food.security.FoodAuthority;
import lk.elevenzcode.bms.food.service.FoodMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static lk.elevenzcode.bms.common.util.Constant.REQ_HEADER_ACCEPT_JSON;

@Controller
@RequestMapping("/app/food/menu")
@Secured(FoodAuthority.ROLE_FOOD_MENU_MGMT)
public class FoodMenuController extends BaseController {
  private static final Logger logger = LoggerFactory.getLogger(FoodMenuController.class);

  @Autowired
  private FoodMenuService foodMenuService;

  @RequestMapping(method = RequestMethod.GET)
  public String getFoodMenuPage(Model model) {
    final EntityDetail loggedEntity = getLoggedEntity();
    model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
    model.addAttribute("foodMenu", new FoodMenu());
    return "/food/food_menu";
  }

  /*when user submit the foodmenu add form all form values will comes here and bind to food object,
   so then we just pass the foodmenu object to the service which will modify/persist food object to database*/
  @RequestMapping(method = RequestMethod.POST, headers = REQ_HEADER_ACCEPT_JSON)
  @ResponseBody
  public Response<Void> save(@ModelAttribute("foodmenu") FoodMenu foodMenu) {
    if (logger.isDebugEnabled()) {
      logger.debug("@save(foodmenu:{})", foodMenu);
    }
    final Response<Void> response = new Response();
    try {
      foodMenuService.save(foodMenu);
    } catch (ServiceException e) {
      logger.error(e.getMessage(), e);
      response.addErrors(e.getMessage());
    }
    return response;
  }

}
