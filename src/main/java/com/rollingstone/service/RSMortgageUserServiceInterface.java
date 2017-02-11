package com.rollingstone.service;

import com.rollingstone.persistence.model.RSMortgagePasswordResetToken;
import com.rollingstone.persistence.model.RsMortgageUser;
import com.rollingstone.persistence.model.RsMortgageVerificationToken;
import com.rollingstone.validation.RsMortgageEmailExistsException;

public interface RSMortgageUserServiceInterface {

    RsMortgageUser registerNewUser(RsMortgageUser user) throws RsMortgageEmailExistsException;

    RsMortgageUser findUserByEmail(String email);

    void createPasswordResetTokenForUser(RsMortgageUser user, String token);

    RSMortgagePasswordResetToken getPasswordResetToken(String token);

    void changeUserPassword(RsMortgageUser user, String password);

    void createVerificationTokenForUser(RsMortgageUser user, String token);

    RsMortgageVerificationToken getVerificationToken(String token);

    void saveRegisteredUser(RsMortgageUser user);

}
