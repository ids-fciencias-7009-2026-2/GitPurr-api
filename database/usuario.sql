DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(150) NOT NULL,
                         email VARCHAR(150) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         token VARCHAR(255)
);

-- se añadieron timestamps:
ALTER TABLE usuario
ADD created_at DATETIME,
ADD updated_at DATETIME,
ADD deleted_at DATETIME;
