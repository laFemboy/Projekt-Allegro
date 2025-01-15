
CREATE TABLE IF NOT EXISTS UserTable (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         username VARCHAR(255) NOT NULL UNIQUE,
                                         password VARCHAR(255) NOT NULL,
                                         email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS ProductTable (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            name VARCHAR(255) NOT NULL,
                                            description TEXT,
                                            category ENUM('ELECTRONICS', 'CLOTHING', 'HOME', 'GARDEN', 'SPORTS', 'BEAUTY', 'HEALTH', 'TOYS', 'MEDIA', 'FOOD', 'OTHER') NOT NULL,
                                            subCategory VARCHAR(255),
                                            price DOUBLE NOT NULL,
                                            discount DOUBLE,
                                            dateAdded DATE NOT NULL,
                                            imageUrl VARCHAR(255),
                                            stockQuantity INT NOT NULL,
                                            isAvailable BOOLEAN NOT NULL,
                                            viewCount INT NOT NULL
);

CREATE TABLE IF NOT EXISTS OrderTable (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       productId INT NOT NULL,
                       userId INT NOT NULL,
                       quantity INT NOT NULL,
                       price DOUBLE NOT NULL,
                       status ENUM('PENDING', 'SHIPPED', 'DELIVERED', 'CANCELLED') NOT NULL,
                       FOREIGN KEY (productId) REFERENCES ProductTable(id),
                       FOREIGN KEY (userId) REFERENCES UserTable(id)
);
