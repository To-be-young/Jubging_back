package com.example.jubging.Repository;

import com.example.jubging.Model.PloggingRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PloggingRepository extends JpaRepository<PloggingRecords, Long> {
    List<PloggingRecords> findAll();

    PloggingRecords findByRecordId(Long recordId);

    Page<PloggingRecords> findByUserId(String UserId, Pageable pageable);

}
