package com.examly.springapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.dto.AddressDto;
import com.examly.springapp.model.Order;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.service.OrderService;
import com.examly.springapp.service.UserService;

@RestController

@CrossOrigin(origins="https://8081-ebfacbcadfdfcfaadbbadeedfbffdec.examlyiopb.examly.io")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/placeorder/{id}")
	public Order placeOrder(@RequestBody AddressDto address,@PathVariable (value="id") long id) {
		UserModel user=userService.getOneUser(id);
		return orderService.placeOrder(user,address);
		
	}
	
	@GetMapping("/listorder/{id}")
	public List<Order> orderList(@PathVariable (value="id") long id){
		UserModel user=userService.getOneUser(id);
		return orderService.listOrder(user);
	}
	@GetMapping("/oneorder/{id}")
	public Order oneOrder(@PathVariable (value="id") long id) {
		return orderService.getOneOrder(id);
	}
	@GetMapping("/allorders")
	public List<Order> allOrders(){
		return orderService.allOrders();
	}
	@GetMapping("/cancelorder/{id}")
	public void cancelOrder(@PathVariable (value="id") long id) {
		 orderService.deleteOrder(id); 
	}
  }

