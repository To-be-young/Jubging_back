package com.example.jubging.model.response.repository;

import com.example.jubging.model.entity.PloggingRecords;
import com.example.jubging.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PloggingRepository extends JpaRepository<PloggingRecords, Long> {
    List<PloggingRecords> findAll();
    Optional<PloggingRecords> findByUserId(String userId);
}
