package de.fi105.nachweiseBackend.configuration;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import de.fi105.nachweiseBackend.exception.ServiceException;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;

@Component
@Setter
public class TokenFilter implements Filter {

    private final JWTVerifier jwtVerifier;

    public TokenFilter(JWTVerifier jwtVerifier) {
        this.jwtVerifier = jwtVerifier;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();

        if (request.getRequestURI().matches("/session/.*") || request.getRequestURI().equals("/error")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (cookies == null) {
            throw new AccessDeniedException("No session cookie");
        }

        Cookie session = Arrays.stream(cookies).filter(x -> x.getName().equals("JSESSIONID"))
                .findFirst().orElse(null);
        if (session == null) {
            throw new AccessDeniedException("No session cookie");
        }

        try {
            jwtVerifier.verify(session.getValue());
        } catch (JWTVerificationException exception) {
            throw new AccessDeniedException("Token not valid");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
