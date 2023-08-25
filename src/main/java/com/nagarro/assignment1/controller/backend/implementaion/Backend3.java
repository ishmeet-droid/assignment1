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
@RequestMapping("/backendserver3")
public class Backend3 implements BackendInterface {

	private WebClient webClient;

	public Backend3(WebClient webClient) {
		this.webClient = webClient;
	}

	public CompletableFuture<List<Transaction>> getServer(String accountNumber) {

		return webClient.get()
				.uri("http://localhost:8085/backendserver3/pending/{accountNumber}", accountNumber)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Transaction>>() {
				})
				.toFuture();

	}

}
