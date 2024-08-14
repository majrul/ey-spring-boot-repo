package com.training.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductApiServiceFallbackFactory implements FallbackFactory<ProductApiService> {
	
	@Override
	public ProductApiService create(Throwable cause) {
		return new ProductApiServiceFallback();
	}

}
