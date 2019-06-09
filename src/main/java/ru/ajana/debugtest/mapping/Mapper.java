package ru.ajana.debugtest.mapping;

/**
 * Интерфейс мапперов сущностей.
 *
 * @author Andrey Kharintsev by 09.06.2019
 */
public interface Mapper<S, T> {

  /**
   * Выполняет маппинг сущности базы данных на объект модели приложения.
   *
   * @param entity сущность базы данных
   * @return объект модели
   */
  T mapTo(S entity);

  /**
   * Выполняет маппинг объекта модели на сущность базы данных.
   *
   * @param object объект модели приложения
   * @return сущность базы данных
   */
  S mapFrom(T object);
}

