package com.nagarro.assignment1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactionDetails")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int amount;
	private String status;
	@JsonIgnore
	private String accountNumber;
	
	
	
	public Transaction() {
		super();
		
	}

	public Transaction(int amount,String status, String accountNumber) {
		this.amount = amount;
		this.status = status;
		this.accountNumber = accountNumber;
	}

	public Long getId() {
		return id;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
}
