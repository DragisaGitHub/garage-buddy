package com.garagebuddy.application;

import com.garagebuddy.domain.User;
import com.garagebuddy.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthServiceTest {
    @Test
    void registerShouldSaveUserWhenEmailNotTaken() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        when(repo.findByEmail("a@b.c")).thenReturn(Optional.empty());
        when(repo.save(any())).thenAnswer(i -> i.getArgument(0));
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        AuthService svc = new AuthService(repo, enc);

        User u = svc.register("a@b.c", "pass123", "Name");
        assertNotNull(u);
        assertEquals("a@b.c", u.email);
        assertNotEquals("pass123", u.passwordHash);
    }

    @Test
    void registerShouldThrowWhenEmailTaken() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        User existing = new User(); existing.email = "a@b.c";
        when(repo.findByEmail("a@b.c")).thenReturn(Optional.of(existing));
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        AuthService svc = new AuthService(repo, enc);

        assertThrows(IllegalArgumentException.class, () -> svc.register("a@b.c", "x", "n"));
    }
}
