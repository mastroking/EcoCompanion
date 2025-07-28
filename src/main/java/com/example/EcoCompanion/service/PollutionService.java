package com.example.EcoCompanion.service;

import com.example.EcoCompanion.entity.PollutionData;
import com.example.EcoCompanion.entity.PollutionStats;
import com.example.EcoCompanion.model.Utente;
import com.example.EcoCompanion.repository.PollutionRepository;
import com.example.EcoCompanion.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PollutionService {

    @Autowired
    private PollutionRepository pollutionRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    private Utente utente;

    public PollutionData addPollutionData(PollutionData pollutionData, String username) {
        // Ottieni l'utente dal database
        interface UserRepository extends JpaRepository<Utente, Long> {
            Optional<Utente> findByUsername(String username);
        }

        // Imposta l'utente che ha inserito i dati
        pollutionData.setUtente(utente);
        pollutionData.setTimestamp(LocalDateTime.now());  // Imposta il timestamp per quando i dati sono stati inseriti

        // Salva i dati nel database
        return pollutionRepository.save(pollutionData);
    }

    public List<PollutionData> getPollutionData(String username) {
        // Ottieni i dati di inquinamento per l'utente autenticato
        return pollutionRepository.findByUtenteUsername(username);
    }

    public PollutionStats calculateStats(String username) {
        // Calcola statistiche come la media dei consumi di CO2, rifiuti e energia
        // Implementazione di esempio, può essere personalizzata
        PollutionStats stats = new PollutionStats();
        List<PollutionData> data = pollutionRepository.findByUtenteUsername(username);

        BigInteger totalCo2 = BigInteger.valueOf(0), totalEnergy = BigInteger.valueOf(0), totalWaste = BigInteger.valueOf(0);
        for (PollutionData entry : data) {
            totalCo2 = entry.getCo2Emissions();
            totalEnergy = entry.getEnergyConsumption();
            totalWaste = entry.getWasteProduced();
        }

        stats.setAverageCo2(totalCo2.divide(BigInteger.valueOf(data.size())));
        stats.setAverageEnergy(totalEnergy.divide(BigInteger.valueOf(data.size())));
        stats.setAverageWaste(totalWaste.divide(BigInteger.valueOf(data.size())));

        return stats;
    }
}
