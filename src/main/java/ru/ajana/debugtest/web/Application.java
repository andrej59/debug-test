package ru.ajana.debugtest.web;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;

/**
 * Точка входа для запуска приложения.
 *
 * @author Andrey Kharintsev on 08.06.2019
 */
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class}, scanBasePackages = {
    "ru.ajana.debugtest"})
public class Application extends SpringBootServletInitializer {

  /**
   * Файл конфигурации для переобпреления настроек по умолчанию
   */
  private static final String APP_PROPERTIES = "debugtest.properties";
  /**
   * Файл конфигурации подключения к базе данных.
   */
  private static final String DATASOURCE_PROPERTIES = "datasource.properties";


  private static PropertySourcesPlaceholderConfigurer configurer;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application;
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigurer()
      throws IOException {
    configurer = new PropertySourcesPlaceholderConfigurer();
    configurer.setFileEncoding("UTF-8");
    configurer.setLocalOverride(true);
    configurer.setIgnoreResourceNotFound(true);
    // application.properties включается по умолчанию
    Resource cpAppConfig = new ClassPathResource(APP_PROPERTIES);
    Resource cpDatasourceConfig = new ClassPathResource(DATASOURCE_PROPERTIES);
    Resource appConfig = new FileUrlResource("config/" + APP_PROPERTIES);
    Resource datasourceConfig = new FileUrlResource("config/" + DATASOURCE_PROPERTIES);
    configurer.setLocations(cpAppConfig, cpDatasourceConfig, appConfig, datasourceConfig);
    return configurer;
  }
}
