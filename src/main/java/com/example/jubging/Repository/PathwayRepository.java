package com.example.jubging.Repository;

import com.example.jubging.DTO.PathwayDTO;
import com.example.jubging.Model.Pathway;
import com.example.jubging.Model.PathwayId;
import com.example.jubging.Model.PloggingRecords;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PathwayRepository extends JpaRepository<Pathway, PathwayId> {
   // @Query(value = "select pathway from Pathway pathway where pathway.recordId.recordId in :recordId")
   // List<Object[]> getPathway(@Param(value = "recordId") Long recordId, Sort time);
    @Query(value = "select pathway from Pathway pathway where pathway.recordId.recordId in :recordId")
    List<Pathway> getPathway(Long recordId);
}
