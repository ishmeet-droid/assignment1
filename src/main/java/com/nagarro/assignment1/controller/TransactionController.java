package com.nagarro.assignment1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.assignment1.BackendFactory;

import com.nagarro.assignment1.model.Transaction;


@RestController
@RequestMapping("/transactions")
public class TransactionController {


   
	private final WebClient webClient;
	
	
	public TransactionController(WebClient webClient) {
		this.webClient = webClient;
		
	}


	@GetMapping("/{accountNumber}")
	public  Map<String,List<Transaction>> getTransactionByStatus(@PathVariable String accountNumber,
													@RequestParam("status") String status){
	
	List<Transaction> trans = null;

	Map<String, List<Transaction>> groupedTransactions = null;
	
	 List<CompletableFuture<List<Transaction>>> futures = new ArrayList<>();

//	 concurrently calling all 3 back-end server points.....	        .collect(Collectors.groupingBy(Transaction::getAccountNumber));
	if(status.equalsIgnoreCase("ALL")) {
		
		// BackendInterface backend = new  BackendFactory(transRepo, webClient).getFactory(status);

	        // Request transactions from Back-end Server 1
	        futures.add(new BackendFactory(webClient).getFactory("success").getServer(accountNumber));

	        // Request transactions from Back-end Server 2
	        futures.add(new BackendFactory(webClient).getFactory("failure").getServer(accountNumber));

	        // Request transactions from Back-end Server 3
	        futures.add(new BackendFactory(webClient).getFactory("pending").getServer(accountNumber));

	       
	
	}else if(
			status.equalsIgnoreCase("success") ||
			status.equalsIgnoreCase("failure") ||
			status.equalsIgnoreCase("pending")){

		
	    // BackendInterface backend = new  BackendFactory(transRepo, webClient).getFactory(status);

		futures.add(new BackendFactory(webClient).getFactory(status).getServer(accountNumber));

		
	}
  // Wait for all requests to complete
	CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

	        // Combine responses into a single list
	trans = allFutures.thenApply(v ->
	                 futures.stream()
	                        .flatMap(future -> future.join().stream())
	                        .collect(Collectors.toList())).join();

	if(trans != null){
		groupedTransactions = trans.stream()
			.collect(Collectors.groupingBy(Transaction::getStatus));
	}


	return groupedTransactions;

	}
}
