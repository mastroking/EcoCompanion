package com.example.EcoCompanion.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class RegisterController {


    Logger logger = Logger.getLogger(RegisterController.class.getName());
    @Operation(summary = "Registra un nuovo utente", description = "Effettua la registrazione di un nuovo utente con username, email e password.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registrazione completata"),
                    @ApiResponse(responseCode = "400", description = "Errore dei parametri di input"),
                    @ApiResponse(responseCode = "500", description = "Errore interno del server")
            })
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> userData) {
        try {
            String username = userData.get("username");
            String email = userData.get("email");
            String password = userData.get("password");

            System.out.println("Registrazione richiesta da: " + username);
            System.out.println("Email: " + email);
            return ResponseEntity.ok("Registrazione completata con successo!");
        } catch (Exception e) {
            logger.severe("Errore durante la registrazione: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la registrazione");
        }
    }
}