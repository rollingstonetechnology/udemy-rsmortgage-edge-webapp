package com.rollingstone.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rollingstone.persistence.model.RSMortgagePasswordResetToken;


public interface PasswordResetTokenRepository extends JpaRepository<RSMortgagePasswordResetToken, Long> {

    RSMortgagePasswordResetToken findByToken(String token);

}
