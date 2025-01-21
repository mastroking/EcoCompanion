package com.example.EcoCompanion.controller;

import com.example.EcoCompanion.entity.PollutionData;
import com.example.EcoCompanion.entity.PollutionStats;
import com.example.EcoCompanion.service.PollutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/pollution")
public class PollutionController {

    @Autowired
    private PollutionService pollutionService;

    // Aggiungi dati di inquinamento
    @PostMapping
    public ResponseEntity<PollutionData> addPollutionData(@RequestBody PollutionData pollutionData, Principal principal) {
        PollutionData savedData = pollutionService.addPollutionData(pollutionData, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    // Ottieni tutti i dati di inquinamento per l'utente autenticato
    @GetMapping
    public List<PollutionData> getPollutionData(Principal principal) {
        return pollutionService.getPollutionData(principal.getName());
    }

    // Ottieni statistiche aggregate (media, totale)
    @GetMapping("/stats")
    public PollutionStats getStats(Principal principal) {
        return pollutionService.calculateStats(principal.getName());
    }
}
