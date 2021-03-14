-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-03-2021 a las 20:34:47
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `celerity`
--
CREATE DATABASE IF NOT EXISTS `celerity` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `celerity`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

CREATE TABLE `tickets` (
  `id` int(11) NOT NULL,
  `nombres` varchar(75) NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(25) NOT NULL,
  `sector` varchar(50) NOT NULL,
  `queja` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`id`, `nombres`, `fecha`, `estado`, `sector`, `queja`) VALUES
(1, 'Julio Acosta', '2021-03-14', 'Prioridad Alta', 'Quitumbe', 'Fallas en el Servicio'),
(2, 'Pedro Pérez', '2021-03-14', 'Atrasado', 'Guamaní', 'Pérdida total del Servicio'),
(3, 'María Delgado', '2021-03-14', 'Atrasado', 'Chillogallo', 'Internet Lento'),
(4, 'Daniela Corrales', '2021-03-14', 'Abierto', 'La Ecuatoriana', 'Intermitencias en el Servicio'),
(5, 'Augusto Barrera', '2021-03-14', 'Cerrado', 'Turubamba', 'Pérdida del Servicio'),
(6, 'Julián Jaramillo', '2021-03-14', 'Escalado', 'Caupicho', 'Internet Lento'),
(7, 'Alberto Porras', '2021-03-14', 'Abierto', 'La Tola', 'Conexión perdida con juegos');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
