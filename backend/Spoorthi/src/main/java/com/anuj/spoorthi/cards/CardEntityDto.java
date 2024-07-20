package com.anuj.spoorthi.cards;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link CardEntity}
 */

public record CardEntityDto(
        @NotBlank String heading,
        @NotBlank String image,
        @NotBlank String content) implements Serializable {
}