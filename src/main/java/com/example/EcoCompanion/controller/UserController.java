package com.example.EcoCompanion.controller;




import com.example.EcoCompanion.model.Utente;
import com.example.EcoCompanion.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint di registrazione
    @PostMapping("/register-new")
    public ResponseEntity<String> registerUser(@Valid @RequestBody Utente utente) {
        String message = userService.registerUser(utente);
        if (message.equals("User registered successfully!")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.badRequest().body(message);
        }
    }
}
