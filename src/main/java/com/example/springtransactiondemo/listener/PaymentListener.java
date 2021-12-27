package com.example.springtransactiondemo.listener;

import com.example.springtransactiondemo.dto.PaymentAcknowldgement;
import com.example.springtransactiondemo.dto.PaymentRequest;
import com.example.springtransactiondemo.service.PaymentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

@Component
@PropertySources({@PropertySource("classpath:payment.queue.properties")})
public class PaymentListener {

    @Autowired
    private PaymentService paymentService;

    @JmsListener(destination = "${request.payment.json.queue")
    public void makePayment(Message messsage) throws JMSException {
        TextMessage textMessage = (TextMessage)messsage;
        String jsonMessage= textMessage.getText();
        PaymentRequest paymentRequest = new Gson().fromJson(jsonMessage, PaymentRequest.class);
        PaymentAcknowldgement paymentAcknowldgement = paymentService.getPaymentStatus(paymentRequest);
        paymentService.sendMessage(paymentAcknowldgement);
    }

}
