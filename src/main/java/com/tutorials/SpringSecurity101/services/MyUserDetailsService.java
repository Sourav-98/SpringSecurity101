package com.tutorials.SpringSecurity101.services;

import com.tutorials.SpringSecurity101.converter.UserEntityModelConverter;
import com.tutorials.SpringSecurity101.entity.UserEntity;
import com.tutorials.SpringSecurity101.model.User;
import com.tutorials.SpringSecurity101.model.UserPrincipal;
import com.tutorials.SpringSecurity101.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserEntityModelConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userDetailsRepository.findByEmail(s);
        if(userEntity == null){
            throw new UsernameNotFoundException("User "+ s + " not found!");
        }
        User user = userConverter.entityToModelConverter(userEntity);
        return new UserPrincipal(user);
    }
}
