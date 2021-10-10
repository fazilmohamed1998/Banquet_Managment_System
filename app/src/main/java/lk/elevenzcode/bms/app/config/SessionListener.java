package lk.elevenzcode.bms.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
  private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

  @Override
  public void sessionCreated(HttpSessionEvent event) {
    if (logger.isDebugEnabled()) {
      logger.debug("session initialized....");
    }
    //set inactive interval as 15 min
    event.getSession().setMaxInactiveInterval(15 * 60);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent event) {
    if (logger.isDebugEnabled()) {
      logger.debug("session destroyed....");
    }
  }
}

