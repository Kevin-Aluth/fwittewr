package com.thethirdway.fwittewr.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thethirdway.fwittewr.model.Post;
import com.thethirdway.fwittewr.model.User;

public interface PostRepository extends JpaRepository<Post, Long>{
	@Query("select p from Post p where p.id = :id")
	Optional<Post> findById(long id);
	
	@Query(nativeQuery = true, value = "select * from Post order by publishment_date desc")
	Optional<List<Post>> getPostsInDateOrder();
	
	@Query(nativeQuery = true, value = "select * from liked_posts where id_post = :postId")
	Optional<List<User>> getPostsLikes(long postId);
}
