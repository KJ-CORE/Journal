package com.aitpune.Journal.service;

import com.aitpune.Journal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailServiceImplTest {

    @MockitoBean
    private UserDetailsServiceImpl userDetailsService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void loadbyusername(){
        when(userDetailsService.loadUserByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Ram").password("asdasc").roles("USER").build());

        UserDetails userDetails = userDetailsService.loadUserByusername("ram");
        Assertions.assertNotNull(userDetails);
    }

}
