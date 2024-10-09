package com.thethirdway.fwittewr.user;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.thethirdway.fwittewr.FwittewrApplication;
import com.thethirdway.fwittewr.model.User;
import com.thethirdway.fwittewr.repo.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = FwittewrApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Test
	@Order(2)
	public void testFindByEmail() {
		assertThat(repo.findByEmail("super@admin.com")).get()
		.extracting(User::getName)
		.isEqualTo("superadmin");
	}
	
	@Test
	@Order(1)
	public void testFindAllUsers() {
		assertEquals(1, repo.findAll().size());
	}
}
