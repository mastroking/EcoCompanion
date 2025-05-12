package com.example.EcoCompanion.service;

import com.example.EcoCompanion.entity.PollutionData;
import com.example.EcoCompanion.entity.PollutionStats;
import com.example.EcoCompanion.model.User;
import com.example.EcoCompanion.repository.PollutionRepository;
import com.example.EcoCompanion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private UserRepository userRepository;

    private User user;

    public PollutionData addPollutionData(PollutionData pollutionData, String username) {
        // Ottieni l'utente dal database
        interface UserRepository extends JpaRepository<User, Long> {
            Optional<User> findByUsername(String username);
        }

        // Imposta l'utente che ha inserito i dati
        pollutionData.setUser(user);
        pollutionData.setTimestamp(LocalDateTime.now());  // Imposta il timestamp per quando i dati sono stati inseriti

        // Salva i dati nel database
        return pollutionRepository.save(pollutionData);
    }

    public List<PollutionData> getPollutionData(String username) {
        // Ottieni i dati di inquinamento per l'utente autenticato
        return pollutionRepository.findByUserUsername(username);
    }

    public PollutionStats calculateStats(String username) {
        // Calcola statistiche come la media dei consumi di CO2, rifiuti e energia
        // Implementazione di esempio, può essere personalizzata
        PollutionStats stats = new PollutionStats();
        List<PollutionData> data = pollutionRepository.findByUserUsername(username);

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
