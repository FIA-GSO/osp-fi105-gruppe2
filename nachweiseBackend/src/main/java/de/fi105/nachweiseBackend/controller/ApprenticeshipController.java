package de.fi105.nachweiseBackend.controller;

import com.auth0.jwt.interfaces.JWTVerifier;
import de.fi105.nachweiseBackend.api.ApprenticeshipApiDelegate;
import de.fi105.nachweiseBackend.model.ApprenticeshipData;
import de.fi105.nachweiseBackend.model.ApprenticeshipGet;
import de.fi105.nachweiseBackend.model.ApprenticeshipPatch;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.service.ApprenticeshipService;
import de.fi105.nachweiseBackend.service.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApprenticeshipController extends LoggedInController implements ApprenticeshipApiDelegate {

    private final ApprenticeshipService apprenticeshipService;
    private final AuthorizationService authorizationService;

    public ApprenticeshipController(JWTVerifier jwtVerifier, AuthorizationService authorizationService, ApprenticeshipService apprenticeshipService, AuthorizationService authorizationService1) {
        super(jwtVerifier, authorizationService);
        this.apprenticeshipService = apprenticeshipService;
        this.authorizationService = authorizationService1;
    }

    @Override
    public ResponseEntity<ApprenticeshipGet> postApprenticeship(ApprenticeshipData apprenticeshipData, String JSESSIONID) {
        authorizationService.checkRole(verifyUser(JSESSIONID), UserCreate.RoleEnum.ADMIN);
        var get = apprenticeshipService.postApprenticeship(apprenticeshipData);
        return ResponseEntity.ok(get);
    }

    @Override
    public ResponseEntity<ApprenticeshipGet> patchApprenticeship(Integer id, ApprenticeshipPatch apprenticeshipPatch, String JSESSIONID) {
        authorizationService.checkRole(verifyUser(JSESSIONID), UserCreate.RoleEnum.ADMIN);
        return ResponseEntity.ok(apprenticeshipService.patchApprenticeship(apprenticeshipPatch, id));
    }


}
