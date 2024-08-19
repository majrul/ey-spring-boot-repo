package com.training;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity(proxyTargetClass = true)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*@Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter(new ServletBearerExchangeFilterFunction())
                .build();
    }*/
	
	@Bean
	RestTemplate rest() {
		RestTemplate rest = new RestTemplate();
		rest.getInterceptors().add((request, body, execution) -> {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null) {
				return execution.execute(request, body);
			}

			if (!(authentication.getCredentials() instanceof AbstractOAuth2Token)) {
				return execution.execute(request, body);
			}

			AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
		    request.getHeaders().setBearerAuth(token.getTokenValue());
		    return execution.execute(request, body);
		});
		return rest;
	}

}

@RestController
class JustChecking {
	
	@Value("${myapp.somesecret}")
	private String secret;
	
	@PostConstruct
	void init() {
		System.out.println("====>" + secret);
	}
	
	@GetMapping("/customer/ping")
	@PreAuthorize("hasAuthority('SCOPE_write_access-scope')")
	//@Secured("hasAuthority('abcd')")
	public String ping() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		return "Scopes " + auth.getAuthorities();
	}
	
}