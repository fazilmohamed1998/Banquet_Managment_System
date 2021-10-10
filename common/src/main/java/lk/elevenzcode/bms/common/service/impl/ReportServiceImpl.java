package lk.elevenzcode.bms.common.service.impl;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author HaShaN on 9/8/2019 1:09 PM.
 */
@Service
public class ReportServiceImpl implements ReportService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
  private static final Locale LOCALE = LocaleContextHolder.getLocale();
  private static final String REPORT_TEMPLATE_DIR = "report/";
  private static JasperPrint jasperPrint;

  @Override
  public void exportReport(HttpServletResponse response, JasperPrint jasperPrint, String exportFileName) throws ServiceException {
    try {
      response.setContentType("application/pdf");
      response.setHeader("Content-Disposition", String.format(StringUtils.repeat("%s", 3),
        "attachment;filename=", exportFileName, ".pdf"));
      JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    } catch (JRException | IOException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e);
    }
  }

  @Override
  public JasperPrint generateReport(JasperReport jasperReport, List<? extends Object> dataList,
                                    Map<String, Object> parameters, String resourceBaseName) throws ServiceException {
    try {
      if (jasperReport == null) {
        return null;
      }

      if (parameters == null) {
        parameters = new HashMap<>();
      }

      LOGGER.debug("locale.getCountry() : {} | locale.getLanguage() : {}", LOCALE.getCountry(),
        LOCALE.getLanguage());
      ResourceBundle resourceBundle = ResourceBundle.getBundle(resourceBaseName, LOCALE);
      parameters.put("REPORT_RESOURCE_BUNDLE", resourceBundle);
      parameters.put("REPORT_LOCALE", LOCALE);

      JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
      jasperReportsContext.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
      jasperReportsContext.setProperty("net.sf.jasperreports.default.font.name", "Arial");

      JRDataSource jrBSource;
      if (CollectionUtils.isNotEmpty(dataList)) {
        jrBSource = new JRBeanCollectionDataSource(dataList);
      } else {
        jrBSource = new JREmptyDataSource();
      }
      jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBSource);

      return jasperPrint;
    } catch (JRException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e);
    }
  }
}
