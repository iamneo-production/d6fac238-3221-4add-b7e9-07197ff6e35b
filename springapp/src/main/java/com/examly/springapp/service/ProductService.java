package com.examly.springapp.service;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.examly.springapp.model.Product;
import com.examly.springapp.repository.ProductRepo;
import com.examly.springapp.responseEntity.ResponseHandler;

@Service
public class ProductService {
private ProductRepo proRepo;

public ProductService(ProductRepo proRepo) {
	super();
	this.proRepo = proRepo;
}

public List<Product> getAllProducts(){
	return proRepo.findAll();
}

public ResponseEntity<Object> addProduct( Product product){
	try {
		Product productlocal= proRepo.save(product);
		return ResponseHandler.generateResponse("Product Added", HttpStatus.OK, productlocal);	}
		catch(Exception e) {
			return ResponseHandler.generateResponse("Product Not Added", HttpStatus.BAD_REQUEST, null);
		}
}
public Product getOneProduct(long id){
	try {
		Product productexist=proRepo.findById(id).orElse(null);
		return productexist;
	}catch(Exception e) {
		return null;
	}
}
public ResponseEntity<Object> updateProduct(Product product, long pid){
	try {
		Product productexist= proRepo.findById(pid).orElseThrow(null);
		productexist.setProduct_name(product.getProduct_name());
		productexist.setPrice(product.getPrice());
		productexist.setDescription(product.getDescription());
		productexist.setQuantity(product.getQuantity());
		productexist.setImageUrl(product.getImageUrl());
		proRepo.save(productexist);
		return ResponseHandler.generateResponse("Product Updated", HttpStatus.OK, productexist);	}
		catch(Exception e) {
			return ResponseHandler.generateResponse("Product Not Found", HttpStatus.BAD_REQUEST, null);
		}
}
public ResponseEntity<Void> deleteProduct(long pid){
	try {
		proRepo.findById(pid).orElseThrow(null);
		proRepo.deleteById(pid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	catch(Exception e) {
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
}

public void changeQuantity(long pid, int quantity) {
	Product product=proRepo.findById(pid).orElse(null);
	int quan=product.getQuantity()-quantity;
	product.setQuantity(quan);
	proRepo.save(product);
	
}
public List<Product> searchProduct(String product_name)
{
	try {
		List<Product> prdt=proRepo.findByName(product_name);
		return prdt;
	}
	catch(Exception e) {
		return null;
	}
}


}

