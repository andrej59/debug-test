package ru.ajana.debugtest.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ajana.debugtest.AbstractDebugTest;
import ru.ajana.debugtest.model.dto.Person;

/**
 * Тест отладки контроллера физического лица.
 *
 * @author Andrey Kharintsev on 09.06.2019
 */

public class PersonControllerDT extends AbstractDebugTest {

  /**
   * Не успешный тест создания физического лица (ФЛ).
   */
  @Test
  public void testInvalidCreatePerson() {

    // Создаём объект ФЛ
    Person person = createPerson();
    // Отчество не указано
    person.setMiddleName(null);
    // Отправляем запрос на сервис
    ResponseEntity<Person> response = post("/persons", person, Person.class);
    // Получили ответ
    Person newPerson = response.getBody();
    LOG.info("result: " + newPerson);
    assertTrue(newPerson.getId() > 0);
  }

  /**
   * Успешный тест создания физического лица (ФЛ).
   */
  @Test
  public void testValidCreatePerson() {
    // Создаём объект ФЛ
    Person person = createPerson();
    // Отправляем запрос на сервис
    ResponseEntity<Person> response = post("/persons", person, Person.class);
    // Получили ответ
    Person newPerson = response.getBody();

    // Делаем проверку
    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    assertTrue(newPerson.getId() > 0);
    LOG.info("Физическон лицо успешно создано");
  }
}