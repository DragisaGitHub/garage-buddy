package com.garagebuddy.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class CarServiceTest {
    
    @MockBean
    JavaMailSender mailSender;
    
    @Test
    void contextLoads() {
        // basic smoke test placeholder
    }
}
