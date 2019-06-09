package ru.ajana.debugtest.web.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ajana.debugtest.model.Person;
import ru.ajana.debugtest.service.PersonService;

/**
 * Контроллер для работы с физическим лицом.
 *
 * @author Andrey Kharintsev on 08.06.2019
 */
@RestController
@RequestMapping(path = "/persons",
    consumes = APPLICATION_JSON_UTF8_VALUE,
    produces = APPLICATION_JSON_UTF8_VALUE)
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity getPerson(@PathVariable Long id) {
    return ResponseEntity.ok(personService.getPerson(id));
  }

  @GetMapping
  public ResponseEntity getAllPersons() {
    return ResponseEntity.ok(personService.getAllPersons());
  }

  @PostMapping
  public ResponseEntity createPerson(@RequestBody Person person) {
    return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
  }

  @PutMapping
  public ResponseEntity updatePerson(@RequestBody Person person) {
    return ResponseEntity.ok(personService.update(person));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deletePersonById(@PathVariable Long id) {
    personService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
