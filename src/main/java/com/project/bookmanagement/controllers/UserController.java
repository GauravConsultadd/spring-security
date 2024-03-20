package com.project.bookmanagement.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookmanagement.Dtos.RegistrationDto;

import com.project.bookmanagement.services.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Validated @RequestBody RegistrationDto user) {
        try {
            userServiceImpl.createUser(user);
            Map<String,Object> resp=new HashMap<>();
            resp.put("user", user);
            resp.put("message", "User created successfully");

            return new ResponseEntity<>(resp,HttpStatus.OK);

        }
        catch(Exception err) {
            System.out.println(err);
            Map<String,String> resp =new HashMap<>();
            resp.put("message", err.getMessage());
            return new ResponseEntity<>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/normal")
    public String getMethodName(@RequestParam String param) {
        return "Users";
    }
    
}
