package com.training.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.entity.Product;
import com.training.entity.ProductAggregate;
import com.training.entity.Rating;

//using RestTemplate to call other Apis
@Service
public class ProductCompositeService2 {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${product-service.url}")
	private String productServiceUrl;
	
	@Value("${rating-service.url}")
	private String ratingServiceUrl;
	
	public ProductAggregate getProduct(int productId) {
		String productUrl = productServiceUrl + "/product/{id}";
		Product product = restTemplate.getForObject(productUrl, Product.class, productId);
		
		String ratingUrl = ratingServiceUrl + "/rating/{id}";
		List<Rating> ratings = restTemplate.getForObject(ratingUrl, List.class, productId);
		
		ProductAggregate productAggregate = new ProductAggregate();
		BeanUtils.copyProperties(product, productAggregate);
		productAggregate.setRatings(ratings);
		
		return productAggregate;
	}
	
}
