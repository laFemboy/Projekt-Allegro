package com.example.allegro_backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/users")
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.getUserByUsername(username);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user.get();
    }


    @PostMapping("/api/users/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserCredentials userCredentials) {
        Optional<User> optionalUser = userRepository.getUserByUsername(userCredentials.getUsername());

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(userCredentials.getPassword())) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/users")
    void createUser(@RequestBody User user) {
        userRepository.createUser(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/api/users/{id}")
    void updateUser(@PathVariable Integer id, @RequestBody User user) {
        Optional<User> existingUser = userRepository.getUserById(id);
        if (existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.updateUser(id, user);
    }
}