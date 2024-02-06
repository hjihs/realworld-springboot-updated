package org.example.springrealworld.controller.user.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonRootName("user")
public class RegisterRequestDTO {
    @NotBlank(message = "Username is required")
    public String username;
    @NotBlank(message = "Email is required")
    public String email;
    @NotBlank(message = "Password is required")
    public String password;
}
