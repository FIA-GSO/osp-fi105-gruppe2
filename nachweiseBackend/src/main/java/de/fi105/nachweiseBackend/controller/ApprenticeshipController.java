package de.fi105.nachweiseBackend.controller;

import de.fi105.nachweiseBackend.api.ApprenticeshipApiDelegate;
import de.fi105.nachweiseBackend.model.ApprenticeshipData;
import de.fi105.nachweiseBackend.model.ApprenticeshipGet;
import de.fi105.nachweiseBackend.model.ApprenticeshipPatch;
import de.fi105.nachweiseBackend.service.ApprenticeshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApprenticeshipController extends BaseController implements ApprenticeshipApiDelegate {

    private final ApprenticeshipService apprenticeshipService;

    public ApprenticeshipController(ApprenticeshipService apprenticeshipService) {
        this.apprenticeshipService = apprenticeshipService;
    }

    @Override
    public ResponseEntity<ApprenticeshipGet> postApprenticeship(ApprenticeshipData apprenticeshipData) {
        var get = apprenticeshipService.postApprenticeship(apprenticeshipData);
        return ResponseEntity.ok(get);
    }

    @Override
    public ResponseEntity<ApprenticeshipGet> patchApprenticeship(Integer id, ApprenticeshipPatch apprenticeshipPatch) {
        return ResponseEntity.ok(apprenticeshipService.patchApprenticeship(apprenticeshipPatch, id));
    }
}
