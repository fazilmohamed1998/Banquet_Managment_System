package lk.elevenzcode.bms.banquethall.controller;

import static lk.elevenzcode.bms.common.util.Constant.REQ_HEADER_ACCEPT_JSON;

import lk.elevenzcode.bms.banquethall.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.banquethall.model.BanquetHall;
import lk.elevenzcode.bms.banquethall.model.BanquetHallStatus;
import lk.elevenzcode.bms.banquethall.model.BanquetHallType;
import lk.elevenzcode.bms.banquethall.security.BanquetHallAuthority;
import lk.elevenzcode.bms.banquethall.service.BanquetHallService;
import lk.elevenzcode.bms.banquethall.service.BanquetHallStatusService;
import lk.elevenzcode.bms.banquethall.service.BanquetHallTypeService;
import lk.elevenzcode.bms.common.controller.BaseController;
import lk.elevenzcode.bms.common.dto.ComboboxData;
import lk.elevenzcode.bms.common.dto.Response;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/app/banquet-hall")
@Secured(BanquetHallAuthority.ROLE_BANQUET_HALL_MGMT)
public class BanquetHallController extends BaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(BanquetHallController.class);

  @Autowired
  private BanquetHallService banquetHallService;

  @Autowired
  private BanquetHallTypeService banquetHallTypeService;

  @Autowired
  private BanquetHallStatusService banquetHallStatusService;

  @Autowired
  private ReportService reportService;

  @RequestMapping(value = "home", method = RequestMethod.GET)
  public String getBanquetHallHomePage(HttpServletRequest request, Model model) {
    final EntityDetail loggedEntity = getLoggedEntity();
    model.addAttribute("loggedEntity", loggedEntity != null ? loggedEntity.getName() : "<No Name>");
    model.addAttribute("banquetHall", new BanquetHall());
    try {
      model.addAttribute("types", getTypes());
      model.addAttribute("statuses", getStatuses());
      model.addAttribute("banquetHalls", banquetHallService.getAll());
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return "/banquethall/home";
  }

  @RequestMapping(method = RequestMethod.POST, headers = REQ_HEADER_ACCEPT_JSON)
  @ResponseBody
  public Response<Void> save(@ModelAttribute("banquetHall") BanquetHall banquetHall) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@save(banquetHall:{})", banquetHall);
    }
    final Response<Void> response = new Response();
    try {
      banquetHallService.save(banquetHall);
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      response.addErrors(e.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "report", method = RequestMethod.GET)
  public void getReport(@RequestParam(value = "typeId", required = false) Integer typeId,
                        @RequestParam(value = "statusId", required = false) Integer statusId,
                        HttpServletResponse response) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("@getReport(typeId:{}, statusId:{})", typeId,statusId);
    }
    try {
      SearchCriteriaDto criteriaDto = new SearchCriteriaDto(typeId, statusId);
      final List<BanquetHall> banquetHalls = banquetHallService.search(criteriaDto);
      if (CollectionUtils.isNotEmpty(banquetHalls)) {
        reportService.exportReport(response, banquetHallService.generateReport(banquetHalls,
            criteriaDto),
            "Banquet Hall Report");
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
      for (BanquetHallType eventType : banquetHallTypeService.getAll()) {
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
      for (BanquetHallStatus eventstatus : banquetHallStatusService.getAll()) {
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
