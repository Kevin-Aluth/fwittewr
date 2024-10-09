package com.thethirdway.fwittewr.user;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.thethirdway.fwittewr.FwittewrApplication;

@SpringBootTest
@ContextConfiguration(classes = {FwittewrApplication.class})
@AutoConfigureMockMvc
public class RegisterUserTest {
	
	@Autowired
	private MockMvc mock;
	
	@Test
	public void registerUserWithNoBody() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/register")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andReturn();
	}
	
	@Test
	public void registerUserWithNoName() throws Exception {
		String json = """
				{
					"email" : "lollogiunta@gmail.com",
					"password" : "ciao123",
					"repeatPassword" : "ciao123"
				}
				"""; 
		postTest(json, MockMvcResultMatchers.status().isBadRequest(), "/register");
	}
	
	@Test
	public void registerUserWithNoEmail() throws Exception {
		String json = """
				{
					"name" : "Lorenzo",
					"password" : "ciao123",
					"repeatPassword" : "ciao123"
				}
				"""; 
		postTest(json, MockMvcResultMatchers.status().isBadRequest(), "/register");
	}
	
	@Test
	public void registerUserWithBody() throws Exception {
		String json = """
				{
					"name" : "Lorenzo",
					"email" : "lollogiunta@gmail.com",
					"password" : "ciao123",
					"repeatPassword" : "ciao123"
				}
				"""; 
		postTest(json, MockMvcResultMatchers.status().isOk(), "/register");
	}
	
	@WithUserDetails("superadmin")
	@Test
	public void getInfo() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/user/info")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}
	
	private void postTest(String json, ResultMatcher matcher, String url) throws Exception {
		mock.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(matcher)
		.andReturn();
	}
}
