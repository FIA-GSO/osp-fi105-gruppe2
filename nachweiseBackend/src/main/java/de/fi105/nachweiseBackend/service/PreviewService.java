package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.entity.ProofEntity;
import de.fi105.nachweiseBackend.model.AcknowledgementList;
import de.fi105.nachweiseBackend.model.ProofPreviewInner;
import de.fi105.nachweiseBackend.repository.ProofRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PreviewService {

    private final ProofRepository proofRepository;
    private final AcknowledgementService acknowledgementService;

    public List<ProofPreviewInner> getPreview(int userId) {
        var entities = proofRepository.findAllByApprenticeId(userId);
        var output = new ArrayList<ProofPreviewInner>();
        for (var entity : entities) {
            AcknowledgementList ack = acknowledgementService.getAcknowledgementsForProof(entity.getId());

            output.add(new ProofPreviewInner()
                    .status(getStatus(entity, ack))
                    .weekStartDate(String.valueOf(entity.getWeekStartDate()))
                    .id(entity.getId()));
        }
        return output;
    }

    private ProofPreviewInner.StatusEnum getStatus(ProofEntity entity, AcknowledgementList acknowledgementList) {
        if (acknowledgementList.getAcknowledged()) {
            if (!entity.isRequested()) {
                return ProofPreviewInner.StatusEnum.REJECTED;
            }
            return ProofPreviewInner.StatusEnum.ACCEPTED;
        }

        if (entity.isRequested()) {
            return ProofPreviewInner.StatusEnum.REQUESTED;
        }

        return ProofPreviewInner.StatusEnum.SAVED;

    }



}
