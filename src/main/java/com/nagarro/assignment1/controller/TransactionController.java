package com.nagarro.assignment1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.assignment1.BackendFactory;
import com.nagarro.assignment1.controller.backend.BackendInterface;
import com.nagarro.assignment1.model.Transaction;
import com.nagarro.assignment1.repository.TransactionRepository;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	
	// @Autowired
	private TransactionRepository transRepo;
   
	private final WebClient webClient;
	
	
	public TransactionController(WebClient webClient, TransactionRepository transRepo) {
		this.webClient = webClient;
		this.transRepo = transRepo;
	}


	@GetMapping("/{accountNumber}")
	public  Map<String,List<Transaction>> getTransactionByStatus(@PathVariable String accountNumber,
													@RequestParam("status") String status){
	
	List<Transaction> trans = null;

	Map<String, List<Transaction>> groupedTransactions = null;
	
	
//	if(status.equalsIgnoreCase("ALL")) {
//	 trans = transRepo.findByAccountNumber(accountNumber);
//	 groupedTransactions = trans.stream()
//	 concurrently calling all 3 back-end server points.....	        .collect(Collectors.groupingBy(Transaction::getAccountNumber));
	if(status.equalsIgnoreCase("ALL")) {
		
		 List<CompletableFuture<List<Transaction>>> futures = new ArrayList<>();

	        // Request transactions from Back-end Server 1
	        futures.add(webClient.get()
	                .uri("/backendserver1/success/{accountNumber}", accountNumber)
	                .retrieve()
	                .bodyToMono(new ParameterizedTypeReference<List<Transaction>>() {})
	                .toFuture());

	        // Request transactions from Back-end Server 2
	        futures.add(webClient.get()
	                .uri("/backendserver2/failure/{accountNumber}", accountNumber)
	                .retrieve()
	                .bodyToMono(new ParameterizedTypeReference<List<Transaction>>() {})
	                .toFuture());

	        // Request transactions from Back-end Server 3
	        futures.add(webClient.get()
	                .uri("/backendserver3/pending/{accountNumber}", accountNumber)
	                .retrieve()
	                .bodyToMono(new ParameterizedTypeReference<List<Transaction>>() {})
	                .toFuture());

	        // Wait for all requests to complete
	        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

	        // Combine responses into a single list
	        trans = allFutures.thenApply(v ->
	                 futures.stream()
	                        .flatMap(future -> future.join().stream())
	                        .collect(Collectors.toList())
	        ).join();
	
	}else if(
			status.equalsIgnoreCase("success") ||
			status.equalsIgnoreCase("failure") ||
			status.equalsIgnoreCase("pending")){

		//TODO: instead of directly retriving pass it to factory method it will call repected server..
	   //  trans = transRepo.findByAccountNumberAndStatus(accountNumber, status);
	    BackendInterface backend = new  BackendFactory(transRepo).getFactory(status);

		trans = backend.getServerTransaction(accountNumber);
	}


	if(trans != null){
		groupedTransactions = trans.stream()
			.collect(Collectors.groupingBy(Transaction::getStatus));
	}


	return groupedTransactions;
//	return trans;
	}
}
