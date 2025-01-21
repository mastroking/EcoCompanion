package com.example.EcoCompanion.entity;

import com.example.EcoCompanion.model.User;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
public class PollutionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relazione con l'utente

    private LocalDateTime timestamp;  // Per tracciare quando i dati sono stati inseriti

    private BigInteger co2Emissions; // Emissioni di CO2 (kg)
    private BigInteger energyConsumption; // Consumo energetico (kWh)
    private BigInteger wasteProduced; // Rifiuti prodotti (kg)

    public PollutionData(Long id, User user, LocalDateTime timestamp, BigInteger co2Emissions, BigInteger energyConsumption, BigInteger wasteProduced) {
        this.id = id;
        this.user = user;
        this.timestamp = timestamp;
        this.co2Emissions = co2Emissions;
        this.energyConsumption = energyConsumption;
        this.wasteProduced = wasteProduced;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
