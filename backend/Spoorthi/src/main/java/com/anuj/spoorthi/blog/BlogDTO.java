package com.anuj.spoorthi.blog;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;

public record BlogDTO(
        @NotBlank String title,
        @NotBlank String image,
        @NotBlank String content,
        @Temporal(TemporalType.TIMESTAMP)
        @DateTimeFormat
        @NotNull Instant publishDate,
        @NotBlank String authorName
        ) implements Serializable {
}
