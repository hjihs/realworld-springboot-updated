package org.example.springrealworld.controller.user;

import lombok.RequiredArgsConstructor;
import org.example.springrealworld.config.TokenUtil;
import org.example.springrealworld.controller.user.dto.LoginRequestDTO;
import org.example.springrealworld.controller.user.dto.LoginResponseDTO;
import org.example.springrealworld.controller.user.dto.RegisterRequestDTO;
import org.example.springrealworld.controller.user.dto.RegisterResponseDTO;
import org.example.springrealworld.exception.GenericErrorModel;
import org.example.springrealworld.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenUtil tokenUtil;

    /**
     * POST /users/login : Existing user login
     * Login for existing user
     *
     * @param body Credentials to use (required)
     * @return User (status code 200)
     * or Unauthorized (status code 401)
     * or Unexpected error (status code 422)
     */
    @Operation(operationId = "login", summary = "Existing user login", description = "Login for existing user", tags = {"User and Authentication"}, responses = {@ApiResponse(responseCode = "200", description = "User", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class))}), @ApiResponse(responseCode = "401", description = "Unauthorized"), @ApiResponse(responseCode = "422", description = "Unexpected error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericErrorModel.class))})})
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        try {
            var user = userService.Login(body.getEmail(), body.getPassword());
            var token = tokenUtil.generateToken(user);
            var loginResponse = LoginResponseDTO.builder().email(user.getEmail()).token(token).username(user.getUsername()).bio(user.getBio()).image(user.getImage()).build();
            return ResponseEntity.ok(loginResponse);
        } catch (IllegalArgumentException e) {
            return GenericErrorModel.handleExceptions(e.getMessage());
        }
    }

    /**
     * POST /users
     * Register a new user
     *
     * @param body Details of the new user to register (required)
     * @return User (status code 201)
     * or Unexpected error (status code 422)
     */
    @Operation(operationId = "createUser", description = "Register a new user", tags = {"User and Authentication"}, responses = {@ApiResponse(responseCode = "201", description = "User", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponseDTO.class))}), @ApiResponse(responseCode = "422", description = "Unexpected error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericErrorModel.class))})})
    @PostMapping("/users")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
        try {
            var user = userService.Register(body.getUsername(), body.getEmail(), body.getPassword());
            var token = tokenUtil.generateToken(user);
            var registerResponse = RegisterResponseDTO.builder().email(user.getEmail()).token(token).username(user.getUsername()).bio(user.getBio()).image(user.getImage()).build();
            return ResponseEntity.status(201).body(registerResponse);
        } catch (IllegalArgumentException e) {
            return GenericErrorModel.handleExceptions(e.getMessage());
        }
    }
}
