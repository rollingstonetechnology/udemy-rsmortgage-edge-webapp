package com.rollingstone.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rollingstone.persistence.dao.PasswordResetTokenRepository;
import com.rollingstone.persistence.dao.UserRepository;
import com.rollingstone.persistence.dao.VerificationTokenRepository;
import com.rollingstone.persistence.model.RSMortgagePasswordResetToken;
import com.rollingstone.persistence.model.RsMortgageUser;
import com.rollingstone.persistence.model.RsMortgageVerificationToken;
import com.rollingstone.validation.RsMortgageEmailExistsException;

@Service
@Transactional
class RsMortgageUserService implements RSMortgageUserServiceInterface {

	private final Logger slf4jLogger = LoggerFactory.getLogger(RsMortgageUserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @Override
    public RsMortgageUser registerNewUser(final RsMortgageUser user) throws RsMortgageEmailExistsException {
        if (emailExist(user.getEmail())) {
            throw new RsMortgageEmailExistsException("There is an account with that email address: " + user.getEmail());
        }
        
        slf4jLogger.info("Kaka Paddword :"+user.getPassword());
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public RsMortgageUser findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(final RsMortgageUser user, final String token) {
        final RSMortgagePasswordResetToken myToken = new RSMortgagePasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public RSMortgagePasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token);
    }

    @Override
    public void changeUserPassword(final RsMortgageUser user, final String password) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void createVerificationTokenForUser(final RsMortgageUser user, final String token) {
        final RsMortgageVerificationToken myToken = new RsMortgageVerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    @Override
    public RsMortgageVerificationToken getVerificationToken(final String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void saveRegisteredUser(final RsMortgageUser user) {
    	slf4jLogger.info("Kakima password :"+user.getPassword());
    	
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private boolean emailExist(final String email) {
        final RsMortgageUser user = userRepository.findByEmail(email);
        return user != null;
    }

}
