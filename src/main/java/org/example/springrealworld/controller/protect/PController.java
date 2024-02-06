package org.example.springrealworld.controller.protect;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PController {
    @GetMapping("/protected/message")
    public String protect() {
        return "protected";
    }

    @GetMapping("/protected/whoami")
    public String whoami() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username = principal.getClaim("username");
        return (String) username;
    }
}
