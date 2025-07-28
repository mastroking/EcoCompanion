package com.example.EcoCompanion.controller;

import com.example.EcoCompanion.DTO.RegistrationRequestDTO; // Importa il DTO
import com.example.EcoCompanion.model.Utente; // Importa l'entità Utente
import com.example.EcoCompanion.service.UtenteService; // Importa il servizio Utente
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid; // Per abilitare la validazione del DTO
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:5500")  // oppure "*"
@RestController
@RequestMapping("/api")
public class RegisterController {


    private static final Logger logger = Logger.getLogger(RegisterController.class.getName());

    private final UtenteService utenteService; // Iniezione del servizio tramite costruttore

    // Costruttore per l'iniezione delle dipendenze
    public RegisterController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    // Endpoint di registrazione
    @Operation(summary = "Registra un nuovo utente", description = "Effettua la registrazione di un nuovo utente con i dati forniti.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Registrazione completata con successo"),
                    @ApiResponse(responseCode = "400", description = "Dati di input non validi o utente già esistente"),
                    @ApiResponse(responseCode = "500", description = "Errore interno del server")
            })
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationRequestDTO registrationDTO) {
        try {
            // La validazione del DTO avviene automaticamente grazie a @Valid
            Utente nuovoUtente = utenteService.registraNuovoUtente(registrationDTO);
            logger.info("Utente registrato con successo: " + nuovoUtente.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body("Registrazione completata con successo!");
        } catch (IllegalArgumentException e) {
            // Cattura eccezioni specifiche del servizio (es. email/username/CF già registrati)
            logger.warning("Errore di registrazione (dati non validi): " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());} catch (Exception e) {
            logger.severe("Errore interno durante la registrazione: " + e.getMessage());
            e.printStackTrace(); // <--- AGGIUNGI QUESTO per vedere stack completo in console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno durante la registrazione");
        }

    }

    // Gestione specifica per le eccezioni di validazione (@Valid)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logger.warning("Errore di validazione input: " + errors);
        return errors;
    }
}
