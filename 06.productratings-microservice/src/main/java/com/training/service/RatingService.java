package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.entity.Rating;
import com.training.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	public List<Rating> getRatings(int productId) {
		return ratingRepository.findByProductId(productId);
		
	}
	
}
