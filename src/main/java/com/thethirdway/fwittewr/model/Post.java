package com.thethirdway.fwittewr.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 512, nullable = false)
	private String content;
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User user;
	private LocalDateTime publishmentDate;
	
	@ManyToMany(mappedBy = "likedPosts")
	private List<User> likedUsers; 
}
