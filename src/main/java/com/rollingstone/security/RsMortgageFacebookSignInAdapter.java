package com.rollingstone.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

@Service
public class RsMortgageFacebookSignInAdapter implements SignInAdapter {
	
	private final Logger slf4jLogger = LoggerFactory.getLogger(RsMortgageFacebookSignInAdapter.class);

    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
    	slf4jLogger.info(" ====== Sign In adapter");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(connection.getDisplayName(), null, Arrays.asList(new SimpleGrantedAuthority("FACEBOOK_USER"))));
        return null;
    }
}
