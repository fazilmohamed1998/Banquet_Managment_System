package lk.elevenzcode.bms.user.controller;

import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.common.dto.ComboboxData;
import lk.elevenzcode.bms.common.dto.Response;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.entity.model.EntityStatus;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import lk.elevenzcode.bms.entity.service.EntityStatusService;
import lk.elevenzcode.bms.user.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.user.model.User;
import lk.elevenzcode.bms.user.model.UserRole;
import lk.elevenzcode.bms.user.security.UserAuthority;
import lk.elevenzcode.bms.user.service.UserRoleService;
import lk.elevenzcode.bms.user.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static lk.elevenzcode.bms.common.util.Constant.REQ_HEADER_ACCEPT_JSON;

@Controller
@RequestMapping("/app/user")
@Secured(UserAuthority.ROLE_USER_MGMT)
public class UserController extends BaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private UserRoleService userRoleService;

  @Autowired
  private EntityStatusService entityStatusService;

  @Autowired
  private ReportService reportService;

  @RequestMapping(method = RequestMethod.GET)
  public String getUser() {
    return "user/user";
  }

  @RequestMapping(value = "home", method = RequestMethod.GET)
  public String getUserHomePage(Model model) {
    final EntityDetail loggedEntity = getLoggedEntity();
    model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
    model.addAttribute("user", new User());
    try {
      model.addAttribute("roles", getRoles());
      model.addAttribute("statuses", getStatuses());
      model.addAttribute("users", userService.getAll());
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return "/user/home";
  }

  @RequestMapping(method = RequestMethod.POST, headers = REQ_HEADER_ACCEPT_JSON)
  @ResponseBody
  public Response<Void> save(@ModelAttribute("user") User user) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@save(user:{})", user);
    }
    final Response<Void> response = new Response();
    try {
      userService.save(user);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      response.addErrors(e.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  @ResponseBody
  public Response<User> getUser(@PathVariable(value = "id") int id) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getUser(id:{})", id);
    }
    final Response<User> response = new Response();
    try {
      response.setResult(userService.get(id));
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
  public Response<Void> deleteUser(@PathVariable(value = "id") int id) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@deleteUser(id:{})", id);
    }
    final Response<Void> response = new Response();
    try {
      userService.delete(userService.get(id));
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

  @RequestMapping(value = "report", method = RequestMethod.GET)
  public void getReport(@RequestParam(value = "roleId", required = false) Integer roleId,
                        @RequestParam(value = "statusId", required = false) Integer statusId,
                        HttpServletResponse response) {
    final SearchCriteriaDto criteriaDto = new SearchCriteriaDto(roleId, statusId);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getReport(criteriaDto:{})", criteriaDto);
    }
    try {
      final List<User> users = userService.search(criteriaDto);
      reportService.exportReport(response, userService.generateReport(users,
        criteriaDto), "User Report");
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  private List<ComboboxData> getRoles() {
    final List<ComboboxData> cbDataList = new ArrayList<>();
    try {
      for (UserRole userRole : userRoleService.getAll()) {
        cbDataList.add(new ComboboxData(userRole.getId(), userRole.getName()));
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
      for (EntityStatus entityStatus : entityStatusService.getAll()) {
        cbDataList.add(new ComboboxData(entityStatus.getId(), entityStatus.getName()));
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
