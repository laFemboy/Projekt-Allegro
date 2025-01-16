package com.example.allegro_backend.product;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Product.
 */
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

    /**
     * Instantiates a new Product.
     *
     * @param id            the id
     * @param name          the name
     * @param description   the description
     * @param category      the category
     * @param subCategory   the sub category
     * @param dateAdded     the date added
     * @param price         the price
     * @param discount      the discount
     * @param imageUrl      the image url
     * @param stockQuantity the stock quantity
     * @param isAvailable   the is available
     * @param viewCount     the view count
     */
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

    /**
     * Gets view count.
     *
     * @return the view count
     */
    public int getViewCount() {
        return viewCount;
    }

    /**
     * Sets view count.
     *
     * @param viewCount the view count
     */
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * Gets date added.
     *
     * @return the date added
     */
    public LocalDate getDateAdded() {
        return dateAdded;
    }

    /**
     * Sets date added.
     *
     * @param dateAdded the date added
     */
    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Name string.
     *
     * @return the string
     */
    public String name() {
        return name;
    }

    /**
     * Description string.
     *
     * @return the string
     */
    public String description() {
        return description;
    }

    /**
     * Category product category.
     *
     * @return the product category
     */
    public ProductCategory category() {
        return category;
    }

    /**
     * Sub category string.
     *
     * @return the string
     */
    public String subCategory() {
        return subCategory;
    }

    /**
     * Price double.
     *
     * @return the double
     */
    public double price() {
        return price;
    }

    /**
     * Image url string.
     *
     * @return the string
     */
    public String imageUrl() {
        return imageUrl;
    }

    /**
     * Stock quantity integer.
     *
     * @return the integer
     */
    public Integer stockQuantity() {
        return stockQuantity;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public ProductCategory getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    /**
     * Gets sub category.
     *
     * @return the sub category
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * Sets sub category.
     *
     * @param subCategory the sub category
     */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets image url.
     *
     * @param imageUrl the image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets stock quantity.
     *
     * @return the stock quantity
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets stock quantity.
     *
     * @param stockQuantity the stock quantity
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
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
