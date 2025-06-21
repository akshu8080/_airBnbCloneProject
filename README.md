# 🏨 AirBnb Clone – Backend

This is the backend of an **Airbnb-inspired hotel booking application**, built with **Spring Boot**, **MySQL**, and **RESTful APIs**. It supports user registration, property listings, bookings, and authentication.

---

## 🚀 Features

- 🔐 Secure user authentication with JWT
- 🏘️ Add and browse property listings
- 📅 Book available properties with date validation
- 👤 View booking history and user-specific data
- 🌐 Fully RESTful API design
- 📖 Integrated API documentation with Swagger UI

---

## 🛠️ Tech Stack

| Layer            | Technology              |
|------------------|--------------------------|
| Language         | Java 17+                 |
| Framework        | Spring Boot              |
| Database         | MySQL                    |
| ORM              | Hibernate / Spring Data JPA |
| Security         | Spring Security + JWT    |
| API Documentation| Springdoc OpenAPI (Swagger) |
| Build Tool       | Maven                    |

---

## 📁 Project Structure

🔐 JWT Security
This project uses JWT (JSON Web Token) for securing endpoints. Once logged in, users receive a token which must be sent in the Authorization header.
