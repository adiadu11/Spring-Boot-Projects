package com.superhero.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.superhero.security.MyUserDetailsService;
import com.superhero.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter
{
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	MyUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.replace("Bearer ", "");
			username = jwtUtil.extractUsername(jwt);
		}
		if (username != null) {
			UserDetails user = userDetailsService.loadUserByUsername(username);
			
			if (jwtUtil.validateToken(jwt, user)) {
				//Setting authentication token, this step would've been done by
				//Spring by default had we not intercepted the requests. But now
				//we're doing this only if token is valid.
				setAuthentication(user, request);
			}
		}
		//Continue the request chain.
		filterChain.doFilter(request, response);
	}

	/**
	 * Setting authentication in context using user and request.
	 * This is by default done by Spring but we have overridden since we
	 * want this to be done only if token is valid.
	 */
	private void setAuthentication(UserDetails user, HttpServletRequest request)
	{
		//Build default Spring authentication token.
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (
				user, null, user.getAuthorities());
		
		//Set details in the token using the request object.
		token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		//Set the authentication into the context.
		SecurityContextHolder.getContext().setAuthentication(token);
	}

}
