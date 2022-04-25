package com.examly.springapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Product;
import com.examly.springapp.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Long>{
	List<Review> findAllByProduct(Product product);
}

