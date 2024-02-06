package org.example.springrealworld.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.stereotype.Component;

/**
 * CustomBearerTokenResolver
 * <p>
 * This class is used to resolve the token from the request header.
 * The token is resolved from the header using the token header and token prefix, which may be different for each application.
 * <p>
 * An example whose token header is "Authorization" and token prefix is "Token":
 * <code>
 * Authorization: "Token {token.value}"
 */
@Component
public class CustomBearerTokenResolver implements BearerTokenResolver {
    @Value("${jwt.token.header}")
    private String tokenHeader;

    @Value("${jwt.token.prefix}")
    private String tokenPrefix;

    @Override
    public String resolve(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(tokenHeader);
        if (authorizationHeader != null && authorizationHeader.startsWith(tokenPrefix + " ")) {
            return authorizationHeader.substring(tokenPrefix.length() + 1);
        }
        return null;
    }
}