package com.example.springtransactiondemo.dto;


import com.example.springtransactiondemo.entity.CustomerInfo;
import com.example.springtransactiondemo.entity.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {

  private CustomerInfo customerInfo;
  private PaymentInfo paymentInfo;

}
