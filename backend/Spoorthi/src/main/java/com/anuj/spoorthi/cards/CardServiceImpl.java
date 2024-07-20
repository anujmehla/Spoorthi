package com.anuj.spoorthi.cards;

import com.anuj.spoorthi.customexceptions.CardNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public String addCard(CardEntityDto cardDto) {
        CardEntity cardEntity = new CardEntity();
        if (cardDto.content() != null) {
            cardEntity.setContent(cardDto.content());
        }
        if (cardDto.heading() != null) {
            cardEntity.setHeading(cardDto.heading());
        }
        if (cardDto.image() != null) {
            cardEntity.setImage(cardDto.image());
        }
        CardEntity save = null;
        try {
            save = cardRepository.save(cardEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (save == null) {
            return null;
        }
        return "Card added successfully";
    }

    @Override
    public List<CardEntityDto> getAllCards() {
        List<CardEntity> cardEntities = cardRepository.findAll();
        List<CardEntityDto> cardDtos = new ArrayList<>();

        cardEntities.forEach(card -> {
            CardEntityDto dto = new CardEntityDto(
                    card.getHeading(),
                    card.getImage(),
                    card.getContent()
            );
            cardDtos.add(dto);
        });
        return cardDtos;
    }

    @Override
    public CardEntityDto getCard(long id) {
        CardEntity card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException("Card Not found with id : "+id));

        return new CardEntityDto(
                card.getHeading(),
                card.getImage(),
                card.getContent()
        );
    }
}
