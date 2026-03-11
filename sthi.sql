-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: mysql:3306
-- Generation Time: Mar 10, 2026 at 12:19 PM
-- Server version: 8.0.45
-- PHP Version: 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sthi`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `icon` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `investments`
--

CREATE TABLE `investments` (
  `id` int NOT NULL,
  `amount` int DEFAULT NULL,
  `balance` int DEFAULT NULL,
  `bonus` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `earned` int DEFAULT NULL,
  `month` varchar(10) DEFAULT NULL,
  `no` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `year` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `investment_types`
--

CREATE TABLE `investment_types` (
  `id` int NOT NULL,
  `amount` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `interest_rate` varchar(10) DEFAULT NULL,
  `maturity_date` datetime(6) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `total_value` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `message` text NOT NULL,
  `metadata` json DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `privileges`
--

CREATE TABLE `privileges` (
  `id` int NOT NULL,
  `access` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `privilege_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `name_kn` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock_quantity` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `vendor_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `re_amenities`
--

CREATE TABLE `re_amenities` (
  `amenity_id` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_amenities`
--

INSERT INTO `re_amenities` (`amenity_id`, `created_at`, `icon`, `name`, `status`, `updated_at`) VALUES
(4, '2025-12-28 00:33:51.000000', NULL, 'Pedestrian Pathway', 1, NULL),
(5, '2025-12-28 00:33:51.000000', NULL, 'Water Feature and Bubbler Fountain', 1, NULL),
(6, '2025-12-28 00:33:51.000000', NULL, 'Amphitheatre', 1, NULL),
(7, '2025-12-28 00:33:51.000000', NULL, 'Yoga Lawn', 1, NULL),
(8, '2025-12-28 00:33:51.000000', NULL, 'Camp Fire', 1, NULL),
(9, '2025-12-28 00:33:51.000000', NULL, 'Sit Outs & Gazebos', 1, NULL),
(10, '2025-12-28 00:33:51.000000', NULL, 'Discovery Garden', 1, NULL),
(11, '2025-12-28 00:33:51.000000', NULL, 'Multipurpose Court', 1, NULL),
(12, '2025-12-28 00:33:51.000000', NULL, 'Kids Playground', 1, NULL),
(13, '2025-12-28 00:33:51.000000', NULL, 'Rock Climbing', 1, NULL),
(14, '2025-12-28 00:33:51.000000', NULL, 'Senior Citizen Area', 1, NULL),
(15, '2025-12-28 00:33:51.000000', NULL, 'Basketball Court', 1, NULL),
(16, '2025-12-28 00:33:51.000000', NULL, 'Joggers Park', 1, NULL),
(17, '2025-12-28 00:33:51.000000', NULL, 'Clubhouse', 1, NULL),
(18, '2025-12-28 00:33:51.000000', NULL, 'Outdoor Gyms', 1, NULL),
(19, '2025-12-28 00:33:51.000000', NULL, 'Reflexology Pathway', 1, NULL),
(20, '2025-12-28 00:33:51.000000', NULL, 'Pet Park', 1, NULL),
(21, '2025-12-28 00:33:51.000000', NULL, 'Yoga and Meditation Deck', 1, NULL),
(22, '2025-12-28 00:33:51.000000', NULL, 'Orchard', 1, NULL),
(23, '2025-12-28 00:33:51.000000', NULL, 'Miyawaki Greens', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `re_construction_status`
--

CREATE TABLE `re_construction_status` (
  `construction_status_id` int NOT NULL,
  `code` varchar(255) NOT NULL,
  `label` varchar(255) NOT NULL,
  `is_active` int DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_construction_status`
--

INSERT INTO `re_construction_status` (`construction_status_id`, `code`, `label`, `is_active`, `created_at`) VALUES
(1, 'NEW_LAUNCH', 'New Launch', 1, '2025-12-27 22:14:29'),
(2, 'UNDER_CONSTRUCTION', 'Under Construction', 1, '2025-12-27 22:14:29'),
(3, 'READY_TO_MOVE', 'Ready to Move', 1, '2025-12-27 22:14:29'),
(4, 'COMPLETED', 'Completed', 1, '2025-12-27 22:14:29'),
(5, 'ON_HOLD', 'On Hold', 1, '2025-12-27 22:14:29');

-- --------------------------------------------------------

--
-- Table structure for table `re_developers`
--

CREATE TABLE `re_developers` (
  `developer_id` bigint NOT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `contact_phone` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `developer_name` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `website_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_developers`
--

INSERT INTO `re_developers` (`developer_id`, `contact_email`, `contact_phone`, `created_at`, `description`, `developer_name`, `status`, `updated_at`, `website_url`) VALUES
(1, 'contact@sobha.com', '9999999999', '2025-12-24 03:30:31.000000', 'Premium real estate developer', 'Sobha Group', 1, NULL, 'https://www.sobha.com'),
(2, NULL, NULL, NULL, '', 'Prestige Group', 1, NULL, 'https://www.prestigeconstructions.com/'),
(4, NULL, NULL, '2025-12-30 00:00:00.000000', 'Major Bengaluru developer', 'Brigade Group', 1, NULL, 'https://www.brigadegroup.com'),
(5, NULL, NULL, '2025-12-30 00:00:00.000000', 'National developer with strong Bangalore presence', 'Godrej Properties', 1, NULL, 'https://www.godrejproperties.com'),
(6, NULL, NULL, '2025-12-30 00:00:00.000000', 'Corporate developer & mixed-use projects', 'Embassy Group', 1, NULL, 'https://www.embassyindia.com'),
(7, NULL, NULL, '2025-12-30 00:00:00.000000', 'Bengaluru-headquartered Puravankara', 'Puravankara Limited', 1, NULL, 'https://www.puravankara.com'),
(8, NULL, NULL, '2025-12-30 00:00:00.000000', 'L&T engineering-led realty arm', 'L&T Realty', 1, NULL, 'https://www.lntrealty.com'),
(9, NULL, NULL, '2025-12-30 00:00:00.000000', 'Salarpuria / Sattva group', 'Salarpuria Sattva Group', 1, NULL, 'https://www.salarpuriasattva.com'),
(10, NULL, NULL, '2025-12-30 00:00:00.000000', 'Residential & commercial developer', 'Mantri Developers', 1, NULL, 'https://www.mantri.in'),
(11, NULL, NULL, '2025-12-30 00:00:00.000000', 'Shriram group realty arm', 'Shriram Properties', 1, NULL, 'https://www.shriramproperties.in'),
(12, NULL, NULL, '2025-12-30 00:00:00.000000', 'Mahindra Lifespaces (Mahindra Group)', 'Mahindra Lifespace Developers', 1, NULL, 'https://www.mahindralifespaces.com'),
(13, NULL, NULL, '2025-12-30 00:00:00.000000', 'Provident / Puravankara affordable arm', 'Provident Housing (Puravankara Group)', 1, NULL, 'https://www.providentestate.com'),
(14, NULL, NULL, '2025-12-30 00:00:00.000000', 'Adarsh Group – active in Bangalore', 'Adarsh Developers', 1, NULL, 'https://www.adarshgroup.co.in'),
(15, NULL, NULL, '2025-12-30 00:00:00.000000', 'Mumbai-headquartered national developer', 'Lodha Group', 1, NULL, 'https://www.lodhagroup.in'),
(16, NULL, NULL, '2025-12-30 00:00:00.000000', 'DLF – national developer with Bangalore projects', 'DLF Ltd', 1, NULL, 'https://www.dlf.in'),
(17, NULL, NULL, '2025-12-30 00:00:00.000000', 'Phoenix group / Phoenix Mills (mixed use)', 'The Phoenix Mills Ltd', 1, NULL, 'https://www.thephoenixmills.com'),
(18, NULL, NULL, '2025-12-30 00:00:00.000000', 'Total Environment – Bangalore-based premium homes', 'Total Environment', 1, NULL, 'https://www.totalenvironment.com'),
(19, NULL, NULL, '2025-12-30 00:00:00.000000', 'Sumadhura Group – active in Bengaluru', 'Sumadhura Group', 1, NULL, 'https://sumadhura.com'),
(20, NULL, NULL, '2025-12-30 00:00:00.000000', 'Casagrand – South India developer', 'Casagrand Builder Pvt Ltd', 1, NULL, 'https://www.casagrand.co.in'),
(21, NULL, NULL, '2025-12-30 00:00:00.000000', 'Concorde Group – Bangalore projects', 'Concorde Group', 1, NULL, 'https://www.concordegroup.in'),
(22, NULL, NULL, '2025-12-30 00:00:00.000000', 'Century Real Estate – national player', 'Century Real Estate', 1, NULL, 'https://www.centuryrealestate.com'),
(23, NULL, NULL, '2025-12-30 00:00:00.000000', 'Adaarsh / Adarsh (regional leader)', 'Adaarsh Developers', 1, NULL, 'https://www.adarshgroup.co.in'),
(24, NULL, NULL, '2025-12-30 00:00:00.000000', 'Ozone Group – large south India presence', 'Ozone Group', 1, NULL, 'https://www.ozonegroup.in'),
(25, NULL, NULL, '2025-12-30 00:00:00.000000', 'Assetz Property Group – active across Bangalore', 'Assetz Property Group', 1, NULL, 'https://www.assetzproperty.com'),
(26, NULL, NULL, '2025-12-30 00:00:00.000000', 'Shriram / luxury residential projects', 'Shriram Housing', 1, NULL, 'https://www.shriramproperties.in'),
(27, NULL, NULL, '2025-12-30 00:00:00.000000', 'Mahaveer Group – Bangalore projects', 'Mahaveer Group', 1, NULL, 'https://www.mahaveergroup.com'),
(28, NULL, NULL, '2025-12-30 00:00:00.000000', 'Mana Projects – Bangalore developer', 'Mana Projects Pvt Ltd', 1, NULL, 'https://www.manaprojects.com'),
(29, NULL, NULL, '2025-12-30 00:00:00.000000', 'Dreamz Infra – active in Bangalore', 'Dreamz Infra India Pvt Ltd', 1, NULL, 'https://dreamzinfra.com'),
(30, NULL, NULL, '2025-12-30 00:00:00.000000', 'DS-MAX Properties – developer', 'DS MAX Properties Pvt Ltd', 1, NULL, 'https://www.dsmax.in'),
(31, NULL, NULL, '2025-12-30 00:00:00.000000', 'K Raheja / Brigade / national brands', 'K Raheja Corp', 1, NULL, 'https://www.krahejacorp.com'),
(32, NULL, NULL, '2025-12-30 00:00:00.000000', 'SNN Estates – Bengaluru developer', 'SNN Group', 1, NULL, 'https://www.snn.in'),
(33, NULL, NULL, '2025-12-30 00:00:00.000000', 'Goyal & Co – regional developer', 'Goyal & Co', 1, NULL, 'https://www.goyal.co.in'),
(34, NULL, NULL, '2025-12-30 00:00:00.000000', 'Chaitanya Builders & Leasing', 'Chaitanya Builders and Leasing', 1, NULL, 'https://www.chaitanyabuilders.com'),
(35, NULL, NULL, '2025-12-30 00:00:00.000000', 'Svamitva Infra – regional developer', 'Svamitva Infra', 1, NULL, 'https://www.svamitvainfra.com'),
(36, NULL, NULL, '2025-12-30 00:00:00.000000', 'B&B Infra – regional player', 'B&B Infra', 1, NULL, 'https://www.bbinfra.in'),
(37, NULL, NULL, '2025-12-30 00:00:00.000000', 'Sowparnika Projects – southern developer', 'Sowparnika Projects', 1, NULL, 'https://www.sowparnika.com'),
(38, NULL, NULL, '2025-12-30 00:00:00.000000', 'Sumadhura / second listing for coverage', 'Sumadhura Infracon Pvt. Ltd.', 1, NULL, 'https://sumadhura.com'),
(39, NULL, NULL, '2025-12-30 00:00:00.000000', 'Raheja Developers – K Raheja Corp projects', 'Kohinoor Group / K Raheja (regional)', 1, NULL, 'https://www.krahejacorp.com'),
(40, NULL, NULL, '2025-12-30 00:00:00.000000', 'Ramky Estates – regional developer', 'Ramky Estates', 1, NULL, 'https://www.ramky.com'),
(41, NULL, NULL, '2025-12-30 00:00:00.000000', 'Ananthamurthy / local developers cluster', 'Elegant Properties', 1, NULL, 'https://www.elegantproperties.com'),
(42, NULL, NULL, '2025-12-30 00:00:00.000000', 'Hiranandani / large national developer', 'Hiranandani Developers', 1, NULL, 'https://www.hiranandani.com'),
(43, NULL, NULL, '2025-12-30 00:00:00.000000', 'Keppel Land India (projects in Bangalore)', 'Keppel Land India', 1, NULL, 'https://www.keppelland.com'),
(44, NULL, NULL, '2025-12-30 00:00:00.000000', 'Renaissance / regional developer', 'RR Group', 1, NULL, 'https://www.rrlgroup.com'),
(45, NULL, NULL, '2025-12-30 00:00:00.000000', 'Shapoorji Pallonji – national developer', 'Shapoorji Pallonji Real Estate', 1, NULL, 'https://www.shapoorjipallonji.com'),
(47, NULL, NULL, '2025-12-30 00:00:00.000000', 'Ansal API – national developer', 'Ansal Properties & Infrastructure', 1, NULL, 'https://www.ansalapi.com'),
(48, NULL, NULL, '2025-12-30 00:00:00.000000', 'Oberoi Realty – national projects', 'Oberoi Realty Ltd', 1, NULL, 'https://www.oberoirealty.com'),
(49, NULL, NULL, '2025-12-30 00:00:00.000000', 'HGS / regional developer listing', 'Hiranandani Communities', 1, NULL, 'https://www.hiranandani.com'),
(50, NULL, NULL, '2025-12-30 00:00:00.000000', 'Shriram / repeated group entry (keeps DB coverage)', 'Shriram Group', 1, NULL, 'https://www.shriramproperties.in'),
(51, NULL, NULL, '2025-12-30 00:00:00.000000', 'A diversified realty group', 'Ramansh Constructions', 1, NULL, 'https://www.ramanshconstructions.com');

-- --------------------------------------------------------

--
-- Table structure for table `re_leads`
--

CREATE TABLE `re_leads` (
  `id` bigint NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `advisor_id` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `requested_by_user_id` int DEFAULT NULL,
  `advisor_whatsapp_link` varchar(1000) DEFAULT NULL,
  `property_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_leads`
--

INSERT INTO `re_leads` (`id`, `full_name`, `phone`, `email`, `message`, `project_id`, `advisor_id`, `status`, `source`, `created_at`, `updated_at`, `requested_by_user_id`, `advisor_whatsapp_link`, `property_id`) VALUES
(3, 'Rahul Sharma', '9876543210', 'rahul@gmail.com', 'Interested in 3BHK, please call', 2, 2, 'NEW', 'WEB', '2026-01-29 22:00:30', '2026-01-29 22:00:30', NULL, NULL, NULL),
(4, 'Rahul Sharma1', '9876543211', 'rahul@gmail.com', 'Interested in 3BHK, please call', 2, 2, 'NEW', 'WEB', '2026-01-29 22:35:29', '2026-01-29 22:35:29', NULL, NULL, NULL),
(25, 'Srinivas B', '8431182353', 'test@test.com', 'Interested in this property', 5, 2, 'NEW', 'WEB', '2026-02-18 20:17:48', '2026-02-18 20:17:48', NULL, 'https://wa.me/9110656001?text=Hi+Srinivas+B%2C%0A%0AYou+enquired+about+Project+ID+5.%0AI+am+your+property+advisor.+When+would+be+a+good+time+to+call+you%3F%0A%0A%E2%80%94+Shivam', 5),
(26, 'Srinivas B', '84311823531', 'test@tes1t.com', 'Interested in this property', NULL, 2, 'NEW', 'WEB', '2026-02-18 20:18:54', '2026-02-18 20:18:54', NULL, 'https://wa.me/9110656001?text=Hi+Srinivas+B%2C%0A%0AYou+enquired+about+Property+ID+5.%0AI+am+your+property+advisor.+When+would+be+a+good+time+to+call+you%3F%0A%0A%E2%80%94+Shivam', 5),
(27, 'Srinivas', '9110656001', 'test@test.com', 'I am interested in this property.Please call me back.', 5, 2, 'NEW', 'WEB', '2026-02-22 10:49:01', '2026-02-22 10:49:01', NULL, 'https://wa.me/9035327942?text=Hi+Srinivas%2C%0A%0AYou+enquired+about+Project+ID+5.%0AI+am+your+property+advisor.+When+would+be+a+good+time+to+call+you%3F%0A%0A%E2%80%94+Shivam', NULL),
(28, 'srinivas test', '4565655545', 'test@test.com', 'Interested in this property', 6, 2, 'NEW', 'WEB', '2026-02-23 17:11:47', '2026-02-23 17:11:47', NULL, 'https://wa.me/9035327942?text=Hi+srinivas+test%2C%0A%0AYou+enquired+about+Project+ID+6.%0AI+am+your+property+advisor.+When+would+be+a+good+time+to+call+you%3F%0A%0A%E2%80%94+Shivam', 6);

-- --------------------------------------------------------

--
-- Table structure for table `re_lead_routing_config`
--

CREATE TABLE `re_lead_routing_config` (
  `id` bigint NOT NULL,
  `project_id` bigint DEFAULT NULL COMMENT 'NULL = applies to all projects',
  `routing_type` varchar(255) DEFAULT NULL,
  `fixed_advisor_id` bigint DEFAULT NULL COMMENT 'Used when routing_type = FIXED_ADVISOR',
  `is_active` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_lead_routing_config`
--

INSERT INTO `re_lead_routing_config` (`id`, `project_id`, `routing_type`, `fixed_advisor_id`, `is_active`, `created_at`, `updated_at`) VALUES
(1, NULL, 'FIXED_ADVISOR', 2, 1, '2026-01-29 14:22:16', '2026-01-29 14:22:16');

-- --------------------------------------------------------

--
-- Table structure for table `re_locations`
--

CREATE TABLE `re_locations` (
  `location_id` bigint NOT NULL,
  `address_line` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_locations`
--

INSERT INTO `re_locations` (`location_id`, `address_line`, `area`, `city`, `created_at`, `latitude`, `longitude`, `project_id`, `updated_at`, `zone`) VALUES
(3, NULL, 'Whitefield', 'Bengaluru', '2025-12-24 19:29:42.615089', 12.9716, 77.5946, 2, NULL, 'East'),
(4, NULL, 'Whitefield', 'Bengaluru', '2025-12-24 19:30:13.099357', 12.9698, 77.75, 3, NULL, 'East'),
(5, NULL, 'Bannerghatta Road', 'Bengaluru ', '2025-12-24 19:30:13.099357', 12.9698, 77.75, 5, NULL, 'South'),
(7, NULL, 'Whitefield', 'Bengaluru', '2025-12-29 11:27:08.312116', 12.9698, 77.75, 8, NULL, 'East');

-- --------------------------------------------------------

--
-- Table structure for table `re_otp_verification`
--

CREATE TABLE `re_otp_verification` (
  `id` bigint NOT NULL,
  `attempts` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `expires_at` datetime(6) NOT NULL,
  `is_verified` int NOT NULL,
  `otp` varchar(6) NOT NULL,
  `phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_otp_verification`
--

INSERT INTO `re_otp_verification` (`id`, `attempts`, `created_at`, `expires_at`, `is_verified`, `otp`, `phone`) VALUES
(1, 0, '2026-01-30 11:45:21.513818', '2026-01-30 11:50:21.513818', 0, '550710', '8431182353'),
(2, 0, '2026-02-15 18:02:23.846477', '2026-02-15 18:07:23.846477', 1, '531792', '8431182353'),
(3, 0, '2026-02-15 18:22:59.455307', '2026-02-15 18:27:59.455307', 0, '452235', '8431182353'),
(4, 0, '2026-02-15 18:23:21.296376', '2026-02-15 18:28:21.296376', 0, '428726', '8431182353'),
(5, 0, '2026-02-15 18:45:05.227777', '2026-02-15 18:50:05.227777', 0, '932058', '8431182353'),
(6, 0, '2026-02-15 19:45:01.362177', '2026-02-15 19:50:01.362177', 0, '332019', '8431182353'),
(7, 0, '2026-02-18 10:21:20.410233', '2026-02-18 10:26:20.410233', 0, '380716', '8431182353'),
(8, 0, '2026-02-18 10:39:34.014834', '2026-02-18 10:44:34.014834', 0, '171695', '8431182353');

-- --------------------------------------------------------

--
-- Table structure for table `re_projects`
--

CREATE TABLE `re_projects` (
  `project_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `developer_id` bigint DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `project_type_id` int DEFAULT NULL,
  `rera_number` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `construction_status` int DEFAULT NULL,
  `possession_date` date DEFAULT NULL,
  `rera_status` int DEFAULT NULL,
  `construction_status_id` int DEFAULT NULL,
  `is_verified` int DEFAULT NULL,
  `website_url` varchar(255) DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `source_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_projects`
--

INSERT INTO `re_projects` (`project_id`, `created_at`, `created_by`, `description`, `developer_id`, `project_name`, `project_type_id`, `rera_number`, `status`, `updated_at`, `updated_by`, `construction_status`, `possession_date`, `rera_status`, `construction_status_id`, `is_verified`, `website_url`, `source_name`, `source_type`) VALUES
(4, '2025-12-24 19:30:13.095270', 1, 'Premium residential apartments', 1, 'Sobha Neopolis', 1, 'PRM/KA/RERA/1251/308/PR/220123/004567', 1, '2026-03-04 19:02:07.924671', 1, 1, NULL, 1, 2, 0, NULL, NULL, NULL),
(5, '2025-12-28 00:26:57.000000', 1, 'Part of a 30+ acre township, Prestige Southern Star is more than a home—it’s a destination where life’s simple pleasures become everyday luxuries. ', 2, 'Prestige Southern Star', 1, 'PRM/KA/RERA/1251/310/PR/210325/007603', 1, '2026-03-05 13:40:12.641306', 1, 2, NULL, 1, 2, 0, NULL, NULL, NULL),
(60, '2026-02-12 13:07:52.466816', 1, 'Crescent Point Development is a thoughtfully planned real estate project designed to blend modern living with long-term value. The development emphasizes quality construction, functional layouts, and a strategic.', 1, ' Cresson Pointe Development', 1, ' PRM/KA/RERA/1251/308/PR/220123/004567', 1, NULL, NULL, NULL, '2026-12-31', 1, 5, 0, ' https://www.test.com', NULL, NULL),
(79, '2026-03-06 10:33:27.881178', 1, 'Sumadhura Solace - Premium residential apartments in Marathahalli, Bengaluru', 1, 'Sumadhura Solace', 1, 'PRM/KA/RERA/1251/446/PR/111225/008330', 0, '2026-03-06 10:34:31.080895', 1, NULL, NULL, 1, 5, 0, NULL, NULL, NULL),
(80, '2026-03-06 10:33:29.285983', 1, 'Sumadhura Edition - Premium residential apartments in Nallurhalli, Bengaluru', 1, 'Sumadhura Edition', 1, 'registration', 1, NULL, NULL, NULL, NULL, 1, 5, 0, NULL, NULL, NULL),
(81, '2026-03-06 10:33:30.709389', 1, 'KNS Samooha Phase 1 - Premium residential apartments in Nelamangala, Bengaluru', 1, 'KNS Samooha Phase 1', 1, 'PRM/KA/RERA/1251/308/PR/220123/001001', 1, NULL, NULL, NULL, NULL, 1, 5, 0, NULL, NULL, NULL),
(82, '2026-03-06 10:33:32.085384', 1, 'Vaishno Serene - Premium residential apartments in Hoodi, Bengaluru', 1, 'Vaishno Serene', 1, 'PRM/KA/RERA/1251/446/PR/210524/006924', 1, NULL, NULL, NULL, NULL, 1, 5, 0, NULL, NULL, NULL),
(83, '2026-03-06 10:33:33.653575', 1, 'KNS Sampada - Premium residential apartments in Kengeri, Bengaluru', 1, 'KNS Sampada', 1, 'PRM/KA/RERA/1251/308/PR/220123/001002', 1, NULL, NULL, NULL, NULL, 1, 5, 0, NULL, NULL, NULL),
(96, '2026-03-09 19:23:06.923281', 1, 'Purvi Lotus is a residential project developed by Sai Purvi Developers at HSR Layout in Bangalore. The project aims to offer a comfortable living condition to the residents by encompassing Gymnasium, Others, Others, Multipurpose Room, Car Parking , adding to its existing many facilities. The project is under construction and is scheduled for possession in possession on Jul 13.', 1, 'Sai Purvi Lotus', 1, 'PRM/KA/RERA/1251/308/PR/220123/001004', 1, '2026-03-09 20:07:53.708835', 1, NULL, NULL, 1, 5, 0, 'https://housing.com/in/buy/projects/page/79491-sai-purvi-lotus-by-sai-purvi-developers-in-hsr-layout', NULL, NULL),
(97, '2026-03-09 19:23:12.112745', 1, 'One of the finest property in Horamavu is now available for sale. This is a 2 BHK Apartment posted directly by owner. Make it yours now. It is on floor 2. It is a 4 storeyed building. The price of this Apartment is Rs 64.0 L. This Apartment is spacious with a built-up area of 1105 square_feet. The carpet area is 1000 square_feet. A separate servant room available in this Apartment. It is a East-facing property with a good view. There are 2 bedrooms and 2 bathroom. Lift facility is also available. This is a gated community. This property is equipped with cctv facility. Other facilities include Intercom, Community hall. This property also enjoys power backup facility. This project has regular water supply. It is also close to good and reputed hospitals like Cloudnine Hospital - HRBR, Ovum Hospitals | Woman & Child Speciality Hospital in Kalyan Nagar, Bangalore, Motherhood Hospital - HRBR Layout', 1, '2 BHK Apartment', 1, 'PRM/KA/RERA/1251/308/PR/220123/001005', 1, '2026-03-09 20:07:54.010554', 1, NULL, NULL, 1, 5, 0, 'https://housing.com/in/buy/resale/page/16222436-2-bhk-apartment-in-horamavu-for-rs-6400000', NULL, NULL),
(98, '2026-03-09 19:31:40.278624', 1, 'Sumadhura Solace - Premium residential apartments in Marathahalli, Bengaluru', 1, 'Sumadhura Solace', 1, 'PRM/KA/RERA/1251/446/PR/111225/008330', 1, '2026-03-09 20:07:54.221686', 1, NULL, NULL, 1, 5, 0, 'https://www.99acres.com/sumadhura-solace-marathahalli-bangalore-east-npxid-r455926', NULL, NULL),
(99, '2026-03-09 20:15:03.328958', 1, '1200 square_feet Plot for sale in Harapanahalli, Bengaluru. This land has a dimension of 40.0 ft length 30.0 ft width. Price. The width of the facing road is 30.0 mt. The most popular landmarks near this plot are S-VYASA AROGYADHAMA PRASHANTI KUTIRAM, Royal greens dabha, Parkway Family Dhaba', 1, 'Residential Plot', 1, 'PRM/KA/RERA/1251/308/PR/220123/001009', 1, NULL, NULL, NULL, NULL, 1, 5, 0, 'https://housing.com/in/buy/resale/page/19077747-residential-plot-in-jigani-for-rs-4800000', NULL, NULL),
(100, '2026-03-09 20:15:05.589059', 1, 'Serenity - Premium residential project', 1, 'Serenity', 1, 'PRM/KA/RERA/1251/308/PR/220123/001010', 1, NULL, NULL, NULL, NULL, 1, 5, 0, 'https://housing.com/in/buy/projects/page/351876-serenity-by-m-r-developers-in-muneshwara-nagar?fltcnt=a481409a-52ab-4e4b-a8fe-bfefa1a7ba89', NULL, NULL),
(101, '2026-03-09 20:15:06.730736', 1, 'United Suvarna Homes - Premium residential project', 1, 'United Suvarna Homes', 1, 'PRM/KA/RERA/1251/308/PR/220123/001011', 1, NULL, NULL, NULL, NULL, 1, 5, 0, 'https://housing.com/in/buy/projects/page/355376-united-suvarna-homes-by-united-suvarna-homes-in-kacharakanahallibisnahalli?fltcnt=6f535826-f0c1-4651-96bb-c60265f046ec', NULL, NULL),
(102, '2026-03-09 20:15:07.862401', 1, 'RRL Palm Altezze - Premium residential project', 1, 'RRL Palm Altezze', 1, 'PRM/KA/RERA/1251/308/PR/220123/001012', 1, NULL, NULL, NULL, NULL, 1, 5, 0, 'https://housing.com/in/buy/projects/page/352013-rrl-palm-altezze-by-rrl-builders-and-developers-pvt-ltd-in-varthur?fltcnt=289de89e-94af-4e49-9084-4586c767694b', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `re_project_advisors`
--

CREATE TABLE `re_project_advisors` (
  `project_advisor_id` bigint NOT NULL,
  `advisor_user_id` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `project_id` bigint NOT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_advisors`
--

INSERT INTO `re_project_advisors` (`project_advisor_id`, `advisor_user_id`, `created_at`, `project_id`, `status`) VALUES
(1, 2, '2026-02-12 13:07:53.446180', 60, 1),
(3, 2, '2026-02-22 10:33:02.044226', 62, 1),
(4, 2, '2026-02-23 16:09:00.569767', 63, 1),
(5, 2, '2026-02-23 16:27:31.055808', 64, 1),
(6, 2, '2026-02-25 13:15:14.476261', 65, 1),
(8, 2, '2026-03-06 10:16:00.281430', 67, 1),
(14, 2, '2026-03-06 10:16:45.684858', 73, 1),
(15, 2, '2026-03-06 10:20:56.890879', 74, 1),
(16, 2, '2026-03-06 10:20:58.529806', 75, 1),
(17, 2, '2026-03-06 10:21:00.673563', 76, 1),
(18, 2, '2026-03-06 10:21:01.748555', 77, 1),
(19, 2, '2026-03-06 10:21:02.768745', 78, 1),
(20, 2, '2026-03-06 10:33:27.900796', 79, 1),
(21, 2, '2026-03-06 10:33:29.299213', 80, 1),
(22, 2, '2026-03-06 10:33:30.717159', 81, 1),
(23, 2, '2026-03-06 10:33:32.093209', 82, 1),
(24, 2, '2026-03-06 10:33:33.661231', 83, 1),
(25, 2, '2026-03-09 13:07:35.554817', 84, 1),
(26, 2, '2026-03-09 13:13:52.412429', 85, 1),
(27, 2, '2026-03-09 13:30:01.119040', 86, 1),
(28, 2, '2026-03-09 13:32:20.023862', 87, 1),
(29, 1, '2026-03-09 13:38:25.759601', 88, 1),
(33, 1, '2026-03-09 13:44:55.908043', 92, 1),
(34, 1, '2026-03-09 13:45:29.943918', 93, 1),
(35, 1, '2026-03-09 14:04:10.281308', 94, 1),
(36, 2, '2026-03-09 14:05:55.352293', 95, 1),
(37, 2, '2026-03-09 19:23:07.002564', 96, 1),
(38, 2, '2026-03-09 19:23:12.127622', 97, 1),
(39, 2, '2026-03-09 19:31:40.298217', 98, 1),
(40, 2, '2026-03-09 20:15:03.350915', 99, 1),
(41, 2, '2026-03-09 20:15:05.600845', 100, 1),
(42, 2, '2026-03-09 20:15:06.747109', 101, 1),
(43, 2, '2026-03-09 20:15:07.878230', 102, 1);

-- --------------------------------------------------------

--
-- Table structure for table `re_project_amenities`
--

CREATE TABLE `re_project_amenities` (
  `project_amenity_id` bigint NOT NULL,
  `amenity_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_amenities`
--

INSERT INTO `re_project_amenities` (`project_amenity_id`, `amenity_id`, `created_at`, `project_id`) VALUES
(7, 1, '2025-12-24 19:30:13.130769', 3),
(8, 2, '2025-12-24 19:30:13.139060', 3),
(9, 3, '2025-12-24 19:30:13.147866', 3),
(41, 5, '2025-12-29 11:27:08.347703', 8),
(42, 6, '2025-12-29 11:27:08.370924', 8),
(43, 7, '2025-12-29 11:27:08.374144', 8),
(44, 4, '2026-01-12 10:39:29.651973', 15),
(45, 8, '2026-01-12 10:39:29.662634', 15),
(46, 4, '2026-01-12 10:49:04.050976', 17),
(47, 5, '2026-01-12 10:49:04.063811', 17),
(48, 6, '2026-01-12 10:49:04.066714', 17),
(49, 7, '2026-01-12 10:49:04.069853', 17),
(50, 8, '2026-01-12 10:49:04.071852', 17),
(51, 9, '2026-01-12 10:49:04.074935', 17),
(52, 10, '2026-01-12 10:49:04.080356', 17),
(53, 11, '2026-01-12 10:49:04.085688', 17),
(54, 12, '2026-01-12 10:49:04.091336', 17),
(55, 13, '2026-01-12 10:49:04.098482', 17),
(56, 14, '2026-01-12 10:49:04.104539', 17),
(57, 15, '2026-01-12 10:49:04.122833', 17),
(58, 16, '2026-01-12 10:49:04.137105', 17),
(59, 17, '2026-01-12 10:49:04.149075', 17),
(60, 18, '2026-01-12 10:49:04.166417', 17),
(61, 19, '2026-01-12 10:49:04.182348', 17),
(62, 20, '2026-01-12 10:49:04.199621', 17),
(63, 21, '2026-01-12 10:49:04.213352', 17),
(64, 22, '2026-01-12 10:49:04.221690', 17),
(65, 23, '2026-01-12 10:49:04.229159', 17),
(68, 5, '2026-01-12 11:02:20.987450', 19),
(69, 6, '2026-01-12 11:02:20.989184', 19),
(70, 4, '2026-01-12 11:02:20.991896', 19),
(71, 5, '2026-01-12 11:08:28.735179', 20),
(72, 6, '2026-01-12 11:08:28.740705', 20),
(73, 4, '2026-01-12 11:08:28.744221', 20),
(74, 5, '2026-01-12 11:11:12.678602', 21),
(75, 6, '2026-01-12 11:11:12.685325', 21),
(76, 4, '2026-01-12 11:11:12.689425', 21),
(77, 5, '2026-01-12 11:30:53.351139', 22),
(78, 6, '2026-01-12 11:30:53.356336', 22),
(79, 4, '2026-01-12 11:30:53.361114', 22),
(80, 5, '2026-01-12 11:34:49.042371', 23),
(81, 6, '2026-01-12 11:34:49.057439', 23),
(82, 4, '2026-01-12 11:34:49.067338', 23),
(83, 5, '2026-01-12 11:34:59.690498', 24),
(84, 6, '2026-01-12 11:34:59.697574', 24),
(85, 4, '2026-01-12 11:34:59.702772', 24),
(86, 5, '2026-01-12 13:03:52.580800', 25),
(87, 6, '2026-01-12 13:03:52.586636', 25),
(88, 4, '2026-01-12 13:03:52.590341', 25),
(89, 5, '2026-01-12 13:06:21.939384', 26),
(90, 6, '2026-01-12 13:06:21.944273', 26),
(91, 4, '2026-01-12 13:06:21.947371', 26),
(92, 5, '2026-01-12 13:33:33.756216', 27),
(93, 6, '2026-01-12 13:33:33.771073', 27),
(94, 4, '2026-01-12 13:33:33.792129', 27),
(100, 5, '2026-01-15 07:53:40.724642', 30),
(101, 6, '2026-01-15 07:53:40.734862', 30),
(102, 5, '2026-01-15 07:57:05.355832', 31),
(103, 6, '2026-01-15 07:57:05.363242', 31),
(106, 5, '2026-01-15 08:03:24.381963', 33),
(107, 6, '2026-01-15 08:03:24.388321', 33),
(108, 5, '2026-01-15 08:04:28.407473', 34),
(109, 6, '2026-01-15 08:04:28.418133', 34),
(110, 5, '2026-01-15 08:13:52.214365', 35),
(111, 6, '2026-01-15 08:13:52.220608', 35),
(112, 5, '2026-01-15 08:18:28.354151', 36),
(113, 6, '2026-01-15 08:18:28.360759', 36),
(114, 5, '2026-01-15 09:15:12.235500', 37),
(115, 6, '2026-01-15 09:15:12.247520', 37),
(116, 5, '2026-01-15 10:06:13.474159', 38),
(117, 6, '2026-01-15 10:06:13.482191', 38),
(118, 5, '2026-01-16 08:16:14.010459', 39),
(119, 6, '2026-01-16 08:16:14.030007', 39),
(120, 5, '2026-01-16 08:17:10.287816', 40),
(121, 6, '2026-01-16 08:17:10.293426', 40),
(122, 5, '2026-01-16 09:12:50.027402', 41),
(123, 6, '2026-01-16 09:12:50.033458', 41),
(124, 4, '2026-01-16 09:32:43.450489', 42),
(125, 5, '2026-01-16 09:32:43.455900', 42),
(126, 6, '2026-01-16 09:32:43.458419', 42),
(127, 7, '2026-01-16 09:32:43.460141', 42),
(128, 8, '2026-01-16 09:32:43.461649', 42),
(129, 9, '2026-01-16 09:32:43.465718', 42),
(130, 10, '2026-01-16 09:32:43.469308', 42),
(131, 11, '2026-01-16 09:32:43.473071', 42),
(132, 12, '2026-01-16 09:32:43.476058', 42),
(133, 13, '2026-01-16 09:32:43.478882', 42),
(134, 14, '2026-01-16 09:32:43.482981', 42),
(135, 15, '2026-01-16 09:32:43.486614', 42),
(136, 16, '2026-01-16 09:32:43.488944', 42),
(137, 17, '2026-01-16 09:32:43.490464', 42),
(138, 18, '2026-01-16 09:32:43.493501', 42),
(139, 19, '2026-01-16 09:32:43.495514', 42),
(140, 20, '2026-01-16 09:32:43.497764', 42),
(141, 21, '2026-01-16 09:32:43.499381', 42),
(142, 22, '2026-01-16 09:32:43.501301', 42),
(143, 23, '2026-01-16 09:32:43.503918', 42),
(144, 4, '2026-01-16 09:48:36.954083', 43),
(145, 5, '2026-01-16 09:48:36.962496', 43),
(146, 6, '2026-01-16 09:48:36.967584', 43),
(147, 7, '2026-01-16 09:48:36.971397', 43),
(148, 8, '2026-01-16 09:48:36.975716', 43),
(149, 9, '2026-01-16 09:48:36.982106', 43),
(150, 10, '2026-01-16 09:48:36.985760', 43),
(151, 11, '2026-01-16 09:48:36.988877', 43),
(152, 12, '2026-01-16 09:48:36.992262', 43),
(153, 13, '2026-01-16 09:48:36.996607', 43),
(154, 14, '2026-01-16 09:48:37.000898', 43),
(155, 15, '2026-01-16 09:48:37.006133', 43),
(156, 16, '2026-01-16 09:48:37.011514', 43),
(157, 17, '2026-01-16 09:48:37.021752', 43),
(158, 18, '2026-01-16 09:48:37.024534', 43),
(159, 19, '2026-01-16 09:48:37.024534', 43),
(160, 20, '2026-01-16 09:48:37.036037', 43),
(161, 21, '2026-01-16 09:48:37.042749', 43),
(162, 22, '2026-01-16 09:48:37.046352', 43),
(163, 23, '2026-01-16 09:48:37.052023', 43),
(164, 5, '2026-01-16 10:05:38.310677', 44),
(165, 6, '2026-01-16 10:05:38.318550', 44),
(166, 5, '2026-01-16 10:05:50.817013', 45),
(167, 6, '2026-01-16 10:05:50.824751', 45),
(168, 5, '2026-01-16 12:14:50.053141', 46),
(169, 6, '2026-01-16 12:14:50.070833', 46),
(170, 4, '2026-01-16 13:23:45.946508', 47),
(171, 5, '2026-01-16 13:23:45.958046', 47),
(172, 6, '2026-01-16 13:23:45.962746', 47),
(173, 7, '2026-01-16 13:23:45.966792', 47),
(174, 8, '2026-01-16 13:23:45.977062', 47),
(175, 9, '2026-01-16 13:23:45.989686', 47),
(176, 10, '2026-01-16 13:23:45.996415', 47),
(177, 11, '2026-01-16 13:23:45.999791', 47),
(178, 12, '2026-01-16 13:23:46.004993', 47),
(179, 13, '2026-01-16 13:23:46.008937', 47),
(180, 14, '2026-01-16 13:23:46.013536', 47),
(181, 15, '2026-01-16 13:23:46.018501', 47),
(182, 16, '2026-01-16 13:23:46.022034', 47),
(183, 17, '2026-01-16 13:23:46.027068', 47),
(184, 18, '2026-01-16 13:23:46.031176', 47),
(185, 19, '2026-01-16 13:23:46.036707', 47),
(186, 20, '2026-01-16 13:23:46.041282', 47),
(187, 21, '2026-01-16 13:23:46.047586', 47),
(188, 22, '2026-01-16 13:23:46.051941', 47),
(189, 23, '2026-01-16 13:23:46.057292', 47),
(190, 4, '2026-01-16 13:24:43.320155', 48),
(191, 5, '2026-01-16 13:24:43.331743', 48),
(192, 6, '2026-01-16 13:24:43.335290', 48),
(193, 7, '2026-01-16 13:24:43.340668', 48),
(194, 8, '2026-01-16 13:24:43.351950', 48),
(195, 9, '2026-01-16 13:24:43.359131', 48),
(196, 10, '2026-01-16 13:24:43.365012', 48),
(197, 11, '2026-01-16 13:24:43.370592', 48),
(198, 12, '2026-01-16 13:24:43.380329', 48),
(199, 13, '2026-01-16 13:24:43.386945', 48),
(200, 14, '2026-01-16 13:24:43.404968', 48),
(201, 15, '2026-01-16 13:24:43.411757', 48),
(202, 16, '2026-01-16 13:24:43.420593', 48),
(203, 17, '2026-01-16 13:24:43.427244', 48),
(204, 18, '2026-01-16 13:24:43.431726', 48),
(205, 19, '2026-01-16 13:24:43.441011', 48),
(206, 20, '2026-01-16 13:24:43.447350', 48),
(207, 21, '2026-01-16 13:24:43.451395', 48),
(208, 22, '2026-01-16 13:24:43.456329', 48),
(209, 23, '2026-01-16 13:24:43.465387', 48),
(210, 4, '2026-01-16 13:25:15.085749', 49),
(211, 5, '2026-01-16 13:25:15.094067', 49),
(212, 6, '2026-01-16 13:25:15.101005', 49),
(213, 7, '2026-01-16 13:25:15.104449', 49),
(214, 8, '2026-01-16 13:25:15.109828', 49),
(215, 9, '2026-01-16 13:25:15.113564', 49),
(216, 10, '2026-01-16 13:25:15.117562', 49),
(217, 11, '2026-01-16 13:25:15.122351', 49),
(218, 12, '2026-01-16 13:25:15.125809', 49),
(219, 13, '2026-01-16 13:25:15.130099', 49),
(220, 14, '2026-01-16 13:25:15.133897', 49),
(221, 15, '2026-01-16 13:25:15.137359', 49),
(222, 16, '2026-01-16 13:25:15.140279', 49),
(223, 17, '2026-01-16 13:25:15.145179', 49),
(224, 18, '2026-01-16 13:25:15.148706', 49),
(225, 19, '2026-01-16 13:25:15.152324', 49),
(226, 20, '2026-01-16 13:25:15.154531', 49),
(227, 21, '2026-01-16 13:25:15.157574', 49),
(228, 22, '2026-01-16 13:25:15.159607', 49),
(229, 23, '2026-01-16 13:25:15.161621', 49),
(230, 4, '2026-01-16 13:25:28.306759', 50),
(231, 5, '2026-01-16 13:25:28.310882', 50),
(232, 6, '2026-01-16 13:25:28.315341', 50),
(233, 7, '2026-01-16 13:25:28.318270', 50),
(234, 8, '2026-01-16 13:25:28.321827', 50),
(235, 9, '2026-01-16 13:25:28.326176', 50),
(236, 10, '2026-01-16 13:25:28.329539', 50),
(237, 11, '2026-01-16 13:25:28.333586', 50),
(238, 12, '2026-01-16 13:25:28.336382', 50),
(239, 13, '2026-01-16 13:25:28.341313', 50),
(240, 14, '2026-01-16 13:25:28.347904', 50),
(241, 15, '2026-01-16 13:25:28.353373', 50),
(242, 16, '2026-01-16 13:25:28.359667', 50),
(243, 17, '2026-01-16 13:25:28.363703', 50),
(244, 18, '2026-01-16 13:25:28.367714', 50),
(245, 19, '2026-01-16 13:25:28.371830', 50),
(246, 20, '2026-01-16 13:25:28.375425', 50),
(247, 21, '2026-01-16 13:25:28.378632', 50),
(248, 22, '2026-01-16 13:25:28.381766', 50),
(249, 23, '2026-01-16 13:25:28.387081', 50),
(270, 5, '2026-01-16 14:12:22.588477', 52),
(271, 6, '2026-01-16 14:12:22.599173', 52),
(272, 5, '2026-01-16 14:42:56.185106', 53),
(273, 6, '2026-01-16 14:42:56.742547', 53),
(274, 5, '2026-01-16 19:22:36.312103', 54),
(275, 6, '2026-01-16 19:22:36.826682', 54),
(278, 5, '2026-01-16 20:19:16.023064', 56),
(279, 6, '2026-01-16 20:19:16.766437', 56),
(301, 5, '2026-01-17 13:39:59.977601', 2),
(302, 6, '2026-01-17 13:40:00.756123', 2),
(305, 4, '2026-01-17 16:51:36.463232', 57),
(306, 10, '2026-01-17 16:51:37.493704', 57),
(327, 4, '2026-01-17 18:27:48.705630', 51),
(328, 5, '2026-01-17 18:27:49.342506', 51),
(329, 6, '2026-01-17 18:27:50.522228', 51),
(330, 7, '2026-01-17 18:27:51.154402', 51),
(331, 8, '2026-01-17 18:27:51.814673', 51),
(332, 9, '2026-01-17 18:27:52.438668', 51),
(333, 10, '2026-01-17 18:27:52.932338', 51),
(334, 11, '2026-01-17 18:27:53.834094', 51),
(335, 12, '2026-01-17 18:27:54.451745', 51),
(336, 13, '2026-01-17 18:27:55.059353', 51),
(337, 14, '2026-01-17 18:27:55.676081', 51),
(338, 15, '2026-01-17 18:27:56.307721', 51),
(339, 16, '2026-01-17 18:27:56.943611', 51),
(340, 17, '2026-01-17 18:27:57.578759', 51),
(341, 18, '2026-01-17 18:27:58.199382', 51),
(342, 19, '2026-01-17 18:27:58.838434', 51),
(343, 20, '2026-01-17 18:28:00.212057', 51),
(344, 21, '2026-01-17 18:28:00.817752', 51),
(345, 22, '2026-01-17 18:28:01.418869', 51),
(346, 23, '2026-01-17 18:28:02.022310', 51),
(347, 5, '2026-01-18 06:20:22.080471', 58),
(348, 6, '2026-01-18 06:20:22.628930', 58),
(523, 5, '2026-02-12 13:07:54.531134', 60),
(524, 6, '2026-02-12 13:07:54.915179', 60),
(525, 8, '2026-02-12 13:07:55.231717', 60),
(532, 4, '2026-02-25 13:15:15.977811', 65),
(533, 5, '2026-03-04 18:23:50.902262', 4),
(534, 6, '2026-03-04 18:23:50.913541', 4),
(595, 4, '2026-03-05 13:40:13.558356', 5),
(596, 5, '2026-03-05 13:40:13.572162', 5),
(597, 6, '2026-03-05 13:40:13.574632', 5),
(598, 7, '2026-03-05 13:40:13.576704', 5),
(599, 8, '2026-03-05 13:40:13.578927', 5),
(600, 9, '2026-03-05 13:40:13.581066', 5),
(601, 10, '2026-03-05 13:40:13.583053', 5),
(602, 11, '2026-03-05 13:40:13.586246', 5),
(603, 12, '2026-03-05 13:40:13.588932', 5),
(604, 13, '2026-03-05 13:40:13.590953', 5),
(605, 14, '2026-03-05 13:40:13.592993', 5),
(606, 15, '2026-03-05 13:40:13.594803', 5),
(607, 16, '2026-03-05 13:40:13.596890', 5),
(608, 17, '2026-03-05 13:40:13.598955', 5),
(609, 18, '2026-03-05 13:40:13.600912', 5),
(610, 19, '2026-03-05 13:40:13.602652', 5),
(611, 20, '2026-03-05 13:40:13.604996', 5),
(612, 21, '2026-03-05 13:40:13.606833', 5),
(613, 22, '2026-03-05 13:40:13.608600', 5),
(614, 23, '2026-03-05 13:40:13.610930', 5),
(617, 5, '2026-03-06 10:16:00.383640', 67),
(618, 6, '2026-03-06 10:16:00.387104', 67),
(629, 5, '2026-03-06 10:16:45.690488', 73),
(630, 6, '2026-03-06 10:16:45.693298', 73),
(631, 5, '2026-03-06 10:20:56.899660', 74),
(632, 6, '2026-03-06 10:20:56.902885', 74),
(633, 5, '2026-03-06 10:20:58.536626', 75),
(634, 6, '2026-03-06 10:20:58.538840', 75),
(635, 5, '2026-03-06 10:21:00.681000', 76),
(636, 6, '2026-03-06 10:21:00.683013', 76),
(637, 5, '2026-03-06 10:21:01.752241', 77),
(638, 6, '2026-03-06 10:21:01.753138', 77),
(639, 5, '2026-03-06 10:21:02.774085', 78),
(640, 6, '2026-03-06 10:21:02.775777', 78),
(641, 5, '2026-03-06 10:33:27.908341', 79),
(642, 6, '2026-03-06 10:33:27.909777', 79),
(643, 5, '2026-03-06 10:33:29.309499', 80),
(644, 6, '2026-03-06 10:33:29.312852', 80),
(645, 5, '2026-03-06 10:33:30.723405', 81),
(646, 6, '2026-03-06 10:33:30.724395', 81),
(647, 5, '2026-03-06 10:33:32.096180', 82),
(648, 6, '2026-03-06 10:33:32.097239', 82),
(649, 5, '2026-03-06 10:33:33.669179', 83),
(650, 6, '2026-03-06 10:33:33.670819', 83),
(651, 1, '2026-03-09 13:07:35.591359', 84),
(652, 2, '2026-03-09 13:07:35.610147', 84),
(653, 1, '2026-03-09 13:30:01.135553', 86),
(654, 2, '2026-03-09 13:30:01.139688', 86),
(655, 1, '2026-03-09 13:38:25.849309', 88),
(656, 2, '2026-03-09 13:38:25.885186', 88),
(663, 1, '2026-03-09 13:44:55.964230', 92),
(664, 2, '2026-03-09 13:44:56.006922', 92),
(665, 1, '2026-03-09 13:45:29.995720', 93),
(666, 2, '2026-03-09 13:45:30.026889', 93),
(667, 1, '2026-03-09 14:04:10.343607', 94),
(668, 2, '2026-03-09 14:04:10.376343', 94),
(669, 5, '2026-03-09 19:23:07.055187', 96),
(670, 6, '2026-03-09 19:23:07.083575', 96),
(671, 5, '2026-03-09 19:23:12.131279', 97),
(672, 6, '2026-03-09 19:23:12.133967', 97),
(673, 5, '2026-03-09 19:31:40.313120', 98),
(674, 6, '2026-03-09 19:31:40.319499', 98),
(675, 5, '2026-03-09 20:15:03.356140', 99),
(676, 6, '2026-03-09 20:15:03.357683', 99),
(677, 5, '2026-03-09 20:15:05.606369', 100),
(678, 6, '2026-03-09 20:15:05.608059', 100),
(679, 5, '2026-03-09 20:15:06.750669', 101),
(680, 6, '2026-03-09 20:15:06.753144', 101),
(681, 5, '2026-03-09 20:15:07.882742', 102),
(682, 6, '2026-03-09 20:15:07.884785', 102);

-- --------------------------------------------------------

--
-- Table structure for table `re_project_brochures`
--

CREATE TABLE `re_project_brochures` (
  `brochure_id` bigint NOT NULL,
  `brochure_type` varchar(255) DEFAULT NULL,
  `brochure_url` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `project_id` bigint NOT NULL,
  `sort_order` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_brochures`
--

INSERT INTO `re_project_brochures` (`brochure_id`, `brochure_type`, `brochure_url`, `created_at`, `project_id`, `sort_order`) VALUES
(4, 'PDF', 'https://re-project-images.r2.dev/projects/33/brochures/190ee043-7d1e-4fb8-a068-510983f42321-Other Institutes VS DA360 Comparison.pdf', '2026-01-15 08:03:26.165836', 33, 2),
(5, 'PDF', 'https://re-project-images.r2.dev/projects/34/brochures/f77fd869-e474-4060-930c-63b93d5b94a1-Other Institutes VS DA360 Comparison.pdf', '2026-01-15 08:04:31.526835', 34, 2),
(6, 'PDF', 'https://re-project-images.r2.dev/projects/35/brochures/48f9297f-ab1a-4288-aaf7-f838fb5fcd07-Other Institutes VS DA360 Comparison.pdf', '2026-01-15 08:14:09.471440', 35, 2),
(7, 'PDF', 'https://re-project-images.r2.dev/projects/36/brochures/41d746fe-20ba-4eac-9b50-45595e23f93e-Other Institutes VS DA360 Comparison.pdf', '2026-01-15 08:18:30.260704', 36, 2),
(8, 'PDF', 'https://re-project-images.r2.dev/projects/37/brochures/9af85587-b5d8-4952-aebe-5a114086435e-Other Institutes VS DA360 Comparison.pdf', '2026-01-15 09:15:16.685605', 37, 2),
(9, 'PDF', 'https://re-project-images.r2.dev/projects/38/brochures/eaaebfd2-0fb9-4180-8746-0a65e29d140b-Other Institutes VS DA360 Comparison.pdf', '2026-01-15 10:06:15.736078', 38, 2),
(10, 'PDF', 'https://re-project-images.r2.dev/projects/39/brochures/340af5df-72f1-469c-a7fa-e32fe2697b7c-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 08:16:18.362049', 39, 2),
(11, 'PDF', 'projects/40/brochures/31a81ec4-149d-48a8-96bc-7d47d7d132f7-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 08:17:12.607919', 40, 2),
(12, 'PDF', 'projects/41/brochures/ee9e5ea9-c03a-480e-b459-88f6bc9d59d9-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 09:12:53.152389', 41, 2),
(13, 'PDF', 'projects/42/brochures/2d7b2c7d-c95a-4b40-ab92-53e4a62c7e57-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 09:32:46.282934', 42, 1),
(14, 'PDF', 'projects/42/brochures/90bdefa6-73f6-4cfe-aa8a-344eb89affc3-test_document.pdf', '2026-01-16 09:32:47.034859', 42, 2),
(15, 'PDF', 'projects/43/brochures/8e2db8e4-6409-4724-a213-83738fd603c5-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 09:48:38.670379', 43, 1),
(16, 'PDF', 'projects/44/brochures/bc02ab31-36d8-414f-8bfe-536fa3dbd6d4-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 10:05:41.781675', 44, 2),
(17, 'PDF', 'projects/45/brochures/085a550d-824c-49f7-8da4-a3c30b48aa6b-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 10:05:53.057335', 45, 2),
(18, 'PDF', 'projects/46/brochures/3c25f170-3296-4475-9b81-ede7d8744ce2-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 12:14:55.268970', 46, 2),
(19, 'PDF', 'projects/47/brochures/dfa9efc3-5448-40a0-98ff-3c2c8831d6e8-test_document.pdf', '2026-01-16 13:23:50.426877', 47, 1),
(20, 'PDF', 'projects/47/brochures/eaff5d60-2b47-487b-96ff-45c73157a3cd-Document (12).pdf', '2026-01-16 13:23:51.438765', 47, 2),
(21, 'PDF', 'projects/48/brochures/ebfa4419-de9c-4733-9a0b-cd1614ab127b-test_document.pdf', '2026-01-16 13:24:47.278088', 48, 1),
(22, 'PDF', 'projects/48/brochures/70df318b-87fd-4305-86e5-8868a637a03b-Document (12).pdf', '2026-01-16 13:24:48.070260', 48, 2),
(23, 'PDF', 'projects/49/brochures/0bb6e7ca-ff03-43e7-82ee-40be8bace6df-test_document.pdf', '2026-01-16 13:25:18.958545', 49, 1),
(24, 'PDF', 'projects/49/brochures/3fbd5267-5cb8-46cd-94a1-f289614bd613-Document (12).pdf', '2026-01-16 13:25:19.615491', 49, 2),
(25, 'PDF', 'projects/50/brochures/68684266-9daa-4a65-9e64-c1dfeba180d5-test_document.pdf', '2026-01-16 13:25:32.822833', 50, 1),
(26, 'PDF', 'projects/50/brochures/d2f5e1c2-5a01-441c-91d5-497e1ad9a199-Document (12).pdf', '2026-01-16 13:25:33.706740', 50, 2),
(29, 'PDF', 'projects/52/brochures/1d579da6-2e20-4753-a714-c47646e15648-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 14:12:24.955877', 52, 2),
(30, 'PDF', 'projects/53/brochures/fa23da1e-4256-4084-b514-c54c3cb16d1e-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 14:43:02.225027', 53, 2),
(31, 'PDF', 'projects/54/brochures/f7f092e8-f6a1-4b34-aaa8-7394da21bf45-Other Institutes VS DA360 Comparison.pdf', '2026-01-16 19:22:43.056527', 54, 2);

-- --------------------------------------------------------

--
-- Table structure for table `re_project_documents`
--

CREATE TABLE `re_project_documents` (
  `project_document_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `document_type` varchar(255) NOT NULL,
  `document_url` varchar(255) NOT NULL,
  `project_id` bigint NOT NULL,
  `sort_order` int DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_documents`
--

INSERT INTO `re_project_documents` (`project_document_id`, `created_at`, `document_type`, `document_url`, `project_id`, `sort_order`, `status`) VALUES
(1, '2026-02-12 13:08:02.463383', ' LAYOUT_PLAN', 'projects/60/documents/_layout_plan/f4b457f6-6cca-49ff-937e-6967e7b82d7a-layout.png', 60, 1, 1),
(2, '2026-02-12 13:08:03.551089', ' BROCHURE', 'projects/60/documents/_brochure/369ed76b-626a-4492-84b9-325e29021daf-layout2.png', 60, 2, 1),
(3, '2026-02-23 16:27:34.838647', 'Other', 'projects/64/documents/other/5cbfe967-f41e-49ca-b59d-81d30c2a24ee-SBI_Purple_Smart_Spend_Planner.pdf', 64, 1, 1),
(4, '2026-02-25 13:15:24.214608', 'Brochure', 'projects/65/documents/brochure/ebc4dbe6-5f5b-466c-b63e-5cac361c66cb-SBI_Purple_Smart_Spend_Planner.pdf', 65, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `re_project_images`
--

CREATE TABLE `re_project_images` (
  `project_image_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `sort_order` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_images`
--

INSERT INTO `re_project_images` (`project_image_id`, `created_at`, `image_url`, `project_id`, `sort_order`, `updated_at`, `status`) VALUES
(4, '2025-12-24 19:30:13.155077', 'https://pub-243b5a32eeff4ab29de95d9d3d75646a.r2.dev/5.webp', 3, 1, NULL, NULL),
(6, '2025-12-29 11:27:08.377972', 'https://pub-243b5a32eeff4ab29de95d9d3d75646a.r2.dev/5.webp', 8, 1, NULL, NULL),
(18, '2026-01-12 11:34:59.705384', 'https://cdn.example.com/project1/img1.jpg', 24, 1, NULL, NULL),
(19, '2026-01-12 11:34:59.709759', 'https://cdn.example.com/project1/img2.jpg', 24, 2, NULL, NULL),
(36, '2026-01-16 08:17:10.295435', 'projects/40/images/62911fc9-4354-4bf7-a051-781b45e0e720-img.png', 40, 1, NULL, NULL),
(37, '2026-01-16 09:12:50.035802', 'projects/41/images/bd720cf2-4df6-4aa6-a3de-d2f3c5e22f2c-img.png', 41, 1, NULL, NULL),
(38, '2026-01-16 09:32:43.505895', 'projects/42/images/a97430df-57ad-4d8d-a7ae-6f6fe5025aaa-house-7124141_1280.jpg', 42, 1, NULL, NULL),
(39, '2026-01-16 09:48:37.055100', 'projects/43/images/049fe8a9-922b-4072-9a14-22e2b27fc000-a8f1bf54-2e40-4164-83c3-0196fd7e7915.webp', 43, 1, NULL, NULL),
(40, '2026-01-16 10:05:38.322119', 'projects/44/images/38054cd8-4f19-4217-b627-1ff23638623f-img.png', 44, 1, NULL, NULL),
(41, '2026-01-16 10:05:50.828973', 'projects/45/images/e68151c7-e7ee-4ee3-b1f0-956b85e66537-img.png', 45, 1, NULL, NULL),
(42, '2026-01-16 12:14:50.075492', 'projects/46/images/19de33c6-97b0-40fd-bcf5-ad4d9c301eb8-img.png', 46, 1, NULL, NULL),
(43, '2026-01-16 13:23:46.059884', 'projects/47/images/349d57ea-7622-4b92-8e2f-156c344e61e6-Generated Image December 31, 2025 - 10_13PM.png', 47, 1, NULL, NULL),
(44, '2026-01-16 13:23:47.586600', 'projects/47/images/de493c60-9b0f-45b2-bbdb-9ee07e40aa0f-f946b53e-6ae4-4304-ae30-54233a7bf00a (1).webp', 47, 2, NULL, NULL),
(45, '2026-01-16 13:23:48.294891', 'projects/47/images/ce592075-0276-4c35-9880-e3073c8ab3f0-5 (1).webp', 47, 3, NULL, NULL),
(46, '2026-01-16 13:23:48.921456', 'projects/47/images/0e1cdabc-355e-49ae-adc2-4ff41d89043b-a8f1bf54-2e40-4164-83c3-0196fd7e7915.webp', 47, 4, NULL, NULL),
(47, '2026-01-16 13:24:43.467394', 'projects/48/images/fca5a090-1b74-499f-a514-866ef4ac18bb-Generated Image December 31, 2025 - 10_13PM.png', 48, 1, NULL, NULL),
(48, '2026-01-16 13:24:44.387390', 'projects/48/images/d66c59c9-ee37-4517-b7b9-775b2899981c-f946b53e-6ae4-4304-ae30-54233a7bf00a (1).webp', 48, 2, NULL, NULL),
(49, '2026-01-16 13:24:44.995263', 'projects/48/images/e9e1f7fd-8e7f-448d-89c4-62b0fed4439c-5 (1).webp', 48, 3, NULL, NULL),
(50, '2026-01-16 13:24:45.722900', 'projects/48/images/a25efb10-634e-43a1-a99d-f2ee43acc473-a8f1bf54-2e40-4164-83c3-0196fd7e7915.webp', 48, 4, NULL, NULL),
(51, '2026-01-16 13:25:15.164149', 'projects/49/images/f36d8776-38de-4f04-86cf-066099d8e719-Generated Image December 31, 2025 - 10_13PM.png', 49, 1, NULL, NULL),
(52, '2026-01-16 13:25:16.148035', 'projects/49/images/bab36142-6d55-466e-9ae4-0218d8f352c8-f946b53e-6ae4-4304-ae30-54233a7bf00a (1).webp', 49, 2, NULL, NULL),
(53, '2026-01-16 13:25:16.817456', 'projects/49/images/8e48955e-c986-44a3-a782-019748be0e09-5 (1).webp', 49, 3, NULL, NULL),
(54, '2026-01-16 13:25:17.514845', 'projects/49/images/d26203fe-2d48-4c5c-b4d0-88437794d796-a8f1bf54-2e40-4164-83c3-0196fd7e7915.webp', 49, 4, NULL, NULL),
(55, '2026-01-16 13:25:28.389180', 'projects/50/images/874ced92-ec69-487d-8d44-799a946919ca-Generated Image December 31, 2025 - 10_13PM.png', 50, 1, NULL, NULL),
(56, '2026-01-16 13:25:29.306388', 'projects/50/images/a0c6c23b-17f4-4d7a-b9a8-7cc2dfde43e5-f946b53e-6ae4-4304-ae30-54233a7bf00a (1).webp', 50, 2, NULL, NULL),
(57, '2026-01-16 13:25:30.153564', 'projects/50/images/d3de5e6e-6b1e-413c-80f7-7119567d51f5-5 (1).webp', 50, 3, NULL, NULL),
(58, '2026-01-16 13:25:30.973868', 'projects/50/images/51b7b567-2a65-49f7-b4e0-dbe4882a44e4-a8f1bf54-2e40-4164-83c3-0196fd7e7915.webp', 50, 4, NULL, NULL),
(63, '2026-01-16 14:12:22.601772', 'projects/52/images/6ca6232a-45aa-46dd-9ef2-8c22eccaf713-img.png', 52, 1, NULL, NULL),
(64, '2026-01-16 14:42:56.995022', 'projects/53/images/66eb329b-7d4a-416a-a77d-b2af5eb1719a-img.png', 53, 1, NULL, NULL),
(65, '2026-01-16 19:22:37.062448', 'projects/54/images/35ccb3e0-f60a-40f6-a377-e96bfb46f168-img.png', 54, 1, NULL, NULL),
(66, '2026-01-16 20:19:16.998830', 'projects/56/images/f2e0e395-405e-4f3c-a5b7-2b3001254752-ChatGPT Image Nov 26, 2025, 04_23_11 PM.png', 56, 1, NULL, NULL),
(71, '2026-01-18 06:20:23.055727', 'projects/58/images/198dec48-0732-4582-98dd-0f1784f6c4ba-ChatGPT Image Nov 26, 2025, 04_23_11 PM.png', 58, 1, NULL, NULL),
(75, '2026-01-21 07:43:07.769499', 'projects/5/images/4b7c80df-da34-4e12-944a-e2eaa82ed716-5.jpg', 5, 1, NULL, NULL),
(83, '2026-02-12 13:07:58.450894', 'projects/60/images/28a8190c-3dd2-4d47-903a-e80dad522d11-csd1.jpg', 60, 1, NULL, NULL),
(84, '2026-02-12 13:08:00.751937', 'projects/60/images/d6ac610e-41c7-457f-bc51-f9c480a7edd1-csd2.png', 60, 2, NULL, NULL),
(85, '2026-02-22 10:33:03.037570', 'projects/62/images/b83c67ec-ea05-4ae5-8dbf-2eb1e9ce3ad5-58752179_6_58752179_3_PropertyImage27-608376977487413_0_320.jpg', 62, 1, NULL, NULL),
(86, '2026-02-23 16:09:01.256937', 'projects/63/images/c6f55b7e-c2ae-4e2d-86ba-75fbf9797df0-58752179_6_58752179_3_PropertyImage27-608376977487413_0_320.jpg', 63, 1, NULL, NULL),
(87, '2026-02-23 16:09:01.970168', 'projects/63/images/affff393-0c43-4dd0-8c66-630d93da8d1c-160056540_15509935531_large.jpg', 63, 2, NULL, NULL),
(88, '2026-02-23 16:27:32.248535', 'projects/64/images/f12d11cf-4e2b-40b9-a450-87b99e0ef369-58752179_6_58752179_3_PropertyImage27-608376977487413_0_320.jpg', 64, 1, NULL, NULL),
(89, '2026-02-23 16:27:33.398768', 'projects/64/images/fd5f654d-406a-4e9d-a13f-8163b8aab2b5-160056540_15509935531_large.jpg', 64, 2, NULL, NULL),
(90, '2026-02-25 13:15:22.329232', 'projects/65/images/f5ee7b73-2238-41bc-a91a-3de403de8074-WhatsApp Image 2026-02-21 at 9.13.19 PM.jpeg', 65, 1, NULL, NULL),
(91, '2026-02-25 13:15:23.442649', 'projects/65/images/b97fa7f8-4353-4d90-af16-b149bcc31030-WhatsApp Image 2026-02-21 at 9.14.14 PM.jpeg', 65, 2, NULL, NULL),
(92, '2026-03-06 10:16:09.884330', 'projects/67/images/59fb8844-512a-4282-9e4c-03843dc72476-WhatsApp Image 2026-02-21 at 9.13.19 PM.jpeg', 67, 1, NULL, NULL),
(93, '2026-03-06 10:20:57.903715', 'projects/74/images/c6708d0c-b717-4395-8b5d-64e812b4eb27-property-0.png', 74, 1, NULL, NULL),
(94, '2026-03-06 10:20:58.948913', 'projects/75/images/ec635729-fa7d-4835-8b6f-68b257349e2c-property-1.png', 75, 1, NULL, NULL),
(95, '2026-03-06 10:21:01.134824', 'projects/76/images/b3747afd-bdf6-4aa4-8877-a0d444a6c86c-property-2.png', 76, 1, NULL, NULL),
(96, '2026-03-06 10:21:02.137535', 'projects/77/images/4b942103-5d0c-41d3-9256-19ba5a6b6c0f-property-3.png', 77, 1, NULL, NULL),
(97, '2026-03-06 10:21:03.210935', 'projects/78/images/7701ce3a-6348-4ba5-9ea7-0c3c5aa25b7f-property-4.png', 78, 1, NULL, NULL),
(98, '2026-03-06 10:33:28.456053', 'projects/79/images/04e3489c-e66d-46d1-8edb-e257f20c78ff-property-0.jpg', 79, 1, NULL, NULL),
(99, '2026-03-06 10:33:29.731288', 'projects/80/images/9d7f57ea-2e53-4d75-95f8-e620479fd484-property-1.jpg', 80, 1, NULL, NULL),
(100, '2026-03-06 10:33:31.234885', 'projects/81/images/5f2be727-0e77-4890-8d7f-b41fe9f051a6-property-2.jpg', 81, 1, NULL, NULL),
(101, '2026-03-06 10:33:32.681049', 'projects/82/images/332951a1-79f2-47d0-b7e4-185ed46eaf87-property-3.jpg', 82, 1, NULL, NULL),
(102, '2026-03-06 10:33:34.096259', 'projects/83/images/c6609b70-c4f1-4449-a986-8012793be782-property-4.jpg', 83, 1, NULL, NULL),
(103, '2026-03-09 19:23:11.364370', 'projects/96/images/4476f31d-5ade-43b1-b0b5-bcef503425d4-property.jpg', 96, 1, NULL, NULL),
(104, '2026-03-09 19:23:12.775422', 'projects/97/images/77dc7df3-1c3e-4634-95f8-16b889216bbd-property.jpg', 97, 1, NULL, NULL),
(105, '2026-03-09 19:31:42.003311', 'projects/98/images/9c75c871-b31c-4c36-8958-b10f9610adf5-property-0-0.jpg', 98, 1, NULL, NULL),
(106, '2026-03-09 19:31:42.724864', 'projects/98/images/2b13efac-76b0-4c2a-92ea-d59bf1b40c55-property-0-1.jpg', 98, 2, NULL, NULL),
(107, '2026-03-09 19:31:43.370309', 'projects/98/images/7989f0f4-ca62-4e6f-9624-e5d7a1bfbd9e-property-0-2.jpg', 98, 3, NULL, NULL),
(108, '2026-03-09 19:31:43.992076', 'projects/98/images/b2bfcf00-c833-4401-b630-b0fca6a0289b-property-0-3.jpg', 98, 4, NULL, NULL),
(109, '2026-03-09 19:31:44.645041', 'projects/98/images/e5bfe83e-cabb-4e44-bd07-e78eac00bb5c-property-0-4.jpg', 98, 5, NULL, NULL),
(110, '2026-03-09 19:31:45.249084', 'projects/98/images/76b46dd9-62e8-4431-984b-c9a7e0148e6d-property-0-5.jpg', 98, 6, NULL, NULL),
(111, '2026-03-09 19:31:45.923953', 'projects/98/images/3db24089-0877-4854-aaa5-d57b014e982d-property-0-6.jpg', 98, 7, NULL, NULL),
(112, '2026-03-09 20:15:04.625126', 'projects/99/images/e4a3e01a-c3bc-470b-a84b-c622d4cf0475-property.jpg', 99, 1, NULL, NULL),
(113, '2026-03-09 20:15:06.030210', 'projects/100/images/87f7c60f-27ab-4db6-ac0b-506b721fc4c1-property.jpg', 100, 1, NULL, NULL),
(114, '2026-03-09 20:15:07.186140', 'projects/101/images/60de8732-11fa-44c0-b904-895c7713152e-property.jpg', 101, 1, NULL, NULL),
(115, '2026-03-09 20:15:08.302147', 'projects/102/images/cbeaf108-851b-4a87-88e6-b77453486216-property.jpg', 102, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `re_project_locations`
--

CREATE TABLE `re_project_locations` (
  `location_id` bigint NOT NULL,
  `address_line` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `latitude` double NOT NULL,
  `location_type` varchar(255) DEFAULT NULL,
  `longitude` double NOT NULL,
  `project_id` bigint NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_locations`
--

INSERT INTO `re_project_locations` (`location_id`, `address_line`, `area`, `city`, `created_at`, `latitude`, `location_type`, `longitude`, `project_id`, `updated_at`, `zone`) VALUES
(2, NULL, 'WhiteField', 'Bengaluru', NULL, 12.9716, NULL, 77.5946, 3, NULL, 'East'),
(4, NULL, 'WhiteField', 'Bengaluru', NULL, 12.9716, NULL, 77.5946, 8, NULL, 'East'),
(5, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 10:39:29.628989', 12.976346, 'PROJECT', 77.6097, 15, NULL, 'East'),
(6, NULL, 'Marathahalli', 'Bengaluru', '2026-01-12 10:49:04.040210', 22.22, 'PROJECT', 44.55, 17, NULL, 'East'),
(8, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 11:02:20.977539', 12.9698, 'PROJECT', 77.75, 19, NULL, 'East'),
(9, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 11:08:28.718139', 12.9698, 'PROJECT', 77.75, 20, NULL, 'East'),
(10, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 11:11:12.660069', 12.9698, 'PROJECT', 77.75, 21, NULL, 'East'),
(11, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 11:30:53.331951', 12.9698, 'PROJECT', 77.75, 22, NULL, 'East'),
(12, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 11:34:49.010099', 12.9698, 'PROJECT', 77.75, 23, NULL, 'East'),
(13, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 11:34:59.670183', 12.9698, 'PROJECT', 77.75, 24, NULL, 'East'),
(14, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 13:03:52.560665', 12.9698, 'PROJECT', 77.75, 25, NULL, 'East'),
(15, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 13:06:21.921979', 12.9698, 'PROJECT', 77.75, 26, NULL, 'East'),
(16, NULL, 'Whitefield', 'Bengaluru', '2026-01-12 13:33:33.691548', 12.9698, 'PROJECT', 77.75, 27, NULL, 'East'),
(19, NULL, 'Whitefield', 'Bengaluru', '2026-01-15 07:53:40.695203', 12.9698, 'PROJECT', 77.75, 30, NULL, 'East'),
(20, NULL, 'Whitefield', 'Bengaluru', '2026-01-15 07:57:05.330976', 12.9698, 'PROJECT', 77.75, 31, NULL, 'East'),
(22, NULL, 'Whitefield', 'Bengaluru', '2026-01-15 08:03:24.359915', 12.9698, 'PROJECT', 77.75, 33, NULL, 'East'),
(23, NULL, 'Whitefield', 'Bengaluru', '2026-01-15 08:04:28.378280', 12.9698, 'PROJECT', 77.75, 34, NULL, 'East'),
(24, NULL, 'Whitefield', 'Bengaluru', '2026-01-15 08:13:52.197152', 12.9698, 'PROJECT', 77.75, 35, NULL, 'East'),
(25, NULL, 'Whitefield', 'Bengaluru', '2026-01-15 08:18:28.333966', 12.9698, 'PROJECT', 77.75, 36, NULL, 'East'),
(26, NULL, 'Whitefield', 'Bengaluru', '2026-01-15 09:15:12.189499', 12.9698, 'PROJECT', 77.75, 37, NULL, 'East'),
(27, NULL, 'Whitefield', 'Bengaluru', '2026-01-15 10:06:13.450858', 12.9698, 'PROJECT', 77.75, 38, NULL, 'East'),
(28, NULL, 'Whitefield', 'Bengaluru', '2026-01-16 08:16:13.953012', 12.9698, 'PROJECT', 77.75, 39, NULL, 'East'),
(29, NULL, 'Kr Puram', 'Bengaluru', '2026-01-16 08:17:10.271654', 12.9698, 'PROJECT', 77.75, 40, NULL, 'East'),
(30, NULL, 'Varthur', 'Bengaluru', '2026-01-16 09:12:50.008808', 12.9698, 'PROJECT', 77.75, 41, NULL, 'East'),
(31, NULL, 'Sarjapur Road', 'Bengaluru', '2026-01-16 09:32:43.442103', 12.860674, 'PROJECT', 77.783203, 42, NULL, 'East'),
(32, NULL, 'Bellandur', 'Bengaluru', '2026-01-16 09:48:36.946732', 12.926938, 'PROJECT', 77.681256, 43, NULL, 'East'),
(33, NULL, 'Varthur', 'Bengaluru', '2026-01-16 10:05:38.279892', 12.9698, 'PROJECT', 77.75, 44, NULL, 'East'),
(34, NULL, 'Varthur', 'Bengaluru', '2026-01-16 10:05:50.795343', 12.9698, 'PROJECT', 77.75, 45, NULL, 'East'),
(35, NULL, 'Varthur', 'Bengaluru', '2026-01-16 12:14:49.998948', 12.9698, 'PROJECT', 77.75, 46, NULL, 'East'),
(36, NULL, 'Bellandur', 'Bengaluru', '2026-01-16 13:23:45.932836', 12.981308, 'PROJECT', 77.613602, 47, NULL, 'East'),
(37, NULL, 'Bellandur', 'Bengaluru', '2026-01-16 13:24:43.307712', 12.981308, 'PROJECT', 77.613602, 48, NULL, 'East'),
(38, NULL, 'Bellandur', 'Bengaluru', '2026-01-16 13:25:15.079234', 12.981308, 'PROJECT', 77.613602, 49, NULL, 'East'),
(39, NULL, 'Bellandur', 'Bengaluru', '2026-01-16 13:25:28.303206', 12.981308, 'PROJECT', 77.613602, 50, NULL, 'East'),
(41, NULL, 'Varthur', 'Bengaluru', '2026-01-16 14:12:22.548017', 12.9698, 'PROJECT', 77.75, 52, NULL, 'East'),
(42, NULL, 'Varthur', 'Bengaluru', '2026-01-16 14:42:54.354288', 12.9698, 'PROJECT', 77.75, 53, NULL, 'East'),
(43, NULL, 'Varthur', 'Bengaluru', '2026-01-16 19:22:34.443602', 12.9698, 'PROJECT', 77.75, 54, NULL, 'East'),
(45, NULL, 'Whitefield', 'Bengaluru', '2026-01-16 20:19:14.467099', 12.9698, 'PROJECT', 77.75, 56, NULL, 'East'),
(61, NULL, 'Varthur', 'Bengaluru', '2026-01-17 13:39:57.666448', 12.9698, 'PROJECT', 77.75, 2, NULL, 'East'),
(63, NULL, 'Marathahalli', 'Bengaluru', '2026-01-17 16:51:34.313310', 12.972609, 'PROJECT', 77.56381, 57, NULL, 'East'),
(65, NULL, 'Bellandur', 'Bengaluru', '2026-01-17 18:27:46.656323', 12.981308, 'PROJECT', 77.613602, 51, NULL, 'East'),
(66, NULL, 'xyz', 'Bengaluru', '2026-01-18 06:20:19.305769', 12.9698, 'PROJECT', 77.75, 58, NULL, 'East'),
(68, NULL, 'Shanthinagar', 'Bengaluru', '2026-01-18 12:49:29.903054', 12.963074, 'PROJECT', 77.600899, 59, NULL, 'East'),
(79, NULL, 'Beguru', 'Bengaluru', '2026-01-21 07:43:05.668863', 12.875231, 'PROJECT', 77.621038, 5, NULL, 'East'),
(91, NULL, ' Whitefield', ' Bengaluru', '2026-02-12 13:07:52.821485', 12.9698, 'PROJECT', 77.75, 60, NULL, ' East'),
(94, NULL, 'Balagere', 'Bengaluru', '2026-02-13 18:38:05.898646', 12.932703, 'PROJECT', 77.713993, 4, NULL, 'East'),
(95, NULL, 'Sampangirama Nagar', 'Bengaluru', '2026-02-22 10:33:02.037565', 12.974728, 'PROJECT', 77.595749, 62, NULL, 'East'),
(96, NULL, 'Byrasandra', 'Bengaluru', '2026-02-23 16:09:00.564775', 12.929273, 'PROJECT', 77.582423, 63, NULL, 'East'),
(97, NULL, 'Sampangirama Nagar', 'Bengaluru', '2026-02-23 16:27:31.052356', 12.975732, 'PROJECT', 77.59469, 64, NULL, 'East'),
(98, NULL, 'Sampangirama Nagar', 'Bengaluru', '2026-02-25 13:15:13.639911', 12.975565, 'PROJECT', 77.595406, 65, NULL, 'East'),
(100, NULL, 'xyz', 'Bengaluru', '2026-03-06 10:16:00.269138', 12.9698, 'PROJECT', 77.75, 67, NULL, 'East'),
(106, NULL, 'xyz', 'Bengaluru', '2026-03-06 10:16:45.677728', 12.9698, 'PROJECT', 77.75, 73, NULL, 'East'),
(107, NULL, 'Marathahalli', 'Bengaluru', '2026-03-06 10:20:56.872694', 12.9698, 'PROJECT', 77.75, 74, NULL, 'East'),
(108, NULL, 'Nallurhalli', 'Bengaluru', '2026-03-06 10:20:58.521866', 12.9698, 'PROJECT', 77.75, 75, NULL, 'East'),
(109, NULL, 'Nelamangala', 'Bengaluru', '2026-03-06 10:21:00.668564', 12.9698, 'PROJECT', 77.75, 76, NULL, 'East'),
(110, NULL, 'Hoodi', 'Bengaluru', '2026-03-06 10:21:01.742831', 12.9698, 'PROJECT', 77.75, 77, NULL, 'East'),
(111, NULL, 'Kengeri', 'Bengaluru', '2026-03-06 10:21:02.764021', 12.9698, 'PROJECT', 77.75, 78, NULL, 'East'),
(112, NULL, 'Marathahalli', 'Bengaluru', '2026-03-06 10:33:27.884923', 12.9698, 'PROJECT', 77.75, 79, NULL, 'East'),
(113, NULL, 'Nallurhalli', 'Bengaluru', '2026-03-06 10:33:29.291410', 12.9698, 'PROJECT', 77.75, 80, NULL, 'East'),
(114, NULL, 'Nelamangala', 'Bengaluru', '2026-03-06 10:33:30.712816', 12.9698, 'PROJECT', 77.75, 81, NULL, 'East'),
(115, NULL, 'Hoodi', 'Bengaluru', '2026-03-06 10:33:32.087697', 12.9698, 'PROJECT', 77.75, 82, NULL, 'East'),
(116, NULL, 'Kengeri', 'Bengaluru', '2026-03-06 10:33:33.656432', 12.9698, 'PROJECT', 77.75, 83, NULL, 'East'),
(117, NULL, 'Whitefield', 'Bengaluru', '2026-03-09 13:07:35.483662', 12.9698, 'PROJECT', 77.7499, 84, NULL, 'East'),
(118, NULL, 'Whitefield', 'Bengaluru', '2026-03-09 13:13:52.284687', 12.9698, 'PROJECT', 77.7499, 85, NULL, 'East'),
(119, NULL, 'Whitefield', 'Bengaluru', '2026-03-09 13:30:01.086624', 12.9698, 'PROJECT', 77.7499, 86, NULL, 'East'),
(120, NULL, 'Whitefield', 'Bengaluru', '2026-03-09 13:32:19.867177', 12.9698, 'PROJECT', 77.7499, 87, NULL, 'East'),
(121, NULL, 'Whitefield', 'Bengaluru', '2026-03-09 13:38:25.682345', 12.9698, 'PROJECT', 77.7499, 88, NULL, 'East'),
(125, NULL, 'Whitefield', 'Bengaluru', '2026-03-09 13:44:55.846132', 12.9698, 'PROJECT', 77.7499, 92, NULL, 'East'),
(126, NULL, 'Whitefield', 'Bengaluru', '2026-03-09 13:45:29.883476', 12.9698, 'PROJECT', 77.7499, 93, NULL, 'East'),
(127, NULL, 'Whitefield', 'Bengaluru', '2026-03-09 14:04:09.934974', 12.9698, 'PROJECT', 77.7499, 94, NULL, 'East'),
(128, NULL, 'Whitefield', 'Bengaluru', '2026-03-09 14:05:55.261064', 12.9698, 'PROJECT', 77.7499, 95, NULL, 'East'),
(129, NULL, 'HSR Layout, South Bangalore, Bangalore', 'Bengaluru', '2026-03-09 19:23:06.957344', 12.900501, 'PROJECT', 77.646301, 96, NULL, 'East'),
(130, NULL, 'Stone bridge Alpha, Horamavu Agara, Horamavu, Bangalore', 'Bengaluru', '2026-03-09 19:23:12.118193', 13.040153, 'PROJECT', 77.662074, 97, NULL, 'East'),
(131, NULL, 'Marathahalli', 'Bengaluru', '2026-03-09 19:31:40.283699', 12.9698, 'PROJECT', 77.75, 98, NULL, 'East'),
(132, NULL, 'Delight Realtors, Jigani, Bangalore', 'Bengaluru', '2026-03-09 20:15:03.332346', 12.793278, 'PROJECT', 77.619101, 99, NULL, 'East'),
(133, NULL, 'Muneshwara Nagar, South Bangalore, Bangalore', 'Bengaluru', '2026-03-09 20:15:05.593247', 12.891997, 'PROJECT', 77.646454, 100, NULL, 'East'),
(134, NULL, 'Kacharakanahalli,Bisnahalli, Bangalore East , Bangalore', 'Bengaluru', '2026-03-09 20:15:06.736516', 12.98673, 'PROJECT', 77.816879, 101, NULL, 'East'),
(135, NULL, 'Varthur, Bangalore East , Bangalore', 'Bengaluru', '2026-03-09 20:15:07.866658', 12.938918, 'PROJECT', 77.741203, 102, NULL, 'East');

-- --------------------------------------------------------

--
-- Table structure for table `re_project_tags`
--

CREATE TABLE `re_project_tags` (
  `project_tag_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `project_id` bigint NOT NULL,
  `status` int DEFAULT NULL,
  `tag_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_tags`
--

INSERT INTO `re_project_tags` (`project_tag_id`, `created_at`, `project_id`, `status`, `tag_id`) VALUES
(1, '2026-02-12 13:08:04.238225', 60, 1, 1),
(2, '2026-02-12 13:08:04.548459', 60, 1, 3),
(3, '2026-02-12 13:08:04.876375', 60, 1, 4),
(4, '2026-03-09 13:07:35.628053', 84, 1, 1),
(5, '2026-03-09 13:07:35.646750', 84, 1, 2),
(6, '2026-03-09 13:30:01.152051', 86, 1, 1),
(7, '2026-03-09 13:30:01.158187', 86, 1, 2),
(8, '2026-03-09 13:38:25.960075', 88, 1, 1),
(9, '2026-03-09 13:38:25.993794', 88, 1, 2),
(10, '2026-03-09 13:44:56.069791', 92, 1, 1),
(11, '2026-03-09 13:44:56.096282', 92, 1, 2),
(12, '2026-03-09 13:45:30.080847', 93, 1, 1),
(13, '2026-03-09 13:45:30.107990', 93, 1, 2),
(14, '2026-03-09 14:04:10.699225', 94, 1, 1),
(15, '2026-03-09 14:04:10.742090', 94, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `re_project_types`
--

CREATE TABLE `re_project_types` (
  `project_type_id` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_types`
--

INSERT INTO `re_project_types` (`project_type_id`, `created_at`, `name`, `status`, `updated_at`) VALUES
(1, '2025-12-24 03:30:46.000000', 'Apartment', 1, NULL),
(2, '2025-12-24 03:30:46.000000', 'Villa', 1, NULL),
(3, '2025-12-24 03:30:46.000000', 'Land', 1, NULL),
(4, '2025-12-24 03:30:46.000000', 'Agriculture Land', 1, NULL),
(5, '2025-12-24 03:30:46.000000', 'Studio', 1, NULL),
(6, '2025-12-24 03:30:46.000000', 'Penthouse', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `re_project_unit_types`
--

CREATE TABLE `re_project_unit_types` (
  `project_unit_type_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `price_max` double DEFAULT NULL,
  `price_min` double DEFAULT NULL,
  `price_unit` varchar(255) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `size_sqft` int DEFAULT NULL,
  `unit_type_id` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_unit_types`
--

INSERT INTO `re_project_unit_types` (`project_unit_type_id`, `created_at`, `price_max`, `price_min`, `price_unit`, `project_id`, `size_sqft`, `unit_type_id`, `updated_at`) VALUES
(4, '2025-12-24 19:30:13.106457', 95, 85, 'L', 3, 1200, 1, NULL),
(5, '2025-12-24 19:30:13.118991', 1.35, 1.15, 'CR', 3, 1600, 2, NULL),
(10, '2025-12-29 11:27:08.328917', 95, 85, 'L', 8, 1200, 1, NULL),
(11, '2025-12-29 11:27:08.337776', 1.35, 1.15, 'l', 8, 1600, 2, NULL),
(12, '2026-01-12 10:39:29.640303', 1.5, 1.1, 'Cr', 15, 1200, 2, NULL),
(15, '2026-01-12 11:02:20.982132', 0.95, 0.85, 'Cr', 19, 1200, 1, NULL),
(16, '2026-01-12 11:02:20.984815', 1.35, 1.15, 'Cr', 19, 1600, 2, NULL),
(17, '2026-01-12 11:08:28.723963', 0.95, 0.85, 'Cr', 20, 1200, 1, NULL),
(18, '2026-01-12 11:08:28.729487', 1.35, 1.15, 'Cr', 20, 1600, 2, NULL),
(19, '2026-01-12 11:11:12.668358', 0.95, 0.85, 'Cr', 21, 1200, 1, NULL),
(20, '2026-01-12 11:11:12.673997', 1.35, 1.15, 'Cr', 21, 1600, 2, NULL),
(21, '2026-01-12 11:30:53.341081', 0.95, 0.85, 'Cr', 22, 1200, 1, NULL),
(22, '2026-01-12 11:30:53.347054', 1.35, 1.15, 'Cr', 22, 1600, 2, NULL),
(23, '2026-01-12 11:34:49.023520', 0.95, 0.85, 'Cr', 23, 1200, 1, NULL),
(24, '2026-01-12 11:34:49.034559', 1.35, 1.15, 'Cr', 23, 1600, 2, NULL),
(25, '2026-01-12 11:34:59.677721', 0.95, 0.85, 'Cr', 24, 1200, 1, NULL),
(26, '2026-01-12 11:34:59.683991', 1.35, 1.15, 'Cr', 24, 1600, 2, NULL),
(27, '2026-01-12 13:03:52.570525', 0.95, 0.85, 'Cr', 25, 1200, 1, NULL),
(28, '2026-01-12 13:03:52.575238', 1.35, 1.15, 'Cr', 25, 1600, 2, NULL),
(29, '2026-01-12 13:06:21.929067', 0.95, 0.85, 'Cr', 26, 1200, 1, NULL),
(30, '2026-01-12 13:06:21.933760', 1.35, 1.15, 'Cr', 26, 1600, 2, NULL),
(31, '2026-01-12 13:33:33.716890', 0.95, 0.85, 'Cr', 27, 1200, 1, NULL),
(32, '2026-01-12 13:33:33.735813', 1.35, 1.15, 'Cr', 27, 1600, 2, NULL),
(37, '2026-01-15 07:53:40.705475', 0.95, 0.85, 'Cr', 30, 1200, 1, NULL),
(38, '2026-01-15 07:53:40.712753', 1.35, 1.15, 'Cr', 30, 1600, 2, NULL),
(39, '2026-01-15 07:57:05.341132', 0.95, 0.85, 'Cr', 31, 1200, 1, NULL),
(40, '2026-01-15 07:57:05.348849', 1.35, 1.15, 'Cr', 31, 1600, 2, NULL),
(43, '2026-01-15 08:03:24.368091', 0.95, 0.85, 'Cr', 33, 1200, 1, NULL),
(44, '2026-01-15 08:03:24.374886', 1.35, 1.15, 'Cr', 33, 1600, 2, NULL),
(45, '2026-01-15 08:04:28.388254', 0.95, 0.85, 'Cr', 34, 1200, 1, NULL),
(46, '2026-01-15 08:04:28.398313', 1.35, 1.15, 'Cr', 34, 1600, 2, NULL),
(47, '2026-01-15 08:13:52.205132', 0.95, 0.85, 'Cr', 35, 1200, 1, NULL),
(48, '2026-01-15 08:13:52.209992', 1.35, 1.15, 'Cr', 35, 1600, 2, NULL),
(49, '2026-01-15 08:18:28.340880', 0.95, 0.85, 'Cr', 36, 1200, 1, NULL),
(50, '2026-01-15 08:18:28.344908', 1.35, 1.15, 'Cr', 36, 1600, 2, NULL),
(51, '2026-01-15 09:15:12.208075', 0.95, 0.85, 'Cr', 37, 1200, 1, NULL),
(52, '2026-01-15 09:15:12.221289', 1.35, 1.15, 'Cr', 37, 1600, 2, NULL),
(53, '2026-01-15 10:06:13.463135', 0.95, 0.85, 'Cr', 38, 1200, 1, NULL),
(54, '2026-01-15 10:06:13.467225', 1.35, 1.15, 'Cr', 38, 1600, 2, NULL),
(55, '2026-01-16 08:16:13.982936', 0.95, 0.85, 'Cr', 39, 1200, 1, NULL),
(56, '2026-01-16 08:16:13.996856', 1.35, 1.15, 'Cr', 39, 1600, 2, NULL),
(57, '2026-01-16 08:17:10.279165', 0.95, 0.85, 'Cr', 40, 1200, 1, NULL),
(58, '2026-01-16 08:17:10.284757', 1.35, 1.15, 'Cr', 40, 1600, 2, NULL),
(59, '2026-01-16 09:12:50.017602', 0.95, 0.85, 'Cr', 41, 1200, 1, NULL),
(60, '2026-01-16 09:12:50.023343', 1.35, 1.15, 'Cr', 41, 1600, 2, NULL),
(61, '2026-01-16 09:32:43.446968', 1.15, 1, 'Cr', 42, 1000, 2, NULL),
(62, '2026-01-16 10:05:38.291415', 0.95, 0.85, 'Cr', 44, 1200, 1, NULL),
(63, '2026-01-16 10:05:38.300373', 1.35, 1.15, 'Cr', 44, 1600, 2, NULL),
(64, '2026-01-16 10:05:50.802638', 0.95, 0.85, 'Cr', 45, 1200, 1, NULL),
(65, '2026-01-16 10:05:50.808374', 1.35, 1.15, 'Cr', 45, 1600, 2, NULL),
(66, '2026-01-16 12:14:50.023700', 0.95, 0.85, 'Cr', 46, 1200, 1, NULL),
(67, '2026-01-16 12:14:50.041270', 1.35, 1.15, 'Cr', 46, 1600, 2, NULL),
(68, '2026-01-16 14:12:22.566333', 0.95, 0.85, 'Cr', 52, 1200, 1, NULL),
(69, '2026-01-16 14:12:22.578498', 1.35, 1.15, 'Cr', 52, 1600, 2, NULL),
(70, '2026-01-16 14:42:54.966069', 0.95, 0.85, 'Cr', 53, 1200, 1, NULL),
(71, '2026-01-16 14:42:55.587738', 1.35, 1.15, 'Cr', 53, 1600, 2, NULL),
(72, '2026-01-16 19:22:35.042395', 0.95, 0.85, 'Cr', 54, 1200, 1, NULL),
(73, '2026-01-16 19:22:35.760230', 1.35, 1.15, 'Cr', 54, 1600, 2, NULL),
(76, '2026-01-16 20:19:15.024035', 0.95, 0.85, 'Cr', 56, 1200, 1, NULL),
(77, '2026-01-16 20:19:15.559120', 1.35, 1.15, 'Cr', 56, 1600, 2, NULL),
(107, '2026-01-17 13:39:58.614053', 0.95, 0.85, 'Cr', 2, 1200, 1, NULL),
(108, '2026-01-17 13:39:59.087881', 1.35, 1.15, 'Cr', 2, 1600, 2, NULL),
(110, '2026-01-17 16:51:35.394197', 1.5, 1.15, 'L', 57, 1200, 2, NULL),
(112, '2026-01-17 18:27:47.737994', 2, 1, 'Cr', 51, 1200, 2, NULL),
(113, '2026-01-18 06:20:20.471291', 0.95, 0.85, 'Cr', 58, 1200, 1, NULL),
(114, '2026-01-18 06:20:21.234158', 1.35, 1.15, 'Cr', 58, 1600, 2, NULL),
(116, '2026-01-18 12:49:29.908340', 2, 1, 'Cr', 59, 100, 10, NULL),
(154, '2026-02-12 13:07:53.869325', 0.95, 0.85, ' Cr', 60, 1200, 1, NULL),
(155, '2026-02-12 13:07:54.209250', 1.35, 1.15, ' Cr', 60, 1600, 2, NULL),
(162, '2026-02-22 10:33:02.048198', 1.5, 1, 'Cr', 62, 1200, 2, NULL),
(166, '2026-02-23 16:20:37.484693', 2, 1.15, 'Cr', 63, 1200, 2, NULL),
(167, '2026-02-23 16:27:31.056822', 1, 1, 'Cr', 64, 1, 11, NULL),
(168, '2026-02-25 13:15:14.767111', 2, 1, 'Cr', 65, 1200, 11, NULL),
(169, '2026-03-04 18:23:49.671447', 4.7, 3.7, 'Cr', 4, 1200, 4, NULL),
(170, '2026-03-04 18:23:50.537707', 4.7, 3.7, 'Cr', 4, 1600, 5, NULL),
(177, '2026-03-05 13:40:12.916695', 2.8, 1.7, 'Cr', 5, 1600, 3, NULL),
(178, '2026-03-05 13:40:13.441883', 2.8, 1.7, 'Cr', 5, 1600, 4, NULL),
(181, '2026-03-06 10:16:00.284884', 0.95, 0.85, 'Cr', 67, 1200, 1, NULL),
(182, '2026-03-06 10:16:00.380990', 1.35, 1.15, 'Cr', 67, 1600, 2, NULL),
(193, '2026-03-06 10:16:45.687234', 0.95, 0.85, 'Cr', 73, 1200, 1, NULL),
(194, '2026-03-06 10:16:45.689186', 1.35, 1.15, 'Cr', 73, 1600, 2, NULL),
(195, '2026-03-06 10:20:56.892877', 1.6, 1.2, 'Cr', 74, 2730, 3, NULL),
(196, '2026-03-06 10:20:56.897296', 2.5, 1.8, 'Cr', 74, 2730, 4, NULL),
(197, '2026-03-06 10:20:58.531996', 1.1, 0.85, 'Cr', 75, 2295, 2, NULL),
(198, '2026-03-06 10:20:58.533970', 1.6, 1.2, 'Cr', 75, 2295, 3, NULL),
(199, '2026-03-06 10:20:58.535487', 2.5, 1.8, 'Cr', 75, 2295, 4, NULL),
(200, '2026-03-06 10:21:00.676286', 0.39, 0.34, 'Cr', 76, 4573, 2, NULL),
(201, '2026-03-06 10:21:00.679083', 0.39, 0.34, 'Cr', 76, 4573, 3, NULL),
(202, '2026-03-06 10:21:01.750022', 1.6, 1.2, 'Cr', 77, 1702, 3, NULL),
(203, '2026-03-06 10:21:02.770512', 0.95, 0.83, 'Cr', 78, 1200, 2, NULL),
(204, '2026-03-06 10:21:02.773038', 0.95, 0.83, 'Cr', 78, 1700, 3, NULL),
(205, '2026-03-06 10:33:27.903200', 1.6, 1.2, 'Cr', 79, 2730, 3, NULL),
(206, '2026-03-06 10:33:27.906844', 2.5, 1.8, 'Cr', 79, 2730, 4, NULL),
(207, '2026-03-06 10:33:29.301398', 1.1, 0.85, 'Cr', 80, 2295, 2, NULL),
(208, '2026-03-06 10:33:29.303257', 1.6, 1.2, 'Cr', 80, 2295, 3, NULL),
(209, '2026-03-06 10:33:29.306513', 2.5, 1.8, 'Cr', 80, 2295, 4, NULL),
(210, '2026-03-06 10:33:30.719216', 0.39, 0.34, 'Cr', 81, 4573, 2, NULL),
(211, '2026-03-06 10:33:30.720539', 0.39, 0.34, 'Cr', 81, 4573, 3, NULL),
(212, '2026-03-06 10:33:32.094129', 1.6, 1.2, 'Cr', 82, 1702, 3, NULL),
(213, '2026-03-06 10:33:33.664071', 0.95, 0.83, 'Cr', 83, 1200, 2, NULL),
(214, '2026-03-06 10:33:33.667722', 0.95, 0.83, 'Cr', 83, 1700, 3, NULL),
(215, '2026-03-09 13:07:35.572663', 120, 98, 'Lac', 84, 1250, 2, NULL),
(216, '2026-03-09 13:30:01.124504', 120, 98, 'Lac', 86, 1250, 2, NULL),
(217, '2026-03-09 13:38:25.795351', 120, 98, 'Lac', 88, 1250, 2, NULL),
(221, '2026-03-09 13:44:55.935852', 120, 98, 'Lac', 92, 1250, 2, NULL),
(222, '2026-03-09 13:45:29.968113', 120, 98, 'Lac', 93, 1250, 2, NULL),
(223, '2026-03-09 14:04:10.310506', 120, 98, 'Lac', 94, 1250, 2, NULL),
(224, '2026-03-09 19:23:07.032231', 1.28, 0.54, 'Cr', 96, 1700, 3, NULL),
(225, '2026-03-09 19:23:12.129659', 1.2, 0.85, 'Cr', 97, 1200, 1, NULL),
(226, '2026-03-09 19:31:40.305778', 1.6, 1.2, 'Cr', 98, 2730, 3, NULL),
(227, '2026-03-09 19:31:40.311304', 2.5, 1.8, 'Cr', 98, 2730, 4, NULL),
(228, '2026-03-09 20:15:03.354348', 1.2, 0.85, 'Cr', 99, 1200, 1, NULL),
(229, '2026-03-09 20:15:05.602914', 1.68, 1.18, 'Cr', 100, 1700, 3, NULL),
(230, '2026-03-09 20:15:06.748703', 1.55, 1.31, 'Cr', 101, 1700, 3, NULL),
(231, '2026-03-09 20:15:07.880817', 1.22, 0.95, 'Cr', 102, 1700, 3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `re_project_videos`
--

CREATE TABLE `re_project_videos` (
  `project_video_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `project_id` bigint NOT NULL,
  `sort_order` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `video_type` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_project_videos`
--

INSERT INTO `re_project_videos` (`project_video_id`, `created_at`, `project_id`, `sort_order`, `status`, `video_type`, `video_url`) VALUES
(1, '2026-02-12 13:08:03.925198', 60, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(2, '2026-02-23 16:27:34.842650', 64, 1, 1, 'YOUTUBE', 'https://www.youtube.com/watch?v=RNMI0uqz5-8'),
(3, '2026-02-25 13:15:24.487304', 65, 1, 1, 'YOUTUBE', 'https://www.youtube.com/watch?v=RNMI0uqz5-8'),
(4, '2026-03-09 13:07:35.614275', 84, 1, 1, 'YOUTUBE', 'https://youtu.be/abc123xyz'),
(5, '2026-03-09 13:30:01.142794', 86, 1, 1, 'YOUTUBE', 'https://youtu.be/abc123xyz'),
(6, '2026-03-09 13:38:25.914493', 88, 1, 1, 'YOUTUBE', 'https://youtu.be/abc123xyz'),
(7, '2026-03-09 13:44:56.034122', 92, 1, 1, 'YOUTUBE', 'https://youtu.be/abc123xyz'),
(8, '2026-03-09 13:45:30.052904', 93, 1, 1, 'YOUTUBE', 'https://youtu.be/abc123xyz'),
(9, '2026-03-09 14:04:10.657244', 94, 1, 1, 'YOUTUBE', 'https://youtu.be/abc123xyz');

-- --------------------------------------------------------

--
-- Table structure for table `re_properties`
--

CREATE TABLE `re_properties` (
  `property_id` bigint NOT NULL,
  `area` varchar(255) DEFAULT NULL,
  `availability_status` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `developer_id` bigint DEFAULT NULL,
  `facing` varchar(255) DEFAULT NULL,
  `floor_number` int DEFAULT NULL,
  `furnishing_status` varchar(255) DEFAULT NULL,
  `is_verified` bit(1) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `owner_phone` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `price_unit` varchar(255) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `seller_user_id` int DEFAULT NULL,
  `size_sqft` double DEFAULT NULL,
  `status` int DEFAULT NULL,
  `tower_name` varchar(255) DEFAULT NULL,
  `unit_type_id` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  `advisor_user_id` int DEFAULT NULL,
  `property_name` varchar(255) DEFAULT NULL,
  `description` text,
  `is_active` bit(1) DEFAULT NULL,
  `listed_by` enum('AGENT','OWNER','STHIRVA') DEFAULT NULL,
  `possession_date` date DEFAULT NULL,
  `sub_area` varchar(255) DEFAULT NULL,
  `amenities_description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_properties`
--

INSERT INTO `re_properties` (`property_id`, `area`, `availability_status`, `city`, `created_at`, `created_by`, `developer_id`, `facing`, `floor_number`, `furnishing_status`, `is_verified`, `latitude`, `longitude`, `owner_name`, `owner_phone`, `price`, `price_unit`, `project_id`, `seller_user_id`, `size_sqft`, `status`, `tower_name`, `unit_type_id`, `updated_at`, `updated_by`, `zone`, `advisor_user_id`, `property_name`, `description`, `is_active`, `listed_by`, `possession_date`, `sub_area`, `amenities_description`) VALUES
(6, 'Hunasehally', 'Available', ' Bengaluru', '2026-02-17 12:40:23.199063', 1, NULL, '', NULL, '', b'1', 12.9081, 77.5858, ' Bhavya HG', ' 9110656001', 1.03, 'Cr', NULL, 1, 5150, NULL, '', 11, '2026-02-25 12:00:25.681363', 1, ' East', 5, 'Agriculture Property', 'Agriculture Property Close to upcoming 80Ft Main R', b'0', 'OWNER', NULL, 'Naduvathi Main Road', NULL),
(10, 'S.K Garden', 'Available', 'Bengaluru', '2026-02-21 14:28:24.966144', 1, 1, '', NULL, '', b'1', 12.988336050010753, 77.66610426984742, 'srini', '1213', 1, 'Cr', NULL, NULL, 1, NULL, '', 7, NULL, NULL, 'East', NULL, '', NULL, b'1', 'OWNER', NULL, NULL, NULL),
(11, 'Hunasehally', ' Available', ' Bengaluru', '2026-02-17 12:40:23.199063', 1, NULL, ' East', 2, ' Not Furnished', b'1', 12.9081, 77.5858, ' Bhavya', ' 9110656001', 1.03, ' Cr', NULL, 1, 5150, NULL, ' A', 2, '2026-02-22 04:52:48.414264', 1, ' East', 2, ' Luxury Villa East', '', b'0', 'OWNER', NULL, NULL, NULL),
(13, 'Electronic City', 'Available', 'Bengaluru', '2026-02-23 14:50:35.056050', 1, NULL, '', NULL, '', b'0', NULL, NULL, 'Shivam Advisor', '9035327942', 1, 'L', NULL, NULL, 1, NULL, '', 11, NULL, NULL, 'South', NULL, '', NULL, b'1', 'OWNER', NULL, NULL, NULL),
(14, 'Sampangirama Nagar', 'Available', 'Bengaluru', '2026-02-23 14:52:26.563260', 1, NULL, '', NULL, '', b'1', 12.975799112494322, 77.59353637622553, 'Shivam Advisor', '9035327942', 1, 'L', NULL, NULL, 1, NULL, '', 11, NULL, NULL, 'Central', NULL, '', NULL, b'1', 'OWNER', NULL, NULL, NULL),
(15, 'Sampangirama Nagar', 'Available', 'Bengaluru', '2026-02-23 15:04:33.851615', 1, NULL, '', NULL, '', b'0', 12.974794909467914, 77.59614944632632, 'Shivam Advisor', '9035327942', 1, 'L', NULL, NULL, 1, NULL, '', 11, NULL, NULL, 'Central', NULL, '', NULL, b'1', 'OWNER', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `re_property_advisors`
--

CREATE TABLE `re_property_advisors` (
  `id` bigint NOT NULL,
  `advisor_user_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `property_id` bigint DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_property_advisors`
--

INSERT INTO `re_property_advisors` (`id`, `advisor_user_id`, `created_at`, `property_id`, `status`) VALUES
(1, 2, '2026-02-15 04:49:50.136975', 2, 1),
(2, 2, '2026-02-15 08:37:04.733701', 3, 1),
(3, 2, '2026-02-15 08:40:21.090003', 4, 1),
(4, 2, '2026-02-15 16:28:30.822613', 5, 1),
(5, 2, '2026-02-17 12:40:24.040576', 6, 1),
(6, 2, '2026-02-17 12:40:40.150372', 7, 1),
(7, 2, '2026-02-17 12:47:12.395257', 8, 1),
(8, 2, '2026-02-20 10:24:15.404183', 9, 1),
(9, 2, '2026-02-21 14:28:29.325421', 10, 1),
(11, 2, '2026-02-23 14:50:35.060312', 13, 1),
(12, 3, '2026-02-23 14:52:26.568180', 14, 1),
(13, 2, '2026-02-23 15:04:33.856087', 15, 1);

-- --------------------------------------------------------

--
-- Table structure for table `re_property_amenities`
--

CREATE TABLE `re_property_amenities` (
  `property_amenity_id` bigint NOT NULL,
  `amenity_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `property_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `re_property_documents`
--

CREATE TABLE `re_property_documents` (
  `document_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `document_url` varchar(255) DEFAULT NULL,
  `property_id` bigint NOT NULL,
  `sort_order` int DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_property_documents`
--

INSERT INTO `re_property_documents` (`document_id`, `created_at`, `document_type`, `document_url`, `property_id`, `sort_order`, `status`) VALUES
(1, '2026-02-15 16:28:34.339695', ' SALE_DEED', 'properties/5/documents/_sale_deed/b3c52047-9a0c-4c8c-ac17-e0cc03448861-Hunasehalli-6.1.png', 5, 1, 1),
(2, '2026-02-17 12:40:27.951033', ' SALE_DEED', 'properties/6/documents/_sale_deed/fd0ab4a8-8c21-411b-92e4-11372940c2f8-Hunasehalli-6.1.png', 6, 1, 1),
(3, '2026-02-17 12:40:42.663364', ' SALE_DEED', 'properties/7/documents/_sale_deed/7ca3992d-f7e7-4092-aaec-eaf411cf931b-Hunasehalli-6.1.png', 7, 1, 1),
(4, '2026-02-17 12:47:15.012623', ' SALE_DEED', 'properties/8/documents/_sale_deed/871dd4e1-7170-47d9-90a5-f5c7dca3d70b-Hunasehalli-6.1.png', 8, 1, 1),
(7, '2026-02-22 09:31:14.800842', 'Brochure', 'properties/6/documents/brochure/c083285e-2f28-4739-9e0b-fd9ec13d7df5-Hoskote_Temporary_Construction_Estimate.pdf', 6, 1, 1),
(8, '2026-02-22 09:31:15.972704', 'Floor Plan', 'properties/6/documents/floor_plan/92226c3f-57b0-4721-8af8-3227fb4f06ed-SBI_Purple_Smart_Spend_Planner.pdf', 6, 2, 1),
(9, '2026-02-22 09:31:17.738379', 'Other', 'properties/6/documents/other/473c4be4-b13e-42fc-b99b-c2ea2a5759d7-Hoskote_Temporary_Construction_Estimate.pdf', 6, NULL, 1),
(10, '2026-02-22 09:31:18.773413', 'Other', 'properties/6/documents/other/b3e7fe4e-7528-4189-ab1a-524139f45267-SBI_Purple_Smart_Spend_Planner.pdf', 6, NULL, 1),
(11, '2026-02-23 14:50:38.717131', 'Other', 'properties/13/documents/other/368d3104-f8d9-445c-8946-cc4fa9c17b53-SBI_Purple_Smart_Spend_Planner.pdf', 13, 1, 1),
(12, '2026-02-23 14:52:30.420511', 'Other', 'properties/14/documents/other/aa1a8e53-b8a9-4c42-8e40-7b5db3b0a523-SBI_Purple_Smart_Spend_Planner.pdf', 14, 1, 1),
(13, '2026-02-23 15:04:36.936288', 'Brochure', 'properties/15/documents/brochure/82e26c41-adee-4d05-80ff-b4931826e422-SBI_Purple_Smart_Spend_Planner.pdf', 15, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `re_property_images`
--

CREATE TABLE `re_property_images` (
  `property_image_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `property_id` bigint DEFAULT NULL,
  `sort_order` int DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_property_images`
--

INSERT INTO `re_property_images` (`property_image_id`, `created_at`, `image_url`, `property_id`, `sort_order`, `status`) VALUES
(1, '2026-02-14 19:38:42.809269', 'projects/1/images/b7f92519-2549-49b8-ab67-8075e7edabc7-4.jpeg', 1, 1, 1),
(2, '2026-02-14 19:38:43.552764', 'projects/1/images/872510b9-7d12-4139-a6ba-f47a72fe7a67-1.jpeg', 1, 2, 1),
(3, '2026-02-15 04:49:54.658480', 'projects/2/images/4fa29781-651d-47a1-abc5-e3f49bd25077-1.jpeg', 2, 1, 1),
(4, '2026-02-15 04:49:55.517512', 'projects/2/images/1584042b-83a8-4cfd-a9b8-97fd83a3c85a-2.jpeg', 2, NULL, 1),
(5, '2026-02-15 08:37:05.651264', 'projects/3/images/74cb3e70-92a7-4578-9702-471b7e67992b-1.jpeg', 3, 1, 1),
(6, '2026-02-15 08:37:07.163797', 'projects/3/images/365385d3-b7f8-462e-ad7f-7050cc8c4408-2.jpeg', 3, NULL, 1),
(7, '2026-02-15 08:40:21.899513', 'projects/4/images/2d15728b-8e85-40d0-9d9b-08814cee696e-1.jpeg', 4, 1, 1),
(8, '2026-02-15 08:40:22.828443', 'projects/4/images/6bf85e3e-ec0c-4ea6-82f3-28e8e988e6e6-2.jpeg', 4, NULL, 1),
(9, '2026-02-15 16:28:32.851454', 'projects/5/images/08c35c52-91f5-4d36-bc88-b59a940445a7-1.jpeg', 5, 1, 1),
(10, '2026-02-15 16:28:33.643151', 'projects/5/images/3eac9845-ebf5-4d46-83c9-c11ed7eaadfa-2.jpeg', 5, NULL, 1),
(11, '2026-02-17 12:40:26.400436', 'projects/6/images/51779ecc-6395-4dc7-bd27-b6e8cd731889-4.jpeg', 6, 1, 1),
(12, '2026-02-17 12:40:27.232687', 'projects/6/images/ea602f2b-eea1-422b-932a-1b329dcb4f35-2.jpeg', 6, NULL, 1),
(13, '2026-02-17 12:40:40.957689', 'projects/7/images/bf5a7cb2-b72f-4524-930d-db07a8ec126f-4.jpeg', 7, 1, 1),
(14, '2026-02-17 12:40:41.747704', 'projects/7/images/78bed014-6475-497c-8b85-be9c9c490c06-2.jpeg', 7, NULL, 1),
(15, '2026-02-17 12:47:13.623394', 'projects/8/images/247c9e1d-9aae-40a2-aa6c-4fb020f8b660-4.jpeg', 8, 1, 1),
(16, '2026-02-17 12:47:14.297812', 'projects/8/images/327374c1-a344-4527-b593-27c08d007a07-2.jpeg', 8, NULL, 1),
(37, '2026-02-17 16:06:20.778155', 'properties/6/images/1bd02d51-fb1d-4b88-a310-494cb195e06b-4.jpeg', 6, 1, 1),
(38, '2026-02-17 16:06:21.726668', 'properties/6/images/0cc0d319-bf45-4922-bbbc-170d5b489ac3-4.jpeg', 6, NULL, 1),
(39, '2026-02-20 10:22:48.415163', 'properties/6/images/42191659-3dfc-4614-82c2-7b06aa324e06-4.jpeg', 6, 1, 1),
(40, '2026-02-20 10:22:49.426969', 'properties/6/images/c8aa5c5c-0caf-4221-868f-ab9b68961d48-2.jpeg', 6, NULL, 1),
(41, '2026-02-20 10:22:50.479115', 'properties/6/images/da454d7d-f45c-4638-8695-96bc76598ed1-4.jpeg', 6, NULL, 1),
(42, '2026-02-20 10:22:51.359375', 'properties/6/images/ac631a58-9e2c-416b-9a24-fba0dd9f17a7-2.jpeg', 6, NULL, 1),
(43, '2026-02-20 10:24:16.547877', 'projects/9/images/b2151ed7-9d3e-4f08-a019-329b3da515e7-4.jpeg', 9, 1, 1),
(44, '2026-02-20 10:24:17.231427', 'projects/9/images/ab4247db-78c3-4937-95f2-8ffb65c93e68-2.jpeg', 9, NULL, 1),
(45, '2026-02-21 14:28:36.961702', 'projects/10/images/1fef5ebf-75c1-407e-bfab-8737b1fa32fe-58752179_6_58752179_3_PropertyImage27-608376977487413_0_320.jpg', 10, 1, 1),
(46, '2026-02-21 14:28:38.406552', 'projects/10/images/aa0aef3d-db03-4794-bf68-b0afc4620ffe-160056540_15509935531_large.jpg', 10, 2, 1),
(49, '2026-02-23 14:50:35.831777', 'projects/13/images/6f1d8171-bb0d-4bd4-b3f1-6aa89f50ce47-58752179_6_58752179_3_PropertyImage27-608376977487413_0_320.jpg', 13, 1, 1),
(50, '2026-02-23 14:50:37.287931', 'projects/13/images/82967acb-bedd-42fc-9243-697d77e4e2d7-160056540_15509935531_large.jpg', 13, 2, 1),
(51, '2026-02-23 14:52:28.117215', 'projects/14/images/551ab6e0-dca0-4240-8800-c8e82e3dd845-58752179_6_58752179_3_PropertyImage27-608376977487413_0_320.jpg', 14, 1, 1),
(52, '2026-02-23 14:52:29.316569', 'projects/14/images/b1b3aa07-7259-4d08-8ce0-bbbd87be86e7-160056540_15509935531_large.jpg', 14, 2, 1),
(53, '2026-02-23 15:04:35.048900', 'projects/15/images/2a191f18-567c-4bb3-8bde-c511b182ad62-160056540_15509935531_large.jpg', 15, 1, 1),
(54, '2026-02-23 15:04:36.250487', 'projects/15/images/bff08f39-13dc-439b-b43b-21c0474b3218-58752179_6_58752179_3_PropertyImage27-608376977487413_0_320.jpg', 15, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `re_property_tags`
--

CREATE TABLE `re_property_tags` (
  `property_tag_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `property_id` bigint NOT NULL,
  `tag_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_property_tags`
--

INSERT INTO `re_property_tags` (`property_tag_id`, `created_at`, `is_active`, `property_id`, `tag_id`) VALUES
(3, '2026-02-17 12:40:43.688667', b'1', 7, 1),
(4, '2026-02-17 12:40:43.986187', b'1', 7, 2),
(5, '2026-02-17 12:47:15.808824', b'1', 8, 3),
(6, '2026-02-17 12:47:16.081031', b'1', 8, 5),
(28, '2026-02-20 10:24:18.050592', b'1', 9, 1),
(29, '2026-02-20 10:24:18.290330', b'1', 9, 2),
(31, '2026-02-22 09:31:20.111822', b'1', 6, 1),
(32, '2026-02-23 14:50:38.738422', b'1', 13, 1),
(33, '2026-02-23 14:52:30.426459', b'1', 14, 1),
(34, '2026-02-23 15:04:36.942822', b'1', 15, 1);

-- --------------------------------------------------------

--
-- Table structure for table `re_property_videos`
--

CREATE TABLE `re_property_videos` (
  `video_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `property_id` bigint NOT NULL,
  `sort_order` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `video_type` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_property_videos`
--

INSERT INTO `re_property_videos` (`video_id`, `created_at`, `property_id`, `sort_order`, `status`, `video_type`, `video_url`) VALUES
(1, '2026-02-15 04:49:55.752520', 2, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(2, '2026-02-15 08:37:07.395323', 3, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(3, '2026-02-15 08:40:23.059009', 4, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(4, '2026-02-15 16:28:34.622944', 5, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(5, '2026-02-15 16:36:53.777277', 1, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(6, '2026-02-17 12:40:28.220199', 6, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(7, '2026-02-17 12:40:42.935021', 7, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(8, '2026-02-17 12:47:15.251408', 8, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(18, '2026-02-17 16:06:21.051911', 6, 1, 1, ' YOUTUBE', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(19, '2026-02-20 10:22:49.676743', 6, 1, 1, '', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(20, '2026-02-20 10:24:17.569199', 9, 1, 1, '', ' https://www.youtube.com/watch?v=jPkBJY1KI_Q'),
(21, '2026-02-23 14:50:38.720635', 13, 1, 1, 'YOUTUBE', 'https://www.youtube.com/watch?v=RNMI0uqz5-8'),
(22, '2026-02-23 14:50:38.723147', 13, 2, 1, 'VIRTUAL_TOUR', 'https://www.youtube.com/watch?v=RNMI0uqz5-8'),
(23, '2026-02-23 14:52:30.422413', 14, 1, 1, 'YOUTUBE', 'https://www.youtube.com/watch?v=RNMI0uqz5-8'),
(24, '2026-02-23 14:52:30.424142', 14, 2, 1, 'VIRTUAL_TOUR', 'https://www.youtube.com/watch?v=RNMI0uqz5-8'),
(25, '2026-02-23 15:04:36.938494', 15, 1, 1, 'YOUTUBE', 'https://www.youtube.com/watch?v=RNMI0uqz5-8'),
(26, '2026-02-23 15:04:36.940042', 15, 2, 1, 'VIRTUAL_TOUR', 'https://www.youtube.com/watch?v=RNMI0uqz5-8');

-- --------------------------------------------------------

--
-- Table structure for table `re_tags`
--

CREATE TABLE `re_tags` (
  `tag_id` bigint NOT NULL,
  `status` int DEFAULT NULL,
  `tag_name` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `display_order` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `tag_code` varchar(255) NOT NULL,
  `tag_type` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_tags`
--

INSERT INTO `re_tags` (`tag_id`, `status`, `tag_name`, `created_at`, `display_order`, `is_active`, `tag_code`, `tag_type`, `updated_at`) VALUES
(1, 1, 'Featured', '2026-02-17 06:46:37.000000', 1, b'1', 'FEATURED', 'FEATURE', NULL),
(4, 1, 'Premium', '2026-02-17 06:46:37.000000', 2, b'1', 'PREMIUM', 'FEATURE', NULL),
(5, 1, 'Hot Deal', '2026-02-17 06:46:37.000000', 3, b'1', 'HOT_DEAL', 'FEATURE', NULL),
(6, 1, 'New Launch', '2026-02-17 06:46:37.000000', 4, b'1', 'NEW_LAUNCH', 'FEATURE', NULL),
(7, 1, 'Ready To Move', '2026-02-17 06:46:37.000000', 5, b'1', 'READY_TO_MOVE', 'FEATURE', NULL),
(8, 1, 'Limited Units', '2026-02-17 06:46:37.000000', 6, b'1', 'LIMITED_UNITS', 'FEATURE', NULL),
(9, 1, 'Luxury Villa', '2026-02-17 06:46:37.000000', 1, b'1', 'LUXURY_VILLA', 'PROPERTY_TYPE', NULL),
(10, 1, 'Family Home', '2026-02-17 06:46:37.000000', 2, b'1', 'FAMILY_HOME', 'PROPERTY_TYPE', NULL),
(11, 1, 'Apartment', '2026-02-17 06:46:37.000000', 3, b'1', 'APARTMENT', 'PROPERTY_TYPE', NULL),
(12, 1, 'Penthouse', '2026-02-17 06:46:37.000000', 4, b'1', 'PENTHOUSE', 'PROPERTY_TYPE', NULL),
(13, 1, 'Farmhouse', '2026-02-17 06:46:37.000000', 5, b'1', 'FARMHOUSE', 'PROPERTY_TYPE', NULL),
(14, 1, 'Row House', '2026-02-17 06:46:37.000000', 6, b'1', 'ROW_HOUSE', 'PROPERTY_TYPE', NULL),
(15, 1, 'Studio', '2026-02-17 06:46:37.000000', 7, b'1', 'STUDIO', 'PROPERTY_TYPE', NULL),
(16, 1, 'Best Value', '2026-02-17 06:46:37.000000', 1, b'1', 'BEST_VALUE', 'VALUE', NULL),
(17, 1, 'Affordable', '2026-02-17 06:46:37.000000', 2, b'1', 'AFFORDABLE', 'VALUE', NULL),
(18, 1, 'Investment Opportunity', '2026-02-17 06:46:37.000000', 3, b'1', 'INVESTMENT_OPPORTUNITY', 'VALUE', NULL),
(19, 1, 'High ROI', '2026-02-17 06:46:37.000000', 4, b'1', 'HIGH_ROI', 'VALUE', NULL),
(20, 1, 'Price Reduced', '2026-02-17 06:46:37.000000', 5, b'1', 'PRICE_REDUCED', 'VALUE', NULL),
(21, 1, 'Swimming Pool', '2026-02-17 06:46:37.000000', 1, b'1', 'SWIMMING_POOL', 'AMENITY', NULL),
(22, 1, 'Club House', '2026-02-17 06:46:37.000000', 2, b'1', 'CLUB_HOUSE', 'AMENITY', NULL),
(23, 1, 'Gym', '2026-02-17 06:46:37.000000', 3, b'1', 'GYM', 'AMENITY', NULL),
(24, 1, 'Garden View', '2026-02-17 06:46:37.000000', 4, b'1', 'GARDEN_VIEW', 'AMENITY', NULL),
(25, 1, 'Gated Community', '2026-02-17 06:46:37.000000', 5, b'1', 'GATED_COMMUNITY', 'AMENITY', NULL),
(26, 1, '24x7 Security', '2026-02-17 06:46:37.000000', 6, b'1', 'SECURITY_24_7', 'AMENITY', NULL),
(27, 1, 'Power Backup', '2026-02-17 06:46:37.000000', 7, b'1', 'POWER_BACKUP', 'AMENITY', NULL),
(28, 1, 'Children Play Area', '2026-02-17 06:46:37.000000', 8, b'1', 'PLAY_AREA', 'AMENITY', NULL),
(29, 1, 'East Facing', '2026-02-17 06:46:37.000000', 1, b'1', 'EAST_FACING', 'FACING', NULL),
(30, 1, 'West Facing', '2026-02-17 06:46:37.000000', 2, b'1', 'WEST_FACING', 'FACING', NULL),
(31, 1, 'North Facing', '2026-02-17 06:46:37.000000', 3, b'1', 'NORTH_FACING', 'FACING', NULL),
(32, 1, 'South Facing', '2026-02-17 06:46:37.000000', 4, b'1', 'SOUTH_FACING', 'FACING', NULL),
(33, 1, 'Under Construction', '2026-02-17 06:46:37.000000', 1, b'1', 'UNDER_CONSTRUCTION', 'PROJECT_STATUS', NULL),
(34, 1, 'Completed', '2026-02-17 06:46:37.000000', 2, b'1', 'COMPLETED', 'PROJECT_STATUS', NULL),
(35, 1, 'Pre Launch', '2026-02-17 06:46:37.000000', 3, b'1', 'PRE_LAUNCH', 'PROJECT_STATUS', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `re_unit_types`
--

CREATE TABLE `re_unit_types` (
  `unit_type_id` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `re_unit_types`
--

INSERT INTO `re_unit_types` (`unit_type_id`, `created_at`, `description`, `name`, `status`, `updated_at`) VALUES
(1, '2025-12-24 03:32:23.000000', 'Studio apartment', 'Studio', 1, NULL),
(2, '2025-12-24 03:32:23.000000', '1 Bedroom Hall Kitchen', '1 BHK', 1, NULL),
(3, '2025-12-24 03:32:23.000000', '2 Bedroom Hall Kitchen', '2 BHK', 1, NULL),
(4, '2025-12-24 03:32:23.000000', '3 Bedroom Hall Kitchen', '3 BHK', 1, NULL),
(5, '2025-12-24 03:32:23.000000', '4 Bedroom Hall Kitchen', '4 BHK', 1, NULL),
(6, '2025-12-24 03:32:23.000000', 'Luxury penthouse unit', 'Penthouse', 1, NULL),
(7, '2025-12-24 03:32:23.000000', 'Independent villa unit', 'Villa', 1, NULL),
(8, '2025-12-24 03:32:23.000000', 'Row house unit', 'Row House', 1, NULL),
(9, '2025-12-24 03:32:23.000000', 'Duplex residential unit', 'Duplex', 1, NULL),
(10, '2025-12-24 03:32:23.000000', 'Empty Area', 'Plot', 1, NULL),
(11, '2025-12-24 03:32:23.000000', 'Agriculture Land', 'Agriculture Land', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint NOT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'RE_ADVISOR'),
(3, 'RE_MANAGER');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` bigint NOT NULL,
  `amount` double NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text NOT NULL,
  `metadata` json DEFAULT NULL,
  `reference_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `astro` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(200) DEFAULT NULL,
  `first_time_login` int DEFAULT NULL,
  `is_active` int DEFAULT NULL,
  `journel` int DEFAULT NULL,
  `last_name` varchar(200) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `position` varchar(150) DEFAULT NULL,
  `profile_pic` tinyblob,
  `shopping` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_type_id` bigint NOT NULL,
  `profile_pic_url` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `astro`, `created_at`, `email`, `first_name`, `first_time_login`, `is_active`, `journel`, `last_name`, `password`, `phone_number`, `position`, `profile_pic`, `shopping`, `updated_at`, `user_type_id`, `profile_pic_url`) VALUES
(1, 1, '2025-12-23 13:57:43.000000', 'srinivas.babu@zohomail.in', 'Super', 1, 1, 1, 'User', 'Admin', '1234567890', 'Administrator', NULL, 1, '2025-12-23 13:57:42.762000', 1, NULL),
(2, 1, '2026-01-29 07:46:24.000000', 'Shivam@sthirvaa.com', 'Shivam', 1, 1, 1, 'Advisor', '$2a$12$cIvjdImf5xkN2z23GCN1nOn1gzGrGQ2aL2uKsr9kG8MSzoFAgoFOu', '9035327942', 'Property Advisor', NULL, 1, '2026-01-29 07:46:24.000000', 2, NULL),
(3, 1, '2026-01-29 07:46:24.000000', 'Babu@Sthirvaa.com', 'Babu', 1, 1, 1, 'Narayan', '$2a$12$cIvjdImf5xkN2z23GCN1nOn1gzGrGQ2aL2uKsr9kG8MSzoFAgoFOu', '8765656522', 'Senior Property Advisor', NULL, 1, '2026-03-02 15:59:52.234000', 2, NULL),
(4, 1, '2026-01-29 07:46:24.000000', 'rohit@company.com', 'Rohit', 1, 1, 1, 'Verma', '$2a$12$cIvjdImf5xkN2z23GCN1nOn1gzGrGQ2aL2uKsr9kG8MSzoFAgoFOu', '8765656533', 'Property Advisor', NULL, 1, '2026-01-29 07:46:24.000000', 3, NULL),
(5, 1, '2026-01-29 07:46:24.000000', 'Srini@sthirvaa.com', 'Srinivas', 1, 1, 1, 'babu', '$2a$12$cIvjdImf5xkN2z23GCN1nOn1gzGrGQ2aL2uKsr9kG8MSzoFAgoFOu', '8765656522', 'Senior Property Advisor', NULL, 1, '2026-01-29 07:46:24.000000', 2, NULL),
(6, NULL, '2026-03-02 15:57:29.850000', 'Ryan@sthirvaa.com', 'Ryan', NULL, 1, NULL, 'Varma', 'securepassword123', '9110656001', 'Property Advisor', NULL, NULL, '2026-03-02 15:57:29.850000', 2, 'https://example.com/images/shivam.png');

-- --------------------------------------------------------

--
-- Table structure for table `user_privileges`
--

CREATE TABLE `user_privileges` (
  `id` int NOT NULL,
  `privilege_id` int NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_types`
--

CREATE TABLE `user_types` (
  `id` bigint NOT NULL,
  `code` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_types`
--

INSERT INTO `user_types` (`id`, `code`, `name`, `description`, `is_active`, `created_at`) VALUES
(1, 'ADMIN', 'Administrator', 'Full system access', 1, '2026-01-29 13:53:36'),
(2, 'ADVISOR', 'Property Advisor', 'Handles assigned leads and customers', 1, '2026-01-29 13:53:36'),
(3, 'PROJECT_MANAGER', 'Project Manager', 'Manages projects and advisor assignments', 1, '2026-01-29 13:53:36'),
(4, 'CRM_AGENT', 'CRM Agent', 'Pre-sales and lead qualification', 1, '2026-01-29 13:53:36');

-- --------------------------------------------------------

--
-- Table structure for table `vendors`
--

CREATE TABLE `vendors` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `delivery_available` bit(1) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `rating` double DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `vouchers`
--

CREATE TABLE `vouchers` (
  `id` bigint NOT NULL,
  `amount` double NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL,
  `expiry_date` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `is_used` bit(1) NOT NULL,
  `title` varchar(255) NOT NULL,
  `used_at` datetime(6) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `voucher_code` varchar(255) NOT NULL,
  `voucher_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `wallet`
--

CREATE TABLE `wallet` (
  `id` int NOT NULL,
  `available_balance` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `investments`
--
ALTER TABLE `investments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `investment_types`
--
ALTER TABLE `investment_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `privileges`
--
ALTER TABLE `privileges`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  ADD KEY `FKs6kdu75k7ub4s95ydsr52p59s` (`vendor_id`);

--
-- Indexes for table `re_amenities`
--
ALTER TABLE `re_amenities`
  ADD PRIMARY KEY (`amenity_id`),
  ADD KEY `idx_amenities_name` (`name`),
  ADD KEY `idx_amenities_status` (`status`);

--
-- Indexes for table `re_construction_status`
--
ALTER TABLE `re_construction_status`
  ADD PRIMARY KEY (`construction_status_id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `re_developers`
--
ALTER TABLE `re_developers`
  ADD PRIMARY KEY (`developer_id`),
  ADD KEY `idx_developers_name` (`developer_name`),
  ADD KEY `idx_developers_status` (`status`);

--
-- Indexes for table `re_leads`
--
ALTER TABLE `re_leads`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_leads_advisor_status` (`advisor_id`,`status`),
  ADD KEY `idx_leads_phone` (`phone`),
  ADD KEY `idx_leads_created_at` (`created_at`);

--
-- Indexes for table `re_lead_routing_config`
--
ALTER TABLE `re_lead_routing_config`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_rlrc_fixed_advisor` (`fixed_advisor_id`);

--
-- Indexes for table `re_locations`
--
ALTER TABLE `re_locations`
  ADD PRIMARY KEY (`location_id`),
  ADD KEY `idx_locations_project_id` (`project_id`),
  ADD KEY `idx_locations_city` (`city`),
  ADD KEY `idx_locations_lat_lng` (`latitude`,`longitude`);

--
-- Indexes for table `re_otp_verification`
--
ALTER TABLE `re_otp_verification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_re_otp_phone` (`phone`),
  ADD KEY `idx_re_otp_verified` (`is_verified`);

--
-- Indexes for table `re_projects`
--
ALTER TABLE `re_projects`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `fk_project_construction_status` (`construction_status_id`),
  ADD KEY `idx_projects_developer_id` (`developer_id`),
  ADD KEY `idx_projects_project_type_id` (`project_type_id`),
  ADD KEY `idx_projects_status` (`status`),
  ADD KEY `idx_projects_rera_number` (`rera_number`),
  ADD KEY `idx_projects_created_at` (`created_at`),
  ADD KEY `idx_projects_possession_date` (`possession_date`);

--
-- Indexes for table `re_project_advisors`
--
ALTER TABLE `re_project_advisors`
  ADD PRIMARY KEY (`project_advisor_id`),
  ADD UNIQUE KEY `UK5dmvf6h9vmd51jg9rwls4ofnq` (`project_id`);

--
-- Indexes for table `re_project_amenities`
--
ALTER TABLE `re_project_amenities`
  ADD PRIMARY KEY (`project_amenity_id`),
  ADD UNIQUE KEY `uk_project_amenity` (`project_id`,`amenity_id`),
  ADD KEY `idx_proj_amenities_project_id` (`project_id`),
  ADD KEY `idx_proj_amenities_amenity_id` (`amenity_id`);

--
-- Indexes for table `re_project_brochures`
--
ALTER TABLE `re_project_brochures`
  ADD PRIMARY KEY (`brochure_id`),
  ADD KEY `idx_brochures_project_id` (`project_id`),
  ADD KEY `idx_brochures_sort_order` (`sort_order`);

--
-- Indexes for table `re_project_documents`
--
ALTER TABLE `re_project_documents`
  ADD PRIMARY KEY (`project_document_id`);

--
-- Indexes for table `re_project_images`
--
ALTER TABLE `re_project_images`
  ADD PRIMARY KEY (`project_image_id`),
  ADD KEY `idx_images_project_id` (`project_id`),
  ADD KEY `idx_images_sort_order` (`sort_order`),
  ADD KEY `idx_images_project_sort` (`project_id`,`sort_order`);

--
-- Indexes for table `re_project_locations`
--
ALTER TABLE `re_project_locations`
  ADD PRIMARY KEY (`location_id`),
  ADD KEY `idx_project_location_project_id` (`project_id`),
  ADD KEY `idx_project_location_city` (`city`),
  ADD KEY `idx_project_location_lat_lng` (`latitude`,`longitude`),
  ADD KEY `idx_project_location_type` (`location_type`),
  ADD KEY `idx_project_area` (`area`),
  ADD KEY `idx_locations_project` (`project_id`);

--
-- Indexes for table `re_project_tags`
--
ALTER TABLE `re_project_tags`
  ADD PRIMARY KEY (`project_tag_id`),
  ADD UNIQUE KEY `UKkg85u2683sgcynsypmc3uj427` (`project_id`,`tag_id`);

--
-- Indexes for table `re_project_types`
--
ALTER TABLE `re_project_types`
  ADD PRIMARY KEY (`project_type_id`),
  ADD KEY `idx_project_types_name` (`name`),
  ADD KEY `idx_project_types_status` (`status`);

--
-- Indexes for table `re_project_unit_types`
--
ALTER TABLE `re_project_unit_types`
  ADD PRIMARY KEY (`project_unit_type_id`),
  ADD KEY `idx_unit_types_project_id` (`project_id`),
  ADD KEY `idx_unit_types_unit_type_id` (`unit_type_id`),
  ADD KEY `idx_unit_types_price_range` (`price_min`,`price_max`),
  ADD KEY `idx_unit_types_size` (`size_sqft`),
  ADD KEY `idx_units_project` (`project_id`);

--
-- Indexes for table `re_project_videos`
--
ALTER TABLE `re_project_videos`
  ADD PRIMARY KEY (`project_video_id`);

--
-- Indexes for table `re_properties`
--
ALTER TABLE `re_properties`
  ADD PRIMARY KEY (`property_id`),
  ADD KEY `idx_property_active_verified_created` (`is_active`,`is_verified`,`created_at`),
  ADD KEY `idx_property_active_verified` (`is_active`,`is_verified`),
  ADD KEY `idx_property_created_at` (`created_at`),
  ADD KEY `idx_property_created` (`created_at`);

--
-- Indexes for table `re_property_advisors`
--
ALTER TABLE `re_property_advisors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `re_property_amenities`
--
ALTER TABLE `re_property_amenities`
  ADD PRIMARY KEY (`property_amenity_id`),
  ADD UNIQUE KEY `UKidgc8twmqehf5icqvlors97i7` (`property_id`,`amenity_id`);

--
-- Indexes for table `re_property_documents`
--
ALTER TABLE `re_property_documents`
  ADD PRIMARY KEY (`document_id`);

--
-- Indexes for table `re_property_images`
--
ALTER TABLE `re_property_images`
  ADD PRIMARY KEY (`property_image_id`),
  ADD KEY `idx_property_images_property_status_sort` (`property_id`,`status`,`sort_order`),
  ADD KEY `idx_images_property_status` (`property_id`,`status`),
  ADD KEY `idx_images_sort` (`property_id`,`sort_order`),
  ADD KEY `idx_property_images_property_status` (`property_id`,`status`);

--
-- Indexes for table `re_property_tags`
--
ALTER TABLE `re_property_tags`
  ADD PRIMARY KEY (`property_tag_id`),
  ADD UNIQUE KEY `UKmosr5v7lpo7r01sts5mto75xq` (`property_id`,`tag_id`);

--
-- Indexes for table `re_property_videos`
--
ALTER TABLE `re_property_videos`
  ADD PRIMARY KEY (`video_id`);

--
-- Indexes for table `re_tags`
--
ALTER TABLE `re_tags`
  ADD PRIMARY KEY (`tag_id`),
  ADD UNIQUE KEY `UKh8dpadjbq94lsmllovlfhn8oj` (`tag_name`),
  ADD UNIQUE KEY `UK4vl0lq1k24cgofa993xrgcrah` (`tag_code`);

--
-- Indexes for table `re_unit_types`
--
ALTER TABLE `re_unit_types`
  ADD PRIMARY KEY (`unit_type_id`),
  ADD KEY `idx_unit_types_name` (`name`),
  ADD KEY `idx_unit_types_status` (`status`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_users_user_type` (`user_type_id`);

--
-- Indexes for table `user_privileges`
--
ALTER TABLE `user_privileges`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8ht5860cyq8fsdbceqwnq517e` (`privilege_id`),
  ADD KEY `FK4pjq82mghfm4ec8innfnfetrm` (`user_id`);

--
-- Indexes for table `user_types`
--
ALTER TABLE `user_types`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `vendors`
--
ALTER TABLE `vendors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vouchers`
--
ALTER TABLE `vouchers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKhvqsc8qffpt5okjmyot3a4b77` (`voucher_code`);

--
-- Indexes for table `wallet`
--
ALTER TABLE `wallet`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `investments`
--
ALTER TABLE `investments`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `investment_types`
--
ALTER TABLE `investment_types`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `privileges`
--
ALTER TABLE `privileges`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `re_amenities`
--
ALTER TABLE `re_amenities`
  MODIFY `amenity_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `re_construction_status`
--
ALTER TABLE `re_construction_status`
  MODIFY `construction_status_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `re_developers`
--
ALTER TABLE `re_developers`
  MODIFY `developer_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `re_leads`
--
ALTER TABLE `re_leads`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `re_lead_routing_config`
--
ALTER TABLE `re_lead_routing_config`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `re_locations`
--
ALTER TABLE `re_locations`
  MODIFY `location_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `re_otp_verification`
--
ALTER TABLE `re_otp_verification`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `re_projects`
--
ALTER TABLE `re_projects`
  MODIFY `project_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- AUTO_INCREMENT for table `re_project_advisors`
--
ALTER TABLE `re_project_advisors`
  MODIFY `project_advisor_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `re_project_amenities`
--
ALTER TABLE `re_project_amenities`
  MODIFY `project_amenity_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=683;

--
-- AUTO_INCREMENT for table `re_project_brochures`
--
ALTER TABLE `re_project_brochures`
  MODIFY `brochure_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `re_project_documents`
--
ALTER TABLE `re_project_documents`
  MODIFY `project_document_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `re_project_images`
--
ALTER TABLE `re_project_images`
  MODIFY `project_image_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;

--
-- AUTO_INCREMENT for table `re_project_locations`
--
ALTER TABLE `re_project_locations`
  MODIFY `location_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=136;

--
-- AUTO_INCREMENT for table `re_project_tags`
--
ALTER TABLE `re_project_tags`
  MODIFY `project_tag_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `re_project_types`
--
ALTER TABLE `re_project_types`
  MODIFY `project_type_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `re_project_unit_types`
--
ALTER TABLE `re_project_unit_types`
  MODIFY `project_unit_type_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=232;

--
-- AUTO_INCREMENT for table `re_project_videos`
--
ALTER TABLE `re_project_videos`
  MODIFY `project_video_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `re_properties`
--
ALTER TABLE `re_properties`
  MODIFY `property_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `re_property_advisors`
--
ALTER TABLE `re_property_advisors`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `re_property_amenities`
--
ALTER TABLE `re_property_amenities`
  MODIFY `property_amenity_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `re_property_documents`
--
ALTER TABLE `re_property_documents`
  MODIFY `document_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `re_property_images`
--
ALTER TABLE `re_property_images`
  MODIFY `property_image_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `re_property_tags`
--
ALTER TABLE `re_property_tags`
  MODIFY `property_tag_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `re_property_videos`
--
ALTER TABLE `re_property_videos`
  MODIFY `video_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `re_tags`
--
ALTER TABLE `re_tags`
  MODIFY `tag_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `re_unit_types`
--
ALTER TABLE `re_unit_types`
  MODIFY `unit_type_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user_privileges`
--
ALTER TABLE `user_privileges`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_types`
--
ALTER TABLE `user_types`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vendors`
--
ALTER TABLE `vendors`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vouchers`
--
ALTER TABLE `vouchers`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `wallet`
--
ALTER TABLE `wallet`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `FKs6kdu75k7ub4s95ydsr52p59s` FOREIGN KEY (`vendor_id`) REFERENCES `vendors` (`id`);

--
-- Constraints for table `re_leads`
--
ALTER TABLE `re_leads`
  ADD CONSTRAINT `fk_leads_advisor` FOREIGN KEY (`advisor_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `re_lead_routing_config`
--
ALTER TABLE `re_lead_routing_config`
  ADD CONSTRAINT `fk_rlrc_fixed_advisor` FOREIGN KEY (`fixed_advisor_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `re_projects`
--
ALTER TABLE `re_projects`
  ADD CONSTRAINT `fk_project_construction_status` FOREIGN KEY (`construction_status_id`) REFERENCES `re_construction_status` (`construction_status_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_types` (`id`);

--
-- Constraints for table `user_privileges`
--
ALTER TABLE `user_privileges`
  ADD CONSTRAINT `FK4pjq82mghfm4ec8innfnfetrm` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK8ht5860cyq8fsdbceqwnq517e` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
