package com.example.jubging.Repository;

import com.example.jubging.Model.PloggingRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PloggingRepository extends JpaRepository<PloggingRecords, Long> {
    List<PloggingRecords> findAll();

    PloggingRecords findByRecordId(Long recordId);

    List<PloggingRecords> findByUserId(String UserId);

}
