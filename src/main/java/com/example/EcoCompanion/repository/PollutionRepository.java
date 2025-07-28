package com.example.EcoCompanion.repository;

import com.example.EcoCompanion.entity.PollutionData; // Assicurati che il package sia corretto
import com.example.EcoCompanion.model.Utente; // Potrebbe servire se hai query dirette su Utente
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PollutionRepository extends JpaRepository<PollutionData, Long> {

    // --- CORREZIONE QUI ---
    // Il nome del campo in PollutionData è 'utente', quindi il metodo deve essere findByUtenteUsername
    List<PollutionData> findByUtenteUsername(String username);

    // Se hai altri metodi che usano 'user' e si riferiscono alla relazione 'utente',
    // dovrai correggerli in modo simile (es. findByUserEmail -> findByUtenteEmail)

    // Esempio: se volessi trovare dati per un utente specifico
    List<PollutionData> findByUtente(Utente utente);
}
