package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.entity.ProofEntity;
import de.fi105.nachweiseBackend.model.AcknowledgementList;
import de.fi105.nachweiseBackend.model.ProofPreviewInner;
import de.fi105.nachweiseBackend.repository.ProofRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PreviewService {

    private final ProofRepository proofRepository;
    private final ApprenticeshipService apprenticeshipService;

    public List<ProofPreviewInner> getPreview(int userId) {
        var apprenticeship = apprenticeshipService.getActiveApprenticeShip(userId, LocalDate.now())
                .orElseThrow(RuntimeException::new).getId();
        return createPreview(proofRepository.findAllByApprenticeshipId(apprenticeship));
    }

    public List<ProofPreviewInner> getRequestedPreview(int userId) {
        var apprenticeship = apprenticeshipService.getActiveApprenticeShip(userId, LocalDate.now())
                .orElseThrow(RuntimeException::new).getId();
        return createPreview(
                proofRepository.findAllByApprenticeshipId(apprenticeship).stream()
                        .filter(e -> !e.isReviewed())
                        .filter(ProofEntity::isRequested).toList()
        );
    }

    public List<ProofPreviewInner> getAcceptedPreview(int userId) {
        var apprenticeship = apprenticeshipService.getActiveApprenticeShip(userId, LocalDate.now())
                .orElseThrow(RuntimeException::new).getId();
        return createPreview(
                proofRepository.findAllByApprenticeshipId(apprenticeship).stream()
                        .filter(ProofEntity::isRequested)
                        .filter(ProofEntity::isReviewed).toList()
        );
    }

    private List<ProofPreviewInner> createPreview(List<ProofEntity> entities) {
        var output = new ArrayList<ProofPreviewInner>();
        for (var entity : entities) {
            output.add(new ProofPreviewInner()
                    .status(getStatus(entity))
                    .weekStartDate(String.valueOf(entity.getWeekStartDate()))
                    .id(entity.getId()));
        }
        return output;
    }

    public ProofPreviewInner.StatusEnum getStatus(ProofEntity entity) {
        if (entity.isReviewed()) {
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
