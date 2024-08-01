package com.anuj.spoorthi.cards;

import java.util.List;
import java.util.UUID;

public interface CardService {
    String addCard(CardEntityDto cardDto);

    List<CardEntityDto> getAllCards();

    CardEntityDto getCard(UUID id);

}
