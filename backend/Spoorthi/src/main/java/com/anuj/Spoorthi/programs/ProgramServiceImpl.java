package com.anuj.Spoorthi.programs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService{

    @Autowired
    ProgramRepository repository;

    @Override
    public String addProgram(ProgramRequest program) {

        ProgramEntity programEntity = new ProgramEntity();
        BeanUtils.copyProperties(program,programEntity);

        ProgramEntity savedEntity = repository.save(programEntity);

        if(savedEntity == null) return null;

        return "CREATED SUCCESSFULLY";

    }

    @Override
    public List<ProgramResponse> getAllPrograms() {

        List<ProgramEntity> all = repository.getAllPrograms();
        List<ProgramResponse> responses = new ArrayList<>();

        all.forEach(program -> {
            ProgramResponse response = new ProgramResponse();
            BeanUtils.copyProperties(program,response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public ProgramResponse updateProgram(ProgramRequest program, int id) {

        ProgramEntity entity = new ProgramEntity();
        BeanUtils.copyProperties(program,entity);

        entity.setId(id);

        ProgramEntity saved = repository.save(entity);

        ProgramResponse response = new ProgramResponse();
        BeanUtils.copyProperties(saved,response);
        return response;
    }
}
