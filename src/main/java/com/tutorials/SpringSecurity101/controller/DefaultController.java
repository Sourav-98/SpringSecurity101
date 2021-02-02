package com.tutorials.SpringSecurity101.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DefaultController {

    @GetMapping("/")
    public String home(){
        return new String("<h2>Welcome to Spring Security 101 Home Page</h2>");
    }

    @GetMapping("/user")
    public String user_home(){
        return new String("<h2>Welcome to Spring Security 101 User Home Page</h2>");
    }

    @GetMapping("/user/page1")
    public String user_page1(){
        return new String("<h2>This is Page 1 under User scope</h2>");
    }

    @GetMapping("/admin")
    public String admin_home(){
        return new String("<h2>Welcome to Spring Security 101 Admin Page</h2>");
    }

    @GetMapping("/admin/page1")
    public String admin_page1(){
        return new String("<h2>This is Page 1 under Admin scope</h2>");
    }

    @PostMapping("/test")
    public ResponseEntity<Map<String, String>> test(@RequestBody Map<String, String> req){
        return new ResponseEntity<>(req, HttpStatus.OK);
    }
}
