package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.entity.AcknowledgmentEntity;
import de.fi105.nachweiseBackend.model.Acknowledgement;
import de.fi105.nachweiseBackend.model.AcknowledgementList;
import de.fi105.nachweiseBackend.repository.AcknowledgementRepository;
import de.fi105.nachweiseBackend.repository.ProofRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class AcknowledgementService {

    private final AcknowledgementRepository acknowledgementRepository;
    private final ProofRepository proofRepository;

    @Transactional
    public Acknowledgement addAcknowledgement(Acknowledgement acknowledgement, int proofId) {
        var proof = proofRepository.getReferenceById(proofId);
        proof.setRequested(acknowledgement.getAcknowledged());
        proof.setReviewed(true);
        acknowledgementRepository.save(new AcknowledgmentEntity()
                        .setProofId(proofId)
                .setComment(acknowledgement.getMessage()));
        proofRepository.save(proof);
        return acknowledgement;
    }

    public AcknowledgementList getAcknowledgementsForProof(int proofId) {
        AcknowledgementList ackList = null;
        var ack = acknowledgementRepository.findAllByProofId(proofId);
        var proof = proofRepository.getReferenceById(proofId);
        if (!ack.isEmpty()) {
            ackList = new AcknowledgementList();
            for (var x : ack) {
                ackList.getMessages().add(x.getComment());
            }
            ackList.acknowledged(proof.isReviewed());
        }
        return ackList;
    }

}
