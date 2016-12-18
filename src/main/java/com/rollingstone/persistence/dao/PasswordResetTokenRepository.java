package com.rollingstone.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rollingstone.persistence.model.PasswordResetToken;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

}
