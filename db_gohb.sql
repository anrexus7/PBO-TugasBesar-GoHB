-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 10, 2024 at 06:13 PM
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
                         `user_id` int(10) UNSIGNED NOT NULL,
                         `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                         `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

CREATE TABLE `drivers` (
                           `driver_id` int(10) UNSIGNED NOT NULL,
                           `user_id` int(10) UNSIGNED NOT NULL,
                           `rating` float NOT NULL,
                           `status` enum('ONLINE','OFFLINE','OCCUPIED') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
                          `destination` varchar(255) NOT NULL,
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
                          `promo_code` varchar(255) NOT NULL,
                          `discount` float NOT NULL,
                          `service_type` enum('GOBIKE','GOCAR','GOSEND','GOFOOD') DEFAULT NULL,
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
                                                                                        (25, 'Margasuka', 'Babakan Ciparay', -6.954379103668284, 107.57450357411693),
                                                                                        (26, 'Sukahaji', 'Babakan Ciparay', -6.927140468833126, 107.5833764843819),
                                                                                        (27, 'Batununggal', 'Bandung Kidul', -6.952289001051662, 107.63207167985657),
                                                                                        (28, 'Kujangsari', 'Bandung Kidul', -6.962143910472071, 107.64288806622447),
                                                                                        (29, 'Mengger', 'Bandung Kidul', -6.960745067664971, 107.62887005822292),
                                                                                        (30, 'Wates', 'Bandung Kidul', -6.9585656734224814, 107.61565119314237),
                                                                                        (31, 'Caringin', 'Bandung Kulon', -6.931495884288152, 107.5701041002759),
                                                                                        (32, 'Cibuntu', 'Bandung Kulon', -6.921877462548265, 107.57195260672565),
                                                                                        (33, 'Cigondewah Kaler', 'Bandung Kulon', -6.93525421393837, 107.56225786506624),
                                                                                        (34, 'Cigondewah Kidul', 'Bandung Kulon', -6.941433355169392, 107.56202489842711),
                                                                                        (35, 'Cigondewah Rahayu', 'Bandung Kulon', -6.9480157487881815, 107.5634029322572),
                                                                                        (36, 'Cijerah', 'Bandung Kulon', -6.920122460119257, 107.56710909232889),
                                                                                        (37, 'Gempolsari', 'Bandung Kulon', -6.931836738673542, 107.55333216860694),
                                                                                        (38, 'Warungmuncang', 'Bandung Kulon', -6.924441327760643, 107.57673556903973),
                                                                                        (39, 'Cihapit', 'Bandung Wetan', -6.907807200673085, 107.62693561903976),
                                                                                        (40, 'Citarum', 'Bandung Wetan', -6.904695719020191, 107.61625653612417),
                                                                                        (41, 'Tamansari', 'Bandung Wetan', -6.9013613304145345, 107.60729748922236),
                                                                                        (42, 'Binong', 'Batununggal', -6.938679065301171, 107.64058015735814),
                                                                                        (43, 'Cibangkong', 'Batununggal', -6.923999450000003, 107.63327239048012),
                                                                                        (44, 'Gumuruh', 'Batununggal', -6.938292168696674, 107.63692389685427),
                                                                                        (45, 'Kacapiring', 'Batununggal', -6.917508670707428, 107.63324461109275),
                                                                                        (46, 'Kebongedang', 'Batununggal', -6.926177698180529, 107.6429088819813),
                                                                                        (47, 'Kebonwaru', 'Batununggal', -6.9164925, 107.6415478724614),
                                                                                        (48, 'Maleer', 'Batununggal', -6.926716863819167, 107.63953592486206),
                                                                                        (49, 'Samoja', 'Batununggal', -6.921483987117907, 107.6271913452814),
                                                                                        (50, 'Babakan Asih', 'Bojongloa Kaler', -6.932553394472322, 107.59469935582229),
                                                                                        (51, 'Babakan Tarogong', 'Bojongloa Kaler', -6.92772591835715, 107.59086450157287),
                                                                                        (52, 'Jamika', 'Bojongloa Kaler', -6.922510734081553, 107.58926673650659),
                                                                                        (53, 'Kopo', 'Bojongloa Kaler', -6.94136965079045, 107.58765279500547),
                                                                                        (54, 'Suka Asih', 'Bojongloa Kaler', -6.933417682515981, 107.58903871034762),
                                                                                        (55, 'Cibaduyut', 'Bojongloa Kidul', -6.953360102017262, 107.59330381349338),
                                                                                        (56, 'Cibaduyut Kidul', 'Bojongloa Kidul', -6.960012337760908, 107.5903958978753),
                                                                                        (57, 'Cibaduyut Wetan', 'Bojongloa Kidul', -6.959275587918634, 107.59922376321747),
                                                                                        (58, 'Kebon Lega', 'Bojongloa Kidul', -6.9465914551691235, 107.59936690998907),
                                                                                        (59, 'Mekarwangi', 'Bojongloa Kidul', -6.954350166720211, 107.60506517088852),
                                                                                        (60, 'Situsaeur', 'Bojongloa Kidul', -6.94071353203864, 107.59548793383007),
                                                                                        (61, 'Cijawura', 'Buahbatu', -6.959276796665412, 107.6519875287529),
                                                                                        (62, 'Jatisari', 'Buahbatu', -6.936225519243602, 107.66134619288076),
                                                                                        (63, 'Margasari', 'Buahbatu', -6.953537362021705, 107.6547973016555),
                                                                                        (64, 'Sekejati', 'Buahbatu', -6.945079437822697, 107.65367012985656),
                                                                                        (65, 'Cigadung', 'Cibeunying Kaler', -6.881292995778236, 107.62678896043026),
                                                                                        (66, 'Cihaurgeulis', 'Cibeunying Kaler', -6.9007067919735565, 107.62819108725172),
                                                                                        (67, 'Neglasari', 'Cibeunying Kaler', -6.894581940793267, 107.63902122883552),
                                                                                        (68, 'Sukaluyu', 'Cibeunying Kaler', -6.895853345106651, 107.6298853478753),
                                                                                        (69, 'Cicadas', 'Cibeunying Kidul', -6.9059298261385065, 107.63711032911147),
                                                                                        (70, 'Cikutra', 'Cibeunying Kidul', -6.902460616101466, 107.64064806377431),
                                                                                        (71, 'Padasuka', 'Cibeunying Kidul', -6.9037510083455595, 107.64775591884647),
                                                                                        (72, 'Pasirlayung', 'Cibeunying Kidul', -6.895140518226741, 107.65612962466874),
                                                                                        (73, 'Sukamaju', 'Cibeunying Kidul', -6.908657537619091, 107.6339673637693),
                                                                                        (74, 'Sukapada', 'Cibeunying Kidul', -6.896335592316177, 107.6470650782837),
                                                                                        (75, 'Cipadung', 'Cibiru', -6.923368614564611, 107.7193419235651),
                                                                                        (76, 'Cisurupan', 'Cibiru', -6.905475589932691, 107.72508470943724),
                                                                                        (77, 'Palasari', 'Cibiru', -6.913446953225485, 107.723233536728),
                                                                                        (78, 'Pasirbiru', 'Cibiru', -6.921481223037026, 107.7242049152315),
                                                                                        (79, 'Arjuna', 'Cicendo', -6.9102776446092165, 107.59356003595477),
                                                                                        (80, 'Husen Sastranegara', 'Cicendo', -6.901403412847973, 107.5784605343819),
                                                                                        (81, 'Pajajaran', 'Cicendo', -6.8994083226160825, 107.58770445711924),
                                                                                        (82, 'Pamoyanan', 'Cicendo', -6.902803564480233, 107.5941336018488),
                                                                                        (83, 'Pasirkaliki', 'Cicendo', -6.906518070129679, 107.60109672116444),
                                                                                        (84, 'Sukaraja', 'Cicendo', -6.890780392658886, 107.56623620168199),
                                                                                        (85, 'Ciumbuleuit', 'Cidadap', -6.861102440510691, 107.6125098596025),
                                                                                        (86, 'Hegarmanah', 'Cidadap', -6.874601645694156, 107.60062002356507),
                                                                                        (87, 'Ledeng', 'Cidadap', -6.856235209890419, 107.59804210082777),
                                                                                        (88, 'Babakan Penghulu', 'Cinambo', -6.935593935810639, 107.68889493658926),
                                                                                        (89, 'Cisaranten Wetan', 'Cinambo', -6.924017915403517, 107.68793395896806),
                                                                                        (90, 'Pakemitan', 'Cinambo', -6.921065925527788, 107.69343192356509),
                                                                                        (91, 'Sukamulya', 'Cinambo', -6.921376179356959, 107.69821385480124),
                                                                                        (92, 'Cipaganti', 'Coblong', -6.891292423521375, 107.60409753493376),
                                                                                        (93, 'Dago', 'Coblong', -6.874870602120679, 107.61607468493372),
                                                                                        (94, 'Lebakgede', 'Coblong', -6.892732417588565, 107.6161807354856),
                                                                                        (95, 'Lebaksiliwangi', 'Coblong', -6.891524073625464, 107.61018930711923),
                                                                                        (96, 'Sadangserang', 'Coblong', -6.893782231794192, 107.62459778678256),
                                                                                        (97, 'Sekeloa', 'Coblong', -6.8862644271087525, 107.62147049362588),
                                                                                        (98, 'Cimincrang', 'Gedebage', -6.9454764071777015, 107.70347414890149),
                                                                                        (99, 'Cisaranten Kidul', 'Gedebage', -6.948567855116574, 107.69254061997545),
                                                                                        (100, 'Rancabolang', 'Gedebage', -6.963010578424027, 107.6938660517841),
                                                                                        (101, 'Rancanumpang', 'Gedebage', -6.960425359654682, 107.71002379677485),
                                                                                        (102, 'Babakansari', 'Kiaracondong', -6.92316244999999, 107.65050343207966),
                                                                                        (103, 'Babakansurabaya', 'Kiaracondong', -6.913761362908491, 107.64758261663921),
                                                                                        (104, 'Cicaheum', 'Kiaracondong', -6.906000111148466, 107.65278674036975),
                                                                                        (105, 'Kebonkangkung', 'Kiaracondong', -6.938103906155175, 107.64379813881932),
                                                                                        (106, 'Kebunjayanti', 'Kiaracondong', -6.927475534744552, 107.64697434592263),
                                                                                        (107, 'Sukapura', 'Kiaracondong', -6.934323638404799, 107.65121497449451),
                                                                                        (108, 'Burangrang', 'Lengkong', -6.929670491431416, 107.61796318086745),
                                                                                        (109, 'Cijagra', 'Lengkong', -6.944453970374652, 107.62549355869572),
                                                                                        (110, 'Cikawao', 'Lengkong', -6.926953654368466, 107.61224048484179),
                                                                                        (111, 'Lingkar Selatan', 'Lengkong', -6.929699730755002, 107.62950068927236),
                                                                                        (112, 'Malabar', 'Lengkong', -6.926311572551452, 107.62177706099152),
                                                                                        (113, 'Paledang', 'Lengkong', -6.924783297476105, 107.61467869989761),
                                                                                        (114, 'Turangga', 'Lengkong', -6.938370005610263, 107.62997924738158),
                                                                                        (115, 'Jatihandap', 'Mandalajati', -6.895812354337693, 107.66346235442369),
                                                                                        (116, 'Karangpamulang', 'Mandalajati', -6.898017340077717, 107.67185466976876),
                                                                                        (117, 'Pasir Impun', 'Mandalajati', -6.897316099625089, 107.67888160546605),
                                                                                        (118, 'Sindangjaya', 'Mandalajati', -6.900778884754477, 107.68383547373544),
                                                                                        (119, 'Cipadung Kidul', 'Panyileukan', -6.940824340071613, 107.71210919220027),
                                                                                        (120, 'Cipadung Kulon', 'Panyileukan', -6.9210909266544025, 107.70441526977181),
                                                                                        (121, 'Cipadung Wetan', 'Panyileukan', -6.931708981888526, 107.70980026943838),
                                                                                        (122, 'Mekarmulya', 'Panyileukan', -6.931141721042378, 107.69974614305026),
                                                                                        (123, 'Cipamokolan', 'Rancasari', -6.946113602615585, 107.67566636944656),
                                                                                        (124, 'Darwati', 'Rancasari', -6.96136576686835, 107.68193024599334),
                                                                                        (125, 'Manjahlega', 'Rancasari', -6.9471160944379795, 107.66685395443054),
                                                                                        (126, 'Mekar Jaya', 'Rancasari', -6.9625827323038925, 107.66898657081599),
                                                                                        (127, 'Ancol', 'Regol', -6.940900480856483, 107.61641468850196),
                                                                                        (128, 'Balonggede', 'Regol', -6.925086760737103, 107.60730334116718),
                                                                                        (129, 'Ciateul', 'Regol', -6.934615295137974, 107.60762173271448),
                                                                                        (130, 'Cigereleng', 'Regol', -6.940635544615136, 107.6101308202952),
                                                                                        (131, 'Ciseureuh', 'Regol', -6.950665289074836, 107.6125082009856),
                                                                                        (132, 'Pasirluyu', 'Regol', -6.947343506984121, 107.61928032269937),
                                                                                        (133, 'Pungkur', 'Regol', -6.9298694414113395, 107.6069528015039),
                                                                                        (134, 'Cipedes', 'Sukajadi', -6.88610089817881, 107.59382046580079),
                                                                                        (135, 'Pasteur', 'Sukajadi', -6.890520662942794, 107.59970102961246),
                                                                                        (136, 'Sukabungah', 'Sukajadi', -6.895639971678651, 107.59409765045767),
                                                                                        (137, 'Sukagalih', 'Sukajadi', -6.88674646629027, 107.58622375442285),
                                                                                        (138, 'Sukawarna', 'Sukajadi', -6.886305517275426, 107.57765640734722),
                                                                                        (139, 'Gegerkalong', 'Sukasari', -6.868307770408678, 107.58908831972629),
                                                                                        (140, 'Isola', 'Sukasari', -6.8528997267037255, 107.59334441821885),
                                                                                        (141, 'Sarijadi', 'Sukasari', -6.8756295144909, 107.57748399993395),
                                                                                        (142, 'Sukarasa', 'Sukasari', -6.873862215111176, 107.58563505838754),
                                                                                        (143, 'Babakanciamis', 'Sumur Bandung', -6.911303303920704, 107.60873061632506),
                                                                                        (144, 'Braga', 'Sumur Bandung', -6.91811283687474, 107.60860178563689),
                                                                                        (145, 'Kebonpisang', 'Sumur Bandung', -6.919205698140888, 107.61667749461161),
                                                                                        (146, 'Merdeka', 'Sumur Bandung', -6.913263821854405, 107.62148971323111),
                                                                                        (147, 'Cigending', 'Ujungberung', -6.910510272225948, 107.69765760839216),
                                                                                        (148, 'Pasanggrahan', 'Ujungberung', -6.910935509498256, 107.71285244907504),
                                                                                        (149, 'Pasirendah', 'Ujungberung', -6.90480812281523, 107.69030089701326),
                                                                                        (150, 'Pasirjati', 'Ujungberung', -6.903624254699069, 107.71074476424016),
                                                                                        (151, 'Pasirwangi', 'Ujungberung', -6.900024445992437, 107.70577284661137);

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
                                `wallet_id` int(10) UNSIGNED NOT NULL,
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
                         `password` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `phone_number` varchar(255) NOT NULL,
                         `user_type` enum('CUSTOMER','DRIVER','ADMIN') NOT NULL,
                         `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
                         `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  ADD KEY `fk_user_id` (`user_id`);

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
    MODIFY `promo_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

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
    MODIFY `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

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
    ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

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
