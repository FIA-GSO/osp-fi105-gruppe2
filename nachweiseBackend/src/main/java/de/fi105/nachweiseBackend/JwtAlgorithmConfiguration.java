package de.fi105.nachweiseBackend;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAlgorithmConfiguration {

    @Bean
    public static Algorithm jwtAlgorithm() {
        return Algorithm.HMAC256("123455asdfBZDKLÖSJDHGHDJS");
    }

    @Bean
    public static JWTVerifier jwtVerifier(@Autowired Algorithm jwtAlgorithm) {
        return JWT.require(jwtAlgorithm).withIssuer("nachweiseBackend").build();
    }

}
