package com.example.allegro_backend;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.allegro_backend.user.User;
import com.example.allegro_backend.user.UserController;
import com.example.allegro_backend.user.UserCredentials;
import com.example.allegro_backend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

class UserControllerTest {

    private UserController userController;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userController = new UserController(userRepository);
    }

    @Test
    void testGetUserByUsername() {
        Optional<User> user = Optional.of(new User(1, "testuser", "password123", "testuser@example.com"));

        when(userRepository.getUserByUsername("testuser")).thenReturn(user);

        User result = userController.getUserByUsername("testuser");

        assertEquals("testuser", result.getUsername());
        verify(userRepository).getUserByUsername("testuser");
    }

    @Test
    void testAuthenticateUser() {
        UserCredentials credentials = new UserCredentials("testuser", "password123");
        Optional<User> user = Optional.of(new User(1, "testuser", "password123", "testuser@example.com"));

        when(userRepository.getUserByUsername("testuser")).thenReturn(user);

        ResponseEntity<?> response = userController.authenticateUser(credentials);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepository).getUserByUsername("testuser");
    }

    @Test
    void testCreateUser() {
        User user = new User(1, "newuser", "password456", "newuser@example.com");

        doNothing().when(userRepository).createUser(user);

        assertDoesNotThrow(() -> userController.createUser(user));
        verify(userRepository).createUser(user);
    }

    @Test
    void testHandleUserNotFound() {
        when(userRepository.getUserByUsername("invaliduser")).thenReturn(Optional.empty());

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> userController.getUserByUsername("invaliduser"));

        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatusCode());
    }
}
