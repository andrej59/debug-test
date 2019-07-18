package ru.ajana.debugtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ajana.debugtest.model.entity.PersonEntity;

/**
 * Репозиторий физического лица.
 *
 * @author Andrey Kharintsev on 08.06.2019
 */
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}
