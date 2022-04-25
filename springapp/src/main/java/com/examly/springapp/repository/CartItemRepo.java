package com.examly.springapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.CartItem;
@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long>{

}
