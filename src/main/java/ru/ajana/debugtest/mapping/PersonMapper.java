package ru.ajana.debugtest.mapping;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import ru.ajana.debugtest.jpa.entity.PersonEntity;
import ru.ajana.debugtest.model.Person;

/**
 * Маппер физического лица.
 *
 * @author Andrey Kharintsev on 09.06.2019
 */
@Component("personMapper")
public class PersonMapper implements Mapper<PersonEntity, Person> {

  @Override
  public Person mapTo(PersonEntity entity) {
    final Person person = new Person();
    person.setId(entity.getId());
    person.setLastName(entity.getLastName());
    person.setFirstName(entity.getFirstName());
    person.setBirthDate(entity.getBirthDate());
    person.setEmail(entity.getEmail());
    return person;
  }

  @Override
  public PersonEntity mapFrom(Person person) {
    final PersonEntity entity = new PersonEntity();
    entity.setId(person.getId());
    entity.setLastName(person.getLastName());
    entity.setFirstName(person.getFirstName());
    entity.setBirthDate(person.getBirthDate());
    entity.setEmail(person.getEmail());
    entity.setCreateDate(LocalDateTime.now());
    return entity;
  }
}
