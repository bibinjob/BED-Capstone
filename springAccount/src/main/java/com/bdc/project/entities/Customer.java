package com.bdc.project.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;



@Entity
@Table (
		name="customer",
		schema = "banking",
				uniqueConstraints = {
						@UniqueConstraint(
							columnNames = "customerId"
						)
				} 
		)
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private long customerId;
	@NotNull(message="firstName shouldnt be null")
	private String firstNAme;
	@NotNull(message="lastName shouldnt be null")
	private String lastName;
	@Email
	private String email;
	
	@ManyToMany
	@JoinTable(
			name="xref_custacc",
			joinColumns = @JoinColumn(name = "customerid"),
			inverseJoinColumns = @JoinColumn(name="accountId")
		)
	
	private Set<Account> custAccXref = new HashSet<>();

	
	




	public Customer(long customerId, String firstNAme, String lastName, String email) {
		super();
		this.customerId = customerId;
		this.firstNAme = firstNAme;
		this.lastName = lastName;
		this.email = email;
	}
	
	


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstNAme=" + firstNAme + ", lastName=" + lastName + ", email="
				+ email + "]";
	}




	public long getCustomerId() {
		return customerId;
	}




	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}




	public String getFirstNAme() {
		return firstNAme;
	}




	public void setFirstNAme(String firstNAme) {
		this.firstNAme = firstNAme;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Set<Account> getCustAccXref() {
		return custAccXref;
	}
	

	public void xrefAccount(Account account) {
		custAccXref.add(account);
		
	}
	
	
}
