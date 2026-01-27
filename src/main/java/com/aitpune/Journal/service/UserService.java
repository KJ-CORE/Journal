package com.aitpune.Journal.service;

import com.aitpune.Journal.entity.User;
import com.aitpune.Journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
    public void saveUser(User user){

        userRepository.save(user);
    }
    public List<User> getAll(){

        return userRepository.findAll();

    }
    public Optional<User> getbyID(ObjectId myID){

        return userRepository.findById(myID);
    }
    public void deletebyID(ObjectId myID){
        userRepository.deleteById(myID);

    }
    public User findByUsername(String username){
        return userRepository.findByusername(username);
    }

}
