package com.example.EcoCompanion.DTO;

import jakarta.validation.constraints.*; // Per le annotazioni di validazione
import java.time.LocalDate;

public class RegistrationRequestDTO {

    @NotBlank(message = "Username non può essere vuoto")
    @Size(min = 3, max = 50, message = "Username deve avere tra 3 e 50 caratteri")
    private String username;

    @NotBlank(message = "Email non può essere vuota")
    @Email(message = "Formato email non valido")
    @Size(max = 255, message = "Email troppo lunga")
    private String email;

    @NotBlank(message = "Password non può essere vuota")
    @Size(min = 8, message = "Password deve avere almeno 8 caratteri")
    private String password; // Questa sarà la password in chiaro dal frontend

    @NotBlank(message = "Nome non può essere vuoto")
    @Size(max = 50, message = "Nome troppo lungo")
    private String nome;

    @NotBlank(message = "Cognome non può essere vuoto")
    @Size(max = 50, message = "Cognome troppo lungo")
    private String cognome;

    @NotNull(message = "Data di nascita non può essere vuota")
    @Past(message = "Data di nascita deve essere nel passato") // Assicura che la data sia nel passato
    private LocalDate dataNascita;

    @NotBlank(message = "Genere non può essere vuoto")
    private String genere;

    @NotBlank(message = "Codice Fiscale non può essere vuoto")
    @Pattern(regexp = "^[A-Z0-9]{16}$", message = "Codice Fiscale non valido (16 caratteri alfanumerici)")
    private String codiceFiscale;

    // Costruttore vuoto
    public RegistrationRequestDTO() {}

    // Costruttore con tutti i campi
    public RegistrationRequestDTO(String username, String email, String password, String nome, String cognome,
                                  LocalDate dataNascita, String genere, String codiceFiscale) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.genere = genere;
        this.codiceFiscale = codiceFiscale;
    }

    // Getter e Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
