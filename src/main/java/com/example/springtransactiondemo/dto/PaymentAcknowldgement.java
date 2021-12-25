package com.example.springtransactiondemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentAcknowldgement {

   private String message;
   private String transactionId;
}
