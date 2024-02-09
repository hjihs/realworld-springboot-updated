package org.example.springrealworld.controller.protect;

import lombok.RequiredArgsConstructor;
import org.example.springrealworld.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PController {
    private final UserService userService;

    @GetMapping("/protected/message")
    public String protect() {
        return "protected";
    }

    @GetMapping("/protected/whoami")
    public String whoami() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.getClaim("email");
        var username = userService.findUserByEmail(email);
        return "You are " + username;
    }
}
