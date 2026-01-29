package com.aitpune.Journal.service;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
public class UserServiceTest {





    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4"
    })
    public void test(int a,int b, int expected){
        assertEquals(expected,a+b);
    }
}
