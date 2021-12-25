package com.example.springtransactiondemo.repository;

import com.example.springtransactiondemo.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfo,Long> {
}
