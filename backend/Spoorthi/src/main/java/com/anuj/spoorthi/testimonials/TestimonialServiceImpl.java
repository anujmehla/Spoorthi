package com.anuj.spoorthi.testimonials;

import com.anuj.spoorthi.customexceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TestimonialServiceImpl implements TestimonialService{


    @Autowired
    private TestimonialRepository testimonialRepository;
    @Override
    public String addTestimonial(TestimonialDTO testimonialDTO) {
        Testimonial testimonialEntity = new Testimonial();
        BeanUtils.copyProperties(testimonialDTO , testimonialEntity);

        Testimonial savedEntity = testimonialRepository.save(testimonialEntity);

        if(savedEntity != null) return "Testimonial successfully added";
        else return "Testimonial not added";
    }

    @Override
    public List<TestimonialDTO> getAllTestimonials() {
        List<Testimonial> testimonials = testimonialRepository.findAll();

        List<TestimonialDTO> testimonialDTOS = new ArrayList<>();
        testimonials.forEach( testimonial -> {
            TestimonialDTO testimonialDTO = new TestimonialDTO(testimonial.getImage(),testimonial.getDescription());
            testimonialDTOS.add(testimonialDTO);
        });

        return testimonialDTOS;
    }

    @Override
    public boolean getATestimonial(int id) {
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No testimonial with "+ id + " found")
        );

        return true;

    }

    @Override
    public String updateTestimonial(TestimonialDTO testimonialDTO , int id) {
        String message = "";
        if(getATestimonial(id)){
            Testimonial testimonialEntity  = new Testimonial();
            BeanUtils.copyProperties(testimonialDTO, testimonialEntity);
            testimonialEntity.setId(id);
            testimonialRepository.save(testimonialEntity);
            message = "Testimonial UPDATED successfully";
        }
        return message;
    }


}
