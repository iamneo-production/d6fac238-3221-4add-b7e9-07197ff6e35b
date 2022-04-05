package com.examly.springapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Login;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.service.UserService;

@RestController
<<<<<<< HEAD
@CrossOrigin(origins="http://localhost:8081")
=======
@CrossOrigin(origins="https://8081-ebfacbcadfdfcfaadbbadeedfbffdec.examlyiopb.examly.io")
>>>>>>> BarkNBurr
public class UserController {
	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	@PostMapping("/signup")
	public ResponseEntity<Object> regUser(@RequestBody UserModel user) {
		return userService.saveUser(user);
		
	}
	@PostMapping("/login")
	public ResponseEntity<Object> logUser(@RequestBody Login user) {
		String email=user.getEmail();
		String password=user.getPassword();
		return userService.loginUser(email,password);
		
	}
	@PostMapping("/edituser/{id}")
	public ResponseEntity<Object> editAdminUser(@PathVariable (value="id")long id,@RequestBody UserModel user){
		return userService.editUser(user, id);
	}
	@GetMapping("/getuser/{id}")
	public UserModel getUserbyId(@PathVariable (value="id") long id) {
		return userService.getOneUser(id);
	}
	@GetMapping("/deleteuser/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable (value="id") long id){
		return userService.deleteUser(id);
	}
	@GetMapping("/alluser")
	public List<UserModel> allUser(){
		return userService.allUser();
	}
}
