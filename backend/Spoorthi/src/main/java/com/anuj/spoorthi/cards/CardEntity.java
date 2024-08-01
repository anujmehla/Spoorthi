package com.anuj.spoorthi.cards;

import com.anuj.spoorthi.shared.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "card")
public class CardEntity extends BaseEntity {

    @NotBlank
    private String heading;

    @NotBlank
    private String image;

    @NotBlank
    private String content;
}
