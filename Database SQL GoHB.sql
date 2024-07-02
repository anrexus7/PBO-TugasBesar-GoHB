CREATE TABLE `Users` (
    `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `phone_number` VARCHAR(255) NOT NULL,
    `user_type` ENUM('CUSTOMER', 'DRIVER', 'ADMIN') NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `Vehicle` (
    `vehicle_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `vehicle_type` ENUM('CAR', 'BIKE') NOT NULL,
    `vehicle_plat` VARCHAR(255) NOT NULL
);

CREATE TABLE `Drivers` (
    `driver_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `vehicle_id` INT UNSIGNED NOT NULL,
    `rating` FLOAT NOT NULL,
    `status` ENUM('ONLINE', 'OFFLINE', 'OCCUPIED') NOT NULL,
    FOREIGN KEY (`vehicle_id`) REFERENCES `Vehicle` (`vehicle_id`)
);

CREATE TABLE `Orders` (
    `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id` INT UNSIGNED NOT NULL,
    `driver_id` INT UNSIGNED NOT NULL,
    `service_type` ENUM('GOBIKE', 'GOCAR', 'GOSEND', 'GOFOOD') NOT NULL,
    `vehicle_type` ENUM('CAR', 'BIKE') NOT NULL,
    `destination` VARCHAR(255) NOT NULL,
    `cost` DOUBLE NOT NULL,
    `order_status` ENUM('PENDING', 'ASSIGNED', 'PICKING', 'DROPPING', 'COMPLETED') NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`customer_id`) REFERENCES `Users` (`user_id`),
    FOREIGN KEY (`driver_id`) REFERENCES `Drivers` (`driver_id`)
);

CREATE TABLE `Promos` (
    `promo_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `promo_code` VARCHAR(255) NOT NULL,
    `description` LONGTEXT NOT NULL,
    `discount` FLOAT NOT NULL,
    `valid_from` DATE NOT NULL,
    `valid_to` DATE NOT NULL,
    `created_by` INT UNSIGNED NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`created_by`) REFERENCES `Users` (`user_id`)
);

CREATE TABLE `Ratings` (
    `rating_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `order_id` INT UNSIGNED NOT NULL,
    `user_id` INT UNSIGNED NOT NULL,
    `driver_id` INT UNSIGNED NOT NULL,
    `rating` INT NOT NULL,
    `review` LONGTEXT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`order_id`) REFERENCES `Orders` (`order_id`),
    FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`),
    FOREIGN KEY (`driver_id`) REFERENCES `Drivers` (`driver_id`)
);

CREATE TABLE `Report` (
    `report_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT UNSIGNED NOT NULL,
    `complaint` LONGTEXT NOT NULL,
    `status` ENUM('OPENED', 'CLOSED') NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`)
);

CREATE TABLE `Stores` (
    `store_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `owner_id` INT UNSIGNED NOT NULL,
    `store_name` VARCHAR(255) NOT NULL,
    `address` TEXT NOT NULL,
    `phone_number` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`owner_id`) REFERENCES `Users` (`user_id`)
);

CREATE TABLE `StoreLocation` (
    `store_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `address` TEXT NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `x_coor` FLOAT NOT NULL,
    `y_coor` FLOAT NOT NULL,
    FOREIGN KEY (`store_id`) REFERENCES `Stores` (`store_id`)
);

CREATE TABLE `Subscription` (
    `subs_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT UNSIGNED NOT NULL,
    `subs_type` ENUM('WEEKLY', 'MONTHLY', 'ANNUALY') NOT NULL,
    `subs_desc` LONGTEXT NOT NULL,
    `valid_from` DATE NOT NULL,
    `valid_to` DATE NOT NULL,
    `status` BOOLEAN NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`)
);

CREATE TABLE `VehicleMaintenance` (
    `maintenance_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `driver_id` INT UNSIGNED NOT NULL,
    `schedule_date` DATE NOT NULL,
    `status` ENUM('SCHEDULED', 'COMPLETED', 'CANCELED', 'ONGOING') NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`driver_id`) REFERENCES `Drivers` (`driver_id`)
);

CREATE TABLE `Package` (
    `package_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `owner_id` INT UNSIGNED NOT NULL,
    `package_name` VARCHAR(255) NOT NULL,
    `description` LONGTEXT NOT NULL,
    `weight` FLOAT NOT NULL,
    `item_type` ENUM('NORMAL', 'FRAGILE', 'RADIOACTIVE', 'CORROSIVE', 'FLAMMABLE', 'HAZARD') NOT NULL,
    FOREIGN KEY (`owner_id`) REFERENCES `Users` (`user_id`)
);

CREATE TABLE `UserLog` (
    `log_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT UNSIGNED NOT NULL,
    `activity_type` ENUM('LOGIN', 'LOGOUT') NOT NULL,
    `timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`)
);

CREATE TABLE `UserBalances` (
    `user_id` INT UNSIGNED NOT NULL PRIMARY KEY,
    `balance` DOUBLE NOT NULL,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`)
);

CREATE TABLE `Products` (
    `product_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `store_id` INT UNSIGNED NOT NULL,
    `product_name` VARCHAR(255) NOT NULL,
    `product_type` ENUM('FOOD', 'BEVERAGE', 'DRUG') NOT NULL,
    `description` LONGTEXT NULL,
    `price` DOUBLE NOT NULL,
    `stock` INT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`store_id`) REFERENCES `Stores` (`store_id`)
);

CREATE TABLE `Admin` (
    `admin_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`admin_id`) REFERENCES `Users` (`user_id`)
);
