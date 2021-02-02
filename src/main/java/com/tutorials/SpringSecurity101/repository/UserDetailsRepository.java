package com.tutorials.SpringSecurity101.repository;

import com.tutorials.SpringSecurity101.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);    // find a particular user by their email id

}
