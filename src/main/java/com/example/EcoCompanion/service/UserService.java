package com.example.EcoCompanion.service;

import com.example.EcoCompanion.model.Utente;
import com.example.EcoCompanion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registrazione dell'utente
    public String registerUser(Utente utente) {
        Optional<Utente> existingUser = userRepository.findByUsername(utente.getUsername());
        if (existingUser.isPresent()) {
            return "Username is already taken!";
        }

        existingUser = userRepository.findByEmail(utente.getEmail());
        if (existingUser.isPresent()) {
            return "Email is already registered!";
        }

        userRepository.save(utente);
        return "User registered successfully!";
    }
}