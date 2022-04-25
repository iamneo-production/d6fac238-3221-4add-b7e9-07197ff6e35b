package com.examly.springapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Order;
import com.examly.springapp.model.OrderItem;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItem,Integer> {
	
}