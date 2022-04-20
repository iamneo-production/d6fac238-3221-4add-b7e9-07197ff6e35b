package com.examly.springapp.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.responseEntity.ResponseHandler;
import com.examly.springapp.util.JwtAuthentication;
@Service
public class UserService {
	String role="User";
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JwtAuthentication jwt;
	public UserService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	public ResponseEntity<Object> saveUser(UserModel user) {
		try {
		if(user.getRole()!="Admin" && user.getRole()==null) {	
		user.setRole(role);
		}
		UserModel userlocal= userRepo.save(user);
		return ResponseHandler.generateResponse("Signup Success", HttpStatus.OK, userlocal);
		}	
		catch(Exception e) {
			return ResponseHandler.generateResponse("Signup Failure", HttpStatus.BAD_REQUEST, null);
		}
	}
	

	public ResponseEntity<Object> loginUser(String email, String password) {
		UserModel userlocal=userRepo.getUserByEmailandPassword(email, password);
		if(userlocal==null) {
			return ResponseHandler.generateResponse("Login Failure", HttpStatus.BAD_REQUEST, null);
		}
		userlocal.setActive(true);
		userRepo.save(userlocal);
		String token = jwt.generateJwt(userlocal);
		return ResponseHandler.generateLoginResponse("Login Success",token, HttpStatus.OK, userlocal);
	}
	
	public UserModel findByEmail(String email) {
		try {
		UserModel userlocal=userRepo.getUserByEmail(email);
		return userlocal;
		}
		catch(Exception e) {
			return null;
		}
	}
	public ResponseEntity<Object> editUser(UserModel user,long id){
		try {
			
			UserModel userlocal=userRepo.findById(id).orElse(null);
			userlocal.setEmail(user.getEmail());
			userlocal.setPhoneNumber(user.getPhoneNumber());
			userlocal.setPassword(user.getPassword());
			userlocal.setUsername(user.getUsername());
			userlocal.setRole(user.getRole());
			userlocal.setActive(user.isActive());
			userRepo.save(userlocal);
			return ResponseHandler.generateResponse("User Updated", HttpStatus.OK, userlocal);
			
		}catch(Exception e) {
			return ResponseHandler.generateResponse("User Not Updated",HttpStatus.BAD_REQUEST, null);
		}
	}
	public ResponseEntity<Void> deleteUser(long id){
		
			UserModel userexist= userRepo.findById(id).orElse(null);
			if(userexist==null) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			else {
			userRepo.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		
			
			}
	}
	public List<UserModel> allUser(){
		return userRepo.findAll();
	}

	public UserModel getOneUser(long id) {
		
		try { 
			UserModel userexist=userRepo.findById(id).orElse(null);
			return userexist;
	}
		catch(Exception e) {
			return null;
		}
}
		
}