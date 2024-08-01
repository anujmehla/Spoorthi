package com.anuj.spoorthi.testimonials;

import com.anuj.spoorthi.customexceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
        TestimonialEntity testimonial = testimonialRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No testimonial with "+ id + " found"));

        return testimonial != null;
    }

    @Override
    public String updateTestimonial(TestimonialDTO testimonialDTO , UUID id) {
        String message = "";
        if(getATestimonial(id)){
            TestimonialEntity testimonialEntity  = new TestimonialEntity();
            BeanUtils.copyProperties(testimonialDTO, testimonialEntity);
            testimonialEntity.setId(id);
            testimonialRepository.save(testimonialEntity);
            message = "Testimonial UPDATED successfully";
        }
        return message;
    }


}
