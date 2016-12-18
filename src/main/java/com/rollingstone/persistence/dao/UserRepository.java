package com.rollingstone.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rollingstone.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    
    User findByUsername(final String username);

}
