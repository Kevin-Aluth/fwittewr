package com.thethirdway.fwittewr.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.thethirdway.fwittewr.model.Role;
import com.thethirdway.fwittewr.model.User;
import com.thethirdway.fwittewr.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

//this class is needed for generating jwt tokens
@Service
@RequiredArgsConstructor
public class TokenManagerService {
	private final UserService userService;
	
	@Value("${my.jwt.key}")
	private String secretKeyText;
	
	//get SecretKey as an object
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(secretKeyText.getBytes());
	}
	
	//create jwt object and "encrypt" it with signature
	public String createToken(User u) {
		long oneDay = 1000L * 60 * 60 * 24;
		
		return Jwts.builder().claims()
				.subject(u.getName())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + oneDay))
				.add("role", u.getRole())
				.add("randomToken", "random value with no meaning")
				.and().signWith(getSecretKey()).compact();
	}
	
	//"decrypt" jwt object
	private Claims getClaims(String token) {
		if (token == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		
		try {
			Claims c = Jwts.parser()
					.verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
			return c;
		} catch (JwtException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}
	
	//methods to get stuff from jwt payload claims 
	
	public Date getCreationDate(String token) {
		return getClaims(token).getIssuedAt();
	}
	
	public Role getRole(String token) {
		return (Role)getClaims(token).get("role");
	}
	
	public LocalDateTime getExpirationDateTime(String token) {
		Date d = getClaims(token).getExpiration();
		return LocalDateTime.ofInstant(d.toInstant(), ZoneId.of("Europe/Rome"));
	}
	
	public User getUser(String token) {
		return userService.getUserByName(getClaims(token).getSubject());
	}
}
