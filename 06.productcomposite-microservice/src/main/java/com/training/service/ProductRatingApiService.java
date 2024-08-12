package com.training.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.training.entity.Rating;

@FeignClient("productratings-microservice")
public interface ProductRatingApiService {

	@GetMapping("/rating/{productId}")
	public List<Rating> getRatings(@PathVariable int productId);

}
