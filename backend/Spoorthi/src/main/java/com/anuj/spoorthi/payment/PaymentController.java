package com.anuj.spoorthi.payment;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity<?> addPayment(@Valid @RequestBody PaymentRequest paymentRequest) {

        String payment = paymentService.addPayment(paymentRequest);
        if (payment == null) {
            return new ResponseEntity<>("Couldn't add payment", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

}
