package com.example.allegro_backend.product;

import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private Integer id;
    private  String name;
    private String description;
    private ProductCategory category;
    private String subCategory;
    private  double price;
    private double discount;
    private LocalDate dateAdded;
    private String imageUrl;
    private Integer stockQuantity;
    private boolean isAvailable;
    private int viewCount;

    public Product(Integer id, String name, String description, ProductCategory category, String subCategory, LocalDate dateAdded, double price, double discount, String imageUrl, Integer stockQuantity, boolean isAvailable, int viewCount) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (subCategory.isBlank()) {
            throw new IllegalArgumentException("Subcategory cannot be blank");
        }
        if (dateAdded == null) {
            throw new IllegalArgumentException("Date added cannot be null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (discount < 0) {
            throw new IllegalArgumentException("Discount cannot be negative");
        }
        if (imageUrl.isBlank()) {
            throw new IllegalArgumentException("Image URL cannot be blank");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        if (viewCount < 0) {
            throw new IllegalArgumentException("View count cannot be negative");
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.subCategory = subCategory;
        this.dateAdded = dateAdded;
        this.price = price;
        this.discount = discount;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.isAvailable = isAvailable;
        this.viewCount = viewCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public ProductCategory category() {
        return category;
    }

    public String subCategory() {
        return subCategory;
    }

    public double price() {
        return price;
    }

    public String imageUrl() {
        return imageUrl;
    }

    public Integer stockQuantity() {
        return stockQuantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id == product.id && Double.compare(price, product.price) == 0 && isAvailable == product.isAvailable && Objects.equals(name, product.name) && Objects.equals(description, product.description) && category == product.category && Objects.equals(subCategory, product.subCategory) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(stockQuantity, product.stockQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, subCategory, price, imageUrl, stockQuantity, isAvailable);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", subCategory='" + subCategory + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
