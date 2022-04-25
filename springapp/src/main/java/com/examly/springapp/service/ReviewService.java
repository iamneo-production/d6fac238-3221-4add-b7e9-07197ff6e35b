package com.examly.springapp.service;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.dto.ReviewDto;
import com.examly.springapp.model.Product;
import com.examly.springapp.model.Review;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.ReviewRepo;
@Service
public class ReviewService {
	@Autowired
	private ReviewRepo reviewRepo;
	
	
	public Review addReview(ReviewDto reviewDto, UserModel user,Product product) {
		Review review=new Review();
		review.setRating(reviewDto.getRating());
		review.setComment(reviewDto.getComment());
		review.setCreatedDate(new Date(System.currentTimeMillis()));
		review.setProduct(product);
		review.setUser(user);
		return reviewRepo.save(review);
	}
	public List<Review> showReview(Product product){
		return reviewRepo.findAllByProduct(product);
	}
	public Review getOneReview(long id) {
		return reviewRepo.findById(id).orElse(null);
	}
	
	public Review editReview(ReviewDto reviewDto) {
		Review review =reviewRepo.findById(reviewDto.getId()).orElse(null);
		if(review!=null) {
			review.setCreatedDate(new Date(System.currentTimeMillis()));
			review.setRating(reviewDto.getRating());
			review.setComment(reviewDto.getComment());
			return reviewRepo.save(review);
		}
		return null;
	}
	public void deleteReview(long id) {
		reviewRepo.deleteById(id); 
		
	}
	

}
