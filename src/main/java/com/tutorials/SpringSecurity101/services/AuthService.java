package com.tutorials.SpringSecurity101.services;


import com.tutorials.SpringSecurity101.converter.UserEntityModelConverter;
import com.tutorials.SpringSecurity101.entity.UserEntity;
import com.tutorials.SpringSecurity101.model.User;
import com.tutorials.SpringSecurity101.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserEntityModelConverter userConverter;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public void addNewUser(User user){
        UserEntity userEntity = userConverter.modelToEntityConverter(user);
        userDetailsRepository.saveAndFlush(userEntity);
    }

}
