package com.anuj.spoorthi.blog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Data
@Table(name = "blog")
@Entity
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    private String image;

    @NotBlank
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat
    @NotNull
    private Instant publishDate;

    @NotBlank
    private String authorName;
}
