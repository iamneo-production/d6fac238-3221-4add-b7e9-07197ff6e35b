package com.examly.springapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	@Query( value =  "select * from product p where p.product_name like concat('%',:product_name,'%')",nativeQuery = true)
	public List<Product> findByName(@Param("product_name") String product_name);
}
