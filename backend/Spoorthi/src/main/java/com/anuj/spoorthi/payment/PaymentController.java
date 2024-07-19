package com.anuj.spoorthi.payment;

import com.anuj.spoorthi.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> addPayment(@Valid @RequestBody PaymentRequest paymentRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        String payment = paymentService.addPayment(paymentRequest);
        if (payment == null) {
            return new ResponseEntity<>("Couldn't add payment", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getAllPayments() {

        List<PaymentResponse> paymentResponseList = paymentService.getAllPayments();

        if (paymentResponseList.isEmpty()) {
            ApiResponse<List<PaymentResponse>> response = new ApiResponse<>("Couldn't get all payments");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        ApiResponse<List<PaymentResponse>> response = new ApiResponse<>(paymentResponseList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
