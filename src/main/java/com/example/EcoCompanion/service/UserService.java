package com.example.EcoCompanion.service;

import com.example.EcoCompanion.model.User;
import com.example.EcoCompanion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registrazione dell'utente
    public String registerUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "Username is already taken!";
        }

        existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Email is already registered!";
        }

        // Codifica la password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "User registered successfully!";
    }
}