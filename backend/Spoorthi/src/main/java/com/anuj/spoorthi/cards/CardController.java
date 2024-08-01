package com.anuj.spoorthi.cards;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/cards")
@RestController()
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<?> addCard(@Valid @RequestBody CardEntityDto cardDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        String cardStatus = cardService.addCard(cardDto);
        if (cardStatus == null) {
            return new ResponseEntity<>("Card not added", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(cardStatus, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCards() {
        List<CardEntityDto> cardsList = cardService.getAllCards();

        if (cardsList.isEmpty()) {
            return new ResponseEntity<>("No cards found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cardsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCardById(@PathVariable UUID id) {
        CardEntityDto card = cardService.getCard(id);

        if (card == null) {
            return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(card, HttpStatus.OK);
    }
}
