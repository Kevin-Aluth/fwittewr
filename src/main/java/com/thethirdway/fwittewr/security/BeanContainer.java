package com.thethirdway.fwittewr.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.thethirdway.fwittewr.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Configuration
public class BeanContainer {
	private final UserRepository repo;
	
	@Bean
	protected UserDetailsService getDetailsService() {
		return username -> repo.findByName(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Bean
	protected PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected AuthenticationManager getManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}
	
	@Bean
	protected AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider(); 
		
		dap.setUserDetailsService(getDetailsService());
		dap.setPasswordEncoder(getPasswordEncoder());
	
		return dap;
	}
	
}