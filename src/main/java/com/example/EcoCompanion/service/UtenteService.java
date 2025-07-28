package com.example.EcoCompanion.service;

import com.example.EcoCompanion.DTO.RegistrationRequestDTO;
import com.example.EcoCompanion.model.Utente;
import com.example.EcoCompanion.repository.UtenteRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // Per l'hashing della password
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Per la gestione delle transazioni

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder; // Inietteremo BCryptPasswordEncoder qui

    // Costruttore per l'iniezione delle dipendenze
    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional // Assicura che l'intera operazione sia atomica
    public Utente registraNuovoUtente(RegistrationRequestDTO registrationDTO) {
        // 1. Controllo unicità (email, username, codice fiscale)
        if (utenteRepository.findByEmail(registrationDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email già registrata.");
        }
        if (utenteRepository.findByUsername(registrationDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username già in uso.");
        }
        if (utenteRepository.findByCodiceFiscale(registrationDTO.getCodiceFiscale()).isPresent()) {
            throw new IllegalArgumentException("Codice Fiscale già registrato.");
        }

        // 2. Hash della password
        String hashedPassword = passwordEncoder.encode(registrationDTO.getPassword());

        // 3. Creazione dell'entità Utente dal DTO
        Utente nuovoUtente = new Utente(
                registrationDTO.getNome(),
                registrationDTO.getCognome(),
                registrationDTO.getDataNascita(),
                registrationDTO.getGenere(),
                registrationDTO.getCodiceFiscale(),
                registrationDTO.getEmail(),
                registrationDTO.getUsername(),
                hashedPassword // Usa la password hashata
        );

        // I campi dataRegistrazione, puntiEcologici e livello sono impostati nel costruttore di Utente
        // o tramite i loro valori di default nel DB.

        // 4. Salvataggio dell'utente nel database
        return utenteRepository.save(nuovoUtente);
    }

    // Puoi aggiungere qui altri metodi di servizio relativi agli utenti (es. trovaUtentePerEmail, aggiornaProfilo, ecc.)
    public Optional<Utente> findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }
}
