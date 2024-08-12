package com.training.service;

import org.springframework.cloud.openfeign.FallbackFactory;

public class ProductApiServiceFallbackFactory implements FallbackFactory<ProductApiService> {
	
	@Override
	public ProductApiService create(Throwable cause) {
		return new ProductApiServiceFallback();
	}

}
