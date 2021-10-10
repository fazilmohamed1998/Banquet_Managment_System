package lk.elevenzcode.bms.app.controller;

import lk.elevenzcode.bms.app.security.AppAuthority;
import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController extends BaseController {
  private static final Logger logger = LoggerFactory.getLogger(MainController.class);

  @Value("${test.property}")
  private String testProperty;

  @RequestMapping(value = {"", "/", "/app"}, method = RequestMethod.GET)
  public String root() {
    return "redirect:/app/dashboard";
  }

  @RequestMapping(value = "/app/dashboard", method = RequestMethod.GET)
  @Secured(AppAuthority.ROLE_APP)
  public String getDashboard(HttpServletRequest request, Model model) {
    final EntityDetail loggedEntity = getLoggedEntity();
    model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");

    if (logger.isDebugEnabled()) {
      logger.debug("testProperty -> {}", testProperty);
    }
    return "/customer/home";
  }

  // for 403 access denied page
  @RequestMapping(value = "/app/403", method = RequestMethod.GET)
  public String accesssDenied(Model model) {
    // check if user is login
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (!(auth instanceof AnonymousAuthenticationToken)) {
      UserDetails userDetail = (UserDetails) auth.getPrincipal();
      model.addAttribute("username", userDetail.getUsername());
    }
    return "403";
  }
}
