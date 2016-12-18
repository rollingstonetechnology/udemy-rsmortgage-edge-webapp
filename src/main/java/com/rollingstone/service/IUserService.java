package com.rollingstone.service;

import com.rollingstone.persistence.model.PasswordResetToken;
import com.rollingstone.persistence.model.User;
import com.rollingstone.persistence.model.VerificationToken;
import com.rollingstone.validation.EmailExistsException;

public interface IUserService {

    User registerNewUser(User user) throws EmailExistsException;

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    PasswordResetToken getPasswordResetToken(String token);

    void changeUserPassword(User user, String password);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String token);

    void saveRegisteredUser(User user);

}
