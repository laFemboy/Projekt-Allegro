package com.example.allegro_backend.product;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Repository class for managing products in the database.
 */
@Repository
public class ProductRepository {
    private static final Logger LOGGER = Logger.getLogger(ProductRepository.class.getName());
    private final JdbcClient jdbcClient;

    /**
     * Constructor for ProductRepository.
     *
     * @param jdbcClient The JDBC client used to execute SQL queries.
     */
    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return A list of all products.
     */
    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM ProductTable")
                .query(Product.class)
                .list();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return An optional containing the product if found, or empty if not found.
     */
    public Optional<Product> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM ProductTable WHERE id = :id")
                .param("id", id)
                .query(Product.class)
                .optional();
    }

    /**
     * Inserts a new product into the database.
     *
     * @param product The product to create.
     * @throws IllegalStateException If the insertion fails.
     */
    public void create(Product product) {
        var updated = jdbcClient.sql("INSERT INTO ProductTable (name, description, " +
                        "category, subCategory, dateAdded, price, discount, imageUrl, " +
                        "stockQuantity, isAvailable, viewCount) " +
                        "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                .params(List.of(
                        product.getName(),
                        product.getDescription(),
                        product.getCategory().name(),
                        product.getSubCategory(),
                        product.getDateAdded(),
                        product.getPrice(),
                        product.getDiscount(),
                        product.getImageUrl(),
                        product.getStockQuantity(),
                        product.isAvailable(),
                        product.getViewCount()
                ))
                .update();
        Assert.state(updated == 1, "Failed to insert product " + product.getName());
    }

    /**
     * Updates an existing product in the database.
     *
     * @param id      The ID of the product to update.
     * @param product The updated product details.
     * @throws IllegalStateException If the update fails.
     */
    public void update(Integer id, Product product) {
        var updated = jdbcClient.sql("UPDATE ProductTable SET name = ?, description = ?, " +
                        "category = ?, subCategory = ?, dateAdded = ?, price = ?, discount = ?, imageUrl = ?, " +
                        "stockQuantity = ?, isAvailable = ?, viewCount = ? WHERE id = ?")
                .params(List.of(
                        product.getName(),
                        product.getDescription(),
                        product.getCategory().name(),
                        product.getSubCategory(),
                        product.getDateAdded(),
                        product.getPrice(),
                        product.getDiscount(),
                        product.getImageUrl(),
                        product.getStockQuantity(),
                        product.isAvailable(),
                        product.getViewCount(),
                        id
                ))
                .update();
        Assert.state(updated == 1, "Failed to update product with id " + id);
    }

    /**
     * Deletes a product from the database.
     *
     * @param id The ID of the product to delete.
     * @throws IllegalStateException If the deletion fails.
     */
    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM ProductTable WHERE id = ?")
                .param(id)
                .update();
        Assert.state(updated == 1, "Failed to delete product with id " + id);
    }

    /**
     * Retrieves the newest products.
     *
     * @return A list of the newest products, with maximum of 6 products.
     */
    public List<Product> findNewestProducts() {
        return jdbcClient.sql("SELECT * FROM ProductTable ORDER BY dateAdded DESC LIMIT 6")
                .query(Product.class)
                .list();
    }

    /**
     * Retrieves the most viewed products.
     *
     * @return A list of the most viewed products, with maximum of 6 products.
     */
    public List<Product> findMostViewedProducts() {
        return jdbcClient.sql("SELECT * FROM ProductTable ORDER BY viewCount DESC LIMIT 6")
                .query(Product.class)
                .list();
    }

    /**
     * Retrieves products by their category.
     *
     * @param category The category of products to retrieve.
     * @return A list of products in the specified category.
     */
    public List<Product> findProductsByCategory(ProductCategory category) {
        return jdbcClient.sql("SELECT * FROM ProductTable WHERE category = :category")
                .param("category", category.name())
                .query(Product.class)
                .list();
    }

    /**
     * Searches for products by a query string in their name.
     *
     * @param query The query string to search for.
     * @return A list of products matching the query.
     */
    public List<Product> findProductsByQuery(String query) {
        return jdbcClient.sql("SELECT * FROM ProductTable WHERE name LIKE :query")
                .param("query", "%" + query + "%")
                .query(Product.class)
                .list();
    }

    /**
     * Increments the view count for a product.
     *
     * @param id The ID of the product to increment the view count for.
     * @throws IllegalArgumentException If the product is not found.
     */
    public void incrementViewCount(Integer id) {
        Optional<Product> product = findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }
        Product updatedProduct = product.get();
        updatedProduct.setViewCount(updatedProduct.getViewCount() + 1);
        update(id, updatedProduct);
    }

    /**
     * Retrieves products with a discount.
     *
     * @return A list of products with a discount, with maximum of 6 products.
     */
    public List<Product> getProductsWithDiscount() {
        return jdbcClient.sql("SELECT * FROM ProductTable WHERE discount > 0 ORDER BY discount DESC LIMIT 6")
                .query(Product.class)
                .list();
    }
}

