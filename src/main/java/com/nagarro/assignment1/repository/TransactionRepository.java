package com.nagarro.assignment1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.assignment1.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
List<Transaction> findByStatus(String status);
List<Transaction> findByAccountNumberAndStatus(String accountNumber,String status);
List<Transaction> findByAccountNumber(String accountNumber);
}
