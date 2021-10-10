package lk.elevenzcode.bms.event.controller;

import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.dto.Response;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import lk.elevenzcode.bms.event.model.Event;
import lk.elevenzcode.bms.event.model.EventStatus;
import lk.elevenzcode.bms.event.security.EventAuthority;
import lk.elevenzcode.bms.event.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/app/event")
@Secured(EventAuthority.ROLE_EVENT_MGMT)
public class EventController extends BaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

  @Autowired
  private EventService eventService;

  @RequestMapping(value = "home", method = RequestMethod.GET)
  public ModelAndView home() {
    ModelAndView mav = new ModelAndView("event/home");//c.where to view home jsp
    final EntityDetail loggedEntity = getLoggedEntity();
    mav.addObject("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
    List<Event> listEvent = null;//1.i call list all method eventService
    try {
      listEvent = eventService.getList(EventStatus.STATUS_DELETED);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    mav.addObject("listEvent", listEvent);//1.what to display view
    return mav;//retun the whole
  }

  @RequestMapping("add")
  public String newEventForm(Map<String, Object> model) {//2. after press go to new page
    model.put("event", new Event());
    return "new_event";
  }


  @RequestMapping(value = "save", method = RequestMethod.POST)//2.call to save methode
  @ResponseBody
  public Response<Void> saveEvent(@ModelAttribute("Event") Event event) {//2. data from model
    // attribue
    final Response<Void> response = new Response<>();
    try {
      eventService.insert(event);
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

  @RequestMapping("edit")
  public ModelAndView editEventForm(@RequestParam int id) {//3.call to save methode
    ModelAndView mav = new ModelAndView("event/edit_event");
    Event event = null;
    try {
      event = eventService.get(id);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    mav.addObject("event", event);

    return mav;

  }

  @RequestMapping("delete")//3.call to delete methode
  public String deleteEvent(@RequestParam int id) {

    try {
      eventService.delete(id);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return "redirect:/app/event/home";

  }


  @RequestMapping("search")
  public ModelAndView search(@RequestParam String keyword) {

    ModelAndView mav = new ModelAndView("search");
    final Pageable pageable = new Pageable("ASC", "id", 0, 15);
    List<Event> result = null;
    try {
      result = eventService.getList(pageable, keyword);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    mav.addObject("result", result);

    return mav;
  }

}


