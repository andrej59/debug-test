package ru.ajana.debugtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

/**
 * Точка входа для запуска приложения.
 *
 * @author Andrey Kharintsev on 08.06.2019
 */
@PropertySource(value = {"classpath:datasource.properties"}, ignoreResourceNotFound = true)
@SpringBootApplication(
    exclude = {HibernateJpaAutoConfiguration.class},
    scanBasePackages = {"ru.ajana.debugtest"})
public class Application extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application;
  }
}
