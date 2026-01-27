package com.aitpune.Journal.controller;

import com.aitpune.Journal.entity.User;
import com.aitpune.Journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            // Check if user already exists
            User existingUser = userService.findByUsername(user.getUsername());
            if (existingUser != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Username already exists: " + user.getUsername());
            }

            userService.saveNewUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error creating user: " + e.getMessage());
        }
    }
    @GetMapping("/health-check")
    public String healthchecker(){
        return "OK";
    }
}
