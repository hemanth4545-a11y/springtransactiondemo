package com.example.springtransactiondemo.service;

import com.example.springtransactiondemo.dto.PaymentAcknowldgement;
import com.example.springtransactiondemo.dto.PaymentRequest;
import com.example.springtransactiondemo.entity.CustomerInfo;
import com.example.springtransactiondemo.entity.PaymentInfo;
import com.example.springtransactiondemo.repository.CustomerInfoRepository;
import com.example.springtransactiondemo.repository.PaymentInfoRepository;
import com.example.springtransactiondemo.util.PaymentUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.TextMessage;

@Service
public class PaymentService {

    @Autowired
    PaymentInfoRepository paymentInfoRepository;

    @Autowired
    CustomerInfoRepository customerInfoRepository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${response.payment.success.queue}")
    private String paymentSuccessQueue;

    @Transactional
   public PaymentAcknowldgement getPaymentStatus(PaymentRequest paymentRequest){
       CustomerInfo customerInfo = paymentRequest.getCustomerInfo();
       customerInfoRepository.save(customerInfo);
       PaymentInfo paymentInfo = paymentRequest.getPaymentInfo();
       PaymentUtils.validatePaymentLimit(customerInfo.getAccountNO(),paymentInfo.getAmount());
       paymentInfo.setCustomerId(customerInfo.getCustomerId());
       paymentInfoRepository.save(paymentInfo);
       PaymentAcknowldgement paymentAcknowldgement = new PaymentAcknowldgement();
       paymentAcknowldgement.setTransactionId(paymentInfo.getPaymentId());
       paymentAcknowldgement.setMessage("success");
       return paymentAcknowldgement;
   }

    public void sendMessage(PaymentAcknowldgement paymentAcknowldgement)  {
        try{
         //   jmsTemplate.convertAndSend(paymentSuccessQueue, paymentAcknowldgement);
            jmsTemplate.send(paymentSuccessQueue, s -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                String s1= null;
                TextMessage textMessage = null;
                try {
                    s1 = objectMapper.writeValueAsString(paymentAcknowldgement);
                    textMessage = s.createTextMessage(s1);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                    return textMessage;
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
