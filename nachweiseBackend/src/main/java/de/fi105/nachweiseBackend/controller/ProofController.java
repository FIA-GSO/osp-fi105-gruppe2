package de.fi105.nachweiseBackend.controller;

import com.auth0.jwt.interfaces.JWTVerifier;
import de.fi105.nachweiseBackend.api.ProofApiDelegate;
import de.fi105.nachweiseBackend.entity.ApprenticeshipEntity;
import de.fi105.nachweiseBackend.entity.ProofEntity;
import de.fi105.nachweiseBackend.model.*;
import de.fi105.nachweiseBackend.repository.ApprenticeshipRepository;
import de.fi105.nachweiseBackend.repository.ProofRepository;
import de.fi105.nachweiseBackend.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static de.fi105.nachweiseBackend.model.ProofPreviewInner.StatusEnum.*;

@Component
public class ProofController extends LoggedInController implements ProofApiDelegate {

    private final ProofService proofService;
    private final ApprenticeshipService apprenticeshipService;
    private final PreviewService previewService;
    private final AcknowledgementService acknowledgementService;
    private final InstructorService instructorService;
    private final ProofRepository proofRepository;
    private final ApprenticeshipRepository apprenticeshipRepository;

    public ProofController(JWTVerifier jwtVerifier, AuthorizationService authorizationService, ProofService proofService, ApprenticeshipService apprenticeshipService, PreviewService previewService, AcknowledgementService acknowledgementService, InstructorService instructorService,
                           ProofRepository proofRepository,
                           ApprenticeshipRepository apprenticeshipRepository) {
        super(jwtVerifier, authorizationService);
        this.proofService = proofService;
        this.apprenticeshipService = apprenticeshipService;
        this.previewService = previewService;
        this.acknowledgementService = acknowledgementService;
        this.instructorService = instructorService;
        this.proofRepository = proofRepository;
        this.apprenticeshipRepository = apprenticeshipRepository;
    }

    @Override
    public ResponseEntity<ProofGet> postProof(String JSESSIONID, ProofPost proofPost) {
        int userId = verifyUser(JSESSIONID);
        var apprenticeship = apprenticeshipService.getActiveApprenticeShip(userId, LocalDate.now());
        if (apprenticeship.isEmpty()) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(proofService.createProof(proofPost, apprenticeship.get().getId()));
    }

    @Override
    public ResponseEntity<ProofGet> getProof(Integer id, String JSESSIONID) {
        if (canAccess(JSESSIONID, id)) {
            return ResponseEntity.ok(proofService.getProof(id));
        }
        return ResponseEntity.status(400).body(null);
    }

    @Override
    public ResponseEntity<List<ProofPreviewInner>> getPreview(String JSESSIONID) {
        int userId = verifyUser(JSESSIONID);
        return ResponseEntity.ok(previewService.getPreview(userId));
    }

    @Override
    public ResponseEntity<ProofGet> patchProof(Integer id, String JSESSIONID, ProofPatch proofPatch) {
        if (canPatch(JSESSIONID, id)) {
            return ResponseEntity.ok(proofService.patchProof(proofPatch, id));
        }
        return ResponseEntity.status(400).body(null);
    }

    private boolean canPatch(String JSESSIONID, int id) {
        int userId = verifyUser(JSESSIONID);
        ProofEntity proof = proofRepository.getReferenceById(id);
        Integer apId = proof.getApprenticeshipId();
        return previewService.getStatus(proof).equals(SAVED) && apprenticeshipRepository.getReferenceById(apId).getApprenticeId() == userId;
    }

    private boolean canAccess(String JSESSIONID, int id) {
        int userId = verifyUser(JSESSIONID);
        ProofEntity proof = proofRepository.getReferenceById(id);
        Integer apId = proof.getApprenticeshipId();
        ApprenticeshipEntity entity = apprenticeshipRepository.getReferenceById(apId);
        ProofPreviewInner.StatusEnum status = previewService.getStatus(proof);
        return entity.getApprenticeId() == userId
                || ((status.equals(ACCEPTED) || status.equals(REQUESTED) && entity.getInstructorId() == userId)
                || (status.equals(ACCEPTED) && authorizationService.hasRole(userId, UserCreate.RoleEnum.PR_FER_LEHRER)));
    }

    @Override
    public ResponseEntity<Acknowledgement> postAcknowledgement(Integer id, String JSESSIONID, Acknowledgement acknowledgement) {
        if (instructorService.canAcknowledge(id, verifyUser(JSESSIONID))) {
            return ResponseEntity.ok(acknowledgementService.addAcknowledgement(acknowledgement, id));
        }
        return ResponseEntity.status(400).body(null);
    }

    @Override
    public ResponseEntity<AcknowledgementList> getAcknowledgements(Integer id, String JSESSIONID) {
        verifyUser(JSESSIONID);
        return ResponseEntity.ok(acknowledgementService.getAcknowledgementsForProof(id));
    }

}
