package com.example.EcoCompanion.model;

import jakarta.persistence.*;
import java.time.LocalDate; // Per data_nascita
import java.time.LocalDateTime; // Per data_registrazione e ultimo_accesso

@Entity
@Table(name = "Utente") // Il nome della tabella nel database è "Utente"
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY per SQL Server auto-increment
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(name = "data_nascita", nullable = false) // Mappa al campo data_nascita nel DB
    private LocalDate dataNascita; // Usa LocalDate per le date senza orario

    @Column(name = "genere") // Potrebbe essere nullable nel DB, se non obbligatorio
    private String genere;

    @Column(name = "codice_fiscale", nullable = false, unique = true, length = 16) // Lunghezza 16, unico e non nullo
    private String codiceFiscale;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true) // Username è unico e non nullo
    private String username;

    @Column(nullable = false)
    private String passwordHash; // Rinominato per chiarezza, conterrà l'hash della password

    @Column(name = "data_registrazione", nullable = false)
    private LocalDateTime dataRegistrazione; // Usa LocalDateTime per data e ora

    @Column(name = "ultimo_accesso") // Può essere nullo inizialmente
    private LocalDateTime ultimoAccesso;

    @Column(name = "punti_ecologici", nullable = false)
    private Integer puntiEcologici; // Usa Integer per i punti

    @Column(nullable = false)
    private Integer livello; // Usa Integer per il livello

    @Column(name = "url_avatar") // Può essere nullo
    private String urlAvatar;

    // Costruttore vuoto (richiesto da JPA)
    public Utente() {
        // Inizializza i campi con valori di default se necessario per la creazione di un nuovo utente
        this.dataRegistrazione = LocalDateTime.now();
        this.puntiEcologici = 0;
        this.livello = 1;
    }

    // Costruttore per la registrazione (senza ID, punti, livello, avatar, ultimo accesso che sono default/generati)
    public Utente(String nome, String cognome, LocalDate dataNascita, String genere,
                  String codiceFiscale, String email, String username, String passwordHash) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.genere = genere;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.dataRegistrazione = LocalDateTime.now(); // Imposta la data di registrazione automaticamente
        this.puntiEcologici = 0; // Inizializza i punti a 0
        this.livello = 1; // Inizializza il livello a 1
        // ultimoAccesso e urlAvatar rimangono nulli di default
    }

    // --- Getter e Setter per tutti i campi ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(LocalDateTime dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    public LocalDateTime getUltimoAccesso() {
        return ultimoAccesso;
    }

    public void setUltimoAccesso(LocalDateTime ultimoAccesso) {
        this.ultimoAccesso = ultimoAccesso;
    }

    public Integer getPuntiEcologici() {
        return puntiEcologici;
    }

    public void setPuntiEcologici(Integer puntiEcologici) {
        this.puntiEcologici = puntiEcologici;
    }

    public Integer getLivello() {
        return livello;
    }

    public void setLivello(Integer livello) {
        this.livello = livello;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", genere='" + genere + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' + // Attenzione: non stampare mai la password in chiaro!
                ", dataRegistrazione=" + dataRegistrazione +
                ", ultimoAccesso=" + ultimoAccesso +
                ", puntiEcologici=" + puntiEcologici +
                ", livello=" + livello +
                ", urlAvatar='" + urlAvatar + '\'' +
                '}';
    }
}
