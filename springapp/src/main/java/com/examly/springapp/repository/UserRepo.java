package com.examly.springapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Long>{
	@Query( value =  "select * from user_model where email=:email and password=:password",nativeQuery = true)
	public UserModel getUserByEmailandPassword(@Param("email") String email ,@Param("password") String password);
	
	
	@Query( value =  "select * from user_model where email=:email",nativeQuery = true)
	public UserModel getUserByEmail(@Param("email") String email);
}