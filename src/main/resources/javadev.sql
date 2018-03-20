-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 20 Mar 2018, 12:11
-- Wersja serwera: 10.1.30-MariaDB
-- Wersja PHP: 5.6.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `javadev`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lectures`
--

CREATE TABLE `lectures` (
  `lecture_id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `localization` varchar(255) DEFAULT NULL,
  `topic` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `lectures`
--

INSERT INTO `lectures` (`lecture_id`, `date`, `localization`, `topic`) VALUES
(1, '2017-10-10 00:00:00', 'Rzeszow WSIZ', 'Java Basic'),
(2, '2017-10-20 00:00:00', 'Rzeszow WSIZ', 'Spring Framework'),
(3, '2018-01-01 00:00:00', 'Online', 'Docker'),
(4, '2018-03-07 18:00:00', 'Rzeszow Polibuda', 'HTML');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lectures_students`
--

CREATE TABLE `lectures_students` (
  `lecture_student_id` bigint(20) NOT NULL,
  `grade` double DEFAULT NULL,
  `lecture_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `lectures_students`
--

INSERT INTO `lectures_students` (`lecture_student_id`, `grade`, `lecture_id`, `student_id`) VALUES
(1, 4, 1, 1),
(2, 3.5, 1, 2),
(3, NULL, 1, 3),
(4, NULL, 2, 1),
(5, NULL, 2, 2),
(6, NULL, 2, 3),
(7, NULL, 3, 1),
(8, NULL, 3, 2),
(9, NULL, 3, 3),
(11, NULL, 4, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `students`
--

CREATE TABLE `students` (
  `student_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `students`
--

INSERT INTO `students` (`student_id`, `email`, `first_name`, `last_name`) VALUES
(1, 'frodo@shire.me', 'Frodo', 'Baggins'),
(2, 'sam@shire.me', 'Samwise', 'Gamgee'),
(3, 'legolas@ithilien.me', 'Legolas', 'son of Thranduil');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `lectures`
--
ALTER TABLE `lectures`
  ADD PRIMARY KEY (`lecture_id`);

--
-- Indexes for table `lectures_students`
--
ALTER TABLE `lectures_students`
  ADD PRIMARY KEY (`lecture_student_id`),
  ADD KEY `FKkovs15n44h9blv2etuun107sl` (`lecture_id`),
  ADD KEY `FKpo8acdilrln7d5oin554535v9` (`student_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `lectures`
--
ALTER TABLE `lectures`
  MODIFY `lecture_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `lectures_students`
--
ALTER TABLE `lectures_students`
  MODIFY `lecture_student_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT dla tabeli `students`
--
ALTER TABLE `students`
  MODIFY `student_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `lectures_students`
--
ALTER TABLE `lectures_students`
  ADD CONSTRAINT `FKkovs15n44h9blv2etuun107sl` FOREIGN KEY (`lecture_id`) REFERENCES `lectures` (`lecture_id`),
  ADD CONSTRAINT `FKpo8acdilrln7d5oin554535v9` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
