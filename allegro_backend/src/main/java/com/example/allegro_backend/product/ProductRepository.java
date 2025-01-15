package com.example.allegro_backend.product;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class ProductRepository {
    private static final Logger LOGGER = Logger.getLogger(ProductRepository.class.getName());
    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM ProductTable")
                .query(Product.class)
                .list();
    }

    public Optional<Product> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM ProductTable WHERE id = :id")
                .param("id", id)
                .query(Product.class)
                .optional();
    }

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
        Assert.state(updated == 1, "Failed to insert product "
                +product.getName());
    }

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
        Assert.state(updated == 1, "Failed to update product with id "
                +id);
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM ProductTable WHERE id = ?")
                .param(id)
                .update();
        Assert.state(updated == 1, "Failed to delete product with id "
                +id);
    }

    public List<Product> findNewestProducts() {
        return jdbcClient.sql("SELECT * FROM ProductTable ORDER BY dateAdded DESC LIMIT 6")
                .query(Product.class)
                .list();
    }

    public List<Product> findMostViewedProducts() {
        return jdbcClient.sql("SELECT * FROM ProductTable ORDER BY viewCount DESC LIMIT 6")
                .query(Product.class)
                .list();
    }

    public List<Product> findProductsByCategory(ProductCategory category) {
        return jdbcClient.sql("SELECT * FROM ProductTable WHERE category = :category")
                .param("category", category.name())
                .query(Product.class)
                .list();
    }

    public List<Product> findProductsByQuery(String query) {
        return jdbcClient.sql("SELECT * FROM ProductTable WHERE name LIKE :query")
                .param("query", "%"+query+"%")
                .query(Product.class)
                .list();
    }

    public void incrementViewCount(Integer id) {
        Optional<Product> product = findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }
        Product updatedProduct = product.get();
        updatedProduct.setViewCount(updatedProduct.getViewCount() + 1);
        update(id, updatedProduct);
    }

    public List<Product> getProductsWithDiscount() {
        return jdbcClient.sql("SELECT * FROM ProductTable WHERE discount > 0 ORDER BY discount DESC LIMIT 6")
                .query(Product.class)
                .list();
    }
}
