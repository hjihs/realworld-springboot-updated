package org.example.springrealworld.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springrealworld.model.User;
import org.example.springrealworld.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User Login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("invalid email or password."));
    }

    public User Register(String username, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("email already exists.");
        }
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("username already exists.");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user not found."));
    }

    public User updateUser(String email, String username, String bio, String image) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user not found."));
        user.setUsername(username);
        user.setBio(bio);
        user.setImage(image);
        return userRepository.save(user);
    }
}
