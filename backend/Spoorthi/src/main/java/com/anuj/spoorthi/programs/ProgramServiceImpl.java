package com.anuj.spoorthi.programs;

import com.anuj.spoorthi.address.AddressEntity;
import com.anuj.spoorthi.address.AddressRequest;
import com.anuj.spoorthi.address.AddressResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ProgramServiceImpl implements ProgramService{

    private final ProgramRepository repository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository repository) {
        this.repository = repository;
    }

    @Override
    public String addProgram(ProgramRequest programRequest) {
        log.info("adding Program : Inside Service");

        ProgramEntity newProgram = new ProgramEntity();
        BeanUtils.copyProperties(programRequest,newProgram);

        AddressRequest addressRequest = programRequest.getAddress();
        AddressEntity newAddress = new AddressEntity();

        BeanUtils.copyProperties(addressRequest,newAddress);
        newProgram.setAddress(newAddress);

        ProgramEntity savedProgram = null;
        try {
            savedProgram = repository.save(newProgram);
        } catch (DataIntegrityViolationException dve) {
            log.error("Error in saving Program : Inside Service due to dve", dve);
        } catch (Exception e) {
            log.error("Error in saving Program : Inside Service", e);
        }
        if(savedProgram == null) return null;

        return "CREATED SUCCESSFULLY";

    }

    @Override
    public List<ProgramResponse> getAllPrograms() {
        log.info("All Programs :Inside Service");

        List<ProgramEntity> all = repository.getAllPrograms();

        log.info("Program Entity {}",all);

        List<ProgramResponse> responses = new ArrayList<>();

        all.forEach(program -> {
            ProgramResponse response = new ProgramResponse();
            BeanUtils.copyProperties(program,response);
            AddressResponse addressResponse = new AddressResponse();
            log.info(program.getAddress().toString());
            BeanUtils.copyProperties(program.getAddress(),addressResponse);
            log.info(addressResponse.toString());
            response.setAddress(addressResponse);
            responses.add(response);
        });
        return responses;
    }

    @Override
    public ProgramResponse updateProgram(ProgramRequest programRequest, UUID id) {

        log.info("Updating programRequest with id " + id);

        ProgramEntity newProgram = new ProgramEntity();
        BeanUtils.copyProperties(programRequest,newProgram);

        AddressRequest addressRequest = programRequest.getAddress();
        AddressEntity newAddress = new AddressEntity();
        BeanUtils.copyProperties(addressRequest,newAddress);
        newAddress.setId(id);


        newProgram.setAddress(newAddress);
        newProgram.setId(id);

        ProgramEntity savedProgram = null;
        try {
            savedProgram = repository.save(newProgram);
        } catch (DataIntegrityViolationException dve) {
            log.error("Error in saving Program : Inside Service", dve);
        }
        if(savedProgram == null) return null;

        ProgramResponse response = new ProgramResponse();
        BeanUtils.copyProperties(savedProgram,response);
        return response;
    }
}
