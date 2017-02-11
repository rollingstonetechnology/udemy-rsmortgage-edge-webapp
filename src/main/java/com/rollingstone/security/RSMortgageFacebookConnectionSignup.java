package com.rollingstone.security;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.rollingstone.controller.RSMortgageEdgeController;
import com.rollingstone.persistence.dao.UserRepository;
import com.rollingstone.persistence.model.RsMortgageUser;

@Service
public class RSMortgageFacebookConnectionSignup implements ConnectionSignUp {

	private final Logger slf4jLogger = LoggerFactory.getLogger(RSMortgageFacebookConnectionSignup.class);
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
    	slf4jLogger.info("signup === ");
        final RsMortgageUser user = new RsMortgageUser();
        user.setUsername(connection.getDisplayName());
        user.setPassword(randomAlphabetic(8));
        userRepository.save(user);
        return user.getUsername();
    }

}
