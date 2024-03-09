package de.fi105.nachweiseBackend.repository;

import de.fi105.nachweiseBackend.entity.EntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<EntryEntity, Integer> {
}
