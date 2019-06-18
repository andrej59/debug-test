package ru.ajana.debugtest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Физическое лицо (ФЛ).
 *
 * @author Andrey Kharintsev on 08.06.2019
 */
public class Person {

  /**
   * Идентификатор.
   */
  private Long id;
  /**
   * Фамилия.
   */
  private String lastName;
  /**
   * Имя.
   */
  private String firstName;
  /**
   * Отчество.
   */
  private String middleName;
  /**
   * Дата рождения.
   */
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate birthDate;
  /**
   * Адрес электронной почты.
   */
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id) &&
        Objects.equals(lastName, person.lastName) &&
        Objects.equals(firstName, person.firstName) &&
        Objects.equals(middleName, person.middleName) &&
        Objects.equals(birthDate, person.birthDate) &&
        Objects.equals(email, person.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
