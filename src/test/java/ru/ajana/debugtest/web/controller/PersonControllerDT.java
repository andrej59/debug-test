package ru.ajana.debugtest.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ajana.debugtest.DebugTest;
import ru.ajana.debugtest.model.Person;

/**
 * Тест отладки контроллера физического лица.
 *
 * @author Andrey Kharintsev on 09.06.2019
 */
public class PersonControllerDT extends DebugTest {

  @Test
  public void testCreatePerson() {
    ParameterizedTypeReference refType = new ParameterizedTypeReference<Person>() {
    };
    // Создаём объект ФЛ
    Person person = createPerson();

    // Отправляем запрос на сервис
    ResponseEntity<Person> response = post("/persons", person, refType);

    // Получили ответ
    Person newPerson = response.getBody();

    // Делаем проверку
    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    assertTrue(newPerson.getId() > 0);
  }


  /**
   * Создаёт эталонный объект ФЛ для тестирования.
   *
   * @return объект ФЛ
   */
  private Person createPerson() {
    Person person = new Person();
    person.setLastName("Тестов");
    person.setFirstName("Иван");
    //person.setMiddleName("Петрович");
    person.setEmail("test@mail.ru");
    person.setBirthDate(LocalDate.of(1980, 01, 01));
    return person;
  }
}