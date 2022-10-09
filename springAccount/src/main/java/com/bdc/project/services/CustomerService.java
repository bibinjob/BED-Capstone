package com.bdc.project.services;

import java.util.List;

import javax.validation.Valid;

import com.bdc.project.entities.Customer;
import com.bdc.project.exception.UserNotFoundException;

public interface CustomerService {

	
public List<Customer> getCustomers();
	
	
	public Customer getCustomer(long customerId) throws UserNotFoundException;
	
	public List<Customer> addCustomer(Customer cust);
	
	public List<Customer> deleteCustomer(long customerId);


	public  Customer updateCustomer(@Valid Customer cust, Long customerId);
}
