package com.anuj.spoorthi.blog;

import com.anuj.spoorthi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Data
@Table(name = "blog")
@Entity
public class BlogEntity extends BaseEntity {

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
