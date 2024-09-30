package com.thethirdway.fwittewr.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thethirdway.fwittewr.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	@Query("select p from Post p where p.id = :id")
	Optional<Post> findById(long id);
}
