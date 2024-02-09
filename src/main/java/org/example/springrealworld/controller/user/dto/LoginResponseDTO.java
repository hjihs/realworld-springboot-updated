package org.example.springrealworld.controller.user.dto;

public class LoginResponseDTO extends UserDTO {
    LoginResponseDTO(String email, String token, String username, String bio, String image) {
        super(email, token, username, bio, image);
    }
}
