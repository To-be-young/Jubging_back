package com.example.jubging.Repository;

import com.example.jubging.Model.Pathway;
import com.example.jubging.Model.PathwayId;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PathwayRepository extends JpaRepository<Pathway, PathwayId> {
    @Query(value = "select pathway.time,pathway.latitude,pathway.longitude from Pathway pathway where pathway.recordId.recordId in :recordId")
    List<Object[]> getPathway(@Param(value = "recordId") Long recordId, Sort time);
}
