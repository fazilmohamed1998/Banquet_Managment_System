package lk.elevenzcode.bms.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hashan on 3/6/19 11:58 AM
 */
@WebFilter(urlPatterns = {"/app/user"})
public class AjaxTimeoutRedirectFilter implements Filter {
  private static final Logger logger = LoggerFactory.getLogger(AjaxTimeoutRedirectFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void destroy() {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    HttpSession session = httpRequest.getSession(false);
    if (session == null || session.isNew()) {
      httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    } else {
      chain.doFilter(request, response);
    }
  }
}
