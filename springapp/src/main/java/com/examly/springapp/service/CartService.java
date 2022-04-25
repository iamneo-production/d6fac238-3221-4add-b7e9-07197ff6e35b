package com.examly.springapp.service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


import com.examly.springapp.dto.AddtoCart;
import com.examly.springapp.dto.CartItemDto;
import com.examly.springapp.model.Cart;
import com.examly.springapp.model.CartItem;
import com.examly.springapp.model.Product;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.CartItemRepo;
import com.examly.springapp.repository.CartRepo;
import com.examly.springapp.responseEntity.ResponseHandler;



@Service
public class CartService {
	@Autowired
	CartRepo cartRepo;
	@Autowired
	CartItemRepo cartItemRepo;
	@Autowired
	ProductService productService;
	public CartService(CartRepo cartRepo) {
		super();
		this.cartRepo = cartRepo;
	}

	public Cart addtoCartFirstTime(AddtoCart addDto, Product product, UserModel user) {
		Cart cartlocal=new Cart();
		CartItem cartItem=new CartItem();
		cartItem.setQuantity(addDto.getQuantity());
		cartItem.setCreatedDate(new Date(System.currentTimeMillis()));
		cartItem.setProduct(product);
		cartlocal.setUser(user);
		cartlocal.getItems().add(cartItem);
		return cartRepo.save(cartlocal);
	}

	public Cart addToExistCart(AddtoCart addDto, Product product, UserModel user) {
		Cart cart=cartRepo.getCartByUserId(user.getId());
		Product productexist=productService.getOneProduct(product.getPid());
		Boolean productInCart=false;
		if(cart!=null) {

			List<CartItem> items=cart.getItems();
			for(CartItem item:items) {
				if(item.getProduct().equals(productexist)) {
				productInCart=true;
				item.setQuantity(item.getQuantity()+addDto.getQuantity());
				cart.setItems(items);
				return cartRepo.save(cart);
				}
			}
		}
		if((cart !=null) && !productInCart)
		{
			CartItem cartItem1=new CartItem();
			cartItem1.setCreatedDate(new Date(System.currentTimeMillis()));
			cartItem1.setProduct(product);
			cartItem1.setQuantity(addDto.getQuantity());
			cart.getItems().add(cartItem1);
			return cartRepo.save(cart);
		}
		
		return this.addtoCartFirstTime(addDto, productexist, user);
		
	}
	
	
	public Cart getCartByUser(long id) {
		
		return cartRepo.getCartByUserId(id);
	}

	
	public Cart editCartItem(CartItemDto cartItem,long user_id) {
		CartItem cartItemlocal=cartItemRepo.findById(cartItem.getCartItemId()).get();
		cartItemlocal.setQuantity(cartItem.getQuantity());
		cartItemRepo.save(cartItemlocal);
		return cartRepo.getCartByUserId(user_id);
	}
	
	public Cart deleteCartItem(long cartItemId,long user_id) {
		Cart cart=cartRepo.getCartByUserId(user_id);
		List<CartItem> items=cart.getItems();
		CartItem cartItemlocal=null;
		for(CartItem item:items) {
			if(item.getCartItemid()==cartItemId) {
				cartItemlocal=item;
			}
		}
		items.remove(cartItemlocal);
		cartItemRepo.delete(cartItemlocal);
		return cartRepo.getCartByUserId(user_id);
	}

	public String deleteCart(long id) {
		Cart cart=cartRepo.getCartByUserId(id);
		if(cart!=null) {
			cartRepo.delete(cart);
			return "Success";
		}
		else {
		return "User Not Found";
		}
	}
}
