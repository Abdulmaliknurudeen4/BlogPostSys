package com.intreswitch.articleblogsystemintv.security.jwt;

import java.io.IOException;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	//private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authToken = request.getHeader(JwtProperties.HEADER_STRING);
		// if the token is present
		if (authToken != null) {
			if (authToken != null && authToken.length() > 7) {
				// Bearer and the whitespace
				authToken = authToken.substring(7);
			}
			// get Username from the token if the authToken is not null
			// this if authToken!=null is improvised
			String username = jwtTokenUtils.getUsernameFromToken(authToken);
			// if the username is not null and the security context holding the
			// authentication is null
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// load user details
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				// get if the token of the user is valid
				boolean isValidUser = jwtTokenUtils.validateToken(authToken, userDetails);
				if (isValidUser) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}

		}
		// if not present then continue the remaining filters
		filterChain.doFilter(request, response);
	}

}
