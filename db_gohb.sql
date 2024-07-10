-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 10, 2024 at 06:55 PM
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
-- Database: `db_gohb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `user_id`, `created_at`, `updated_at`) VALUES
(1, 5, '2024-07-10 12:13:46', '2024-07-10 14:27:22');

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

CREATE TABLE `drivers` (
  `driver_id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `rating` float DEFAULT NULL,
  `status` enum('ONLINE','OFFLINE','OCCUPIED') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `drivers`
--

INSERT INTO `drivers` (`driver_id`, `user_id`, `rating`, `status`) VALUES
(1, 4, NULL, 'ONLINE'),
(2, 7, NULL, 'ONLINE');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(10) UNSIGNED NOT NULL,
  `customer_id` int(10) UNSIGNED NOT NULL,
  `driver_id` int(10) UNSIGNED NOT NULL,
  `service_type` enum('GOBIKE','GOCAR','GOSEND','GOFOOD') NOT NULL,
  `vehicle_type` enum('CAR','BIKE') NOT NULL,
  `region_id` int(11) NOT NULL,
  `destination` varchar(255) NOT NULL,
  `current_location` varchar(255) CHARACTER SET utf32 COLLATE utf32_general_ci NOT NULL,
  `cost` double NOT NULL,
  `order_status` enum('PENDING','ASSIGNED','PICKING','DROPPING','COMPLETED') NOT NULL,
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
  `admin_id` int(10) UNSIGNED DEFAULT NULL,
  `promo_code` varchar(255) NOT NULL,
  `discount` float NOT NULL,
  `service_type` enum('GOBIKE','GOCAR','GOSEND','GOFOOD') DEFAULT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `promos`
--

INSERT INTO `promos` (`promo_id`, `admin_id`, `promo_code`, `discount`, `service_type`, `valid_from`, `valid_to`, `created_at`, `updated_at`) VALUES
(1, NULL, 'YESYESYES', 15000, 'GOBIKE', '2024-07-10', '2024-07-24', '2024-07-10 15:27:03', '2024-07-10 15:27:03');

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
-- Table structure for table `regions`
--

CREATE TABLE `regions` (
  `region_id` int(3) NOT NULL,
  `village` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `regions`
--

INSERT INTO `regions` (`region_id`, `village`, `district`, `latitude`, `longitude`) VALUES
(1, 'Campaka', 'Andir', -6.900116043788698, 107.56518089566322),
(2, 'Ciroyom', 'Andir', -6.915872403125079, 107.58887382069527),
(3, 'Dunguscariang', 'Andir', -6.914815172239449, 107.58022126034763),
(4, 'Garuda', 'Andir', -6.911940415549802, 107.57565706376928),
(5, 'Kebonjeruk', 'Andir', -6.917748876604702, 107.59917895358542),
(6, 'Maleber', 'Andir', -6.908722165108434, 107.57181275725074),
(7, 'Cibadak', 'Astana Anyar', -6.923048543900033, 107.5957076097958),
(8, 'Karanganyar', 'Astana Anyar', -6.923487944785102, 107.60134266988881),
(9, 'Karasak', 'Astana Anyar', -6.949413918924009, 107.60815731588593),
(10, 'Nyengseret', 'Astana Anyar', -6.930912742234248, 107.6016804402042),
(11, 'Panjunan', 'Astana Anyar', -6.9296218146363655, 107.59807787458611),
(12, 'Pelindunghewan', 'Astana Anyar', -6.940161352711022, 107.6029754717163),
(13, 'Antapani Kidul', 'Antapani', -6.923561254804692, 107.66025180711924),
(14, 'Antapani Kulon', 'Antapani', -6.9103362842920175, 107.65625936376931),
(15, 'Antapani Tengah', 'Antapani', -6.917670904443877, 107.66285336683241),
(16, 'Antapani Wetan', 'Antapani', -6.909105963168787, 107.66380802513795),
(17, 'Cisaranten Bina Harapan', 'Arcamanik', -6.911647213742029, 107.68539291349337),
(18, 'Cisaranten Endah', 'Arcamanik', -6.931511172128537, 107.67216287301325),
(19, 'Cisaranten Kulon', 'Arcamanik', -6.925279086559913, 107.68050930424941),
(20, 'Sukamiskin', 'Arcamanik', -6.913145711268177, 107.67414851561807),
(21, 'Babakan', 'Babakan Ciparay', -6.933178078963069, 107.57547591506625),
(22, 'Babakanciparay', 'Babakan Ciparay', -6.941458049999989, 107.57946960877476),
(23, 'Cirangrang', 'Babakan Ciparay', -6.9575431336988185, 107.58494900896805),
(24, 'Margahayu Utara', 'Babakan Ciparay', -6.947792349795048, 107.5762097275386),
(25, 'Margasuka', 'Babakan Ciparay', -6.954379103668284, 107.57450357411693);

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
  `region_id` int(11) NOT NULL,
  `city` varchar(255) NOT NULL
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
  `price` double NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subscription`
--

INSERT INTO `subscription` (`subs_id`, `user_id`, `subs_type`, `price`, `valid_from`, `valid_to`, `status`) VALUES
(1, 3, 'WEEKLY', 10000, '2024-07-10', '2024-07-17', 1);

-- --------------------------------------------------------

--
-- Table structure for table `userbalances`
--

CREATE TABLE `userbalances` (
  `wallet_id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `balance` double NOT NULL,
  `coin` double UNSIGNED DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userbalances`
--

INSERT INTO `userbalances` (`wallet_id`, `user_id`, `balance`, `coin`, `updated_at`) VALUES
(1, 3, 5000000, 0, '2024-07-10 03:13:50'),
(2, 4, 5000000, 0, '2024-07-10 15:41:28'),
(3, 5, 5000000, 0, '2024-07-10 15:41:34'),
(4, 6, 5000000, 0, '2024-07-10 15:41:41'),
(5, 7, 5000000, NULL, '2024-07-10 16:26:08');

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
(3, 'john_doe', 'jhony does', '6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', 'john@example.com', '1234567890', 'CUSTOMER', '2024-07-10 03:01:56', '2024-07-10 03:01:56'),
(4, 'asep123', 'tatang kasep', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f', 'asep@gmail.com', '9876543201', 'DRIVER', '2024-07-10 11:26:33', '2024-07-10 11:26:33'),
(5, 'admin1', 'dimas', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'dimas@ithb.ac.id', '9078564321', 'ADMIN', '2024-07-10 12:13:46', '2024-07-10 12:14:05'),
(6, 'kasel123', 'michael', '5fc2a69c692964582a0f0bff4d5574a665666b1404254bb7a366a90c55330d87', 'chael@email.com', '9021784365', 'CUSTOMER', '2024-07-10 12:55:15', '2024-07-10 12:55:15'),
(7, 'asdwa', 'wawas', '2f6d9c6cd4b23e88b73bc2d3b0ed190a1f5f6dc8518c86cb55c6bc7e9e9ff0b3', 'asdwa@gmail.com', '9012345678', 'DRIVER', '2024-07-10 16:26:08', '2024-07-10 16:26:08');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `vehicle_id` int(10) UNSIGNED NOT NULL,
  `driver_id` int(10) UNSIGNED NOT NULL,
  `vehicle_type` enum('CAR','BIKE') NOT NULL,
  `vehicle_plat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`vehicle_id`, `driver_id`, `vehicle_type`, `vehicle_plat`) VALUES
(1, 1, 'BIKE', 'B4321CAZ');

-- --------------------------------------------------------

--
-- Table structure for table `vehiclemaintenance`
--

CREATE TABLE `vehiclemaintenance` (
  `maintenance_id` int(10) UNSIGNED NOT NULL,
  `driver_id` int(10) UNSIGNED NOT NULL,
  `admin_id` int(10) UNSIGNED NOT NULL,
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
  ADD PRIMARY KEY (`admin_id`),
  ADD KEY `admin_ibfk_1` (`user_id`);

--
-- Indexes for table `drivers`
--
ALTER TABLE `drivers`
  ADD PRIMARY KEY (`driver_id`),
  ADD KEY `drivers_ibfk_1` (`user_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `driver_id` (`driver_id`),
  ADD KEY `region_id` (`region_id`);

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
  ADD KEY `promos_ibfk_1` (`admin_id`);

--
-- Indexes for table `ratings`
--
ALTER TABLE `ratings`
  ADD PRIMARY KEY (`rating_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `driver_id` (`driver_id`);

--
-- Indexes for table `regions`
--
ALTER TABLE `regions`
  ADD PRIMARY KEY (`region_id`);

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
  ADD PRIMARY KEY (`store_id`),
  ADD KEY `region_id` (`region_id`);

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
  ADD PRIMARY KEY (`wallet_id`),
  ADD KEY `user_id` (`user_id`);

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
  ADD PRIMARY KEY (`vehicle_id`),
  ADD KEY `driver_id` (`driver_id`);

--
-- Indexes for table `vehiclemaintenance`
--
ALTER TABLE `vehiclemaintenance`
  ADD PRIMARY KEY (`maintenance_id`),
  ADD KEY `driver_id` (`driver_id`),
  ADD KEY `admin_id` (`admin_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `drivers`
--
ALTER TABLE `drivers`
  MODIFY `driver_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

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
  MODIFY `promo_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
  MODIFY `rating_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `regions`
--
ALTER TABLE `regions`
  MODIFY `region_id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=152;

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
  MODIFY `subs_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `userbalances`
--
ALTER TABLE `userbalances`
  MODIFY `wallet_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `userlog`
--
ALTER TABLE `userlog`
  MODIFY `log_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `vehicle_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `drivers`
--
ALTER TABLE `drivers`
  ADD CONSTRAINT `drivers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`driver_id`),
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`region_id`) REFERENCES `regions` (`region_id`);

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
  ADD CONSTRAINT `promos_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`);

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
  ADD CONSTRAINT `storelocation_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  ADD CONSTRAINT `storelocation_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `regions` (`region_id`);

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
-- Constraints for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`driver_id`);

--
-- Constraints for table `vehiclemaintenance`
--
ALTER TABLE `vehiclemaintenance`
  ADD CONSTRAINT `vehiclemaintenance_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`driver_id`),
  ADD CONSTRAINT `vehiclemaintenance_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
