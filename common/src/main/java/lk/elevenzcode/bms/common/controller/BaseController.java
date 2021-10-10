package lk.elevenzcode.bms.common.controller;

import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
  @Qualifier("messageSource")
  @Autowired
  protected MessageSource messageSource;
  @Autowired
  @Lazy
  private AuthenticationTrustResolver authenticationTrustResolver;
  private Locale locale = LocaleContextHolder.getLocale();

  /**
   * This method returns true if users is already authenticated [logged-in], else false.
   */
  public boolean isAuthenticated() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authenticationTrustResolver.isAnonymous(authentication);
  }

  public String getMessage(String msg, String... args) {
    return messageSource.getMessage(msg, args, locale);
  }

  public EntityDetail getLoggedEntity() {
    final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return principal != null && principal instanceof EntityDetail ? (EntityDetail) principal : null;
  }

  public boolean hasRole(String role) {
    final EntityDetail loggedEntity = getLoggedEntity();
    if (loggedEntity != null) {
      return loggedEntity.getAuthorities().contains(new SimpleGrantedAuthority(role));
    }
    return false;
  }
}
