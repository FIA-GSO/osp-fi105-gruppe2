package de.fi105.nachweiseBackend.controller;

import com.auth0.jwt.interfaces.JWTVerifier;
import de.fi105.nachweiseBackend.api.ApprenticeApiDelegate;
import de.fi105.nachweiseBackend.model.ProofPreviewInner;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.repository.PersonRepository;
import de.fi105.nachweiseBackend.service.ApprenticeshipService;
import de.fi105.nachweiseBackend.service.AuthorizationService;
import de.fi105.nachweiseBackend.service.PreviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApprenticeController extends LoggedInController implements ApprenticeApiDelegate {

    private final ApprenticeshipService apprenticeshipService;
    private final PreviewService previewService;
    private final PersonRepository personRepository;

    public ApprenticeController(JWTVerifier jwtVerifier, AuthorizationService authorizationService, ApprenticeshipService apprenticeshipService, PreviewService previewService,
                                PersonRepository personRepository) {
        super(jwtVerifier, authorizationService);
        this.apprenticeshipService = apprenticeshipService;
        this.previewService = previewService;
        this.personRepository = personRepository;
    }

    @Override
    public ResponseEntity<List<String>> findUsers(String JSESSIONID) {
        int userId = verifyUser(JSESSIONID);
        return ResponseEntity.ok(apprenticeshipService.getApprentices(userId));
    }

    @Override
    public ResponseEntity<List<ProofPreviewInner>> getForUser(String userName, String JSESSIONID) {
        int userId = verifyUser(JSESSIONID);
        if (authorizationService.hasRole(userId, UserCreate.RoleEnum.AUSBILDER)) {
            List<String> apprentices = apprenticeshipService.getApprentices(userId);
            if (apprentices.contains(userName)) {
                var id = personRepository.findByUsername(userName).orElseThrow().getId();
                return ResponseEntity.ok(previewService.getAcceptedPreview(id));
            }
        } else  if (authorizationService.hasRole(userId, UserCreate.RoleEnum.PR_FER_LEHRER)) {
            return ResponseEntity.ok(previewService.getAcceptedPreview(userId));
        }
        return ResponseEntity.status(400).body(null);
    }

    @Override
    public ResponseEntity<List<ProofPreviewInner>> getRequestedForUser(String userName, String JSESSIONID) {
        List<String> apprentices = apprenticeshipService.getApprentices(verifyUser(JSESSIONID));
        var id = personRepository.findByUsername(userName).orElseThrow().getId();
        if (apprentices.contains(userName)) {
            return ResponseEntity.ok(previewService.getRequestedPreview(id));
        }
        return ResponseEntity.status(400).body(null);
    }
}
