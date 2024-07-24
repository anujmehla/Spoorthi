package com.anuj.spoorthi.testimonials;

import java.util.List;
import java.util.Optional;

public interface TestimonialService {

    String addTestimonial(TestimonialDTO testimonialDTO);

    List<TestimonialDTO> getAllTestimonials();

    boolean getATestimonial( int id);

    String updateTestimonial( TestimonialDTO testimonialDTO , int id);
}
