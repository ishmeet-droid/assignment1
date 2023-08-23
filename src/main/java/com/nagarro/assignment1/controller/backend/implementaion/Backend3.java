package com.nagarro.assignment1.controller.backend.implementaion;

import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.assignment1.controller.backend.BackendInterface;
import com.nagarro.assignment1.model.Transaction;
import com.nagarro.assignment1.repository.TransactionRepository;

@RestController
@RequestMapping("/backendserver3")
public class Backend3 implements BackendInterface {
	// @Autowired
	private TransactionRepository transRepository;
	
	public Backend3(TransactionRepository transactionRepository){
		this.transRepository = transactionRepository ;
	}
//	@GetMapping("/backendserver3{accountNumber}")
//	public List<Transaction> getServer3Transaction(@PathVariable String accountNumber,@RequestParam("status") String status ){
//	
//	@GetMapping("/pending/{accountNumber}")	
//	public  Map<String, List<Transaction>> getServer3Transaction(@PathVariable String accountNumber ){
		//List<Transaction> trans = transRepository.findByStatus(status.toLowerCase());
		 
		
		//List<Transaction> trans = transRepository.findByAccountNumber(accountNumber);
		
	@Override
	@GetMapping("/pending/{accountNumber}")	
	public  List<Transaction> getServerTransaction(@PathVariable String accountNumber ){
		List<Transaction> trans = transRepository.findByAccountNumberAndStatus(accountNumber, "pending");

		return trans;
//		Map<String, List<Transaction>> groupedTransactions;
//		groupedTransactions = trans.stream()
//			        .collect(Collectors.groupingBy(Transaction::getAccountNumber));

//		 
//		return groupedTransactions;
	}

}
