package com.example.allegro_backend.product;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/products")
    List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/api/products/{id}")
    Product findProductById(@PathVariable Integer id, HttpServletRequest request) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/products")
    void create(@RequestBody Product product) {
        productRepository.create(product);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/api/products/{id}")
    void update(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        productRepository.update(id, product);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/products/{id}")
    void delete(@PathVariable Integer id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        productRepository.delete(id);
    }

    @GetMapping("/api/products/newest")
    List<Product> findNewestProducts() {
        return productRepository.findNewestProducts();
    }

    @GetMapping("/api/products/most-viewed")
    List<Product> findMostViewedProducts() {
        return productRepository.findMostViewedProducts();
    }

    @GetMapping("/api/products/category/{category}")
    List<Product> findProductsByCategory(@PathVariable ProductCategory category) {
        return productRepository.findProductsByCategory(category);
    }

    @GetMapping("/api/products/search/{query}")
    List<Product> findProductsByQuery(@PathVariable String query) {
        return productRepository.findProductsByQuery(query);
    }

    @GetMapping("/api/products/{id}/increment-view-count")
    void incrementViewCount(@PathVariable Integer id) {
        productRepository.incrementViewCount(id);
    }

    @GetMapping("/api/products/discounted")
    List<Product> getProductsWithDiscount() {
        return productRepository.getProductsWithDiscount();
    }
}
