package com.example.allegro_backend.orders;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing orders in the database.
 */
@Repository
public class OrderRepository {
    private final JdbcClient jdbcClient;

    /**
     * Constructor for OrderRepository.
     *
     * @param jdbcClient The JDBC client for database operations.
     */
    public OrderRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return A list of all orders.
     */
    public List<Order> findAll() {
        return jdbcClient.sql("SELECT * FROM OrderTable")
                .query(Order.class)
                .list();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return An Optional containing the order if found, or empty if not found.
     */
    public Optional<Order> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM OrderTable WHERE id = :id")
                .param("id", id)
                .query(Order.class)
                .optional();
    }

    /**
     * Creates a new order in the database.
     *
     * @param order The order to create.
     */
    public void create(Order order) {
        var updated = jdbcClient.sql("INSERT INTO OrderTable (productId, userId, quantity, price, status) " +
                        "VALUES (?, ?, ?, ?, ?)")
                .params(List.of(
                        order.getProductId(),
                        order.getUserId(),
                        order.getQuantity(),
                        order.getPrice(),
                        order.getStatus().name()
                ))
                .update();
        Assert.state(updated == 1, "Failed to insert order for productId "
                + order.getProductId());
    }

    /**
     * Updates an existing order in the database.
     *
     * @param id    The ID of the order to update.
     * @param order The updated order data.
     */
    public void update(Integer id, Order order) {
        var updated = jdbcClient.sql("UPDATE OrderTable SET productId = ?, userId = ?, quantity = ?, price = ?, status = ? WHERE id = ?")
                .params(List.of(
                        order.getProductId(),
                        order.getUserId(),
                        order.getQuantity(),
                        order.getPrice(),
                        order.getStatus().name(),
                        id
                ))
                .update();
        Assert.state(updated == 1, "Failed to update order with id "
                + id);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to delete.
     */
    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM OrderTable WHERE id = ?")
                .param(id)
                .update();
        Assert.state(updated == 1, "Failed to delete order with id "
                + id);
    }

    /**
     * Retrieves all orders with a specific status.
     *
     * @param status The status of the orders to retrieve.
     * @return A list of orders with the specified status.
     */
    public List<Order> findByStatus(OrderStatus status) {
        return jdbcClient.sql("SELECT * FROM OrderTable WHERE status = :status")
                .param("status", status.name())
                .query(Order.class)
                .list();
    }
}
