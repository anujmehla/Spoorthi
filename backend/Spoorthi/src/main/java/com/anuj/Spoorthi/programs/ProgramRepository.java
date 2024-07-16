package com.anuj.Spoorthi.programs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Integer> {


    @Query(value = "SELECT * FROM PROGRAM WHERE DELETED = FALSE" , nativeQuery = true)
    List<ProgramEntity> getAllPrograms();

//    List<ProgramEntity> findAllAndIsDeleted(boolean b);
}