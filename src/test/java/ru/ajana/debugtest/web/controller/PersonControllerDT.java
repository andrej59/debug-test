package ru.ajana.debugtest.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ajana.debugtest.AbstractDebugTest;
import ru.ajana.debugtest.model.Person;

/**
 * Тест отладки контроллера физического лица.
 *
 * @author Andrey Kharintsev on 09.06.2019
 */
@Slf4j
public class PersonControllerDT extends AbstractDebugTest {

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
    log.info("Физическон лицо успешно создано");
  }

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

    // Делаем проверку
    assertEquals("При создании физического лица произошла ошибка",
        response.getStatusCode(), HttpStatus.CREATED);
    assertTrue(newPerson.getId() > 0);
  }
}