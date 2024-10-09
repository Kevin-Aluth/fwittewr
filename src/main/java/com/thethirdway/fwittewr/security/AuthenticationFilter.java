package com.thethirdway.fwittewr.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.thethirdway.fwittewr.model.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
//this extention makes it so the filter method gets called on every request
//we are here in the filter area, in between the client request and our endpoints
public class AuthenticationFilter extends OncePerRequestFilter {
	private final TokenManagerService service;
	
	//check for authorization
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//search for header content
		String headerToken = request.getHeader("Authorization");
		
		//jwt "encrypted" objects always start with "Bearer "
		if (headerToken != null && headerToken.startsWith("Bearer ")) {
			//see if  authentication isn't already done
			if (SecurityContextHolder.getContext().getAuthentication() == null) {
				User u = null; 
				//try to find already "logged" user
				try {
					u = service.getUser(headerToken);
				} catch (ResponseStatusException e) {
					response.sendError(e.getStatusCode().value(), e.getMessage());
					return;
				}
				//set authentication up
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
			
			}
		}
		//move on from the filter
		filterChain.doFilter(request, response);
	}
}
