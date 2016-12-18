package com.rollingstone.security;

import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rollingstone.persistence.dao.UserRepository;
import com.rollingstone.persistence.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	

	private final Logger slf4jLogger = LoggerFactory.getLogger(MyUserDetailsService.class);

    private static final String ROLE_USER = "ROLE_USER";
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public MyUserDetailsService() {
        super();
    }

    // API

    @Override
    public UserDetails loadUserByUsername(final String username) {
    	
    	slf4jLogger.info("Inside loadUserByUsername :"+username);
    	
        final User user = userRepository.findByUsername(username);
        if (user == null) {
        	slf4jLogger.info("Inside loadUserByUsername is null:");
            throw new UsernameNotFoundException(username);
        }
        else {
        	slf4jLogger.info("Inside loadUserByUsername is not null:"+username);
        }
//        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, getAuthorities(ROLE_USER));

    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
    
    public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
