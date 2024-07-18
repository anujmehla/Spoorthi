package com.anuj.spoorthi.programs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Integer> {


    @Query(value = "select \n" +
            "p.id,\n" +
            "p.start_date,\n" +
            "p.end_date,\n" +
            "p.address_id,\n" +
            "p.deleted,\n" +
            "p.goal_amount,\n" +
            "p.raised_amount,\n" +
            "p.description,\n" +
            "p.extra,\n" +
            "p.name,\n" +
            "a.pincode,\n" +
            "a.city,\n" +
            "a.country,\n" +
            "a.district,\n" +
            "a.locality,\n" +
            "a.state,\n" +
            "a.street_name\n" +
            "from program as p inner join address as a on p.address_id = a.id where p.deleted = false;", nativeQuery = true)
    List<ProgramEntity>getAllPrograms();


//    List<ProgramEntity> findAllAndIsDeleted(boolean b);
}