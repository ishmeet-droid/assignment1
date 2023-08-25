package com.nagarro.assignment1.controller.backend.implementaion;

import java.util.List;

import java.util.concurrent.CompletableFuture;

import org.springframework.core.ParameterizedTypeReference;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.assignment1.controller.backend.BackendInterface;
import com.nagarro.assignment1.model.Transaction;


@RestController
@RequestMapping("/backendserver2")
public class Backend2 implements BackendInterface {
	
	private WebClient webClient;

	public Backend2(WebClient webClient) {
		this.webClient = webClient;
	}

	public CompletableFuture<List<Transaction>> getServer(String accountNumber) {

		return webClient.get()
				.uri("http://localhost:8084/backendserver2/failure/{accountNumber}", accountNumber)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Transaction>>() {
				})
				.toFuture();

	}

}
