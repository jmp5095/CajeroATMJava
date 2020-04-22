-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 22-04-2020 a las 16:53:29
-- Versión del servidor: 8.0.17-0ubuntu2
-- Versión de PHP: 7.3.8-1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cajero_atm_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Ciudad`
--

CREATE TABLE `Ciudad` (
  `ciu_id_ciudad` int(3) NOT NULL,
  `ciu_nombre` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Ciudad`
--

INSERT INTO `Ciudad` (`ciu_id_ciudad`, `ciu_nombre`) VALUES
(1, 'Yumbo'),
(2, 'Cali'),
(3, 'Buga'),
(4, 'Palmira');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cliente`
--

CREATE TABLE `Cliente` (
  `cli_cedula` varchar(20) NOT NULL,
  `cli_nombre` varchar(20) DEFAULT NULL,
  `cli_apellido` varchar(20) DEFAULT NULL,
  `cli_direccion` varchar(20) DEFAULT NULL,
  `cli_contraseña` varchar(20) DEFAULT NULL,
  `tbl_ciu_id_ciudad` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Cliente`
--

INSERT INTO `Cliente` (`cli_cedula`, `cli_nombre`, `cli_apellido`, `cli_direccion`, `cli_contraseña`, `tbl_ciu_id_ciudad`) VALUES
('10501', 'WERNER', 'HEISENBERG', 'CR 5ta Norte #3 P-3', 'werner123', 2),
('1118846377', 'LIU', 'QUIROZ', 'CR 23ta Sur #9 A-2', 'liu123', 4),
('288475864', 'VLADIMIR', 'PUTIN', 'CLL 1ra #3 J-32', 'vladimir123', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cuenta`
--

CREATE TABLE `Cuenta` (
  `cue_numero_de_cuenta` varchar(20) NOT NULL,
  `cue_tipo_de_cuenta` varchar(20) DEFAULT NULL,
  `cue_monto` decimal(15,0) DEFAULT NULL,
  `tbl_cli_cedula` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Cuenta`
--

INSERT INTO `Cuenta` (`cue_numero_de_cuenta`, `cue_tipo_de_cuenta`, `cue_monto`, `tbl_cli_cedula`) VALUES
('112', 'ahorro', '6100000', '1118846377'),
('567', 'ahorro', '5000000', '10501'),
('890', 'ahorro', '4102000', '288475864');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Transaccion`
--

CREATE TABLE `Transaccion` (
  `tra_id_transaccion` int(15) NOT NULL,
  `tra_monto` decimal(15,0) DEFAULT NULL,
  `tra_fecha` datetime DEFAULT NULL,
  `tra_aprobacion` tinyint(1) DEFAULT NULL,
  `tra_realizada` tinyint(1) DEFAULT NULL,
  `tbl_cue_depositante` varchar(20) NOT NULL,
  `tbl_cue_deposito` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Transaccion`
--

INSERT INTO `Transaccion` (`tra_id_transaccion`, `tra_monto`, `tra_fecha`, `tra_aprobacion`, `tra_realizada`, `tbl_cue_depositante`, `tbl_cue_deposito`) VALUES
(16, '100000', '2020-04-20 17:52:43', 0, 0, '890', '890'),
(17, '200000', '2020-04-20 20:07:07', 1, 1, '890', '890');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Ciudad`
--
ALTER TABLE `Ciudad`
  ADD PRIMARY KEY (`ciu_id_ciudad`);

--
-- Indices de la tabla `Cliente`
--
ALTER TABLE `Cliente`
  ADD PRIMARY KEY (`cli_cedula`),
  ADD KEY `tbl_ciu_id_ciudad` (`tbl_ciu_id_ciudad`);

--
-- Indices de la tabla `Cuenta`
--
ALTER TABLE `Cuenta`
  ADD PRIMARY KEY (`cue_numero_de_cuenta`),
  ADD KEY `tbl_cli_cedula` (`tbl_cli_cedula`);

--
-- Indices de la tabla `Transaccion`
--
ALTER TABLE `Transaccion`
  ADD PRIMARY KEY (`tra_id_transaccion`),
  ADD KEY `tbl_cue_depositante` (`tbl_cue_depositante`),
  ADD KEY `tbl_cue_deposito` (`tbl_cue_deposito`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Ciudad`
--
ALTER TABLE `Ciudad`
  MODIFY `ciu_id_ciudad` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `Transaccion`
--
ALTER TABLE `Transaccion`
  MODIFY `tra_id_transaccion` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Cliente`
--
ALTER TABLE `Cliente`
  ADD CONSTRAINT `Cliente_ibfk_1` FOREIGN KEY (`tbl_ciu_id_ciudad`) REFERENCES `Ciudad` (`ciu_id_ciudad`);

--
-- Filtros para la tabla `Cuenta`
--
ALTER TABLE `Cuenta`
  ADD CONSTRAINT `Cuenta_ibfk_1` FOREIGN KEY (`tbl_cli_cedula`) REFERENCES `Cliente` (`cli_cedula`);

--
-- Filtros para la tabla `Transaccion`
--
ALTER TABLE `Transaccion`
  ADD CONSTRAINT `Transaccion_ibfk_1` FOREIGN KEY (`tbl_cue_depositante`) REFERENCES `Cuenta` (`cue_numero_de_cuenta`),
  ADD CONSTRAINT `Transaccion_ibfk_2` FOREIGN KEY (`tbl_cue_deposito`) REFERENCES `Cuenta` (`cue_numero_de_cuenta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
