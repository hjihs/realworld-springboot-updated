package org.example.springrealworld.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.example.springrealworld.model.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TokenUtil {
    private final JwtEncoder jwtEncoder;

    /**
     * The expiration time of the token in seconds.
     */
    @Value("${jwt.expiration.seconds}")
    private Long expirationSeconds;

    /**
     * Generate a token from the user information using HS256 algorithm.
     *
     * @param user: The user information.
     * @return JWT token with claims including username, email, and expiration time.
     */
    public String generateToken(User user) {
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(expirationSeconds))
                .subject(user.getEmail())
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}