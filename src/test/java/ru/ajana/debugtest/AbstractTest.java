package ru.ajana.debugtest;

import java.time.LocalDate;
import ru.ajana.debugtest.model.Person;

/**
 * Обстрактный класс теста.
 *
 * @author Andrey Kharintsev on 09.06.2019
 */
public abstract class AbstractTest {

  /**
   * Создаёт эталонный объект ФЛ для тестирования.
   *
   * @return объект ФЛ
   */
  protected Person createPerson() {
    Person person = new Person();
    person.setId(123L);
    person.setLastName("Тестов");
    person.setFirstName("Иван");
    person.setMiddleName("Петрович");
    person.setEmail("test@mail.ru");
    person.setBirthDate(LocalDate.of(1980, 01, 01));
    return person;
  }
}
