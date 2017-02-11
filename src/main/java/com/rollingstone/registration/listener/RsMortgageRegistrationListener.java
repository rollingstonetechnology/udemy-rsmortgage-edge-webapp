package com.rollingstone.registration.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.rollingstone.persistence.model.RsMortgageUser;
import com.rollingstone.registration.RsMortgageOnRegistrationCompleteEvent;
import com.rollingstone.service.RSMortgageUserServiceInterface;

@Component
public class RsMortgageRegistrationListener implements ApplicationListener<RsMortgageOnRegistrationCompleteEvent> {

    @Autowired
    private RSMortgageUserServiceInterface service;
 
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    // API

    @Override
    public void onApplicationEvent(final RsMortgageOnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final RsMortgageOnRegistrationCompleteEvent event) {
        final RsMortgageUser user = event.getUser();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

    //

    private SimpleMailMessage constructEmailMessage(final RsMortgageOnRegistrationCompleteEvent event, final RsMortgageUser user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("Please open the following URL to verify your account: \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
