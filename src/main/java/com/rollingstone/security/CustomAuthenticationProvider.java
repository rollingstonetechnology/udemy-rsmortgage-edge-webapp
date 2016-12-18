package com.rollingstone.security;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final Logger slf4jLogger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		
		slf4jLogger.info("Entered Name :"+name);

		// You can get the password here
		if (authentication.getCredentials() != null){
			String password = authentication.getCredentials().toString();
			
			slf4jLogger.info("Entered Password :"+password);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(name);
			
			slf4jLogger.info("Retreived Password :"+userDetails.getPassword());
			
			if (userDetails != null && userDetails.getPassword() != null && password != null){
				
				boolean validUser = passwordEncoder.matches(password, userDetails.getPassword());
				
				slf4jLogger.info("Password Matched ? :"+validUser);

				if (validUser){
					 Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
					 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(name, password, authorities);
					//Authentication auth = new UsernamePasswordAuthenticationToken(name, password);
					return authenticationToken;
				}
			}

		}

		// Your custom authentication logic here

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	

}
