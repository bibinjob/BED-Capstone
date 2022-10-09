package com.bdc.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdc.project.entities.Account;
import com.bdc.project.entities.Customer;
import com.bdc.project.entities.Transfer;
import com.bdc.project.exception.BalanceNotEnoughException;
import com.bdc.project.exception.UserNotFoundException;
import com.bdc.project.repository.AccountRepositiry;
import com.bdc.project.repository.CustomerRepositiry;
import com.bdc.project.services.AccountService;
import com.bdc.project.services.CustomerService;

@RestController
public class Controller {

	@GetMapping("/accountss")
	public String getTest() {
		return "account";
	}

	@Autowired
	private AccountService accountService;
	@Autowired
	private CustomerService customerService;

	@Autowired

	/**
	 **********************************************
	 ************ ACCOUNT APIS ********************
	 **********************************************
	 */

	// GET ALL ACCOUNTS
	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		return this.accountService.getAccounts();
	}

	// GGET ACCOUNT WITH ID
	@GetMapping("/account/{accountId}")
	public Account getAccount(@PathVariable String accountId) {
		return this.accountService.getAccount(Long.parseLong(accountId));
	}

	// CREATE/ADD NEW ACCOUNT
	@PostMapping(path = "/accounts", consumes = "application/json")
	public List<Account> addAccount(@RequestBody Account acc) {
		return accountService.addAccounts(acc);
	}

	// CREATE/ADD NEW ACCOUNT
	@PostMapping(path = "/accounts2", consumes = "application/json")
	public ResponseEntity<List<Account>> addAccount2(@RequestBody Account acc) {
		return ResponseEntity.ok(accountService.addAccounts(acc));
	}

	// DELETE ACCOUNT WITH ID
	@DeleteMapping("/account/{accountId}")
	public List<Account> deleteAccount(@PathVariable String accountId) {
		return this.accountService.deleteAccount(Long.parseLong(accountId));
	}

	/**
	 **********************************************
	 ************ CUSTOMER APIS *******************
	 **********************************************
	 */

	// Get ALL CUSTOMERS
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return this.customerService.getCustomers();
	}

	// GGET CUSTOMER WITH ID
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable Long customerId) throws UserNotFoundException {
		return this.customerService.getCustomer(customerId);
	}

	// CREATE/ADD NEW CUSTOMER
	@PostMapping(path = "/customer", consumes = "application/json")
	public ResponseEntity<List<Customer>> addCustomer(@RequestBody @Valid Customer cust) {
		return ResponseEntity.ok(customerService.addCustomer(cust));
	}

	// DELETE Customer WITH ID
	@DeleteMapping("/customer/{customerId}")
	public List<Customer> deleteCustomer(@PathVariable String customerId) {
		return this.customerService.deleteCustomer(Long.parseLong(customerId));
	}

	// Update Customer details
	@PutMapping(path = "/customer/{customerId}", consumes = "application/json")
	public Customer updateCustomer(@PathVariable Long customerId, @RequestBody @Valid Customer cust) {
		return customerService.updateCustomer(cust, customerId);
	}

	/**
	 **********************************************
	 ************ CUSTOMER REF APIS *******************
	 **********************************************
	 */

	@Autowired
	CustomerRepositiry customerRepositiry;
	@Autowired
	AccountRepositiry accountRepositiry;

	@PutMapping("/customers/{custId}/accounts/{accId}")
	public Customer xrefCustomer(@PathVariable Long custId, @PathVariable Long accId) {
		Customer customer = customerRepositiry.findById(custId).get();
		Account account = accountRepositiry.findById(accId).get();
		customer.xrefAccount(account);
		return customerRepositiry.save(customer);
	}

	@PostMapping("/customers/{custId}/accounts")
	public Customer xrefCustomers(@RequestBody Account acc, @PathVariable Long custId) throws UserNotFoundException {
		Customer customer = customerService.getCustomer(custId);
		if (customer != null) {
			Account account = accountService.addAccount(acc);
			customer.xrefAccount(account);
			return customerRepositiry.save(customer);
		} else {
			throw new UserNotFoundException("CustomerId: " + custId + " not found");
		}
	}

	/**
	 **********************************************
	 ************ TRANSFER *******************
	 **********************************************
	 */

	@PutMapping("/accounts/transferFund")
	public Account fundTransfer(@RequestBody Transfer transaction) throws BalanceNotEnoughException {
		return this.accountService.fundTransfer(transaction);
	}

}
