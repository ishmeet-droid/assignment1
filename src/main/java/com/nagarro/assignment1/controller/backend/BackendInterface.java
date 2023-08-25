package com.nagarro.assignment1.controller.backend;

import java.util.List;
import java.util.concurrent.CompletableFuture;



import com.nagarro.assignment1.model.Transaction;

public interface BackendInterface {

    public  CompletableFuture<List<Transaction>> getServer(String accountNumber );
    
}
