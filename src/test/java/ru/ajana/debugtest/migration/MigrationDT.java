package ru.ajana.debugtest.migration;

import org.flywaydb.core.Flyway;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ajana.debugtest.DebugTest;

/**
 * Отладочный тест для тестирования миграций базы данных.
 *
 * @author Andrey Kharintsev on 09.06.2019
 */
public class MigrationDT extends DebugTest {

  @Autowired
  private Flyway flyway;

  /**
   * Тест для отладки миграций базы данных.
   */
  @Test
  public void testMigration() {
    flyway.migrate();
  }
}
