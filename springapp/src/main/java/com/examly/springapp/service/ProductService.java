package com.examly.springapp.service;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.ProductNotExistException;
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
		
			Optional<Product> productexist=proRepo.findById(id);
			if(!productexist.isPresent()) {
				  throw new ProductNotExistException("Product id is invalid " + id);
			}
			return productexist.get();
		
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
		Optional<Product> product=proRepo.findById(pid);
		if(!product.isPresent()) {
			throw new ProductNotExistException("Product id is invalid " + pid);
		}
		int quan=product.get().getQuantity()-quantity;
		product.get().setQuantity(quan);
		proRepo.save(product.get());
		
	}
	public List<Product> searchProduct(String product_name)
	{
		
			List<Product> prdt=proRepo.findByName(product_name);
			return prdt;
		
	}
}

