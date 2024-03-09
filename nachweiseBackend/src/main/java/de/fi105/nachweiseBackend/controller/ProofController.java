package de.fi105.nachweiseBackend.controller;

import de.fi105.nachweiseBackend.api.ProofApiDelegate;
import de.fi105.nachweiseBackend.model.ProofGet;
import de.fi105.nachweiseBackend.model.ProofPost;
import de.fi105.nachweiseBackend.service.ProofService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestScope;

@Component
@AllArgsConstructor
public class ProofController extends BaseController implements ProofApiDelegate {

    private final ProofService proofService;

    @Override
    public ResponseEntity<ProofGet> postProof(ProofPost proofPost) {
        Cookie[] cookies = servletRequest.getCookies();

    }
}
