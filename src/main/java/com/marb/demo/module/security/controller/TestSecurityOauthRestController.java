package com.marb.demo.module.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Call a service with an oauth token retrieved and added to the HEADER.
 * Also check that the user has the api/write scope
 * @see {@link com.marb.demo.module.iguanafix.controller.JobController#findJobById(long)}
 */
@RestController
@RequestMapping(path = "/test-security")
public class TestSecurityOauthRestController {

	private final OAuth2RestTemplate oAuth2RestTemplate;

	public TestSecurityOauthRestController(OAuth2RestTemplate oAuth2RestTemplate) {
		this.oAuth2RestTemplate = oAuth2RestTemplate;
	}

	@RequestMapping("/job-with-token")
	public String oauthTemplate() {
		ResponseEntity<String> response = oAuth2RestTemplate.getForEntity("http://localhost:8081/job/1", String.class);
		return response.getBody();
	}
}
