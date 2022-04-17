package com.examly.springapp.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Product;
import com.examly.springapp.service.ProductService;

@RestController


@CrossOrigin(origins="https://8081-ebfacbcadfdfcfaadbbadeedfbffdec.examlyiopb.examly.io")

public class ProductController {
	private ProductService productserv;

	public ProductController(ProductService productserv) {
		super();
		this.productserv = productserv;
	}
	
	@GetMapping("/all")
	public List<Product> allProducts(){
		return productserv.getAllProducts();
	}
	@PostMapping("/addproduct")
	public ResponseEntity<Object> addProduct(@RequestBody Product product){
		return productserv.addProduct(product);
	}
	@PostMapping("/productEdit/{pid}")
	public ResponseEntity<Object> editProduct(@RequestBody Product product,@PathVariable  (value="pid")long pid){
		return productserv.updateProduct(product,pid);
	}
	@GetMapping("/delete/{pid}")
	public ResponseEntity<Void> deleteProduct(@PathVariable (value="pid")long pid){
		return productserv.deleteProduct(pid);
	}
	@GetMapping("/getproduct/{pid}")
	public Product showProduct(@PathVariable (value="pid")long id) {
		return productserv.getOneProduct(id);
	}
	
	
}

