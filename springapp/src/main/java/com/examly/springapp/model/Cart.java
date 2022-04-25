package com.examly.springapp.model;
import java.util.ArrayList;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartid;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	private List<CartItem> items = new ArrayList<CartItem>();
	
	@JsonIgnore
	@OneToOne(targetEntity = UserModel.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private UserModel user;
	
	@Transient
	private Double total;

	public Long getCartid() {
		return cartid;
	}

	public void setCartid(Long cartid) {
		this.cartid = cartid;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}


	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Double getTotal() {
		Double sum = 0.0;
		for(CartItem item : this.items) {
			sum = sum + item.getProduct().getPrice()*item.getQuantity();
		}
		return sum;
	}

	
	
	
}
