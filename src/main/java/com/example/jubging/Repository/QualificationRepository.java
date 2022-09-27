package com.example.jubging.Repository;

import com.example.jubging.DTO.QualificationDTO;
import com.example.jubging.Model.PloggingRecords;
import com.example.jubging.Model.Qualification;
import com.example.jubging.Model.QualificationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QualificationRepository extends JpaRepository<Qualification, QualificationId> {

    @Query(value = "select qualification from Qualification qualification where qualification.postId.postId in :postId")
    List<Qualification> getQualification(Long postId);
}
