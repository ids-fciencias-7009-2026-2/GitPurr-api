# GitPurr API - Sistema de Adopción 

Este proyecto es una API construida con **Spring Boot**, **Kotlin**, **JPA** y **Maven**. 

---

## Requisitos Previos
* **Java 21** o superior.
* **MariaDB Server** instalado y activo.
* **Maven** (incluido en el proyecto mediante el wrapper `./mvnw`).

---

## Configuración de la Base de Datos (MariaDB)

Para que el proyecto funcione localmente, es necesario inicializar la base de datos desde cero utilizando el script de esquema principal.

**1. Levantar el servicio (en Linux)**
Si el servicio no está activo, ejecute:
```bash
sudo systemctl start mariadb
```

**2. Construir la Base de Datos y Tablas**
Acceda como root asegurándose de estar en la raíz del proyecto y ejecute el esquema:
```bash
sudo mariadb -u root
source database/schema.sql;
```

**3. Crear Usuario y Permisos (Primera vez)**
Si no ha configurado el usuario de la API localmente, ejecute lo siguiente en la consola de MariaDB:
```sql
CREATE USER 'gitpurr_user'@'localhost' IDENTIFIED BY 'admin123';
GRANT ALL PRIVILEGES ON AppAdopcion.* TO 'gitpurr_user'@'localhost';
FLUSH PRIVILEGES;
```

---

## Configuración del Entorno (.env)

La aplicación no arrancará sin su archivo de configuración. Cree un archivo llamado `.env` en la raíz del proyecto (al mismo nivel que `pom.xml`) con el siguiente contenido:

```env
URL_DB=localhost:3306/AppAdopcion
USER_DB=gitpurr_user
PASSWORD_DB=admin123
```
> **Nota:** Este archivo está excluido en el `.gitignore` por seguridad y no debe subirse al repositorio.

---

## Cómo levantar el Proyecto

### Opción 1: Desde la Terminal
Ejecute el siguiente comando para limpiar, compilar y arrancar el servidor:
```bash
./mvnw clean spring-boot:run
```

### Opción 2: Desde IntelliJ IDEA
1. Asegúrese de que el archivo `.env` esté en la raíz.
2. Haga clic en el botón **Run** en la clase `SistemaAdopcionApplication.kt`.

### Verificación de éxito
La aplicación se ha iniciado correctamente si en la consola aparece: `Tomcat started on port(s): 8080 (http)` y no hay errores de conexión.

---

## Pruebas
Puede probar los endpoints utilizando la colección de Postman incluida en la carpeta `/postman`. Asegúrese de registrar un usuario e iniciar sesión para obtener su token Bearer.
