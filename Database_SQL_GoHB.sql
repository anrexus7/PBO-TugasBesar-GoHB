-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2024 at 07:04 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gohb_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
                         `admin_id` int(10) UNSIGNED NOT NULL,
                         `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                         `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

CREATE TABLE `drivers` (
                           `driver_id` int(10) UNSIGNED NOT NULL,
                           `vehicle_id` int(10) UNSIGNED NOT NULL,
                           `rating` float NOT NULL,
                           `status` enum('ONLINE','OFFLINE','OCCUPIED') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
                          `order_id` int(10) UNSIGNED NOT NULL,
                          `customer_id` int(10) UNSIGNED DEFAULT NULL,
                          `driver_id` int(10) UNSIGNED DEFAULT NULL,
                          `service_type` enum('GOBIKE','GOCAR','GOSEND','GOFOOD') DEFAULT NULL,
                          `vehicle_type` enum('CAR','BIKE') DEFAULT NULL,
                          `current_location` varchar(255) DEFAULT NULL,
                          `destination` varchar(255) DEFAULT NULL,
                          `cost` double DEFAULT NULL,
                          `order_status` enum('PENDING','ASSIGNED','PICKING','DROPPING','COMPLETED') DEFAULT NULL,
                          `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                          `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `package`
--

CREATE TABLE `package` (
                           `package_id` int(10) UNSIGNED NOT NULL,
                           `owner_id` int(10) UNSIGNED NOT NULL,
                           `package_name` varchar(255) NOT NULL,
                           `description` longtext NOT NULL,
                           `weight` float NOT NULL,
                           `item_type` enum('NORMAL','FRAGILE','RADIOACTIVE','CORROSIVE','FLAMMABLE','HAZARD') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
                            `product_id` int(10) UNSIGNED NOT NULL,
                            `store_id` int(10) UNSIGNED NOT NULL,
                            `product_name` varchar(255) NOT NULL,
                            `product_type` enum('FOOD','BEVERAGE','DRUG') NOT NULL,
                            `description` longtext DEFAULT NULL,
                            `price` double NOT NULL,
                            `stock` int(11) DEFAULT NULL,
                            `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                            `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promos`
--

CREATE TABLE `promos` (
                          `promo_id` int(10) UNSIGNED NOT NULL,
                          `promo_code` varchar(255) NOT NULL,
                          `description` longtext NOT NULL,
                          `discount` float NOT NULL,
                          `valid_from` date NOT NULL,
                          `valid_to` date NOT NULL,
                          `created_by` int(10) UNSIGNED NOT NULL,
                          `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                          `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ratings`
--

CREATE TABLE `ratings` (
                           `rating_id` int(10) UNSIGNED NOT NULL,
                           `order_id` int(10) UNSIGNED NOT NULL,
                           `user_id` int(10) UNSIGNED NOT NULL,
                           `driver_id` int(10) UNSIGNED NOT NULL,
                           `rating` int(11) NOT NULL,
                           `review` longtext NOT NULL,
                           `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
                          `report_id` int(10) UNSIGNED NOT NULL,
                          `user_id` int(10) UNSIGNED NOT NULL,
                          `complaint` longtext NOT NULL,
                          `status` enum('OPENED','CLOSED') NOT NULL,
                          `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                          `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `storelocation`
--

CREATE TABLE `storelocation` (
                                 `store_id` int(10) UNSIGNED NOT NULL,
                                 `address` text NOT NULL,
                                 `city` varchar(255) NOT NULL,
                                 `x_coor` float NOT NULL,
                                 `y_coor` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `stores`
--

CREATE TABLE `stores` (
                          `store_id` int(10) UNSIGNED NOT NULL,
                          `owner_id` int(10) UNSIGNED NOT NULL,
                          `store_name` varchar(255) NOT NULL,
                          `address` text NOT NULL,
                          `phone_number` varchar(255) NOT NULL,
                          `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                          `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `subscription`
--

CREATE TABLE `subscription` (
                                `subs_id` int(10) UNSIGNED NOT NULL,
                                `user_id` int(10) UNSIGNED NOT NULL,
                                `subs_type` enum('WEEKLY','MONTHLY','ANNUALY') NOT NULL,
                                `subs_desc` longtext NOT NULL,
                                `valid_from` date NOT NULL,
                                `valid_to` date NOT NULL,
                                `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `userbalances`
--

CREATE TABLE `userbalances` (
                                `user_id` int(10) UNSIGNED NOT NULL,
                                `balance` double NOT NULL,
                                `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `userlog`
--

CREATE TABLE `userlog` (
                           `log_id` int(10) UNSIGNED NOT NULL,
                           `user_id` int(10) UNSIGNED NOT NULL,
                           `activity_type` enum('LOGIN','LOGOUT') NOT NULL,
                           `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
                         `user_id` int(10) UNSIGNED NOT NULL,
                         `username` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `phone_number` varchar(255) NOT NULL,
                         `user_type` enum('CUSTOMER','DRIVER','ADMIN') NOT NULL,
                         `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                         `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `name`, `password`, `email`, `phone_number`, `user_type`, `created_at`, `updated_at`) VALUES
    (4, 'Test', 'Testing', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'test@gmail.com', '0000', 'CUSTOMER', '2024-07-07 09:35:19', '2024-07-07 09:35:19');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
                           `vehicle_id` int(10) UNSIGNED NOT NULL,
                           `vehicle_type` enum('CAR','BIKE') NOT NULL,
                           `vehicle_plat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `vehiclemaintenance`
--

CREATE TABLE `vehiclemaintenance` (
                                      `maintenance_id` int(10) UNSIGNED NOT NULL,
                                      `driver_id` int(10) UNSIGNED NOT NULL,
                                      `schedule_date` date NOT NULL,
                                      `status` enum('SCHEDULED','COMPLETED','CANCELED','ONGOING') NOT NULL,
                                      `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                                      `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
    ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `drivers`
--
ALTER TABLE `drivers`
    ADD PRIMARY KEY (`driver_id`),
  ADD KEY `vehicle_id` (`vehicle_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
    ADD PRIMARY KEY (`order_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `driver_id` (`driver_id`);

--
-- Indexes for table `package`
--
ALTER TABLE `package`
    ADD PRIMARY KEY (`package_id`),
  ADD KEY `owner_id` (`owner_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
    ADD PRIMARY KEY (`product_id`),
  ADD KEY `store_id` (`store_id`);

--
-- Indexes for table `promos`
--
ALTER TABLE `promos`
    ADD PRIMARY KEY (`promo_id`),
  ADD KEY `created_by` (`created_by`);

--
-- Indexes for table `ratings`
--
ALTER TABLE `ratings`
    ADD PRIMARY KEY (`rating_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `driver_id` (`driver_id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
    ADD PRIMARY KEY (`report_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `storelocation`
--
ALTER TABLE `storelocation`
    ADD PRIMARY KEY (`store_id`);

--
-- Indexes for table `stores`
--
ALTER TABLE `stores`
    ADD PRIMARY KEY (`store_id`),
  ADD KEY `owner_id` (`owner_id`);

--
-- Indexes for table `subscription`
--
ALTER TABLE `subscription`
    ADD PRIMARY KEY (`subs_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `userbalances`
--
ALTER TABLE `userbalances`
    ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `userlog`
--
ALTER TABLE `userlog`
    ADD PRIMARY KEY (`log_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
    ADD PRIMARY KEY (`vehicle_id`);

--
-- Indexes for table `vehiclemaintenance`
--
ALTER TABLE `vehiclemaintenance`
    ADD PRIMARY KEY (`maintenance_id`),
  ADD KEY `driver_id` (`driver_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
    MODIFY `admin_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `drivers`
--
ALTER TABLE `drivers`
    MODIFY `driver_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
    MODIFY `order_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `package`
--
ALTER TABLE `package`
    MODIFY `package_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
    MODIFY `product_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `promos`
--
ALTER TABLE `promos`
    MODIFY `promo_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
    MODIFY `rating_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
    MODIFY `report_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `storelocation`
--
ALTER TABLE `storelocation`
    MODIFY `store_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stores`
--
ALTER TABLE `stores`
    MODIFY `store_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subscription`
--
ALTER TABLE `subscription`
    MODIFY `subs_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `userlog`
--
ALTER TABLE `userlog`
    MODIFY `log_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
    MODIFY `vehicle_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vehiclemaintenance`
--
ALTER TABLE `vehiclemaintenance`
    MODIFY `maintenance_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
    ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `drivers`
--
ALTER TABLE `drivers`
    ADD CONSTRAINT `drivers_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`vehicle_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
    ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`driver_id`);

--
-- Constraints for table `package`
--
ALTER TABLE `package`
    ADD CONSTRAINT `package_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
    ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`);

--
-- Constraints for table `promos`
--
ALTER TABLE `promos`
    ADD CONSTRAINT `promos_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `ratings`
--
ALTER TABLE `ratings`
    ADD CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `ratings_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `ratings_ibfk_3` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`driver_id`);

--
-- Constraints for table `report`
--
ALTER TABLE `report`
    ADD CONSTRAINT `report_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `storelocation`
--
ALTER TABLE `storelocation`
    ADD CONSTRAINT `storelocation_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`);

--
-- Constraints for table `stores`
--
ALTER TABLE `stores`
    ADD CONSTRAINT `stores_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `subscription`
--
ALTER TABLE `subscription`
    ADD CONSTRAINT `subscription_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `userbalances`
--
ALTER TABLE `userbalances`
    ADD CONSTRAINT `userbalances_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `userlog`
--
ALTER TABLE `userlog`
    ADD CONSTRAINT `userlog_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `vehiclemaintenance`
--
ALTER TABLE `vehiclemaintenance`
    ADD CONSTRAINT `vehiclemaintenance_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`driver_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
