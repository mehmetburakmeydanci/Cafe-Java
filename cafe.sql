-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 28 Ara 2023, 08:05:28
-- Sunucu sürümü: 10.4.28-MariaDB
-- PHP Sürümü: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `cafe`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `giris`
--

CREATE TABLE `giris` (
  `P_ID` int(11) NOT NULL,
  `sifre` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `giris`
--

INSERT INTO `giris` (`P_ID`, `sifre`) VALUES
(4, 'Şef432'),
(5, 'Müdür567'),
(9, 'Şef890');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `menu`
--

CREATE TABLE `menu` (
  `menu_ID` int(11) NOT NULL,
  `Menu_Ad` varchar(50) DEFAULT NULL,
  `Fiyat` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `menu`
--

INSERT INTO `menu` (`menu_ID`, `Menu_Ad`, `Fiyat`) VALUES
(1, '2 kişilik kahvaltı ', 162.00),
(2, 'Aile boyu kahvaltı ', 350.75),
(3, 'Tek Kişilik Kahvaltı', 90.50),
(4, 'Tatlı-Kahve', 150.00),
(5, 'Hafta içi Ne yersen', 312.25),
(6, 'Pazar Süprizi', 179.90),
(7, '2 tatlı-1 kahve', 190.50),
(8, 'Maç Klasiği', 216.00),
(9, 'Doğum günü özel', 465.90),
(10, 'Sevindiren menü', 143.25),
(11, 'Çıldırtan ', 115.50);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `personel`
--

CREATE TABLE `personel` (
  `P_ID` int(11) NOT NULL,
  `ad` varchar(25) DEFAULT NULL,
  `soyad` varchar(15) DEFAULT NULL,
  `tc` bigint(11) NOT NULL,
  `tel` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `maas` double DEFAULT NULL,
  `adres` varchar(150) DEFAULT NULL,
  `baslangic_tarihi` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `personel`
--

INSERT INTO `personel` (`P_ID`, `ad`, `soyad`, `tc`, `tel`, `email`, `maas`, `adres`, `baslangic_tarihi`) VALUES
(1, 'Ahmet ', 'Yılmaz', 12345678901, NULL, 'ahmet@gmail.com', 5000, 'a mahallesi tan sokak no:21 daire 12 istanbul/esenyurt', '2022-01-14'),
(2, 'Arif', 'kel', 12345678902, NULL, 'arif@gmail.com', 7000, NULL, '2021-01-11'),
(3, 'Ayça', 'Türk', 22345678901, NULL, 'ayca@gmail.com', 8000, 'rana mahallesi mana sokak no:5 daire 10 istanbul/şirinevler', '2023-06-15'),
(4, 'Ece', 'Tarık', 12345678905, NULL, 'ece@gmail.com', 9000, 'kanal mahallesi çorak sokak no:21 daire 11 istanbul/bağcılar', '2023-01-27'),
(5, 'Tarkan', 'Bal', 12345676901, NULL, 'tarkan@gmail.com', 6000, 'çan mahallesi cansu sokak no:12 daire 23 istanbul/sarıyer', '2023-01-29'),
(6, 'Buğra', 'Ton', 12345678091, NULL, 'bugra@gmail.com', 7000, 'yan mahallesi berkay sokak no:14 daire 5 istanbul/bahçelievler', '2023-01-26'),
(7, 'Fahrettin', 'Yeşil', 23145678901, 0, 'fahrettin@gmail.com', 7000, 'veri mahallesi kantin sokak no:11 daire 1 istanbul/eyüp', '2023-01-04'),
(8, 'Yakup', 'Bozdoğan', 31245678901, NULL, 'yakup@gmail.com', 7000, 'yıl mahallesi yaş sokak no:22 daire 6 istanbul/mecidiyeköy', '2023-01-15'),
(9, 'Umut', 'Bulut', 12354678901, NULL, 'umut@gmail.com', 9000, 'jale mahallesi sarı sokak no:24 daire 3 istanbul/bakırköy', '2023-01-16'),
(10, 'Barış', 'Çiçek', 12345670891, NULL, 'baris@gmail.com', 9000, 'zafer mahallesi yeşil sokak no:17 daire 4 istanbul/sarıyer', '2023-01-15');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `unvan`
--

CREATE TABLE `unvan` (
  `unvan_id` int(11) NOT NULL,
  `P_ID` int(11) DEFAULT NULL,
  `unvan_adi` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `unvan`
--

INSERT INTO `unvan` (`unvan_id`, `P_ID`, `unvan_adi`) VALUES
(1, 1, 'garson'),
(2, 2, 'Komi'),
(3, 3, 'Bulaşıkçı'),
(4, 4, 'Şef'),
(5, 5, 'Müdür'),
(6, 6, 'Komi'),
(7, 7, 'Garson'),
(8, 8, 'Garson'),
(9, 9, 'Şef'),
(10, 10, 'Komi');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_ID`);

--
-- Tablo için indeksler `personel`
--
ALTER TABLE `personel`
  ADD PRIMARY KEY (`P_ID`);

--
-- Tablo için indeksler `unvan`
--
ALTER TABLE `unvan`
  ADD PRIMARY KEY (`unvan_id`),
  ADD KEY `P_ID` (`P_ID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `personel`
--
ALTER TABLE `personel`
  MODIFY `P_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `unvan`
--
ALTER TABLE `unvan`
  ADD CONSTRAINT `unvan_ibfk_1` FOREIGN KEY (`P_ID`) REFERENCES `personel` (`P_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
