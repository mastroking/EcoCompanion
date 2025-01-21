package com.example.EcoCompanion.entity;

import com.example.EcoCompanion.model.User;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class PollutionStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // L'utente a cui appartiene questa statistica

    private BigInteger totalCO2;  // Emissioni totali di CO2 (come BigInteger)
    private BigInteger totalEnergy;  // Consumo energetico totale (come BigInteger)
    private BigInteger totalWaste;  // Produzione totale di rifiuti (come BigInteger)

    private BigInteger averageCO2;  // Media delle emissioni di CO2
    private BigInteger averageEnergy;  // Media del consumo energetico
    private BigInteger averageWaste;  // Media della produzione di rifiuti

    public PollutionStats(User user, BigInteger totalCO2, BigInteger totalEnergy, BigInteger totalWaste) {
        this.user = user;
        this.totalCO2 = totalCO2;
        this.totalEnergy = totalEnergy;
        this.totalWaste = totalWaste;

        // Calcola le medie (usando BigInteger per la divisione)
        this.averageCO2 = totalCO2.divide(BigInteger.valueOf(30));  // Supponiamo che i dati siano mensili
        this.averageEnergy = totalEnergy.divide(BigInteger.valueOf(30));
        this.averageWaste = totalWaste.divide(BigInteger.valueOf(30));
    }

    public PollutionStats() {

    }

    // Getters and setters
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

    public BigInteger getTotalCO2() {
        return totalCO2;
    }

    public void setTotalCO2(BigInteger totalCO2) {
        this.totalCO2 = totalCO2;
    }

    public BigInteger getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(BigInteger totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public BigInteger getTotalWaste() {
        return totalWaste;
    }

    public void setTotalWaste(BigInteger totalWaste) {
        this.totalWaste = totalWaste;
    }

    public BigInteger getAverageCO2() {
        return averageCO2;
    }

    public void setAverageCO2(BigInteger averageCO2) {
        this.averageCO2 = averageCO2;
    }

    public BigInteger getAverageEnergy() {
        return averageEnergy;
    }

    public void setAverageEnergy(BigInteger averageEnergy) {
        this.averageEnergy = averageEnergy;
    }

    public BigInteger getAverageWaste() {
        return averageWaste;
    }

    public void setAverageWaste(BigInteger averageWaste) {
        this.averageWaste = averageWaste;
    }

    public void setAverageCo2(BigInteger i) {
    }
}
