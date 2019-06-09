package ru.ajana.debugtest.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ajana.debugtest.jpa.entity.PersonEntity;
import ru.ajana.debugtest.mapping.Mapper;
import ru.ajana.debugtest.model.Person;
import ru.ajana.debugtest.repository.PersonRepository;
import ru.ajana.debugtest.service.PersonService;

/**
 * Реализация сервиса для работы с ФЛ.
 *
 * @author Andrey Kharintsev on 08.06.2019
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {

  /**
   * Маппинг ФЛ.
   */
  private final Mapper<PersonEntity, Person> personMapper;
  /**
   * Репозиторий ФЛ,
   */
  private final PersonRepository personRepository;


  @Autowired
  public PersonServiceImpl(Mapper personMapper, PersonRepository personRepository) {
    this.personMapper = personMapper;
    this.personRepository = personRepository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Person getPerson(Long id) {
    Objects.requireNonNull(id, "The argument id must not be null");
    PersonEntity personEntity = personRepository.findById(id).orElse(null);
    return personMapper.mapTo(personEntity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Person> getAllPersons() {
    return personRepository.findAll().stream().map(personMapper::mapTo)
        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Person create(Person person) {
    Objects.requireNonNull(person, "The argument person must not be null");
    PersonEntity personEntity = personRepository.save(personMapper.mapFrom(person));
    person.setId(personEntity.getId());
    return person;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Person update(Person person) {
    Objects.requireNonNull(person, "The argument person must not be null");
    personRepository.save(personMapper.mapFrom(person));
    return person;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete(Person person) {
    Objects.requireNonNull("The argument person must not be null");
    personRepository.delete(personMapper.mapFrom(person));
  }

  @Override
  public void deleteById(Long id) {
    Objects.requireNonNull(id, "The argument id must not be null");
    personRepository.deleteById(id);
  }
}
