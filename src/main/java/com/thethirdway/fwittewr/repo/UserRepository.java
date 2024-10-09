package com.thethirdway.fwittewr.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thethirdway.fwittewr.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("select u from User u where u.name = :name and u.password = :password")
	Optional<User> findByNameAndPassword(String name, String password); 
	@Query("select u from User u where u.id = :id")
	Optional<User> findById(long id);
	@Query("select u from User u where u.name = :name")
	Optional<User> findByName(String name);
	@Query("select u from User u where u.email = :email")
	Optional<User> findByEmail(String email);
}
