package com.luannascimento.cursomc.security;

import java.io.Serializable;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTUtil implements Serializable{


	private static final long serialVersionUID = 1L;

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
		
	
	private SecretKey getsignKey() {
				
		return Keys.hmacShaKeyFor(secret.getBytes()); 
	}
	
	public String genereteToken(String username) {
		return Jwts.builder()
				.subject(username)
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getsignKey(), Jwts.SIG.HS512)
				.compact();
}
	
	
}