package com.user.management.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.user.management.exception.CustomException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtAuthFilter extends GenericFilterBean{
	
	private JWTAuthenticationToken jwtAuthToken;
	
	 public JwtAuthFilter(JWTAuthenticationToken jwtAuthToken) {
	        this.jwtAuthToken = jwtAuthToken;
	    }
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
	
	 @Override
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
	            throws IOException, ServletException {
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        String token = jwtAuthToken.resolveToken((HttpServletRequest) req);
	        if (token != null) {
	            if (!jwtAuthToken.isTokenPresentInDB(token)) {
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid JWT token");
	                throw new CustomException("Invalid JWT token",HttpStatus.UNAUTHORIZED);
	            }
	            try {
	            	jwtAuthToken.validateToken(token) ;
	            } catch (JwtException | IllegalArgumentException e) {
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid JWT token");
	                throw new CustomException("Invalid JWT token",HttpStatus.UNAUTHORIZED);
	            }
	            Authentication auth = token != null ? jwtAuthToken.getAuthentication(token) : null;
	            //setting auth in the context.
	            SecurityContextHolder.getContext().setAuthentication(auth);
	        }
	        filterChain.doFilter(req, res);

	    }
	
	
}
