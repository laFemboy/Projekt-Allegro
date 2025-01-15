package com.example.allegro_backend.user;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    Optional<User> getUserById(Integer id) {
        return jdbcClient.sql("SELECT * FROM UserTable WHERE id = :id")
                .param("id", id)
                .query(User.class)
                .optional();
    }

    Optional<User> getUserByUsername(String username) {
        return jdbcClient.sql("SELECT * FROM UserTable WHERE username = :username")
                .param("username", username)
                .query(User.class)
                .optional();
    }

    public void createUser (User user) {
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

  