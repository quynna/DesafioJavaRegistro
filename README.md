# 📌 Desafío API RESTful - Registro de Usuarios

Este proyecto implementa una API RESTful con **Spring Boot**, **Hibernate**, **H2 Database**, **JWT** y validaciones personalizadas, que permite registrar usuarios junto a sus teléfonos, cumpliendo reglas de negocio y seguridad.

---

## ✅ Tecnologías

- Java 17
- Spring Boot 3.1+
- Spring Data JPA
- Hibernate
- H2 Database (en memoria)
- Jakarta Validation
- JWT (JSON Web Tokens)
- Gradle

---

## 📦 Estructura del Proyecto

usuarios/
├── controller/ → Controlador REST
├── modelo/ → Entidades JPA (User, Phone)
├── service/ → Lógica de negocio (registro, validación, JWT)
├── Repositorio/ → Repositorio JPA (UserRepository)
├── UsuariosApplication → Clase principal de Spring Boot
├── application.properties → Configuración


---

## 📌 Reglas de la API

### 🔐 Reglas de contraseña
La contraseña debe:
- Tener al menos 6 caracteres
- Incluir una **mayúscula**
- Incluir una **minúscula**
- Tener **al menos 2 números**

---

## 📥 Endpoint principal

### `POST /api/registro`

#### 🔸 Request Body (JSON)
```json
{
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "password": "Abc12345",
  "phones": [
    {
      "number": "12345678",
      "citycode": "2",
      "contrycode": "56"
    }
  ]
}
{
  "id": "e2a1a1a3-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "password": "Abc12345",
  "phones": [...],
  "created": "2025-07-10T15:30:45.108",
  "modified": "2025-07-10T15:30:45.108",
  "lastLogin": "2025-07-10T15:30:45.108",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "active": true
}

🔸 Errores posibles
400 BAD REQUEST: Campos inválidos

Contraseña inválida

Email ya registrado

500 INTERNAL SERVER ERROR: Fallo al guardar usuario

🧪 Cómo probar la API
Ejecutar la app:


./gradlew bootRun
Probar con Postman:

URL: http://localhost:8080/api/registro

Método: POST

Body: JSON (como el ejemplo de arriba)

Verificar los datos en la consola H2:

Navegar a http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:usuariosdb

Usuario: sa, Password: (vacío)

Tablas: USERS, PHONE

🔒 Token JWT
Al registrar un usuario exitosamente, se genera un token JWT firmado con HS256, que identifica al usuario. Este token se devuelve en la respuesta.

⚙️ Configuración (application.properties)
properties
spring.datasource.url=jdbc:h2:mem:usuariosdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.show-sql=true
jwt.secret=mysecretkey
📂 Clonar y ejecutar

git clone https://github.com/tu-usuario/desafio-usuarios-api.git
cd desafio-usuarios-api
./gradlew bootRun
👨‍💻 Autor
Evelik Quezada Muñoz
Desarrollador Backend Java | Spring Boot
