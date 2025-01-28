package com.f5.taskf5.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class ImageControllere2e {
	
	@Autowired
	private WebTestClient webTestClient;
	//requires webflux dependency
	//act as a client making real request and examining response

	@Test
	@DisplayName("WHEN a GET /image request"
            +"THEN it should respond with 200 http status and a JSON list")
	void getImages() {
		webTestClient.get()
		             .uri("/images?userId=user123")
		             .exchange()
		             .expectStatus().isOk()
		             .expectBody()
		             .jsonPath("$").isArray();
	}

}
