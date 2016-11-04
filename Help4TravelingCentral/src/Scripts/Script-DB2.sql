CREATE DATABASE  IF NOT EXISTS `help4traveling` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `help4traveling`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: help4traveling
-- ------------------------------------------------------
-- Server version	5.5.51

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `padre` varchar(50) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`nombre`,`padre`),
  KEY `padre` (`padre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES ('Vuelos','Categorias'),('Alojamientos','Categorias'),('Automoviles','Categorias'),('Cruceros','Categorias'),('Empresas','Vuelos'),('Tipo vuelo','Vuelos'),('Tipo alojamiento','Alojamientos'),('Ubicacion','Alojamientos'),('Habitaciones','Alojamientos'),('Tarifa','Automoviles'),('Tipo vehiculo','Automoviles'),('Marca','Automoviles'),('Mediterraneo','Cruceros'),('Mar negro','Cruceros'),('Caribe','Cruceros'),('Nilo','Cruceros'),('Alaska','Cruceros'),('Iberia','Empresas'),('American Airlines','Empresas'),('Air France','Empresas'),('TAM','Empresas'),('LowCost','Tipo vuelo'),('Standard','Tipo vuelo'),('First Class','Tipo vuelo'),('Hotel','Tipo alojamiento'),('Hostal','Tipo alojamiento'),('Apartamento','Tipo alojamiento'),('Casa','Tipo alojamiento'),('Playa','Ubicacion'),('Rural','Ubicacion'),('Montaña','Ubicacion'),('1 ambiente','Habitaciones'),('1 dormitorio','Habitaciones'),('2 dormitorios','Habitaciones'),('Mini','Tarifa'),('Economico','Tarifa'),('Standard','Tarifa'),('Full','Tarifa'),('Auto','Tipo vehiculo'),('Camioneta','Tipo vehiculo'),('Camion','Tipo vehiculo'),('Moto','Tipo vehiculo'),('Chevrolet','Marca'),('Peugeot','Marca'),('Daihatsu','Marca'),('Fiat','Marca'),('Ford','Marca');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudades`
--

DROP TABLE IF EXISTS `ciudades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudades` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `pais` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`nombre`),
  KEY `pais` (`pais`),
  CONSTRAINT `ciudades_ibfk_1` FOREIGN KEY (`pais`) REFERENCES `paises` (`nombre`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudades`
--

LOCK TABLES `ciudades` WRITE;
/*!40000 ALTER TABLE `ciudades` DISABLE KEYS */;
INSERT INTO `ciudades` VALUES ('Berlin','Alemania'),('Bariloche','Argentina'),('Buenos Aires','Argentina'),('Sidney','Australia'),('Florianopilis','Brasil'),('Caton','China'),('Pekin','China'),('Bogota','Colombia'),('Miami','EEUU'),('Madrid','España'),('VAlencia','España'),('Paris','Francia'),('Ginebra','Suiza'),('Montevideo','Uruguay');
/*!40000 ALTER TABLE `ciudades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `nickname` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`nickname`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`nickname`) REFERENCES `usuarios` (`nickname`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('BruceS'),('eWaston'),('JeffW'),('oWood');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentarios` (
  `servicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorServicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cliente` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `puntuacion` enum('1','2','3','4','5','6','7','8','9','10') COLLATE utf8_spanish_ci DEFAULT NULL,
  `titulo` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `comentario` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`servicio`,`proveedorServicio`,`cliente`),
  KEY `cliente` (`cliente`),
  CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`nickname`) ON UPDATE CASCADE,
  CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`servicio`, `proveedorServicio`) REFERENCES `servicios` (`nombre`, `proveedor`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ofertas`
--

DROP TABLE IF EXISTS `ofertas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ofertas` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedor` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`nombre`),
  KEY `proveedor` (`proveedor`),
  CONSTRAINT `ofertas_ibfk_1` FOREIGN KEY (`proveedor`) REFERENCES `proveedores` (`nickname`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofertas`
--

LOCK TABLES `ofertas` WRITE;
/*!40000 ALTER TABLE `ofertas` DISABLE KEYS */;
INSERT INTO `ofertas` VALUES ('TAM-FC','adippet'),('Casa para p4 BsAs','mHooch'),('Coche-Miami','mHooch'),('Luxury south beach corner apartment ','mHooch'),('Miami-Viaje','mHooch'),('Sudamerica-Casas','mHooch'),('Euro-Car-1','moody'),('Euro-Car-2','moody'),('Euro-Car-3','moody'),('Euro-Cars-E-F','moody'),('Euro-Cars-E-S','moody'),('Euro-Cars-S-F','moody'),('Floripa G. House','moody'),('Euro-Vuelo-FC','remus'),('Euro-Vuelo-LC','remus'),('Euro-Vuelo-S','remus'),('Euro-Vuelos-LC-FC','remus'),('Euro-Vuelos-S-FC','remus'),('Euro-Vuelos-S-LC','remus'),('Air-France-FC','tCook');
/*!40000 ALTER TABLE `ofertas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paises` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` VALUES ('Alemania'),('Argentina'),('Australia'),('Brasil'),('China'),('Colombia'),('EEUU'),('España'),('Francia'),('Suiza'),('Uruguay');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promociones`
--

DROP TABLE IF EXISTS `promociones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promociones` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descuento` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`nombre`,`proveedor`),
  KEY `proveedor` (`proveedor`),
  CONSTRAINT `promociones_ibfk_1` FOREIGN KEY (`nombre`) REFERENCES `ofertas` (`nombre`),
  CONSTRAINT `promociones_ibfk_2` FOREIGN KEY (`proveedor`) REFERENCES `ofertas` (`proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promociones`
--

LOCK TABLES `promociones` WRITE;
/*!40000 ALTER TABLE `promociones` DISABLE KEYS */;
INSERT INTO `promociones` VALUES ('Euro-Cars-E-F','moody',30,420),('Euro-Cars-E-S','moody',30,420),('Euro-Cars-S-F','moody',30,420),('Euro-Vuelos-LC-FC','remus',40,1290),('Euro-Vuelos-S-FC','remus',40,1440),('Euro-Vuelos-S-LC','remus',40,1170),('Miami-Viaje','mHooch',30,462),('Sudamerica-Casas','mHooch',50,135);
/*!40000 ALTER TABLE `promociones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocionesservicios`
--

DROP TABLE IF EXISTS `promocionesservicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocionesservicios` (
  `promocion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorPromocion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `servicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorServicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`promocion`,`proveedorPromocion`,`servicio`,`proveedorServicio`),
  KEY `servicio` (`servicio`,`proveedorServicio`),
  CONSTRAINT `promocionesservicios_ibfk_1` FOREIGN KEY (`promocion`, `proveedorPromocion`) REFERENCES `promociones` (`nombre`, `proveedor`) ON UPDATE CASCADE,
  CONSTRAINT `promocionesservicios_ibfk_2` FOREIGN KEY (`servicio`, `proveedorServicio`) REFERENCES `servicios` (`nombre`, `proveedor`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocionesservicios`
--

LOCK TABLES `promocionesservicios` WRITE;
/*!40000 ALTER TABLE `promocionesservicios` DISABLE KEYS */;
/*!40000 ALTER TABLE `promocionesservicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedores` (
  `nickname` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `empresa` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `link` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`nickname`),
  CONSTRAINT `proveedores_ibfk_1` FOREIGN KEY (`nickname`) REFERENCES `usuarios` (`nickname`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES ('adippet','Tam','http://www.tam.com.br'),('mHooch','Segundo Hogar','http://www.segundohogar.com'),('moody','EuroCar','http://www.eurocar.com.uy'),('remus','Lberia','http://www.iberia.com/uy/'),('tCook','AirFrance','http://www.airfrance.com');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservas` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `total` double DEFAULT NULL,
  `estado` enum('REGISTRADA','CANCELADA','PAGADA','FACTURADA') COLLATE utf8_spanish_ci DEFAULT NULL,
  `cliente` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`numero`),
  KEY `cliente` (`cliente`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`nickname`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (17,'2015-01-01',1100,'FACTURADA','oWood'),(18,'2015-01-01',3050,'CANCELADA','eWaston'),(19,'2015-03-05',80,'PAGADA','BruceS'),(20,'2015-05-08',600,'PAGADA','jeffW'),(21,'2015-08-07',200,'REGISTRADA','oWood'),(22,'2015-08-07',270,'REGISTRADA','eWaston'),(23,'2015-08-07',1700,'REGISTRADA','BruceS');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservasitems`
--

DROP TABLE IF EXISTS `reservasitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservasitems` (
  `reserva` int(11) NOT NULL,
  `oferta` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorOferta` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `inicio` date DEFAULT NULL,
  `fin` date DEFAULT NULL,
  PRIMARY KEY (`reserva`,`oferta`,`proveedorOferta`),
  KEY `oferta` (`oferta`),
  KEY `proveedorOferta` (`proveedorOferta`),
  CONSTRAINT `reservasitems_ibfk_1` FOREIGN KEY (`reserva`) REFERENCES `reservas` (`numero`) ON UPDATE CASCADE,
  CONSTRAINT `reservasitems_ibfk_2` FOREIGN KEY (`oferta`) REFERENCES `ofertas` (`nombre`) ON UPDATE CASCADE,
  CONSTRAINT `reservasitems_ibfk_3` FOREIGN KEY (`proveedorOferta`) REFERENCES `ofertas` (`proveedor`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservasitems`
--

LOCK TABLES `reservasitems` WRITE;
/*!40000 ALTER TABLE `reservasitems` DISABLE KEYS */;
INSERT INTO `reservasitems` VALUES (17,'Euro-Vuelo-S','remus',1,'2015-01-01','2015-01-01'),(18,'Euro-Vuelo-LC','remus',1,'2015-01-01','2015-01-01'),(18,'Euro-Vuelo-S','remus',2,'2015-01-01','2015-01-01'),(19,'Sudamerica-Casas','mHooch',1,'2015-03-05','2015-04-02'),(20,'Euro-Car-2','moody',1,'2015-05-08','2015-05-12'),(20,'Euro-Car-3','moody',1,'2015-05-08','2015-05-12'),(21,'Air-France-FC','tCook',2,'2015-08-07','2015-08-10'),(22,'Casa para p4 BsAs','mHooch',1,'2015-08-07','2015-08-14'),(22,'Miami-Viaje','mHooch',1,'2015-08-14','2015-08-21'),(23,'Euro-Vuelo-LC','remus',2,'2015-08-07','2015-08-07');
/*!40000 ALTER TABLE `reservasitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicios` (
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedor` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci,
  `precio` double DEFAULT NULL,
  `origen` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `destino` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`nombre`,`proveedor`),
  KEY `origen` (`origen`),
  KEY `destino` (`destino`),
  KEY `proveedor` (`proveedor`),
  CONSTRAINT `servicios_ibfk_2` FOREIGN KEY (`origen`) REFERENCES `ciudades` (`nombre`) ON UPDATE CASCADE,
  CONSTRAINT `servicios_ibfk_3` FOREIGN KEY (`destino`) REFERENCES `ciudades` (`nombre`) ON UPDATE CASCADE,
  CONSTRAINT `servicios_ibfk_4` FOREIGN KEY (`nombre`) REFERENCES `ofertas` (`nombre`),
  CONSTRAINT `servicios_ibfk_5` FOREIGN KEY (`proveedor`) REFERENCES `ofertas` (`proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios`
--

LOCK TABLES `servicios` WRITE;
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
INSERT INTO `servicios` VALUES ('Air-France-FC','tCook','¡Un vuelo de primera! Excelencia y experiencia en mejorar sus viajes',100,'Paris','Berlin'),('Casa para p4 BsAs','mHooch','Esta hermosa casa, se encuentra ubicada en el corazón de Buenos Aires y ofrece una capacidad para cuatro personas. La propiedad cuenta con un dormitorio con dos camas simples, que pueden transformarse en una matrimonial y dos baños completos, que incluyen toallas.',80,'Buenos Aires',NULL),('Coche-Miami','mHooch','A useful car to travel around Miami',360,'Miami',NULL),('Euro-Car-1','moody','S4 Euro-Car. Autos de buena calidad y comodidad. Versión Económica',300,'Madrid','Valencia'),('Euro-Car-2','moody','Euro-Car. Autos de buena calidad y comodidad. Versión Standard.',300,'Madrid','Valencia'),('Euro-Car-3','moody','6 Euro-Car. Autos de buena calidad y comodidad. Una camioneta para toda la familia',300,'Valencia',NULL),('Euro-Vuelo-FC','remus','Vuelo de primera clase. Excelente atención, comodidad y servicio.',1300,'Montevideo','Valencia'),('Euro-Vuelo-LC','remus','Vuelo con excelente atención y comodidad a un precio accesible.',850,'Montevideo','Valencia'),('Euro-Vuelo-S','remus','Vuelo con excelente atención y comodidad',1100,'Montevideo','Valencia'),('Floripa G. House','mHooch','Estamos a sólo unos pasos de un supermercado, restaurantes, cajero automático, gasolinera, farmacia, gimnasio, etc. Lagoa da Conceição es 7 km de nuestra casa de huéspedes y tarda sólo 10-15 minutos en el transporte público. Allí se encuentra una buena vida nocturna con bares y música en vivo',190,'Florianopilis',NULL),('Luxury south beach corner apartment','mHooch','Beautiful large 2 bedrooms 2 bathrooms apartment CORNER UNIT. Marble floor  throughout, beautiful open kitchen, granite counter top, spacious dining room area and living room area. Spectacular views of Miami from all windows and balcony',300,'Miami',NULL),('TAM-FC','adippet','¡Un vuelo de primera! Excelencia y experiencia.',150,'Florianopilis','Pekin');
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicioscategorias`
--

DROP TABLE IF EXISTS `servicioscategorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicioscategorias` (
  `servicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `proveedorServicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `categoria` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `categoriaPadre` varchar(50) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`servicio`,`proveedorServicio`,`categoria`,`categoriaPadre`),
  KEY `categoria` (`categoria`,`categoriaPadre`),
  CONSTRAINT `servicioscategorias_ibfk_1` FOREIGN KEY (`categoria`, `categoriaPadre`) REFERENCES `categorias` (`nombre`, `padre`) ON UPDATE CASCADE,
  CONSTRAINT `servicioscategorias_ibfk_2` FOREIGN KEY (`servicio`, `proveedorServicio`) REFERENCES `servicios` (`nombre`, `proveedor`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicioscategorias`
--

LOCK TABLES `servicioscategorias` WRITE;
/*!40000 ALTER TABLE `servicioscategorias` DISABLE KEYS */;
INSERT INTO `servicioscategorias` VALUES ('Casa para p4 BsAs','mHooch','2 dormitorios','Habitaciones'),('Floripa G. House','mHooch','2 dormitorios','Habitaciones'),('Luxury south beach corner apartment','mHooch','2 dormitorios','Habitaciones'),('Air-France-FC','tCook','Air France','Empresas'),('Euro-Car-1','moody','Auto','Tipo vehiculo'),('Euro-Car-2','moody','Auto','Tipo vehiculo'),('Luxury south beach corner apartment','mHooch','Auto','Tipo vehiculo'),('Euro-Car-3','moody','Camioneta','Tipo vehiculo'),('Casa para p4 BsAs','mHooch','Casa','Tipo alojamiento'),('Floripa G. House','mHooch','Casa','Tipo alojamiento'),('Coche-Miami','mHooch','Chevrolet','Marca'),('Euro-Car-1','moody','Chevrolet','Marca'),('Euro-Car-2','moody','Chevrolet','Marca'),('Euro-Car-3','moody','Chevrolet','Marca'),('Coche-Miami','mHooch','Economico','Tarifa'),('Euro-Car-1','moody','Economico','Tarifa'),('Air-France-FC','tCook','First Class','Tipo vuelo'),('Euro-Vuelo-FC','remus','First Class','Tipo vuelo'),('Euro-Car-3','moody','Full','Tarifa'),('Luxury south beach corner apartment','mHooch','Hotel','Tipo alojamiento'),('Euro-Vuelo-FC','remus','Iberia','Empresas'),('Euro-Vuelo-LC','remus','Iberia','Empresas'),('Euro-Vuelo-S','remus','Iberia','Empresas'),('Euro-Vuelo-LC','remus','LowCost','Tipo vuelo'),('TAM-FC','adippet','Playa','Ubicacion'),('Euro-Car-2','moody','Standar','Tarifa'),('Euro-Vuelo-S','remus','Standar','Tipo vuelo'),('TAM-FC','adippet','TAM','Empresas');
/*!40000 ALTER TABLE `servicioscategorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviciosimagenes`
--

DROP TABLE IF EXISTS `serviciosimagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serviciosimagenes` (
  `servicio` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `imagen` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`servicio`,`imagen`),
  CONSTRAINT `serviciosimagenes_ibfk_1` FOREIGN KEY (`servicio`) REFERENCES `servicios` (`nombre`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviciosimagenes`
--

LOCK TABLES `serviciosimagenes` WRITE;
/*!40000 ALTER TABLE `serviciosimagenes` DISABLE KEYS */;
INSERT INTO `serviciosimagenes` VALUES ('Air-France-FC',' http://bit.ly/1MPIlFE'),('Casa para p4 BsAs',' http://bit.ly/1JRo55O'),('Casa para p4 BsAs',' http://bit.ly/1KgftnS'),('Coche-Miami',' http://bit.ly/1Jsx5rx'),('Euro-Car-1',' http://bit.ly/1Jsx5rx'),('Euro-Car-2',' http://bit.ly/1POes6T'),('Euro-Car-3',' http://bit.ly/1Ua0ywm'),('Euro-Vuelo-FC',' http://bit.ly/1ha3Ine'),('Euro-Vuelo-LC',' http://bit.ly/1ha3Ine'),('Euro-Vuelo-S',' http://bit.ly/1ha3Ine'),('Floripa G. House',' http://bit.ly/1ha1LY4'),('Floripa G. House',' http://bit.ly/1hDqGnC'),('Floripa G. House',' http://bit.ly/1Iavd5R'),('Luxury south beach corner apartment',' http://bit.ly/1hcoOkY'),('TAM-FC',' http://bit.ly/1JhIFJ2');
/*!40000 ALTER TABLE `serviciosimagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `nickname` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(50) CHARACTER SET ucs2 NOT NULL,
  `imagen` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fechaNac` date DEFAULT NULL,
  PRIMARY KEY (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('adippet','Armando','Dippet','Dippet123','tam@outlook.com',NULL,'1967-02-12'),('BruceS','Bruce','Sewell','Sewell123','bruce.swell@gmail.com',NULL,'1978-12-03'),('eWaston','Emma','Watson','Watson123','e.watson@gmail.com',NULL,'1990-04-15'),('jeffW','jeff','Wiliams','Wiliams123','jeff.williams@gmail.com',NULL,'1984-11-27'),('mHooch','Madam','Hooch','Hooch123','segHogar@gmail.com',NULL,'1963-08-05'),('moody','Alastor','Moody','Moody123','eu.car@eucar.com',NULL,'1965-09-02'),('oWood','Oliver','Wood','Wood123','quidditch28@gmail.com',NULL,'1988-12-28'),('remus','Remus','Lupin','Lupin123','iberia@gmail.com',NULL,'1970-05-04'),('tCook','Tim','Cook','Cook123','air.f@gmail.com',NULL,'1960-11-01');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuariosimagenes`
--

DROP TABLE IF EXISTS `usuariosimagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuariosimagenes` (
  `usuario` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `imagen` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`usuario`,`imagen`),
  CONSTRAINT `usuariosimagenes_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`nickname`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariosimagenes`
--

LOCK TABLES `usuariosimagenes` WRITE;
/*!40000 ALTER TABLE `usuariosimagenes` DISABLE KEYS */;
INSERT INTO `usuariosimagenes` VALUES ('adippet','http://bit.ly/1U4SdPY'),('eWaston','http://bit.ly/1hEGTcq'),('mhooch','http://bit.ly/1hEGDdb'),('moody','http://bit.ly/1hcs2os'),('oWood','http://bit.ly/1Kifw2j'),('remus','http://bit.ly/1NHepdk'),('tCook','http://bit.ly/1LvodoZ');
/*!40000 ALTER TABLE `usuariosimagenes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-04 23:45:23
