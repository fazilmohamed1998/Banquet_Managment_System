package lk.elevenzcode.bms.common.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @author HaShaN on 10/1/2019 12:19 AM.
 */
public class JasperUtil {
  private static final String REPORT_TEMPLATE_DIR = "/report/";

  private static final Logger LOGGER = LoggerFactory.getLogger(JasperUtil.class);

  public static JasperReport getTemplate(String template, Class aClass) {
    JasperReport jasperReport = null;
    try {
      LOGGER.debug("class:{}", aClass.getName());
      InputStream inputStream = aClass.getResourceAsStream(String.format(StringUtils.repeat("%s", 3),
        REPORT_TEMPLATE_DIR, template.trim(), ".jasper"));
      try {
        jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
      } catch (JRException e) {
        LOGGER.error(e.getMessage(), e);
        LOGGER.debug("no compiled jasper found.. compiling....");
        inputStream = aClass.getResourceAsStream(String.format(StringUtils.repeat("%s", 3),
          REPORT_TEMPLATE_DIR, template.trim(), ".jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        jasperReport = JasperCompileManager.compileReport(jasperDesign);
      }
    } catch (JRException e) {
      e.printStackTrace();
    }
    return jasperReport;
  }
}
