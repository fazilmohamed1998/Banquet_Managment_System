package lk.elevenzcode.bms.entity.controller;

import lk.elevenzcode.bms.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app/entity")
public class EntityController extends BaseController {
  @RequestMapping(value = "login", method = RequestMethod.GET)
  public String getLogin() {
    return "entity/login";
  }
}
