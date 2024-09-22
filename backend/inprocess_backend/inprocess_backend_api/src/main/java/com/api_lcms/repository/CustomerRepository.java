package com.api_lcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_lcms.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
