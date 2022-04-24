package com.examly.springapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.exception.UserNotExistException;
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
	public ResponseEntity<Order> placeOrder(@RequestBody AddressDto address,@PathVariable (value="id") long id) throws UserNotExistException{
		UserModel user=userService.getOneUser(id);
		Order order= orderService.placeOrder(user,address);
		if(order!=null) {
			return new ResponseEntity<>(order,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/listorder/{id}")
	public ResponseEntity<List<Order>> orderList(@PathVariable (value="id") long id) throws UserNotExistException{
		UserModel user=userService.getOneUser(id);
		List<Order>  orders= orderService.listOrder(user);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	@GetMapping("/oneorder/{id}")
	public ResponseEntity<Order> oneOrder(@PathVariable (value="id") long id) {
		Order order= orderService.getOneOrder(id);
		if(order!=null) {
			return new ResponseEntity<>(order,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/allorders")
	public ResponseEntity<List<Order>> allOrders(){
		return new ResponseEntity<>(orderService.allOrders(),HttpStatus.OK);
	}
	@GetMapping("/cancelorder/{id}")
	public void cancelOrder(@PathVariable (value="id") long id) {
		 orderService.deleteOrder(id); 
	}
  }

