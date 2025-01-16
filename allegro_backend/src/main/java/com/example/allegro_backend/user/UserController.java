package com.example.allegro_backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * REST controller for managing users.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserRepository userRepository;

    /**
     * Constructor for UserController.
     *
     * @param userRepository The repository for managing user data.
     */
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The user with the specified username.
     * @throws ResponseStatusException If the user is not found.
     */
    @GetMapping("/api/users")
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.getUserByUsername(username);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user.get();
    }

    /**
     * Authenticates a user based on their credentials.
     *
     * @param userCredentials The user's credentials (username and password).
     * @return A ResponseEntity containing the authenticated user, or an error response if authentication fails.
     * @throws ResponseStatusException If the username or password is invalid.
     */
    @PostMapping("/api/users/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserCredentials userCredentials) {
        // Check if the method is working
        System.out.println("authenticateUser method is working");
        Optional<User> optionalUser = userRepository.getUserByUsername(userCredentials.getUsername());

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(userCredentials.getPassword())) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    /**
     * Creates a new user in the database.
     *
     * @param user The user to create.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/users")
    public void createUser(@RequestBody User user) {
        userRepository.createUser(user);
    }

    /**
     * Updates an existing user's information.
     *
     * @param id   The ID of the user to update.
     * @param user The updated user data.
     * @throws ResponseStatusException If the user is not found.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/api/users/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        Optional<User> existingUser = userRepository.getUserById(id);
        if (existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.updateUser(id, user);
    }
}
