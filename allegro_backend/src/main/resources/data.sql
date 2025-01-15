-- add products from different categories
-- Add products from different categories
INSERT INTO ProductTable (
    name,
    description,
    category,
    subCategory,
    price,
    discount,
    dateAdded,
    imageUrl,
    stockQuantity,
    isAvailable,
    viewCount
)
VALUES
-- Electronics: Smartphones
('Apple iPhone 12 Pro Max',
 'Apple iPhone 12 Pro Max smartphone runs on iOS v14 operating system. The phone is powered by Hexa Core (3.1 GHz, Dual core, Firestorm + 1.8 GHz, Quad core, Icestorm) processor. It runs on the Apple A14 Bionic Chipset. It has 6 GB RAM and 128 GB internal storage.',
 'ELECTRONICS', 'SMARTPHONE', 1099.99, 10.0, '2020-01-01',
 'https://www.apple.com/newsroom/images/product/iphone/standard/Apple_announce-iphone12pro_10132020_big.jpg.large.jpg',
 100, true, 0),

('Samsung Galaxy S21 Ultra',
 'Samsung Galaxy S21 Ultra smartphone runs on Android v11 operating system. The phone is powered by Octa core (2.9 GHz, Single core, Cortex X1 + 2.8 GHz, Tri core, Cortex A78 + 2.2 GHz, Quad core, Cortex A55) processor. It runs on the Samsung Exynos 2100 Chipset. It has 12 GB RAM and 128 GB internal storage.',
 'ELECTRONICS', 'SMARTPHONE', 1199.99, 100.0, '2021-01-01',
 'https://a.allegroimg.com/original/11e830/2c97976544d7bb690502e3a7af4a/Smartfon-Samsung-Galaxy-S21-Ultra-5G-G998-gwarancja-NOWY-12-128GB',
 100, true, 1),

-- Electronics: Laptops
('Apple MacBook Pro',
 'Apple MacBook Pro laptop has a 13.3 Inches (33.78 cm) display for your daily needs. This laptop is powered by Apple M1 processor, coupled with 8 GB of RAM and has 256 GB SSD storage at this price point.',
 'ELECTRONICS', 'LAPTOP', 1299.99, 200.0, '2022-01-01',
 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/mbp-spacegray-select-202011?wid=892&hei=820&&qlt=80&.v=1603406905000',
 100, true, 0),

-- Electronics: Accessories
('Apple AirPods Pro',
 'Apple AirPods Pro wireless earphones have been launched in India. The new AirPods Pro are priced at Rs. 24,900 and come with a new in-ear design with three different sizes of soft, flexible silicone ear tips that are said to provide a comfortable fit and a superior seal.',
 'ELECTRONICS', 'EARPHONES', 249.99, 50.0, '2023-01-01',
 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MWP22?wid=1144&hei=1144&fmt=jpeg&qlt=80&op_usm=0.5,0.5&.v=1591634795000',
 100, true, 34),

('Apple Watch Series 6',
 'Apple Watch Series 6 smartwatch runs on WatchOS v7.0 operating system. The phone is powered by Dual core, Apple S6 processor. It runs on the Apple S6 Chipset. It has 1 GB RAM and 32 GB internal storage.',
 'ELECTRONICS', 'SMARTWATCH', 399.99, 300.0, '2024-01-01',
 'https://files.refurbed.io/ii/apple-watch-series-6-alu-44mm-1611898549.jpg?t=fitdesign&h=600&w=800',
 100, true, 0),

('Apple iPad Pro',
 'Apple iPad Pro 12.9 (2021) Wi-Fi tablet was launched on 20th April 2021. The tablet comes with a 12.90-inch touchscreen display with a resolution of 2732x2048 pixels at a pixel density of 264 pixels per inch (ppi). Apple iPad Pro 12.9 (2021) Wi-Fi is powered by an octa-core Apple M1 processor. It comes with 8GB of RAM.',
 'ELECTRONICS', 'TABLET', 999.99, 100.0, '2025-01-01',
 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/ipad-pro-12-select-wifi-spacegray-202104_FMT_WHH?wid=940&hei=1112&fmt=jpeg&qlt=80&.v=1617126628000',
 100, true, 22),

('Apple AirTag',
 'Apple AirTag is a small device that helps you keep track of and find the items that matter most with Apple’s Find My app. AirTag is easy to use and can be attached to your keys, wallet, backpack, or other items. It has a replaceable battery that lasts over a year.',
 'ELECTRONICS', 'ACCESSORY', 29.99, 5.0, '2026-01-01',
 'https://m.media-amazon.com/images/I/71+5mYCqy7S._AC_UF1000,1000_QL80_.jpg',
 100, true, 20),

-- Food: Drinks and Desserts
('Apple juice',
 'Apple juice is a fruit juice made by the maceration and pressing of apples. The resulting expelled juice may be further treated by enzymatic and centrifugal clarification to remove the starch and pectin, which holds fine particulate in suspension, and then pasteurized for packaging.',
 'FOOD', 'DRINK', 2.99, 0.0, '2027-01-01',
 'https://www.alphafoodie.com/wp-content/uploads/2021/11/Apple-Juice-Square.jpeg',
 100, true, 3),

('Apple pie',
 'An apple pie is a pie in which the principal filling ingredient is apple. It is often served with whipped cream, ice cream, or cheddar cheese.',
 'FOOD', 'DESSERT', 4.99, 0.0, '2028-01-01',
 'https://www.ifyougiveablondeakitchen.com/wp-content/uploads/2023/04/best-apple-pie-from-scratch.jpg',
 100, true, 10);


INSERT INTO UserTable (username, password, email)
VALUES ('admin', 'password', 'example@gmail.com');

INSERT INTO OrderTable (productId, userId, quantity, price, status)
VALUES (1, 1, 1, 1099.99, 'PENDING');