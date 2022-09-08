package com.example.jubging.Repository;

import com.example.jubging.Model.Pathway;
import com.example.jubging.Model.PathwayId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathwayRepository extends JpaRepository<Pathway, PathwayId> {

}
