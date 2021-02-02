package com.tutorials.SpringSecurity101.converter;

import com.tutorials.SpringSecurity101.entity.UserEntity;
import com.tutorials.SpringSecurity101.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserEntityModelConverter {

    public User entityToModelConverter(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getFname(),
                userEntity.getLname(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole()
        );
    }

    public UserEntity modelToEntityConverter(User user){
        return new UserEntity(
                user.getId(),
                user.getFname(),
                user.getLname(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}
