package org.example.springrealworld.controller.user.dto;

public class RegisterResponseDTO extends UserDTO {
    RegisterResponseDTO(String email, String token, String username, String bio, String image) {
        super(email, token, username, bio, image);
    }
}