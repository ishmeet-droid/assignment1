package com.nagarro.assignment1.controller.backend;

import java.util.List;

// import org.springframework.web.bind.annotation.PathVariable;

import com.nagarro.assignment1.model.Transaction;

public interface BackendInterface {

    public  List<Transaction> getServerTransaction(String accountNumber );
    
}
