package com.example.EcoCompanion.entity;

import com.example.EcoCompanion.model.Utente; // Importa la tua entità Utente
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "pollution_data") // Aggiunto per mappare esplicitamente il nome della tabella nel DB
public class PollutionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Molti record di PollutionData per un Utente
    @JoinColumn(name = "user_id", nullable = false) // Colonna della chiave esterna nel DB
    private Utente utente; // Relazione con l'utente

    @Column(nullable = false) // Assicurati che il timestamp non sia nullo
    private LocalDateTime timestamp;  // Per tracciare quando i dati sono stati inseriti

    @Column(name = "co2_emissions") // Mappa al nome della colonna nel DB se diverso
    private BigInteger co2Emissions; // Emissioni di CO2 (kg)

    @Column(name = "energy_consumption") // Mappa al nome della colonna nel DB se diverso
    private BigInteger energyConsumption; // Consumo energetico (kWh)

    @Column(name = "waste_produced") // Mappa al nome della colonna nel DB se diverso
    private BigInteger wasteProduced; // Rifiuti prodotti (kg)

    // Costruttore vuoto (richiesto da JPA)
    public PollutionData() {}

    // Costruttore con tutti i campi (come fornito dall'utente)
    public PollutionData(Long id, Utente utente, LocalDateTime timestamp, BigInteger co2Emissions, BigInteger energyConsumption, BigInteger wasteProduced) {
        this.id = id;
        this.utente = utente;
        this.timestamp = timestamp;
        this.co2Emissions = co2Emissions;
        this.energyConsumption = energyConsumption;
        this.wasteProduced = wasteProduced;
    }

    // Costruttore senza ID per la creazione di nuovi record (più comune)
    public PollutionData(Utente utente, LocalDateTime timestamp, BigInteger co2Emissions, BigInteger energyConsumption, BigInteger wasteProduced) {
        this.utente = utente;
        this.timestamp = timestamp;
        this.co2Emissions = co2Emissions;
        this.energyConsumption = energyConsumption;
        this.wasteProduced = wasteProduced;
    }


    // --- Getter e Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Corretto il nome del getter/setter per la relazione 'utente'
    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigInteger getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(BigInteger co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public BigInteger getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(BigInteger energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public BigInteger getWasteProduced() {
        return wasteProduced;
    }

    public void setWasteProduced(BigInteger wasteProduced) {
        this.wasteProduced = wasteProduced;
    }

    @Override
    public String toString() {
        return "PollutionData{" +
                "id=" + id +
                ", utenteId=" + (utente != null ? utente.getId() : "null") + // Evita loop infiniti
                ", timestamp=" + timestamp +
                ", co2Emissions=" + co2Emissions +
                ", energyConsumption=" + energyConsumption +
                ", wasteProduced=" + wasteProduced +
                '}';
    }
}
