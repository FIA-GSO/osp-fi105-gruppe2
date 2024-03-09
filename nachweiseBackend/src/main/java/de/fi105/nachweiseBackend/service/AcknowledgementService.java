package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.entity.AcknowledgmentEntity;
import de.fi105.nachweiseBackend.model.Acknowledgement;
import de.fi105.nachweiseBackend.model.AcknowledgementList;
import de.fi105.nachweiseBackend.repository.AcknowledgementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class AcknowledgementService {

    private final AcknowledgementRepository acknowledgementRepository;

    @Transactional
    public Acknowledgement addAcknowledgement(Acknowledgement acknowledgement, int proofId) {
        acknowledgementRepository.save(new AcknowledgmentEntity()
                        .setProofId(proofId)
                        .setAcknowlegded(acknowledgement.getAcknowledged()))
                .setComment(acknowledgement.getMessage());
        return acknowledgement;
    }

    public AcknowledgementList getAcknowledgementsForProof(int proofId) {
        AcknowledgementList ackList = null;
        var ack = acknowledgementRepository.findAllByProofId(proofId);
        if (!ack.isEmpty()) {
            ackList = new AcknowledgementList();
            for (var x : ack) {
                ackList.getMessages().add(x.getComment());
            }
            ackList.acknowledged(ack.get(ack.size() -1).isAcknowlegded());
        }
        return ackList;
    }

}
