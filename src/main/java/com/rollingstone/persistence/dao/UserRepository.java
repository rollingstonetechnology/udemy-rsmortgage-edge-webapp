package com.rollingstone.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rollingstone.persistence.model.RsMortgageUser;

public interface UserRepository extends JpaRepository<RsMortgageUser, Long> {

    RsMortgageUser findByEmail(String email);
    
    RsMortgageUser findByUsername(final String username);

}
