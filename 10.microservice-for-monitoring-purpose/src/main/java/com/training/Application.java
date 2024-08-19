package com.training;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import io.micrometer.observation.aop.ObservedAspect;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	@Bean
	@Observed(name = "api-access", contextualName = "api-access-load-posts")
	CommandLineRunner commandLineRunner(JsonPlaceHolderService jsonPlaceHolderService) {
		return args ->
			jsonPlaceHolderService.findAll();
	}
	
	@Bean
	JsonPlaceHolderService jsonPlaceHolderService() {
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(JsonPlaceHolderService.class);
	}
	
	@Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }
}
