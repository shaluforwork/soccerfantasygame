package com.soccerfantasy.app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soccerfantasy.app.model.request.UserRequestModel;
import com.soccerfantasy.app.service.UserService;
import com.soccerfantasy.app.util.AppConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private UserService userService;
	private Environment env;

	public AuthenticationFilter(AuthenticationManager authenticationManager,
			UserService userService, 
			Environment env) {
		this.authenticationManager = authenticationManager;
		this.env = env;
		this.userService = userService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException {
		try {
			UserRequestModel creds = new ObjectMapper()
					.readValue(req.getInputStream(), UserRequestModel.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getEmail(),
							creds.getPassword(),
							new ArrayList<>())
					);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String userName = ((User) auth.getPrincipal()).getUsername();  

		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + AppConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("tokenSecret") )
				.compact();
		UserDetails userDetails = userService.loadUserByUsername(userName);

		res.addHeader(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + token);
		res.addHeader("UserID", userDetails.getUsername());

	}  

}
