CREATE DATABASE IF NOT EXISTS AppAdopcion;
USE AppAdopcion;

DROP TABLE IF EXISTS animalito;
DROP TABLE IF EXISTS usuario;

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

CREATE TABLE animalito (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(150) NOT NULL,
                           edad VARCHAR(50) NOT NULL,
                           especie VARCHAR(80) NOT NULL,
                           raza VARCHAR(120),
                           sexo VARCHAR(30) NOT NULL,
                           tamano VARCHAR(50) NOT NULL,
                           descripcion TEXT,
                           codigo_postal VARCHAR(10) NOT NULL,
                           latitud_aprox DOUBLE,
                           longitud_aprox DOUBLE,
                           foto_url VARCHAR(500),
                           usuario_id BIGINT NOT NULL,
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           deleted_at DATETIME,
                           CONSTRAINT fk_animalito_usuario
                               FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);