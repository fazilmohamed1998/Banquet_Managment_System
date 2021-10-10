package lk.elevenzcode.bms.customer.controller;

import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.common.dto.Response;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.customer.model.Customer;
import lk.elevenzcode.bms.customer.security.CustomerAuthority;
import lk.elevenzcode.bms.customer.service.CustomerService;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
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

import javax.servlet.http.HttpServletRequest;

import static lk.elevenzcode.bms.common.util.Constant.REQ_HEADER_ACCEPT_JSON;

@Controller
@RequestMapping("/app/customer")
@Secured(CustomerAuthority.ROLE_CUSTOMER_MGMT)
public class CustomerController extends BaseController {
  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerService customerService;

  @RequestMapping(value = "home", method = RequestMethod.GET)
  public String getCustomerHomePage(HttpServletRequest request, Model model) {
    final EntityDetail loggedEntity = getLoggedEntity();
    model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
    return "/customer/home";
  }

  @RequestMapping(method = RequestMethod.POST, headers = REQ_HEADER_ACCEPT_JSON)
  @ResponseBody
  public Response<Void> save(@ModelAttribute("user") Customer customer) {
    if (logger.isDebugEnabled()) {
      logger.debug("@save(customer:{})", customer);
    }
    final Response<Void> response = new Response();
    try {
      customerService.save(customer);
    } catch (ServiceException e) {
      logger.error(e.getMessage(), e);
      response.addErrors(e.getMessage());
    }
    return response;
  }
}
