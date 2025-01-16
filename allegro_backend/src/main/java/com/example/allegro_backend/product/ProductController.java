package com.example.allegro_backend.product;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing products.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final ProductRepository productRepository;

    /**
     * Constructor for ProductController.
     *
     * @param productRepository The repository used to manage products.
     */
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a list of all products.
     *
     * @return A list of all products.
     */
    @GetMapping("/api/products")
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id      The ID of the product to retrieve.
     * @param request The HTTP request object (used for additional processing if needed).
     * @return The product with the specified ID.
     * @throws ResponseStatusException If the product is not found (HTTP 404).
     */
    @GetMapping("/api/products/{id}")
    public Product findProductById(@PathVariable Integer id, HttpServletRequest request) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product.get();
    }

    /**
     * Creates a new product.
     *
     * @param product The product to create.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/products")
    public void create(@RequestBody Product product) {
        productRepository.create(product);
    }

    /**
     * Updates an existing product.
     *
     * @param id      The ID of the product to update.
     * @param product The updated product details.
     * @throws ResponseStatusException If the product is not found (HTTP 404).
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/api/products/{id}")
    public void update(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        productRepository.update(id, product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @throws ResponseStatusException If the product is not found (HTTP 404).
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/products/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        productRepository.delete(id);
    }

    /**
     * Retrieves the newest products.
     *
     * @return A list of the newest products.
     */
    @GetMapping("/api/products/newest")
    public List<Product> findNewestProducts() {
        System.out.println("someone wants to get newest products");
        return productRepository.findNewestProducts();
    }

    /**
     * Retrieves the most viewed products.
     *
     * @return A list of the most viewed products.
     */
    @GetMapping("/api/products/most-viewed")
    List<Product> findMostViewedProducts() {
        return productRepository.findMostViewedProducts();
    }

    /**
     * Retrieves products by category.
     *
     * @param category The category of the products to retrieve.
     * @return A list of products in the specified category.
     */
    @GetMapping("/api/products/category/{category}")
    List<Product> findProductsByCategory(@PathVariable ProductCategory category) {
        return productRepository.findProductsByCategory(category);
    }

    /**
     * Searches for products based on a query string.
     *
     * @param query The query string to search for.
     * @return A list of products matching the query.
     */
    @GetMapping("/api/products/search/{query}")
    List<Product> findProductsByQuery(@PathVariable String query) {
        return productRepository.findProductsByQuery(query);
    }

    /**
     * Increments the view count of a product.
     *
     * @param id The ID of the product whose view count should be incremented.
     */
    @GetMapping("/api/products/{id}/increment-view-count")
    void incrementViewCount(@PathVariable Integer id) {
        productRepository.incrementViewCount(id);
    }

    /**
     * Retrieves products with a discount.
     *
     * @return A list of products that have a discount.
     */
    @GetMapping("/api/products/discounted")
    List<Product> getProductsWithDiscount() {
        return productRepository.getProductsWithDiscount();
    }
}
