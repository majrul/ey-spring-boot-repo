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

@Service
public class ProductCompositeService {

	@Autowired
	private ProductApiService productApiService;

	@Autowired
	private ProductRatingApiService ratingApiService;
	
	public ProductAggregate getProduct(int productId) {
		Product product = productApiService.getProduct(productId);
		
		List<Rating> ratings = ratingApiService.getRatings(productId);
		
		ProductAggregate productAggregate = new ProductAggregate();
		BeanUtils.copyProperties(product, productAggregate);
		productAggregate.setRatings(ratings);
		
		return productAggregate;
	}
	
}
