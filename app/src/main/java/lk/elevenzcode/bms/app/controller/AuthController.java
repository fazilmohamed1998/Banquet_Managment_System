package lk.elevenzcode.bms.app.controller;

import lk.elevenzcode.bms.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/app/auth")
public class AuthController extends BaseController {
  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @RequestMapping(value = "login", method = RequestMethod.GET)
  public String getSignInPage(@RequestParam(value = "error", required = false) boolean error,
                              @RequestParam(value = "logout", required = false) boolean logout,
                              Model model) {
    if (error) {
      model.addAttribute("error",
        getMessage("err.entity.login.failed.invalid.credentials"));
    } else if (logout) {
      model.addAttribute("msg", "You have successfully logged out.");
    }
    return "forward:/app/entity/login";
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public String signIn(Model model) {
    if (logger.isDebugEnabled()) {
      logger.debug("@sign-in...");
    }
    if (isAuthenticated()) {
      if (logger.isDebugEnabled()) {
        logger.debug("signin");
      }
      return "app/login";
    } else {
      if (logger.isDebugEnabled()) {
        logger.debug("redirect:/app/dashboard");
      }
      return "redirect:/app/dashboard";
    }
  }

  @RequestMapping(value = "logout", method = RequestMethod.GET)
  public String signout(HttpServletRequest request, HttpServletResponse response, Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "app/login";
  }
}
