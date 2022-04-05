package com.examly.springapp.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


<<<<<<< HEAD
<<<<<<< HEAD
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepo;
import com.example.demo.responseEntity.ResponseHandler;
import com.example.demo.util.JwtAuthentication;
=======
=======
>>>>>>> BarkNBurr
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.responseEntity.ResponseHandler;
//import com.examly.springapp.JwtAuthentication;
<<<<<<< HEAD
>>>>>>> 43eca3de2a516ecb1bc8aa7d1c9b5f73d1efc33f
=======
>>>>>>> BarkNBurr

@Service
public class UserService {
	String role="User";
	@Autowired
	private UserRepo userRepo;
<<<<<<< HEAD
<<<<<<< HEAD
	@Autowired
	private JwtAuthentication jwt;
=======
	//@Autowired
	//private JwtAuthentication jwt;
>>>>>>> 43eca3de2a516ecb1bc8aa7d1c9b5f73d1efc33f
=======
	//@Autowired
	//private JwtAuthentication jwt;
>>>>>>> BarkNBurr
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
<<<<<<< HEAD
<<<<<<< HEAD
		String token = jwt.generateJwt(userlocal);
		return ResponseHandler.generateLoginResponse("Login Success",token, HttpStatus.OK, userlocal);
=======
		//String token = jwt.generateJwt(userlocal);
		return ResponseHandler.generateResponse("Login Success", HttpStatus.OK, userlocal);
>>>>>>> 43eca3de2a516ecb1bc8aa7d1c9b5f73d1efc33f
=======
		//String token = jwt.generateJwt(userlocal);
		return ResponseHandler.generateResponse("Login Success", HttpStatus.OK, userlocal);
>>>>>>> BarkNBurr
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