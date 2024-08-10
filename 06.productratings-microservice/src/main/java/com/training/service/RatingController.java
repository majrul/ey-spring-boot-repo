package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.Rating;

@RestController
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@GetMapping("/rating/{productId}")
	public List<Rating> getRatings(@PathVariable int productId) {
		return ratingService.getRatings(productId);
	}
	
}
