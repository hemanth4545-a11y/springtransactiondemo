package com.example.springtransactiondemo.controller;

import com.example.springtransactiondemo.dto.PaymentAcknowldgement;
import com.example.springtransactiondemo.dto.PaymentRequest;
import com.example.springtransactiondemo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public PaymentAcknowldgement doPayment(@RequestBody PaymentRequest paymentRequest){
        return paymentService.getPaymentStatus(paymentRequest);
    }
}
