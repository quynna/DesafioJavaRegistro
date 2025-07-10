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

