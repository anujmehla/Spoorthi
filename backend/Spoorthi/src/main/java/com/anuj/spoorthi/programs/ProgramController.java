package com.anuj.spoorthi.programs;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/programs")
public class ProgramController {

    @Autowired
    ProgramService programService;

    @PostMapping("/create")
    public ResponseEntity<?> addProgram(@Valid @RequestBody ProgramRequest program , BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        String status = programService.addProgram(program);
        if(status == null){
            return new ResponseEntity<>("Program NOT CREATED" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(status,HttpStatus.CREATED);

    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllPrograms(){
        List<ProgramResponse> allPrograms = programService.getAllPrograms();

        return new ResponseEntity<>(allPrograms,HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProgram(@Valid @RequestBody ProgramRequest program ,
                                           @PathVariable("id") UUID id){

        ProgramResponse response = programService.updateProgram(program, id);
        String message = "UPDATED SUCCESSFULLY";
        if(response == null) message = "UPDATE FAILED";

        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
