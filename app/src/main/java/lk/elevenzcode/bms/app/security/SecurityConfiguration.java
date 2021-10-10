package lk.elevenzcode.bms.app.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

  @Autowired
  @Qualifier("userDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/app/auth/login")
            .permitAll().antMatchers("/app/**")
            .hasRole(AppAuthority.MODULE_PREFIX).and()
            .formLogin()
            .loginPage("/app/auth/login")
            .failureUrl("/app/auth/login?error=true")
            .loginProcessingUrl("/app/auth/login")
            .defaultSuccessUrl("/app/dashboard", true)
            .usernameParameter("username")
            .passwordParameter("password").and().csrf().disable()
            .logout()
            .logoutUrl("/app/auth/logout")
      .logoutSuccessUrl("/app/auth/login?logout=true")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true).clearAuthentication(true).and()
            .exceptionHandling().accessDeniedPage("/app/403").and().sessionManagement()
            .invalidSessionUrl("/app/auth/login");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean(name = "authResolver")
  public AuthenticationTrustResolver getAuthenticationTrustResolver() {
    return new AuthenticationTrustResolverImpl();
  }
}
