package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@RestController
class JwtCheck {

	@RequestMapping("/token")
	public String requestMethodName(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
		return authorizedClient.getAccessToken().getTokenValue();
	}

}