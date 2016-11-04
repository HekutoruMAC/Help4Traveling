-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-10-2016 a las 16:46:36
-- Versión del servidor: 10.1.9-MariaDB
-- Versión de PHP: 5.6.15

SET FOREIGN_KEY_CHECKS=0;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `help4traveling`
--
CREATE DATABASE IF NOT EXISTS `help4traveling` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `help4traveling`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `padre` varchar(50) COLLATE utf8_spanish_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `categorias`:
--

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`nombre`, `padre`) VALUES('1 ambiente', 'Habitaciones');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('1 dormitorio', 'Habitaciones');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('2 dormitorios', 'Habitaciones');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Air France', 'Empresas');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Alaska', 'Cruceros');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Alojamientos', 'Categorias');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('American Airlines', 'Empresas');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Apartamento', 'Tipo alojamiento');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Auto', 'Tipo vehiculo');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Automoviles', 'Categorias');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Camion', 'Tipo vehiculo');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Camioneta', 'Tipo vehiculo');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Caribe', 'Cruceros');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Caribe Sudamerica', 'Caribe Sur');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Caribe Sur', 'Cruceros');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Casa', 'Tipo alojamiento');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Chevrolet', 'Marca');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Cruceros', 'Categorias');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Daihatsu', 'Marca');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Economico', 'Tarifa');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Empresas', 'Vuelos');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Fiat', 'Marca');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('First Class', 'Tipo vuelo');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Ford', 'Marca');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Full', 'Tarifa');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Habitaciones', 'Alojamientos');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Hostal', 'Tipo alojamiento');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Hotel', 'Tipo alojamiento');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Iberia', 'Empresas');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('LowCost', 'Tipo vuelo');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Mar negro', 'Cruceros');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Marca', 'Automoviles');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Mediterraneo', 'Cruceros');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Mini', 'Tarifa');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Monta?a', 'Ubicacion');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Moto', 'Tipo vehiculo');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Nilo', 'Cruceros');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Peugeot', 'Marca');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Playa', 'Ubicacion');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Rural', 'Ubicacion');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Standard', 'Tarifa');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Standard', 'Tipo vuelo');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('TAM', 'Empresas');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Tarifa', 'Automoviles');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Tipo alojamiento', 'Alojamientos');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Tipo vehiculo', 'Automoviles');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Tipo vuelo', 'Vuelos');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Ubicacion', 'Alojamientos');
INSERT INTO `categorias` (`nombre`, `padre`) VALUES('Vuelos', 'Categorias');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudades`
--

CREATE TABLE `ciudades` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `pais` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `ciudades`:
--   `pais`
--       `paises` -> `nombre`
--

--
-- Volcado de datos para la tabla `ciudades`
--

INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Berlin', 'Alemania');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Bariloche', 'Argentina');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Buenos Aires', 'Argentina');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Sidney', 'Australia');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Florianopilis', 'Brasil');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Caton', 'China');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Pekin', 'China');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Bogota', 'Colombia');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Miami', 'EEUU');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Madrid', 'Espa?a');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('VAlencia', 'Espa?a');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Paris', 'Francia');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Ginebra', 'Suiza');
INSERT INTO `ciudades` (`nombre`, `pais`) VALUES('Montevideo', 'Uruguay');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `nickname` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `clientes`:
--   `nickname`
--       `usuarios` -> `nickname`
--

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`nickname`) VALUES('BruceS');
INSERT INTO `clientes` (`nickname`) VALUES('eWatson');
INSERT INTO `clientes` (`nickname`) VALUES('JeffW');
INSERT INTO `clientes` (`nickname`) VALUES('oWood');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `servicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorServicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cliente` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `puntuacion` enum('1','2','3','4','5','6','7','8','9','10') COLLATE utf8_spanish_ci DEFAULT NULL,
  `titulo` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `comentario` text COLLATE utf8_spanish_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `comentarios`:
--   `cliente`
--       `clientes` -> `nickname`
--   `servicio`
--       `servicios` -> `nombre`
--   `proveedorServicio`
--       `servicios` -> `proveedor`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ofertas`
--

CREATE TABLE `ofertas` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedor` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `ofertas`:
--   `proveedor`
--       `proveedores` -> `nickname`
--

--
-- Volcado de datos para la tabla `ofertas`
--

INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Servicio de Turismo Interno', 'adippet');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('TAM-FC', 'adippet');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Alas U', 'mHooch');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Casa para p4 BsAs', 'mHooch');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Coche-Miami', 'mHooch');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Luxury south beach corner apartment ', 'mHooch');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Miami-Viaje', 'mHooch');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Sudamerica-Casas', 'mHooch');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Ultimo Dia', 'mHooch');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Car-1', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Car-2', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Car-3', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Cars-E-F', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Cars-E-S', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Cars-S-F', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Floripa G. House', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Hostel de la Rua', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('mmmmmmmmm', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('ooooooo', 'moody');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Vuelo-FC', 'remus');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Vuelo-LC', 'remus');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Vuelo-S', 'remus');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Vuelos-LC-FC', 'remus');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Vuelos-S-FC', 'remus');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Euro-Vuelos-S-LC', 'remus');
INSERT INTO `ofertas` (`nombre`, `proveedor`) VALUES('Air-France-FC', 'tCook');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paises`
--

CREATE TABLE `paises` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `paises`:
--

--
-- Volcado de datos para la tabla `paises`
--

INSERT INTO `paises` (`nombre`) VALUES('Alemania');
INSERT INTO `paises` (`nombre`) VALUES('Argentina');
INSERT INTO `paises` (`nombre`) VALUES('Australia');
INSERT INTO `paises` (`nombre`) VALUES('Brasil');
INSERT INTO `paises` (`nombre`) VALUES('China');
INSERT INTO `paises` (`nombre`) VALUES('Colombia');
INSERT INTO `paises` (`nombre`) VALUES('EEUU');
INSERT INTO `paises` (`nombre`) VALUES('Espa?a');
INSERT INTO `paises` (`nombre`) VALUES('Francia');
INSERT INTO `paises` (`nombre`) VALUES('Suiza');
INSERT INTO `paises` (`nombre`) VALUES('Uruguay');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promociones`
--

CREATE TABLE `promociones` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descuento` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `promociones`:
--   `nombre`
--       `ofertas` -> `nombre`
--   `proveedor`
--       `ofertas` -> `proveedor`
--

--
-- Volcado de datos para la tabla `promociones`
--

INSERT INTO `promociones` (`nombre`, `proveedor`, `descuento`, `total`) VALUES('Euro-Cars-E-F', 'moody', 30, 420);
INSERT INTO `promociones` (`nombre`, `proveedor`, `descuento`, `total`) VALUES('Euro-Cars-E-S', 'moody', 30, 420);
INSERT INTO `promociones` (`nombre`, `proveedor`, `descuento`, `total`) VALUES('Euro-Cars-S-F', 'moody', 30, 420);
INSERT INTO `promociones` (`nombre`, `proveedor`, `descuento`, `total`) VALUES('Euro-Vuelos-LC-FC', 'remus', 40, 1290);
INSERT INTO `promociones` (`nombre`, `proveedor`, `descuento`, `total`) VALUES('Euro-Vuelos-S-FC', 'remus', 40, 1440);
INSERT INTO `promociones` (`nombre`, `proveedor`, `descuento`, `total`) VALUES('Euro-Vuelos-S-LC', 'remus', 40, 1170);
INSERT INTO `promociones` (`nombre`, `proveedor`, `descuento`, `total`) VALUES('Miami-Viaje', 'mHooch', 30, 462);
INSERT INTO `promociones` (`nombre`, `proveedor`, `descuento`, `total`) VALUES('Sudamerica-Casas', 'mHooch', 50, 135);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promocionesservicios`
--

CREATE TABLE `promocionesservicios` (
  `promocion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorPromocion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `servicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorServicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `promocionesservicios`:
--   `promocion`
--       `promociones` -> `nombre`
--   `proveedorPromocion`
--       `promociones` -> `proveedor`
--   `servicio`
--       `servicios` -> `nombre`
--   `proveedorServicio`
--       `servicios` -> `proveedor`
--

--
-- Volcado de datos para la tabla `promocionesservicios`
--

INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Cars-E-F', 'moody', 'Euro-Car-1', 'moody');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Cars-E-F', 'moody', 'Euro-Car-3', 'moody');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Cars-E-S', 'moody', 'Euro-Car-1', 'moody');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Cars-E-S', 'moody', 'Euro-Car-2', 'moody');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Cars-S-F', 'moody', 'Euro-Car-2', 'moody');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Cars-S-F', 'moody', 'Euro-Car-3', 'moody');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Vuelos-LC-FC', 'remus', 'Euro-Vuelo-FC', 'remus');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Vuelos-LC-FC', 'remus', 'Euro-Vuelo-LC', 'remus');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Vuelos-S-FC', 'remus', 'Euro-Vuelo-FC', 'remus');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Vuelos-S-FC', 'remus', 'Euro-Vuelo-S', 'remus');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Vuelos-S-LC', 'remus', 'Euro-Vuelo-LC', 'remus');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Euro-Vuelos-S-LC', 'remus', 'Euro-Vuelo-S', 'remus');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Miami-Viaje', 'mHooch', 'Coche-Miami', 'mHooch');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Miami-Viaje', 'mHooch', 'Luxury south beach corner apartment', 'mHooch');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Sudamerica-Casas', 'mHooch', 'Casa para p4 BsAs', 'mHooch');
INSERT INTO `promocionesservicios` (`promocion`, `proveedorPromocion`, `servicio`, `proveedorServicio`) VALUES('Sudamerica-Casas', 'mHooch', 'Floripa G. House', 'mHooch');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `nickname` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `empresa` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `link` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `proveedores`:
--   `nickname`
--       `usuarios` -> `nickname`
--

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`nickname`, `empresa`, `link`) VALUES('adippet', 'Tam', 'http://www.tam.com.br');
INSERT INTO `proveedores` (`nickname`, `empresa`, `link`) VALUES('mHooch', 'Segundo Hogar', 'http://www.segundohogar.com');
INSERT INTO `proveedores` (`nickname`, `empresa`, `link`) VALUES('moody', 'EuroCar', 'http://www.eurocar.com.uy');
INSERT INTO `proveedores` (`nickname`, `empresa`, `link`) VALUES('remus', 'Lberia', 'http://www.iberia.com/uy/');
INSERT INTO `proveedores` (`nickname`, `empresa`, `link`) VALUES('tCook', 'AirFrance', 'http://www.airfrance.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `numero` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `total` double DEFAULT NULL,
  `estado` enum('REGISTRADA','CANCELADA','PAGADA','FACTURADA') COLLATE utf8_spanish_ci DEFAULT NULL,
  `cliente` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `reservas`:
--   `cliente`
--       `clientes` -> `nickname`
--

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`numero`, `fecha`, `total`, `estado`, `cliente`) VALUES(17, '2015-01-01', 1100, 'FACTURADA', 'oWood');
INSERT INTO `reservas` (`numero`, `fecha`, `total`, `estado`, `cliente`) VALUES(18, '2015-01-01', 3050, 'CANCELADA', 'eWatson');
INSERT INTO `reservas` (`numero`, `fecha`, `total`, `estado`, `cliente`) VALUES(19, '2015-03-05', 80, 'PAGADA', 'BruceS');
INSERT INTO `reservas` (`numero`, `fecha`, `total`, `estado`, `cliente`) VALUES(20, '2015-05-08', 600, 'PAGADA', 'jeffW');
INSERT INTO `reservas` (`numero`, `fecha`, `total`, `estado`, `cliente`) VALUES(21, '2015-08-07', 200, 'REGISTRADA', 'oWood');
INSERT INTO `reservas` (`numero`, `fecha`, `total`, `estado`, `cliente`) VALUES(22, '2015-08-07', 270, 'REGISTRADA', 'eWatson');
INSERT INTO `reservas` (`numero`, `fecha`, `total`, `estado`, `cliente`) VALUES(23, '2015-08-07', 1700, 'REGISTRADA', 'BruceS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservasitems`
--

CREATE TABLE `reservasitems` (
  `reserva` int(11) NOT NULL,
  `oferta` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorOferta` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `inicio` date DEFAULT NULL,
  `fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `reservasitems`:
--   `reserva`
--       `reservas` -> `numero`
--   `oferta`
--       `ofertas` -> `nombre`
--   `proveedorOferta`
--       `ofertas` -> `proveedor`
--

--
-- Volcado de datos para la tabla `reservasitems`
--

INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(17, 'Euro-Vuelo-S', 'remus', 1, '2015-01-01', '2015-01-01');
INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(18, 'Euro-Vuelo-LC', 'remus', 1, '2015-01-01', '2015-01-01');
INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(18, 'Euro-Vuelo-S', 'remus', 2, '2015-01-01', '2015-01-01');
INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(19, 'Sudamerica-Casas', 'mHooch', 1, '2015-03-05', '2015-04-02');
INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(20, 'Euro-Car-2', 'moody', 1, '2015-05-08', '2015-05-12');
INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(20, 'Euro-Car-3', 'moody', 1, '2015-05-08', '2015-05-12');
INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(21, 'Air-France-FC', 'tCook', 2, '2015-08-07', '2015-08-10');
INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(22, 'Casa para p4 BsAs', 'mHooch', 1, '2015-08-07', '2015-08-14');
INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(22, 'Miami-Viaje', 'mHooch', 1, '2015-08-14', '2015-08-21');
INSERT INTO `reservasitems` (`reserva`, `oferta`, `proveedorOferta`, `cantidad`, `inicio`, `fin`) VALUES(23, 'Euro-Vuelo-LC', 'remus', 2, '2015-08-07', '2015-08-07');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci,
  `precio` double DEFAULT NULL,
  `origen` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `destino` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `servicios`:
--   `origen`
--       `ciudades` -> `nombre`
--   `destino`
--       `ciudades` -> `nombre`
--   `nombre`
--       `ofertas` -> `nombre`
--   `proveedor`
--       `ofertas` -> `proveedor`
--

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Air-France-FC', 'tCook', '?Un vuelo de primera! Excelencia y experiencia en mejorar sus viajes', 100, 'Paris', 'Berlin');
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Casa para p4 BsAs', 'mHooch', 'Esta hermosa casa, se encuentra ubicada en el coraz?n de Buenos Aires y ofrece una capacidad para cuatro personas. La propiedad cuenta con un dormitorio con dos camas simples, que pueden transformarse en una matrimonial y dos ba?os completos, que incluyen toallas.', 80, 'Buenos Aires', NULL);
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Coche-Miami', 'mHooch', 'A useful car to travel around Miami', 360, 'Miami', NULL);
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Euro-Car-1', 'moody', 'S4 Euro-Car. Autos de buena calidad y comodidad. Versi?n Econ?mica', 300, 'Madrid', 'Valencia');
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Euro-Car-2', 'moody', 'Euro-Car. Autos de buena calidad y comodidad. Versi?n Standard.', 300, 'Madrid', 'Valencia');
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Euro-Car-3', 'moody', '6 Euro-Car. Autos de buena calidad y comodidad. Una camioneta para toda la familia', 300, 'Valencia', NULL);
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Euro-Vuelo-FC', 'remus', 'Vuelo de primera clase. Excelente atenci?n, comodidad y servicio.', 1300, 'Montevideo', 'Valencia');
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Euro-Vuelo-LC', 'remus', 'Vuelo con excelente atenci?n y comodidad a un precio accesible.', 850, 'Montevideo', 'Valencia');
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Euro-Vuelo-S', 'remus', 'Vuelo con excelente atenci?n y comodidad', 1100, 'Montevideo', 'Valencia');
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Floripa G. House', 'mHooch', 'Estamos a s?lo unos pasos de un supermercado, restaurantes, cajero autom?tico, gasolinera, farmacia, gimnasio, etc. Lagoa da Concei??o es 7 km de nuestra casa de hu?spedes y tarda s?lo 10-15 minutos en el transporte p?blico. All? se encuentra una buena vida nocturna con bares y m?sica en vivo', 190, 'Florianopilis', NULL);
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('Luxury south beach corner apartment', 'mHooch', 'Beautiful large 2 bedrooms 2 bathrooms apartment CORNER UNIT. Marble floor  throughout, beautiful open kitchen, granite counter top, spacious dining room area and living room area. Spectacular views of Miami from all windows and balcony', 300, 'Miami', NULL);
INSERT INTO `servicios` (`nombre`, `proveedor`, `descripcion`, `precio`, `origen`, `destino`) VALUES('TAM-FC', 'adippet', '?Un vuelo de primera! Excelencia y experiencia.', 150, 'Florianopilis', 'Pekin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicioscategorias`
--

CREATE TABLE `servicioscategorias` (
  `servicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorServicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `categoria` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `categoriaPadre` varchar(50) COLLATE utf8_spanish_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `servicioscategorias`:
--   `categoria`
--       `categorias` -> `nombre`
--   `categoriaPadre`
--       `categorias` -> `padre`
--   `servicio`
--       `servicios` -> `nombre`
--   `proveedorServicio`
--       `servicios` -> `proveedor`
--

--
-- Volcado de datos para la tabla `servicioscategorias`
--

INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Air-France-FC', 'tCook', 'Air France', 'Empresas');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Air-France-FC', 'tCook', 'First Class', 'Tipo vuelo');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Casa para p4 BsAs', 'mHooch', '2 dormitorios', 'Habitaciones');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Casa para p4 BsAs', 'mHooch', 'Casa', 'Tipo alojamiento');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Coche-Miami', 'mHooch', 'Chevrolet', 'Marca');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Coche-Miami', 'mHooch', 'Economico', 'Tarifa');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Car-1', 'moody', 'Auto', 'Tipo vehiculo');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Car-1', 'moody', 'Chevrolet', 'Marca');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Car-1', 'moody', 'Economico', 'Tarifa');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Car-2', 'moody', 'Auto', 'Tipo vehiculo');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Car-2', 'moody', 'Chevrolet', 'Marca');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Car-2', 'moody', 'Standar', 'Tarifa');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Car-3', 'moody', 'Camioneta', 'Tipo vehiculo');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Car-3', 'moody', 'Chevrolet', 'Marca');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Car-3', 'moody', 'Full', 'Tarifa');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Vuelo-FC', 'remus', 'First Class', 'Tipo vuelo');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Vuelo-FC', 'remus', 'Iberia', 'Empresas');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Vuelo-LC', 'remus', 'Iberia', 'Empresas');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Vuelo-LC', 'remus', 'LowCost', 'Tipo vuelo');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Vuelo-S', 'remus', 'Iberia', 'Empresas');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Euro-Vuelo-S', 'remus', 'Standar', 'Tipo vuelo');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Floripa G. House', 'mHooch', '2 dormitorios', 'Habitaciones');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Floripa G. House', 'mHooch', 'Casa', 'Tipo alojamiento');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Luxury south beach corner apartment', 'mHooch', '2 dormitorios', 'Habitaciones');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Luxury south beach corner apartment', 'mHooch', 'Auto', 'Tipo vehiculo');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('Luxury south beach corner apartment', 'mHooch', 'Hotel', 'Tipo alojamiento');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('TAM-FC', 'adippet', 'Playa', 'Ubicacion');
INSERT INTO `servicioscategorias` (`servicio`, `proveedorServicio`, `categoria`, `categoriaPadre`) VALUES('TAM-FC', 'adippet', 'TAM', 'Empresas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `serviciosimagenes`
--

CREATE TABLE `serviciosimagenes` (
  `servicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `imagen` varchar(150) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `serviciosimagenes`:
--   `servicio`
--       `servicios` -> `nombre`
--

--
-- Volcado de datos para la tabla `serviciosimagenes`
--

INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Air-France-FC', ' http://bit.ly/1MPIlFE');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Air-France-FC', 'http://globedia.com/imagenes/noticias/2011/11/25/avion-a340-air-france-perdio-tornillos-trayecto-paris-boston_1_985547.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Air-France-FC', 'http://www.doralnewsonline.com/doralfinal/wp-content/uploads/2016/01/Air-France.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Casa para p4 BsAs', 'http://imganuncios.mitula.net/casa_2_plantas_venta_moron_en_moron_bs_as_g_b_a_zona_oeste_4_dormitorios_140_m2_4840083469302347533.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Casa para p4 BsAs', 'http://imganuncios.mitula.net/casa_de_campo_a_5_kms_de_gral_madariaga_y_30_casa_de_campo_a_5_kms_de_gral_7940135440109335717.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Coche-Miami', ' http://bit.ly/1Jsx5rx');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Euro-Car-1', ' http://bit.ly/1Jsx5rx');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Euro-Car-2', ' http://bit.ly/1POes6T');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Euro-Car-3', ' http://bit.ly/1Ua0ywm');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Euro-Vuelo-FC', 'http://en.academic.ru/pictures/enwiki/49/1er_vol_de_l''_A380.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Euro-Vuelo-LC', 'http://e03-elmundo.uecdn.es/assets/multimedia/imagenes/2016/07/11/14682649952278.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Euro-Vuelo-LC', 'http://okdiario.com/img/2015/12/aireuropa.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Euro-Vuelo-S', 'http://img.ev.mu/images/articles/600x/452676.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Floripa G. House', ' http://bit.ly/1hDqGnChttp://c50039.r39.cf3.rackcdn.com/uploads/photo/file/18846103/preview.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Floripa G. House', 'http://c50039.r39.cf3.rackcdn.com/uploads/photo/file/18846103/preview.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Floripa G. House', 'https://www.hostelz.com/pics/hostels/owner/big/67/729067.jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('Luxury south beach corner apartment', 'http://www.fort-lauderdale-condos-for-sale.com/images/DSC00110%20(600%20x%20450).jpg');
INSERT INTO `serviciosimagenes` (`servicio`, `imagen`) VALUES('TAM-FC', 'http://img.emol.com/2012/10/09/tamsoloap_14146.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `nickname` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(50) CHARACTER SET ucs2 NOT NULL,
  `imagen` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fechaNac` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `usuarios`:
--

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `password`, `email`, `imagen`, `fechaNac`) VALUES('adippet', 'Armando', 'Dippet', 'Dippet123', 'tam@outlook.com', NULL, '1967-02-12');
INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `password`, `email`, `imagen`, `fechaNac`) VALUES('BruceS', 'Bruce', 'Sewell', 'Sewell123', 'bruce.swell@gmail.com', NULL, '1978-12-03');
INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `password`, `email`, `imagen`, `fechaNac`) VALUES('eWatson', 'Emma', 'Watson', 'Watson123', 'e.watson@gmail.com', NULL, '1990-04-15');
INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `password`, `email`, `imagen`, `fechaNac`) VALUES('jeffW', 'jeff', 'Wiliams', 'Wiliams123', 'jeff.williams@gmail.com', NULL, '1984-11-27');
INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `password`, `email`, `imagen`, `fechaNac`) VALUES('mHooch', 'Madam', 'Hooch', 'Hooch123', 'segHogar@gmail.com', NULL, '1963-08-05');
INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `password`, `email`, `imagen`, `fechaNac`) VALUES('moody', 'Alastor', 'Moody', 'Moody123', 'eu.car@eucar.com', NULL, '1965-09-02');
INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `password`, `email`, `imagen`, `fechaNac`) VALUES('oWood', 'Oliver', 'Wood', 'Wood123', 'quidditch28@gmail.com', NULL, '1988-12-28');
INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `password`, `email`, `imagen`, `fechaNac`) VALUES('remus', 'Remus', 'Lupin', 'Lupin123', 'iberia@gmail.com', NULL, '1970-05-04');
INSERT INTO `usuarios` (`nickname`, `nombre`, `apellido`, `password`, `email`, `imagen`, `fechaNac`) VALUES('tCook', 'Tim', 'Cook', 'Cook123', 'air.f@gmail.com', NULL, '1960-11-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuariosimagenes`
--

CREATE TABLE `usuariosimagenes` (
  `usuario` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `imagen` varchar(150) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `usuariosimagenes`:
--   `usuario`
--       `usuarios` -> `nickname`
--

--
-- Volcado de datos para la tabla `usuariosimagenes`
--

INSERT INTO `usuariosimagenes` (`usuario`, `imagen`) VALUES('adippet', 'http://bit.ly/1U4SdPY');
INSERT INTO `usuariosimagenes` (`usuario`, `imagen`) VALUES('eWatson', 'http://bit.ly/1hEGTcq');
INSERT INTO `usuariosimagenes` (`usuario`, `imagen`) VALUES('mhooch', 'http://bit.ly/1hEGDdb');
INSERT INTO `usuariosimagenes` (`usuario`, `imagen`) VALUES('moody', 'http://hp-intothefire.wdfiles.com/local--files/moody/Madeye.jpg');
INSERT INTO `usuariosimagenes` (`usuario`, `imagen`) VALUES('oWood', 'http://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=25987860');
INSERT INTO `usuariosimagenes` (`usuario`, `imagen`) VALUES('remus', 'http://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=21724965');
INSERT INTO `usuariosimagenes` (`usuario`, `imagen`) VALUES('tCook', 'http://bit.ly/1LvodoZ');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`nombre`,`padre`),
  ADD KEY `padre` (`padre`);

--
-- Indices de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `pais` (`pais`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`nickname`);

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`servicio`,`proveedorServicio`,`cliente`),
  ADD KEY `cliente` (`cliente`);

--
-- Indices de la tabla `ofertas`
--
ALTER TABLE `ofertas`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `proveedor` (`proveedor`);

--
-- Indices de la tabla `paises`
--
ALTER TABLE `paises`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD PRIMARY KEY (`nombre`,`proveedor`),
  ADD KEY `proveedor` (`proveedor`);

--
-- Indices de la tabla `promocionesservicios`
--
ALTER TABLE `promocionesservicios`
  ADD PRIMARY KEY (`promocion`,`proveedorPromocion`,`servicio`,`proveedorServicio`),
  ADD KEY `servicio` (`servicio`,`proveedorServicio`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`nickname`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`numero`),
  ADD KEY `cliente` (`cliente`);

--
-- Indices de la tabla `reservasitems`
--
ALTER TABLE `reservasitems`
  ADD PRIMARY KEY (`reserva`,`oferta`,`proveedorOferta`),
  ADD KEY `oferta` (`oferta`),
  ADD KEY `proveedorOferta` (`proveedorOferta`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`nombre`,`proveedor`),
  ADD KEY `origen` (`origen`),
  ADD KEY `destino` (`destino`),
  ADD KEY `proveedor` (`proveedor`);

--
-- Indices de la tabla `servicioscategorias`
--
ALTER TABLE `servicioscategorias`
  ADD PRIMARY KEY (`servicio`,`proveedorServicio`,`categoria`,`categoriaPadre`),
  ADD KEY `categoria` (`categoria`,`categoriaPadre`);

--
-- Indices de la tabla `serviciosimagenes`
--
ALTER TABLE `serviciosimagenes`
  ADD PRIMARY KEY (`servicio`,`imagen`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`nickname`);

--
-- Indices de la tabla `usuariosimagenes`
--
ALTER TABLE `usuariosimagenes`
  ADD PRIMARY KEY (`usuario`,`imagen`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `numero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ciudades`
--
ALTER TABLE `ciudades`
  ADD CONSTRAINT `ciudades_ibfk_1` FOREIGN KEY (`pais`) REFERENCES `paises` (`nombre`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`nickname`) REFERENCES `usuarios` (`nickname`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`nickname`) ON UPDATE CASCADE,
  ADD CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`servicio`,`proveedorServicio`) REFERENCES `servicios` (`nombre`, `proveedor`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `ofertas`
--
ALTER TABLE `ofertas`
  ADD CONSTRAINT `ofertas_ibfk_1` FOREIGN KEY (`proveedor`) REFERENCES `proveedores` (`nickname`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `promociones`
--
ALTER TABLE `promociones`
  ADD CONSTRAINT `promociones_ibfk_1` FOREIGN KEY (`nombre`) REFERENCES `ofertas` (`nombre`),
  ADD CONSTRAINT `promociones_ibfk_2` FOREIGN KEY (`proveedor`) REFERENCES `ofertas` (`proveedor`);

--
-- Filtros para la tabla `promocionesservicios`
--
ALTER TABLE `promocionesservicios`
  ADD CONSTRAINT `promocionesservicios_ibfk_1` FOREIGN KEY (`promocion`,`proveedorPromocion`) REFERENCES `promociones` (`nombre`, `proveedor`) ON UPDATE CASCADE,
  ADD CONSTRAINT `promocionesservicios_ibfk_2` FOREIGN KEY (`servicio`,`proveedorServicio`) REFERENCES `servicios` (`nombre`, `proveedor`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD CONSTRAINT `proveedores_ibfk_1` FOREIGN KEY (`nickname`) REFERENCES `usuarios` (`nickname`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`nickname`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `reservasitems`
--
ALTER TABLE `reservasitems`
  ADD CONSTRAINT `reservasitems_ibfk_1` FOREIGN KEY (`reserva`) REFERENCES `reservas` (`numero`) ON UPDATE CASCADE,
  ADD CONSTRAINT `reservasitems_ibfk_2` FOREIGN KEY (`oferta`) REFERENCES `ofertas` (`nombre`) ON UPDATE CASCADE,
  ADD CONSTRAINT `reservasitems_ibfk_3` FOREIGN KEY (`proveedorOferta`) REFERENCES `ofertas` (`proveedor`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD CONSTRAINT `servicios_ibfk_2` FOREIGN KEY (`origen`) REFERENCES `ciudades` (`nombre`) ON UPDATE CASCADE,
  ADD CONSTRAINT `servicios_ibfk_3` FOREIGN KEY (`destino`) REFERENCES `ciudades` (`nombre`) ON UPDATE CASCADE,
  ADD CONSTRAINT `servicios_ibfk_4` FOREIGN KEY (`nombre`) REFERENCES `ofertas` (`nombre`),
  ADD CONSTRAINT `servicios_ibfk_5` FOREIGN KEY (`proveedor`) REFERENCES `ofertas` (`proveedor`);

--
-- Filtros para la tabla `servicioscategorias`
--
ALTER TABLE `servicioscategorias`
  ADD CONSTRAINT `servicioscategorias_ibfk_1` FOREIGN KEY (`categoria`,`categoriaPadre`) REFERENCES `categorias` (`nombre`, `padre`) ON UPDATE CASCADE,
  ADD CONSTRAINT `servicioscategorias_ibfk_2` FOREIGN KEY (`servicio`,`proveedorServicio`) REFERENCES `servicios` (`nombre`, `proveedor`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `serviciosimagenes`
--
ALTER TABLE `serviciosimagenes`
  ADD CONSTRAINT `serviciosimagenes_ibfk_1` FOREIGN KEY (`servicio`) REFERENCES `servicios` (`nombre`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuariosimagenes`
--
ALTER TABLE `usuariosimagenes`
  ADD CONSTRAINT `usuariosimagenes_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`nickname`) ON UPDATE CASCADE;


--
-- Metadatos
--
USE `phpmyadmin`;

--
-- Metadatos para categorias
--

--
-- Metadatos para ciudades
--

--
-- Metadatos para clientes
--

--
-- Metadatos para comentarios
--

--
-- Metadatos para ofertas
--

--
-- Metadatos para paises
--

--
-- Metadatos para promociones
--

--
-- Metadatos para promocionesservicios
--

--
-- Metadatos para proveedores
--

--
-- Metadatos para reservas
--

--
-- Metadatos para reservasitems
--

--
-- Metadatos para servicios
--

--
-- Metadatos para servicioscategorias
--

--
-- Metadatos para serviciosimagenes
--

--
-- Metadatos para usuarios
--

--
-- Volcado de datos para la tabla `pma__table_uiprefs`
--

INSERT INTO `pma__table_uiprefs` (`username`, `db_name`, `table_name`, `prefs`, `last_update`) VALUES('root', 'help4traveling', 'usuarios', '{"sorted_col":"`nickname` ASC"}', '2016-10-17 11:46:29');

--
-- Metadatos para usuariosimagenes
--

--
-- Metadatos para help4traveling
--

SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
