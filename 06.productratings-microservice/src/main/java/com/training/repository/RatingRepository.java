package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	public List<Rating> findByProductId(int productId);
}
