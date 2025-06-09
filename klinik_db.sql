-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 09, 2025 at 04:55 AM
-- Server version: 8.4.3
-- PHP Version: 8.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `klinik_db`
--
CREATE DATABASE klinik_db;
USE klinik_db;
-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `idAdmin` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `idUser` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `idDokter` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `spesialisasi` varchar(50) NOT NULL,
  `idUser` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dokter`
--

INSERT INTO `dokter` (`idDokter`, `nama`, `spesialisasi`, `idUser`) VALUES
('DOK001', 'Dr. Fadli', 'Umum', 'USR002'),
('DOK003', 'DR. Supri', 'Kayang', 'USR002'),
('DOK005', 'Nazwa', 'Jantung', 'USR002'),
('DOK008', 'Julia', 'Jantung', 'USR003'),
('DOK019', 'Nazwa', 'Jantung', 'USR002'),
('DOK111', 'kirana', 'Otak', 'USR004'),
('DOK120', 'ZIZI', 'Tulang', 'USR005');

--
-- Triggers `dokter`
--
DELIMITER $$
CREATE TRIGGER `insert_user_dokter` BEFORE INSERT ON `dokter` FOR EACH ROW BEGIN
    DECLARE new_id VARCHAR(10);
    DECLARE new_username VARCHAR(50);
    DECLARE suffix INT DEFAULT 0;
    
    -- Generate ID User baru jika tidak disertakan
    IF NEW.idUser IS NULL OR NEW.idUser = '' THEN
        SET new_id = CONCAT('USR', LPAD((SELECT COUNT(*) FROM user) + 1, 3, '0'));
        SET NEW.idUser = new_id;
    END IF;
    
    -- Generate username
    SET new_username = LOWER(REPLACE(NEW.nama, ' ', '_'));
    WHILE EXISTS (SELECT 1 FROM user WHERE username = new_username) DO
        SET suffix = suffix + 1;
        SET new_username = CONCAT(LOWER(REPLACE(NEW.nama, ' ', '_')), suffix);
    END WHILE;
    
    -- Insert user baru
    INSERT INTO user (idUser, username, password, role)
    VALUES (NEW.idUser, new_username, '12345', 'dokter');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `jadwal`
--

CREATE TABLE `jadwal` (
  `idJadwal` varchar(10) NOT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat','Sabtu','Minggu') NOT NULL,
  `jamMulai` time NOT NULL,
  `jamBerakhir` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jadwal`
--

INSERT INTO `jadwal` (`idJadwal`, `hari`, `jamMulai`, `jamBerakhir`) VALUES
('JD002', 'Jumat', '08:00:00', '16:00:00'),
('JD003', 'Jumat', '08:00:00', '16:00:00'),
('JDL001', 'Senin', '08:00:00', '12:00:00'),
('JDL002', 'Selasa', '13:00:00', '17:00:00'),
('JDL003', 'Rabu', '08:00:00', '12:00:00'),
('JDL004', 'Kamis', '10:00:00', '14:00:00'),
('JDL005', 'Jumat', '09:00:00', '12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `jadwaldokter`
--

CREATE TABLE `jadwaldokter` (
  `idJadwalDokter` varchar(10) NOT NULL,
  `idDokter` varchar(10) NOT NULL,
  `idJadwal` varchar(10) NOT NULL,
  `idRuangan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jadwaldokter`
--

INSERT INTO `jadwaldokter` (`idJadwalDokter`, `idDokter`, `idJadwal`, `idRuangan`) VALUES
('JD001', 'DOK001', 'JDL001', 'RU003'),
('JD002', 'DOK019', 'JD002', 'RU004'),
('JD003', 'DOK003', 'JD003', 'RU002');

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `idObat` varchar(10) NOT NULL,
  `namaObat` varchar(100) NOT NULL,
  `stok` int NOT NULL DEFAULT '0',
  `harga` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`idObat`, `namaObat`, `stok`, `harga`) VALUES
('OBT001', 'Vitamin C', 10, 15000.00),
('OBT002', 'Amoxicillin', 80, 7500.00),
('OBT003', 'Ibuprofen', 50, 6500.00);

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE `pasien` (
  `idPasien` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` text,
  `telepon` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`idPasien`, `nama`, `alamat`, `telepon`) VALUES
('PSN001', 'Rina Marlina', 'Jl. Mawar No. 7', '081234567890'),
('PSN002', 'Agus Salim', 'Jl. Melati No. 5', '082345678901'),
('PSN003', 'Dewi Ayu', 'Jl. Anggrek No. 9', '081345678901'),
('PSN004', 'Fajar Nugroho', 'Jl. Kenanga No. 11', '083456789012'),
('PSN005', 'Wulan Sari', 'Jl. Sakura No. 15', '081567890123'),
('PSN006', 'Lia', 'Sumedang', '08111868115'),
('PSN007', 'Ridwan', 'Karawang', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `resep`
--

CREATE TABLE `resep` (
  `idResep` varchar(10) NOT NULL,
  `penyakit` varchar(100) NOT NULL,
  `tanggal` date NOT NULL,
  `idPasien` varchar(10) NOT NULL,
  `idDokter` varchar(10) NOT NULL,
  `idObat` varchar(10) NOT NULL,
  `status` enum('ditebus','belum ditebus') NOT NULL DEFAULT 'belum ditebus'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `resep`
--

INSERT INTO `resep` (`idResep`, `penyakit`, `tanggal`, `idPasien`, `idDokter`, `idObat`, `status`) VALUES
('RSP001', 'Demam', '2025-06-09', 'PSN004', 'DOK008', 'OBT002', 'belum ditebus');

-- --------------------------------------------------------

--
-- Table structure for table `ruangan`
--

CREATE TABLE `ruangan` (
  `idRuangan` varchar(10) NOT NULL,
  `namaRuangan` varchar(50) NOT NULL,
  `lantai` int NOT NULL,
  `kapasitas` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ruangan`
--

INSERT INTO `ruangan` (`idRuangan`, `namaRuangan`, `lantai`, `kapasitas`) VALUES
('RU001', 'Ruang Umum 1', 1, 2),
('RU002', 'Ruang Gigi 2', 1, 1),
('RU003', 'Ruang Anak 3', 2, 2),
('RU004', 'Ruang Kandungan 4', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idUser` varchar(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','dokter','staff') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `username`, `password`, `role`) VALUES
('USR001', 'admin', '12345', 'admin'),
('USR002', 'dokter', '12345', 'dokter'),
('USR003', 'julia', '12345', 'dokter'),
('USR004', 'kirana', '12345', 'dokter'),
('USR005', 'zizi', '12345', 'dokter');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idAdmin`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`idDokter`),
  ADD KEY `idUser` (`idUser`);

--
-- Indexes for table `jadwal`
--
ALTER TABLE `jadwal`
  ADD PRIMARY KEY (`idJadwal`);

--
-- Indexes for table `jadwaldokter`
--
ALTER TABLE `jadwaldokter`
  ADD PRIMARY KEY (`idJadwalDokter`),
  ADD KEY `idDokter` (`idDokter`),
  ADD KEY `idJadwal` (`idJadwal`),
  ADD KEY `idRuangan` (`idRuangan`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`idObat`);

--
-- Indexes for table `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`idPasien`);

--
-- Indexes for table `resep`
--
ALTER TABLE `resep`
  ADD PRIMARY KEY (`idResep`),
  ADD KEY `idPasien` (`idPasien`),
  ADD KEY `idDokter` (`idDokter`),
  ADD KEY `idObat` (`idObat`);

--
-- Indexes for table `ruangan`
--
ALTER TABLE `ruangan`
  ADD PRIMARY KEY (`idRuangan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE;

--
-- Constraints for table `dokter`
--
ALTER TABLE `dokter`
  ADD CONSTRAINT `dokter_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE;

--
-- Constraints for table `jadwaldokter`
--
ALTER TABLE `jadwaldokter`
  ADD CONSTRAINT `jadwaldokter_ibfk_1` FOREIGN KEY (`idDokter`) REFERENCES `dokter` (`idDokter`) ON DELETE CASCADE,
  ADD CONSTRAINT `jadwaldokter_ibfk_2` FOREIGN KEY (`idJadwal`) REFERENCES `jadwal` (`idJadwal`),
  ADD CONSTRAINT `jadwaldokter_ibfk_3` FOREIGN KEY (`idRuangan`) REFERENCES `ruangan` (`idRuangan`);

--
-- Constraints for table `resep`
--
ALTER TABLE `resep`
  ADD CONSTRAINT `fk_resep_obat` FOREIGN KEY (`idObat`) REFERENCES `obat` (`idObat`) ON DELETE RESTRICT,
  ADD CONSTRAINT `idObat` FOREIGN KEY (`idObat`) REFERENCES `obat` (`idObat`) ON DELETE RESTRICT,
  ADD CONSTRAINT `resep_ibfk_1` FOREIGN KEY (`idPasien`) REFERENCES `pasien` (`idPasien`),
  ADD CONSTRAINT `resep_ibfk_2` FOREIGN KEY (`idDokter`) REFERENCES `dokter` (`idDokter`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
