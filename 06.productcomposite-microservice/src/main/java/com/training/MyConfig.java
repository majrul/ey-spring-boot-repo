package com.training;

import java.util.Date;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;

@Configuration
public class MyConfig {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Retryer retryer() {
		//return new Retryer.Default(..);
		return new CustomRetryer(5, 2000L);
	}
	
	@Bean
	public ErrorDecoder feignErrorDecoder() {
		return new CustomFeignErrorDecoder();
	}
}

class CustomRetryer implements Retryer {
	
	private int retryMaxAttempt;

	private long retryInterval;

	private int attempt = 1;

	public CustomRetryer(int retryMaxAttempt, Long retryInterval) {
		this.retryMaxAttempt = retryMaxAttempt;
		this.retryInterval = retryInterval;
	}

	@Override
	public void continueOrPropagate(RetryableException e) {
		System.out.printf("\nFeign retry attempt %d due to %s", attempt, e.getMessage());

		if (attempt++ == retryMaxAttempt) {
			throw e;
		}
		try {
			Thread.sleep(retryInterval);
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
		}

	}

	@Override
	public Retryer clone() {
		return new CustomRetryer(retryMaxAttempt, retryInterval);
	}
}

class CustomFeignErrorDecoder implements ErrorDecoder {
	
	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {

		if (response.status() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
			System.out.println("Error while executing " + methodKey + " Error code " + HttpStatus.NOT_FOUND);
			return new RetryableException(response.status(), methodKey, null, new Date(System.currentTimeMillis()),
					response.request());
		}
		return defaultErrorDecoder.decode(methodKey, response);

	}

}