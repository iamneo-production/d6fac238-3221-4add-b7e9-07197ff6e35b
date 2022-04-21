package com.examly.springapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Cart;



@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{
	
	@Query( value = "select * from cart where user_id=:user_id",nativeQuery = true)
	public Cart getCartByUserId(@Param("user_id") Long user_id);
	
	
}
