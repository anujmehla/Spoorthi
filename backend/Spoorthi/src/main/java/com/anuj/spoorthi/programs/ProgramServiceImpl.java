package com.anuj.spoorthi.programs;

import com.anuj.spoorthi.address.AddressEntity;
import com.anuj.spoorthi.address.AddressRepository;
import com.anuj.spoorthi.address.AddressRequest;
import com.anuj.spoorthi.address.AddressResponse;
import com.anuj.spoorthi.customexceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final AddressRepository addressRepository;

    public ProgramServiceImpl(ProgramRepository programRepository, AddressRepository addressRepository) {
        this.programRepository = programRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public String addProgram(ProgramRequest programRequest) {
        log.info("adding Program : Inside Service");

        ProgramEntity newProgram = new ProgramEntity();
        BeanUtils.copyProperties(programRequest, newProgram);

        AddressRequest addressRequest = programRequest.getAddress();
        AddressEntity newAddress = new AddressEntity();

        BeanUtils.copyProperties(addressRequest, newAddress);
        newProgram.setAddress(newAddress);

        ProgramEntity savedProgram = null;
        try {
            savedProgram = programRepository.save(newProgram);
        } catch (DataIntegrityViolationException dve) {
            log.error("Error in saving Program : Inside Service due to dve", dve);
        } catch (Exception e) {
            log.error("Error in saving Program : Inside Service", e);
        }
        if (savedProgram == null) return null;

        return "CREATED SUCCESSFULLY";

    }

    @Override
    public List<ProgramResponse> getAllPrograms() {
        log.info("All Programs :Inside Service");

        List<ProgramEntity> all = programRepository.getAllPrograms();

        log.info("Program Entity {}", all);

        List<ProgramResponse> responses = new ArrayList<>();

        all.forEach(program -> {

            ProgramResponse response = new ProgramResponse();
            BeanUtils.copyProperties(program, response);

            AddressResponse addressResponse = new AddressResponse();
            BeanUtils.copyProperties(program.getAddress(), addressResponse);

            response.setAddress(addressResponse);
            responses.add(response);
        });

        return responses;
    }

    @Transactional
    @Override
    public ProgramResponse updateProgram(ProgramRequest programRequest, UUID id) {

        log.info("Updating programRequest with id " + id);

        Optional<ProgramEntity> optionalProgramEntity = programRepository.findById(id);

        optionalProgramEntity.ifPresentOrElse(programEntity -> {

                    AddressEntity addressEntity = addressRepository.findById(programEntity.getAddress().getId())
                                    .orElseThrow( ()-> new ResourceNotFoundException("No Such Address found"));

                    BeanUtils.copyProperties(programRequest.getAddress(), addressEntity);
                    BeanUtils.copyProperties(programRequest, programEntity);

                },
                () -> {
                    throw new ResourceNotFoundException("No such user with exists with id " + id);
                }
        );

        ProgramEntity saved = programRepository.save(optionalProgramEntity.get());

        ProgramResponse response = null;
        if (saved != null) {
            response = new ProgramResponse();
            BeanUtils.copyProperties(saved, response);
            AddressResponse addressResponse = new AddressResponse();
            BeanUtils.copyProperties(saved.getAddress(), addressResponse);
            response.setAddress(addressResponse);
        }

        return response;
    }
}
