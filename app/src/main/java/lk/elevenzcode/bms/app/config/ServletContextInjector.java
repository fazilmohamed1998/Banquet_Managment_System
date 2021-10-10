package lk.elevenzcode.bms.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @author HaShaN on 8/14/2019 1:08 PM.
 */
@Component
public class ServletContextInjector implements ServletContextAware, InitializingBean {
  private static final Logger LOGGER = LoggerFactory.getLogger(ServletContextInjector.class);
  private ServletContext servletContext;

  @Value("${enable.html.compression}")
  private boolean isHtmlCompressionEnabled;

  public void setServletContext(ServletContext sc) {
    this.servletContext = sc;
  }

  public void afterPropertiesSet() {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.info("isHtmlCompressionEnabled - {}", isHtmlCompressionEnabled);
    }
    servletContext.setAttribute("isHtmlCompressionEnabled", isHtmlCompressionEnabled);
  }
}
