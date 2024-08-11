package com.anuj.spoorthi.testimonials;

import com.anuj.spoorthi.customexceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class TestimonialServiceImpl implements TestimonialService{

    private final TestimonialRepository testimonialRepository;

    TestimonialServiceImpl(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    @Override
    public String addTestimonial(TestimonialDTO testimonialDTO) {
        TestimonialEntity testimonialEntity = new TestimonialEntity();
        BeanUtils.copyProperties(testimonialDTO , testimonialEntity);

        TestimonialEntity savedEntity = testimonialRepository.save(testimonialEntity);

        if(savedEntity != null) return "Testimonial successfully added";
        else return "Testimonial not added";
    }

    @Override
    public List<TestimonialDTO> getAllTestimonials() {
        List<TestimonialEntity> testimonials = testimonialRepository.findAll();

        List<TestimonialDTO> testimonialDTOS = new ArrayList<>();
        testimonials.forEach( testimonial -> {
            TestimonialDTO testimonialDTO = new TestimonialDTO(testimonial.getImage(),testimonial.getDescription());
            testimonialDTOS.add(testimonialDTO);
        });

        return testimonialDTOS;
    }

    @Override
    public boolean getATestimonial(UUID id) {
        log.info("getting testimonial with id {}", id);
        TestimonialEntity testimonial = testimonialRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No testimonial with "+ id + " found"));

        return testimonial != null;
    }

    @Override
    public String updateTestimonial(TestimonialDTO testimonialDTO , UUID id) {
        String message = "";

        // Check if the testimonial exists
        Optional<TestimonialEntity> optionalTestimonial = testimonialRepository.findById(id);
        if (optionalTestimonial.isPresent()) {
            TestimonialEntity testimonialEntity = optionalTestimonial.get();

            // Copy properties from DTO to the existing entity
            BeanUtils.copyProperties(testimonialDTO, testimonialEntity, "id", "version");

            // Save the updated entity
            testimonialRepository.save(testimonialEntity);
            message = "Testimonial UPDATED successfully";
        } else {
            throw new ResourceNotFoundException("No Testimonial with id " + id + " found");
        }

        return message;
    }


}
