package de.fi105.nachweiseBackend.repository;

import de.fi105.nachweiseBackend.dto.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    List<PersonEntity> findAllByUsername(String username);
}
