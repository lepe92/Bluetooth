-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-01-2016 a las 20:34:30
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `distribuidos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bitacora`
--

CREATE TABLE IF NOT EXISTS `bitacora` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `disp_mac` varchar(45) NOT NULL,
  `persona_mac` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Volcado de datos para la tabla `bitacora`
--

INSERT INTO `bitacora` (`id`, `fecha`, `disp_mac`, `persona_mac`) VALUES
(21, '2016-01-23 20:31:08', '30:76:6F:47:B7:BE', 'D4:0B:1A:2F:DD:51'),
(22, '2016-01-23 20:31:36', '30:76:6F:47:B7:BE', 'D4:0B:1A:2F:DD:51'),
(23, '2016-01-23 20:32:09', '30:76:6F:47:B7:BE', 'D4:0B:1A:2F:DD:51'),
(24, '2016-01-23 20:32:40', '30:76:6F:47:B7:BE', 'D4:0B:1A:2F:DD:51');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dispositivos_bluetooth`
--

CREATE TABLE IF NOT EXISTS `dispositivos_bluetooth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `mac` varchar(45) NOT NULL,
  `ubicacion` varchar(45) NOT NULL,
  `menu` varchar(450) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `dispositivos_bluetooth`
--

INSERT INTO `dispositivos_bluetooth` (`id`, `nombre`, `mac`, `ubicacion`, `menu`) VALUES
(1, 'HC-05', '98:D3:31:40:1B:EF', 'vinculacion', 'Dar de alta,Dar de baja,Solicitar apoyo'),
(2, 'Hector Jalil Desi (GT-', '30:D6:C9:10:76:66', 'biblioteca', 'Prestamo de libro,Registrar,Pagar copias'),
(3, 'Diego37', 'F8:0C:F3:A9:CC:E9', 'cafeteria', 'Comprar comida,Comprar postre,Usar maquinita'),
(4, 'LG-P708g', '30:76:6F:47:B7:BE', 'laboratoriocomputacion', 'Solicitar clave internet,Utilizar equipo,Pedir asesoria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dummy`
--

CREATE TABLE IF NOT EXISTS `dummy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `texto` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `dummy`
--

INSERT INTO `dummy` (`id`, `texto`) VALUES
(3, 'F8:0C:F3:A9:CC:E9');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE IF NOT EXISTS `personas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL,
  `nombre_disp` varchar(45) NOT NULL,
  `mac` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `nombre`, `nombre_disp`, `mac`) VALUES
(1, 'Maestrante Jalil', 'Jalil', 'D4:0B:1A:2F:DD:51');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
