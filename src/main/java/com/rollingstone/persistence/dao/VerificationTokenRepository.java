package com.rollingstone.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rollingstone.persistence.model.RsMortgageVerificationToken;

public interface VerificationTokenRepository extends JpaRepository<RsMortgageVerificationToken, Long> {

    RsMortgageVerificationToken findByToken(String token);

}
