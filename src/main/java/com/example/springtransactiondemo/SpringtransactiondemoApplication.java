package com.example.springtransactiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringtransactiondemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringtransactiondemoApplication.class, args);
	}

}
