package de.fi105.nachweiseBackend.repository;

import de.fi105.nachweiseBackend.entity.ApprenticeshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenticeshipRepository extends JpaRepository<ApprenticeshipEntity, Integer> {
}
