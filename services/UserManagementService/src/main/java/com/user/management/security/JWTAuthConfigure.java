package com.user.management.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	 private JWTAuthenticationToken jwtTokenProvider;

	    public JWTAuthConfigure(JWTAuthenticationToken jwtTokenProvider) {
	        this.jwtTokenProvider = jwtTokenProvider;
	    }

	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	        JwtAuthFilter customFilter = new JwtAuthFilter(jwtTokenProvider);
	        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	    }

}
