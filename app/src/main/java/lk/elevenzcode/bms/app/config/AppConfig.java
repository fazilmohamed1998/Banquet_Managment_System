package lk.elevenzcode.bms.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import lk.elevenzcode.bms.common.util.JsonUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

@EnableWebMvc
@EnableScheduling
@Configuration
@ComponentScan({"lk.elevenzcode.bms.*"})
@PropertySource("classpath:application.properties")
public class AppConfig extends WebMvcConfigurerAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);


  @Value("${mail.default.from.address}")
  private String defaultAddress;

  @Value("${mail.default.password}")
  private String defaultPassword;

  @Value("${mail.default.host}")
  private String defaultHost;

  @Value("${mail.default.port}")
  private int defaultPort;

  //To resolve ${} in @Value
  @Bean
  public static PropertyPlaceholderConfigurer placeHolderConfigurer() throws IOException {
    final PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
    final Resource[] resources = new PathMatchingResourcePatternResolver()
      .getResources("classpath*:*-module.properties");
    configurer.setLocations(
      ArrayUtils.addAll(resources,
        new ClassPathResource("jdbc.properties"),
        new ClassPathResource("application.properties")
      )
    );
    return configurer;
  }

  @Bean
  public static PropertiesFactoryBean modulePropertyBean() throws IOException {
    final PropertiesFactoryBean propertiesBean = new PropertiesFactoryBean();
    final Resource[] resources = new PathMatchingResourcePatternResolver()
      .getResources("classpath*:*-module.properties");
    propertiesBean.setLocations(ArrayUtils.addAll(
      resources,
      new ClassPathResource("application.properties")
    ));
    return propertiesBean;
  }

  /**
   * Configure ViewResolvers to deliver preferred views.
   */
  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {

    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/view/");
    viewResolver.setSuffix(".jsp");
    registry.viewResolver(viewResolver);
  }

  /**
   * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("/static/");
  }

  /**
   * Configure MessageSource to lookup any validation/error message in internationalized property
   * files
   */
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource =
      new ReloadableResourceBundleMessageSource();
    messageSource.setBasenames("classpath:app-messages",
      "classpath:entity-messages",
      "classpath:banquet-hall-messages",
      "classpath:common-messages",
      "classpath:customer-messages",
      "classpath:event-messages",
      "classpath:food-messages",
      "classpath:reservation-messages",
      "classpath:restaurant-messages",
      "classpath:payment-messages",
      "classpath:user-messages");
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setUseCodeAsDefaultMessage(true);

    return messageSource;
  }

  /**
   * Optional. It's only required when handling '.' in @PathVariables which otherwise ignore
   * everything after last '.' in @PathVaidables argument.
   * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring
   * 4.1.7.
   * This is a workaround for this issue.
   */
  @Override
  public void configurePathMatch(PathMatchConfigurer matcher) {
    matcher.setUseRegisteredSuffixPatternMatch(true);
  }

  /* Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured
  ObjectMapper
   * to the MessageConverter and return it to be added to the HttpMessageConverters of our
   * application*/
  public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
    MappingJackson2HttpMessageConverter messageConverter = new
      MappingJackson2HttpMessageConverter();

    ObjectMapper mapper = new ObjectMapper();
    //Registering Hibernate4Module to support lazy objects
    mapper.registerModule(new Hibernate4Module());
    mapper.setTimeZone(TimeZone.getDefault());

    messageConverter.setObjectMapper(mapper);
    return messageConverter;

  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //Here we add our custom-configured HttpMessageConverter
    converters.add(jacksonMessageConverter());
    super.configureMessageConverters(converters);
  }

  /*Mail Sender*/
  @Bean
  public JavaMailSender getMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    //Using Gmail SMTP configuration.
    mailSender.setHost(defaultHost);
    mailSender.setPort(defaultPort);
    mailSender.setUsername(defaultAddress);
    mailSender.setPassword(defaultPassword);
    try {
      final File file = ResourceUtils.getFile("classpath:mail-config.json");
      final String content = new String(Files.readAllBytes(file.toPath()));
      final Map<String, String> map = new JsonUtil<>(Map.class).readValue(content);
      LOGGER.debug("mailConfigurations:{}", map);
      if (MapUtils.isNotEmpty(map)){
        final Properties javaMailProperties = new Properties();
        for (String key : map.keySet()) {
          javaMailProperties.put(key, map.get(key));
        }
        mailSender.setJavaMailProperties(javaMailProperties);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return mailSender;
  }

  @Bean
  public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
    VelocityEngineFactory factory = new VelocityEngineFactory();
    Properties props = new Properties();
    props.put("resource.loader", "class");
    props.put("class.resource.loader.class",
      "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    factory.setVelocityProperties(props);
    return factory.createVelocityEngine();
  }

  //This will allow file upload
  @Bean(name = "multipartResolver")
  public CommonsMultipartResolver getResolver() {
    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    //Set the maximum allowed size (in bytes) for each individual file.
    resolver.setMaxUploadSizePerFile(10485760);//10MB
    return resolver;
  }
}