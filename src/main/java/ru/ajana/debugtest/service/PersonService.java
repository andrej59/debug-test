package ru.ajana.debugtest.service;

import java.util.List;
import ru.ajana.debugtest.model.dto.Person;

/**
 * Интерфейс сервис для работы с физическим лицом (ФЛ).
 *
 * @author Andrey Kharintsev on 08.06.2019
 */
public interface PersonService {

  /**
   * Возвращает ФЛ по идентификатору.
   */
  Person getPerson(Long id);

  /**
   * Возвращает список всех ФЛ.
   */
  List<Person> getAllPersons();

  /**
   * Создаёт нового ФЛ.
   *
   * @param person объект ФЛ.
   * @return объект ФЛ.
   */
  Person create(Person person);

  /**
   * Обновляет ФЛ.
   *
   * @param person объект ФЛ.
   * @return объект ФЛ.
   */
  Person update(Person person);

  /**
   * Удаляет ФЛ.
   */
  void delete(Person person);

  /**
   * Удаляет ФЛ по идентификатору.
   *
   * @param id идентификатор
   */
  void deleteById(Long id);
}
