package com.example.EcoCompanion.controller;

import com.example.EcoCompanion.service.UtenteService; // Importa il servizio Utente
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    private final UtenteService utenteService; // Iniezione del servizio tramite costruttore

    // Costruttore per l'iniezione delle dipendenze
    public UserController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    // Questo controller ora sarà dedicato ad altre operazioni sull'utente.
    // L'endpoint di registrazione è stato rimosso e sarà gestito dal RegisterController.

    // Esempio: un futuro endpoint per ottenere i dettagli dell'utente
    /*
    @GetMapping("/user/{id}")
    public ResponseEntity<Utente> getUserDetails(@PathVariable Long id) {
        try {
            Utente utente = utenteService.findById(id)
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));
            return ResponseEntity.ok(utente);
        } catch (Exception e) {
            logger.severe("Errore durante il recupero dei dettagli utente: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */
}
