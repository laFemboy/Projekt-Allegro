INSERT INTO ProductTable (name, description,
                          category, subCategory, price, discount,
                          dateAdded, imageUrl, stockQuantity, isAvailable, viewCount)
VALUES ('Apple iPhone 12 Pro Max', 'Apple iPhone 12 Pro Max smartphone runs on iOS v14 operating system. The phone is powered by Hexa Core (3.1 GHz, Dual core, Firestorm + 1.8 GHz, Quad core, Icestorm) processor. It runs on the Apple A14 Bionic Chipset. It has 6 GB RAM and 128 GB internal storage.',
        'ELECTRONICS', 'SMARTPHONE', 1099.99, 0.0, '2021-01-01',
        'https://www.apple.com/newsroom/images/product/iphone/standard/Apple_announce-iphone12pro_10132020_big.jpg.large.jpg',
        100, true, 0);

INSERT INTO UserTable (username, password, email)
VALUES ('admin', 'password', 'example@gmail.com');

INSERT INTO OrderTable (productId, userId, quantity, price, status)
VALUES (1, 1, 1, 1099.99, 'PENDING');