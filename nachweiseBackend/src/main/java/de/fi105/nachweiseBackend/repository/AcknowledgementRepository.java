package de.fi105.nachweiseBackend.repository;

import de.fi105.nachweiseBackend.entity.AcknowledgmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcknowledgementRepository extends JpaRepository<AcknowledgmentEntity, Integer> {

    List<AcknowledgmentEntity> findAllByProofId(int proofId);

}
