package ru.ajana.debugtest.jpa.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность физического лица.
 *
 * @author Andrey Kharintsev on 08.06.2019
 */

@Entity
@Table(name = "person")
public class PersonEntity {

  /**
   * Идентификатор.
   */
  @Id
  private Long id;
  /**
   * Фамилия.
   */
  @Column(name = "last_name")
  private String lastName;
  /**
   * Имя.
   */
  @Column(name = "first_name")
  private String firstName;
  /**
   * Отчество.
   */
  @Column(name = "middle_name")
  private String middleName;
  /**
   * Дата рождения.
   */
  @Column(name = "birth_date")
  private LocalDate birthDate;
  /**
   * Адрес электронной почты.
   */
  @Column(name = "email")
  private String email;

  /**
   * Дата и время создания.
   */
  @Column(name = "dt_create")
  private LocalDateTime createDate;

  /**
   * Дата и время обновления.
   */
  @Column(name = "dt_update")
  private LocalDateTime updateDate;


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

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public LocalDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonEntity that = (PersonEntity) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(lastName, that.lastName) &&
        Objects.equals(firstName, that.firstName) &&
        Objects.equals(middleName, that.middleName) &&
        Objects.equals(birthDate, that.birthDate) &&
        Objects.equals(email, that.email) &&
        Objects.equals(createDate, that.createDate) &&
        Objects.equals(updateDate, that.updateDate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id);
  }
}
