package com.tutorials.SpringSecurity101.controller;

import com.tutorials.SpringSecurity101.converter.UserEntityModelConverter;
import com.tutorials.SpringSecurity101.entity.UserEntity;
import com.tutorials.SpringSecurity101.model.User;
import com.tutorials.SpringSecurity101.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    AuthService authService;

    @PostMapping("/newuser")
    public ResponseEntity<Map<String, String>> newUser(@RequestBody Map<String, String> req){
        Map<String, String> res = new HashMap<>();
        String password = passwordEncoder.encode(req.get("password"));
        User user = new User(req.get("fname"), req.get("lname"), req.get("email"), password, req.get("role"));
        authService.addNewUser(user);
        res.put("message", "User Added!");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
