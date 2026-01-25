package com.aitpune.Journal.controller;

import com.aitpune.Journal.entity.User;
import com.aitpune.Journal.repository.UserRepository;
import com.aitpune.Journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userinDb = userService.findByUsername(username);

        userinDb.setUsername(user.getUsername());
        userinDb.setPassword(user.getPassword());
        userService.saveNewUser(userinDb);

        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteByusername(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByusername(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
