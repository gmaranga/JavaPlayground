package com.example.gateway;

import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.junit.Assert;

public class GatewayApplicationLiveTest {

	
	@Test
	public void testAccess(){
		
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		String testUrl = "http://localhost:8080";
		
		ResponseEntity<String> response = testRestTemplate.getForEntity(testUrl + "/book-service/books",String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
	}
}
