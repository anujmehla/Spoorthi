package com.anuj.spoorthi.testimonials;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TestimonialService {

    String addTestimonial(TestimonialDTO testimonialDTO);

    List<TestimonialDTO> getAllTestimonials();

    boolean getATestimonial( UUID id);

    String updateTestimonial( TestimonialDTO testimonialDTO , UUID id);
}
