package com.thethirdway.fwittewr.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "user")
	private List<Post> publishedPosts;
	@ManyToMany
	@JoinTable(name = "liked_posts", 
	joinColumns = @JoinColumn(name = "id_user", nullable = false), 
	inverseJoinColumns = @JoinColumn(name = "id_post", nullable = false), 
	uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user", "id_post"})})
	private List<Post> likedPosts;
	
	private Role role;
	private boolean deactivated;
}
