package com.bdc.project.repository;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bdc.project.entities.Customer;

@SpringBootTest
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepositiry customerRepositiry;
	
	@Test
	void createCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(0);
		customer.setFirstNAme("Bibin");
		customer.setLastName("job");
		customer.setEmail("bibin@gmail.com");
		
		Customer savedCustomer = customerRepositiry.save(customer);
		
		System.out.println(savedCustomer.getCustomerId());
		System.out.println(savedCustomer.getEmail());
				
	}

}
