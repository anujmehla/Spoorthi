package com.anuj.spoorthi.cards;

import java.util.List;

public interface CardService {
    String addCard(CardEntityDto cardDto);

    List<CardEntityDto> getAllCards();

    CardEntityDto getCard(long id);

}
