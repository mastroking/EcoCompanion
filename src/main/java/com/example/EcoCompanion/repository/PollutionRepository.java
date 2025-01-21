package com.example.EcoCompanion.repository;

import com.example.EcoCompanion.entity.PollutionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollutionRepository extends JpaRepository<PollutionData, Long> {
    List<PollutionData> findByUserUsername(String username);
}
