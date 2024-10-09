package com.thethirdway.fwittewr.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.thethirdway.fwittewr.model.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class FilterChainManager {
	private final AuthenticationFilter filter; 
	private final AuthenticationProvider provider; 
	
	@Bean
	//this bean sets up who can access certain endpoints and other browser options
	protected SecurityFilterChain getfilterChain(HttpSecurity http) throws Exception {
		http
		//disable safe browsers
		.csrf(AbstractHttpConfigurer::disable)
		//allow many requests (for testing purposes) remove when shipping
		//.headers(c->c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
		//set up authorizations (TODO: add authorizations)
		.authorizeHttpRequests(req->req
				.requestMatchers("user/**").hasRole(Role.USER.toString())
				.anyRequest().permitAll())
		//remove "stupid" languages
		.cors(AbstractHttpConfigurer::disable)
		//create a stateless session
		.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(provider)
		//add filter with class specified in auth filter
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
