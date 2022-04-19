package com.examly.springapp.util;
import java.sql.Date;

import org.springframework.stereotype.Component;

import com.example.demo.model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthentication {
	private static String secretKey="My_secret_key";
	 private static long expiryDuration = 60 * 60;
	public String generateJwt(UserModel user) {
		long milliTime = System.currentTimeMillis();
       long expiryTime = milliTime + expiryDuration * 1000;

       Date issuedAt = new Date(milliTime);
       Date expiryAt = new Date(expiryTime);
       Claims claims = Jwts.claims()
               .setIssuer(user.getId().toString())
               .setIssuedAt(issuedAt)
               .setExpiration(expiryAt);

       
       claims.put("role", user.getRole());
       claims.put("name", user.getUsername());
       claims.put("emailId", user.getEmail());
       claims.put("phno", user.getPhoneNumber());
       
       return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();

	}
}
