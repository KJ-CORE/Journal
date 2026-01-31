package com.aitpune.Journal.service;

import com.aitpune.Journal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;


public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void loadbyusername(){
        when(userDetailsService.loadUserByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Ram").password("asdasc").roles("USER").build());

        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }

}
