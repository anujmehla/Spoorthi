package com.anuj.spoorthi.testimonials;

import com.anuj.spoorthi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "testimonial")
public class TestimonialEntity extends BaseEntity {

    @NotBlank
    private String image;

    @NotBlank
    private String description;


}