package com.example.springtransactiondemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customerInfo")
public class CustomerInfo {

    @Id
    @GeneratedValue
    private Long customerId;
    private String accountNO;
    private String customerName;


}
