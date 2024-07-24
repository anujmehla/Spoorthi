package com.anuj.spoorthi.testimonials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {


    @Autowired
    TestimonialService testimonialService;
    @Autowired
    private TestimonialRepository testimonialRepository;

    @PostMapping
    public ResponseEntity<String> addTestimonial(@RequestBody TestimonialDTO testimonialDTO){
        String message = testimonialService.addTestimonial(testimonialDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TestimonialDTO>> getAllTestimonials(){
        List<TestimonialDTO> allTestimonials = testimonialService.getAllTestimonials();
        return new ResponseEntity<>(allTestimonials, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editTestimonial(@RequestBody TestimonialDTO testimonialDTO , @PathVariable int id){
        String status = testimonialService.updateTestimonial(testimonialDTO, id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
