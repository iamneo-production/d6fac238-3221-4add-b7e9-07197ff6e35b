package com.examly.springapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.dto.AddtoCart;
import com.examly.springapp.dto.CartItemDto;
import  com.examly.springapp.model.Cart;

import  com.examly.springapp.model.Product;
import com.examly.springapp.model.UserModel;

import com.examly.springapp.service.CartService;
import com.examly.springapp.service.ProductService;
import com.examly.springapp.service.UserService;

@RestController

@CrossOrigin(origins="https://8081-ebfacbcadfdfcfaadbbadeedfbffdec.examlyiopb.examly.io")

public class CartController {
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	
	@PostMapping("/cart/add/{id}")
	public Cart addtoCart(@RequestBody AddtoCart addDto,@PathVariable (value="id") long id){
		Product product=productService.getOneProduct(addDto.getProductId());
		UserModel user=userService.getOneUser(id);
		Cart cart=cartService.getCartByUser(id);
		if(cart!=null) {
			return cartService.addToExistCart(addDto, product, user);
		}
		else {
			return cartService.addtoCartFirstTime(addDto, product, user);
		
		}
		
	}
	
	@GetMapping("/list/{id}")
	public  Cart list(@PathVariable (value="id") long id) {
		return cartService.getCartByUser(id);
	}
	
	@PostMapping("/edit/{id}")
	public Cart updateCart(@RequestBody CartItemDto cartItem,@PathVariable (value="id") long id) {
		
		return cartService.editCartItem(cartItem, id);
	}
	
	@GetMapping("delete/{cartItemid}/{id}")
	public Cart deleteCartItem(@PathVariable (value="cartItemid") long cartItemid,@PathVariable (value="id") long id) {
		return cartService.deleteCartItem(cartItemid, id);
	}
	
	@GetMapping("/deletecart/{id}")
	public String deleteCart(@PathVariable (value="id")long id) {
		return cartService.deleteCart(id);
	}
	
	
}
