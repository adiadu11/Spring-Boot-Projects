package com.superhero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.superhero.model.AuthenticationResponse;
import com.superhero.model.LoginRequest;
import com.superhero.security.MyUserDetailsService;
import com.superhero.util.JwtUtil;

@RestController
public class LoginController
{
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest)
	{
		//This can throw BadCredentialsException
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
				loginRequest.getUserName(),
				loginRequest.getPassword()));
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwtUtil.generateToken(userDetails)), HttpStatus.OK);
	}
}
