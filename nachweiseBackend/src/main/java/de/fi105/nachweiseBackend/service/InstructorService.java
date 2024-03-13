package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.repository.ApprenticeshipRepository;
import de.fi105.nachweiseBackend.repository.ProofRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InstructorService {

    private final AuthorizationService authorizationService;
    private final ProofRepository proofRepository;
    private final ApprenticeshipRepository apprenticeshipRepository;
    private final ApprenticeshipService apprenticeshipService;

    public InstructorService(AuthorizationService authorizationService, ProofRepository proofRepository, ApprenticeshipRepository apprenticeshipRepository, ApprenticeshipService apprenticeshipService) {
        this.authorizationService = authorizationService;
        this.proofRepository = proofRepository;
        this.apprenticeshipRepository = apprenticeshipRepository;
        this.apprenticeshipService = apprenticeshipService;
    }

    public boolean canAcknowledge(int proofId, int userId) {
        if (!authorizationService.hasRole(userId, UserCreate.RoleEnum.AUSBILDER)) {
            return false;
        }
        var entity = proofRepository.getReferenceById(proofId);
        var apprenticeShip = apprenticeshipRepository.getReferenceById(entity.getApprenticeshipId());
        if (apprenticeShip.getInstructorId() != userId) {
            return false;
        }
        return entity.isRequested() && !entity.isReviewed();
    }

}
