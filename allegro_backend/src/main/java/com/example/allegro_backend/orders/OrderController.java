package com.example.allegro_backend.orders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing Orders.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    /**
     * Constructor for OrderController.
     *
     * @param orderRepository The repository for order data operations.
     */
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return A list of all orders.
     */
    @GetMapping
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return The order if found.
     */
    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        return order.get();
    }

    /**
     * Creates a new order.
     *
     * @param order The order to be created.
     * @return ResponseEntity indicating the status of creation.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody Order order) {
        orderRepository.create(order);
    }

    /**
     * Updates an existing order.
     *
     * @param id    The ID of the order to update.
     * @param order The updated order data.
     * @return ResponseEntity indicating the status of update.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        orderRepository.update(id, order);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to delete.
     * @return ResponseEntity indicating the status of deletion.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Integer id) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        orderRepository.delete(id);
    }

    /**
     * Retrieves all orders with a specific status.
     *
     * @param status The status of the orders to retrieve.
     * @return A list of orders with the specified status.
     */
    @GetMapping("/status/{status}")
    public List<Order> findOrdersByStatus(@PathVariable OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
}
