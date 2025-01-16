package com.example.allegro_backend;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.allegro_backend.product.Product;
import com.example.allegro_backend.product.ProductCategory;
import com.example.allegro_backend.product.ProductController;
import com.example.allegro_backend.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ProductControllerTest {

    private ProductController productController;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productController = new ProductController(productRepository);
    }

    @Test
    void testFindAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1, "Product1", "Description1", ProductCategory.ELECTRONICS, "SubCategory1", LocalDate.parse("2022-03-23"), 100.0, 0.0, "/imgs/1", 10, true, 5),
                new Product(2, "Product2", "Description2", ProductCategory.FOOD, "SubCategory2", LocalDate.parse("2024-04-04"), 150.0, 10.0, "imgs/2", 5, true, 3)
        );

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productController.findAllProducts();

        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }

    @Test
    void testFindProductById() {
        Optional<Product> product = Optional.of(new Product(1, "Product1", "Description1", ProductCategory.ELECTRONICS, "SubCategory1", LocalDate.parse("2025-01-01"), 100.0, 0.0, "/test/img/url", 10, true, 5));

        when(productRepository.findById(1)).thenReturn(product);

        Product result = productController.findProductById(1, null);

        assertEquals("Product1", result.getName());
        verify(productRepository).findById(1);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product(1, "New Product", "Description", ProductCategory.TOYS, "SubCategory3", LocalDate.parse("2024-12-31"), 50.0, 0.0, "/imgs/url", 20, true, 0);

        doNothing().when(productRepository).create(product);

        assertDoesNotThrow(() -> productController.create(product));
        verify(productRepository).create(product);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product(1, "Updated Product", "Updated Description", ProductCategory.FOOD, "SubCategory4", LocalDate.parse("2024-11-29"), 60.0, 5.0, "null", 30, true, 2);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).update(1, product);

        assertDoesNotThrow(() -> productController.update(1, product));
        verify(productRepository).update(1, product);
    }

    @Test
    void testFindNewestProducts() {
        List<Product> products = Arrays.asList(
                new Product(1, "Product1", "Description1", ProductCategory.ELECTRONICS, "SubCategory1", LocalDate.parse("2023-02-02"), 100.0, 0.0, "null", 10, true, 5),
                new Product(2, "Product2", "Description2", ProductCategory.FOOD, "SubCategory2", LocalDate.parse("2024-03-03"), 150.0, 10.0, "null", 5, true, 3)
        );

        when(productRepository.findNewestProducts()).thenReturn(products);

        List<Product> result = productController.findNewestProducts();

        assertEquals(2, result.size());
        verify(productRepository).findNewestProducts();
    }
}
