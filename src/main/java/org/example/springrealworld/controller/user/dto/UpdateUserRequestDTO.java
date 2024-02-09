package org.example.springrealworld.controller.user.dto;

public class UpdateUserRequestDTO extends UserDTO{

    UpdateUserRequestDTO(String email, String token, String username, String bio, String image) {
        super(email, token, username, bio, image);
    }
}
