package com.examly.springapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.dto.ReviewDto;
import com.examly.springapp.model.Product;
import com.examly.springapp.model.Review;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.service.ProductService;
import com.examly.springapp.service.ReviewService;
import com.examly.springapp.service.UserService;

@RestController
@CrossOrigin(origins="https://8081-ebfacbcadfdfcfaadbbadeedfbffdec.examlyiopb.examly.io")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	public ReviewController(ReviewService reviewService, UserService userService, ProductService productService) {
		super();
		this.reviewService = reviewService;
		this.userService = userService;
		this.productService = productService;
	}

	@PostMapping("/addreview")
	public Review addReview(@RequestBody ReviewDto reviewDto) {
		UserModel user=userService.getOneUser(reviewDto.getUserid());
		Product product=productService.getOneProduct(reviewDto.getProductid());
		return reviewService.addReview(reviewDto,user,product);
	}
	@GetMapping("/listreview/{pid}")
	public List<Review> listReview(@PathVariable long pid){
		Product product=productService.getOneProduct(pid);
		return reviewService.showReview(product);
	}
	@GetMapping("/getonereview/{id}")
	public Review getReview(@PathVariable long id) {
		return reviewService.getOneReview(id);
	}
	@PostMapping("/editreview")
	public Review editReview(@RequestBody ReviewDto reviewDto) {
		return reviewService.editReview(reviewDto);
	}
	@GetMapping("/deletereview/{id}")
	public void deleteReview(@PathVariable long id) {
		reviewService.deleteReview(id);
	}
}
