package com.examly.springapp.dto;
public class CartItemDto {
	private long cartItemId;
	private int quantity;
	
	
	public CartItemDto() {
		super();
	}
	
	
	
	public long getCartItemId() {
		return cartItemId;
	}



	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}



	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
