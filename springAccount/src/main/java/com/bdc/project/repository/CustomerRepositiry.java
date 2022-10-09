package com.bdc.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdc.project.entities.Customer;

public interface CustomerRepositiry  extends JpaRepository<Customer, Long>{

}
