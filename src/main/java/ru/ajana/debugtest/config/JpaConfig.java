package ru.ajana.debugtest.config;

import java.util.Collections;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * Конфигурация JPA.
 *
 * @author Andrey Kharintsev on 08.06.2019
 */
@Configuration
@EntityScan(basePackages = {"ru.ajana.debugtest.model.entity"})
@EnableJpaRepositories(basePackages = {"ru.ajana.debugtest.repository"})
public class JpaConfig extends JpaBaseConfiguration {

  private static Logger LOG = LoggerFactory.getLogger(JpaConfig.class);

  /**
   * Конструктор конфигурации JPA.
   * @param dataSource источник данныз
   * @param properties свойства
   * @param jtaTransactionManager провайдер TransactionManager
   * @param transactionManagerCustomizers настроенный провайдер TransactionManager
   */
  public JpaConfig(DataSource dataSource,
      JpaProperties properties,
      ObjectProvider<JtaTransactionManager> jtaTransactionManager,
      ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
    super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
    LOG.info("JpaProperties: {}", properties);
  }

  @Override
  protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
    return new EclipseLinkJpaVendorAdapter();
  }

  @Override
  protected Map<String, Object> getVendorProperties() {
    return Collections.singletonMap("eclipselink.weaving", "false");
  }
}
