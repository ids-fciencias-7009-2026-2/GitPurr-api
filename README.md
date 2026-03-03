GitPurr API - Sistema de Adopción 

Este proyecto es una API construida con **Spring Boot**, **Kotlin**, **JPA** y **Maven**. 

---

## Requisitos Previos
* **Java 21** o superior.
* **MariaDB Server** instalado y activo.
* **Maven** (incluido en el proyecto mediante el wrapper `./mvnw`).

---

Configuración de la Base de Datos (MariaDB)

Para que el proyecto funcione localmente:

Levantar el servicio (en Linux)
Si el servicio no está activo, ejecute:

sudo systemctl start mariadb


Crear Base de Datos, Usuario y Permisos

Acceda como root (`sudo mariadb -u root`) y ejecute los siguientes comandos:
Crear la Tabla Usuario

Utilice el script ubicado en `/database/usuario.sql` o ejecute lo siguiente:

```sql
USE AppAdopcion;
La aplicación no arrancará sin el archivo de configuración. Cree un archivo llamado .env en la raíz del proyecto (al mismo nivel que `pom.xml`) 

> **Nota:** Este archivo está excluido en el `.gitignore` por seguridad.

---

## Cómo levantar el Proyecto

### Opción 1: Desde la Terminal

Ejecute el siguiente comando para limpiar, compilar y arrancar:

```bash
./mvnw clean spring-boot:run

```

Opción 2: Desde IntelliJ IDEA

1. Asegúrese de que el archivo `.env` esté en la raíz.
2. Haga clic en el botón **Run** en la clase `SistemaAdopcionApplication.kt`.

### Verificación de éxito

La aplicación se ha iniciado correctamente si en la consola aparece:
`Tomcat started on port(s): 8080 (http)` y no hay errores de `HikariPool` o `DotenvException`.

---

Insertar un Usuario de Prueba


INSERT INTO usuario (nombre, email, password, token) 
VALUES ('Ayudante de Laboratorio', 'ayudante@test.com', 'admin123', 'token_temporal_abc123');

B. Consultar Usuarios Registrados
SQL

SELECT * FROM usuario;
