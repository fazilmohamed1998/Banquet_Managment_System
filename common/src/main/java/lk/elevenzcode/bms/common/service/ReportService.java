package lk.elevenzcode.bms.common.service;

import lk.elevenzcode.bms.common.exception.ServiceException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author HaShaN on 9/8/2019 1:09 PM.
 */
public interface ReportService {
  void exportReport(HttpServletResponse response, JasperPrint jasperPrint, String exportFileName)
    throws ServiceException;

  JasperPrint generateReport(JasperReport jasperReport, List<? extends Object> dataList,
                             Map<String, Object> parameters,
                             String resourceBaseName) throws ServiceException;
}
