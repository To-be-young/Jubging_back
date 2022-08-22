package com.example.jubging.Repository;

import com.example.jubging.Model.PloggingRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PloggingRepository extends JpaRepository<PloggingRecords, Long> {
    List<PloggingRecords> findAll();
    Optional<PloggingRecords> findByUserId(String userId);
}
