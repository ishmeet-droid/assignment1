package com.nagarro.assignment1.controller.backend.implementaion;

import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.assignment1.controller.backend.BackendInterface;
import com.nagarro.assignment1.model.Transaction;
import com.nagarro.assignment1.repository.TransactionRepository;

@RestController
@RequestMapping("/backendserver1")
public class Backend1 implements BackendInterface{

	// @Autowired
	private TransactionRepository transRepository;
	public Backend1(TransactionRepository transactionRepository){
		this.transRepository = transactionRepository ;
	}
//	@GetMapping("/backendserver1/{accountNumber}")
//	public List<Transaction> getServer1Transaction(@PathVariable String accountNumber,@RequestParam("status") String status ){
	
	
//	@GetMapping("/success/{accountNumber}")	
//	public Map<String, List<Transaction>> getServer1Transaction(@PathVariable String accountNumber ){
		//List<Transaction> trans = transRepository.findByStatus(status.toLowerCase());
		 
		
		//List<Transaction> trans = transRepository.findByAccountNumber(accountNumber);
	
	@Override
	@GetMapping("/success/{accountNumber}")	
	public List<Transaction> getServerTransaction(@PathVariable String accountNumber ){
		System.out.println(accountNumber);
		List<Transaction> trans = this.transRepository.findByAccountNumberAndStatus(accountNumber, "success");
		
		return trans;
//		Map<String, List<Transaction>> groupedTransactions;
//		groupedTransactions = trans.stream()
//		        .collect(Collectors.groupingBy(Transaction::getAccountNumber));
//		return groupedTransactions;
	}

}
