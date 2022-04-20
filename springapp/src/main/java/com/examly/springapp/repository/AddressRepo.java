package com.examly.springapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
