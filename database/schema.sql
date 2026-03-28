CREATE DATABASE IF NOT EXISTS AppAdopcion;
USE AppAdopcion;

-- Limpieza previa para ejecución segura
DROP TABLE IF EXISTS usuario;

-- Estructura de la tabla usuario (estado actual real)
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME
);
