package com.example.allegro_backend.user;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing user data in the database.
 */
@Repository
public class UserRepository {
    private final JdbcClient jdbcClient;

    /**
     * Constructor for UserRepository.
     *
     * @param jdbcClient The JDBC client used for database operations.
     */
    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return An Optional containing the user if found, or an empty Optional if not.
     */
    public Optional<User> getUserById(Integer id) {
        return jdbcClient.sql("SELECT * FROM UserTable WHERE id = :id")
                .param("id", id)
                .query(User.class)
                .optional();
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return An Optional containing the user if found, or an empty Optional if not.
     */
    public Optional<User> getUserByUsername(String username) {
        return jdbcClient.sql("SELECT * FROM UserTable WHERE username = :username")
                .param("username", username)
                .query(User.class)
                .optional();
    }

    /**
     * Creates a new user in the database.
     *
     * @param user The user to be created.
     * @throws IllegalStateException If the user creation fails.
     */
    public void createUser(User user) {
        var updated = jdbcClient.sql("INSERT INTO UserTable (username, password, email) VALUES (?, ?, ?)")
                .params(List.of(
                        user.getUsername(),
                        user.getPassword(),
                        user.getEmail()
                ))
                .update();
        Assert.state(updated == 1, "Failed to insert user "
                + user.getUsername());
    }

    /**
     * Updates an existing user's data in the database.
     *
     * @param id   The ID of the user to update.
     * @param user The updated user data.
     * @throws IllegalStateException If the user update fails.
     */
    public void updateUser(Integer id, User user) {
        var updated = jdbcClient.sql("UPDATE UserTable SET username = ?, password = ?, email = ? WHERE id = ?")
                .params(List.of(
                        user.getUsername(),
                        user.getPassword(),
                        user.getEmail(),
                        id
                ))
                .update();
        Assert.state(updated == 1, "Failed to update user "
                + user.getUsername());
    }
}


  