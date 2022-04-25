package com.examly.springapp.repository;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Order;
import com.examly.springapp.model.UserModel;

@Repository
public interface OrderRepo  extends JpaRepository<Order, Long> {
    List<Order> findAllByUserOrderByCreatedDateDesc(UserModel user);

}