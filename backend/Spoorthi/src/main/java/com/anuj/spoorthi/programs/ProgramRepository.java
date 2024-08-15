package com.anuj.spoorthi.programs;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProgramRepository extends JpaRepository<ProgramEntity, UUID > {
    @Query ( value = "Select * from program where deleted =false" , nativeQuery = true)
    List<ProgramEntity> getAllPrograms();


}