package com.thethirdway.fwittewr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Post {
	@Column(length = 512, nullable = false)
	private String content;
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User user;
	private boolean deactivated;
}
