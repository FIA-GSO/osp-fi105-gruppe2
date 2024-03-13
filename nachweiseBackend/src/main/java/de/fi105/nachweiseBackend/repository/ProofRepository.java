package de.fi105.nachweiseBackend.repository;

import de.fi105.nachweiseBackend.entity.ProofEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProofRepository extends JpaRepository<ProofEntity, Integer> {

    List<ProofEntity> findAllByApprenticeshipId(int apprenticeshipId);

}
