 # 🏨 AirBnb Clone – Backend

This is the backend of an **Airbnb-inspired hotel booking application**, built with **Spring Boot**, **PostgreSQL**, and **RESTful APIs**. It supports user registration, property listings, bookings, and authentication.

---

## 🚀 Features

* 🔐 Secure user authentication with JWT.
* 🏘️ Add and browse property listings.
* 📅 Book available properties with date validation.
* 👤 View booking history and user-specific data.
* 🌐 Fully RESTful API design.
* 💸 Integration with Stripe for handling payments.
* Dynamic pricing strategies based on demand, holidays, and urgency.
* ✔️ Scheduled tasks for updating prices.

---

## 🛠️ Tech Stack

| Layer            | Technology              |
|------------------|--------------------------|
| Language         | Java 21+                 |
| Framework        | Spring Boot              |
| Database         | PostgreSQL                    |
| ORM              | Hibernate / Spring Data JPA |
| Security         | Spring Security + JWT    |
| API Documentation| Springdoc OpenAPI (Swagger) |
| Build Tool       | Maven                    |
| Payments | Stripe |

---

## 📁 Project Structure

The project follows a standard layered architecture:
Markdown

# 🏨 AirBnb Clone – Backend

This is the backend of an **Airbnb-inspired hotel booking application**, built with **Spring Boot**, **PostgreSQL**, and **RESTful APIs**. It supports user registration, property listings, bookings, and authentication.

---

## 🚀 Features

* 🔐 Secure user authentication with JWT.
* 🏘️ Add and browse property listings.
* 📅 Book available properties with date validation.
* 👤 View booking history and user-specific data.
* 🌐 Fully RESTful API design.
* 💸 Integration with Stripe for handling payments.
* Dynamic pricing strategies based on demand, holidays, and urgency.
* ✔️ Scheduled tasks for updating prices.

---

## 🛠️ Tech Stack

| Layer            | Technology              |
|------------------|--------------------------|
| Language         | Java 21+                 |
| Framework        | Spring Boot              |
| Database         | PostgreSQL                    |
| ORM              | Hibernate / Spring Data JPA |
| Security         | Spring Security + JWT    |
| API Documentation| Springdoc OpenAPI (Swagger) |
| Build Tool       | Maven                    |
| Payments | Stripe |

---

## 📁 Project Structure

The project follows a standard layered architecture:

├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───codingshuttle
│   │   │           └───airBnb
│   │   │               └───airBnbCloneProject
│   │   │                   ├───advice
│   │   │                   ├───config
│   │   │                   ├───controller
│   │   │                   ├───DTO
│   │   │                   ├───Entity
│   │   │                   ├───exceptions
│   │   │                   ├───repository
│   │   │                   ├───security
│   │   │                   ├───services
│   │   │                   └───stratergy
│   │   └───resources
│   └───test
└───.gitignore
└───pom.xml
└───README.md

```markdown
# 🏨 AirBnb Clone – Backend

This is the backend of an **Airbnb-inspired hotel booking application**, built with **Spring Boot**, **PostgreSQL**, and **RESTful APIs**. It supports user registration, property listings, bookings, and authentication.

---

## 🚀 Features

* 🔐 Secure user authentication with JWT.
* 🏘️ Add and browse property listings.
* 📅 Book available properties with date validation.
* 👤 View booking history and user-specific data.
* 🌐 Fully RESTful API design.
* 💸 Integration with Stripe for handling payments.
* Dynamic pricing strategies based on demand, holidays, and urgency.
* ✔️ Scheduled tasks for updating prices.

---

## 🛠️ Tech Stack

| Layer            | Technology              |
|------------------|--------------------------|
| Language         | Java 21+                 |
| Framework        | Spring Boot              |
| Database         | PostgreSQL                    |
| ORM              | Hibernate / Spring Data JPA |
| Security         | Spring Security + JWT    |
| API Documentation| Springdoc OpenAPI (Swagger) |
| Build Tool       | Maven                    |
| Payments | Stripe |

---

## 📁 Project Structure

The project follows a standard layered architecture:

```

├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───codingshuttle
│   │   │           └───airBnb
│   │   │               └───airBnbCloneProject
│   │   │                   ├───advice
│   │   │                   ├───config
│   │   │                   ├───controller
│   │   │                   ├───DTO
│   │   │                   ├───Entity
│   │   │                   ├───exceptions
│   │   │                   ├───repository
│   │   │                   ├───security
│   │   │                   ├───services
│   │   │                   └───stratergy
│   │   └───resources
│   └───test
└───.gitignore
└───pom.xml
└───README.md

````

---

## 🔐 JWT Security
This project uses JWT (JSON Web Token) for securing endpoints. Once logged in, users receive a token which must be sent in the `Authorization` header. The `JWTAuthFilter` intercepts requests to validate the token.

---

## ⚙️ Configuration

The application settings are in `src/main/resources/application.properties`. Key properties to configure are:

* **Database:**
    * `spring.datasource.url`
    * `spring.datasource.username`
    * `spring.datasource.password`
* **JWT:**
    * `jwt.secretKey`
* **Stripe:**
    * `stripe.secret.key`
    * `stripe.webhook.secret`
* **Frontend URL:**
    * `frontend.url`

---

## API Endpoints

Here are the primary API endpoints:

### Authentication

* `POST /auth/signup`: Register a new user.
* `POST /auth/login`: Authenticate and receive JWT tokens.
* `POST /auth/refresh`: Refresh the access token.

### Hotels

* `POST /admin/hotels`: Create a new hotel.
* `GET /admin/hotels/{hotelId}`: Get hotel details.
* `PUT /admin/hotels/{hotelId}`: Update a hotel.
* `DELETE /admin/hotels/{hotelId}`: Delete a hotel.
* `PATCH /admin/hotels/{hotelId}`: Activate a hotel.
* `GET /hotels/search`: Search for hotels.
* `GET /hotels/{hotelId}/info`: Get hotel information and rooms.

### Rooms

* `POST /admin/hotels/{hotelId}/rooms`: Add a new room to a hotel.
* `GET /admin/hotels/{hotelId}/rooms`: Get all rooms in a hotel.
* `GET /admin/hotels/{hotelId}/rooms/{roomId}`: Get room details.
* `DELETE /admin/hotels/{hotelId}/rooms/{roomId}`: Delete a room.

### Bookings

* `POST /bookings/init`: Initialize a new booking.
* `POST /bookings/{bookingId}/addGuests`: Add guests to a booking.
* `POST /bookings/{bookingId}/payments`: Initiate payment for a booking.
* `POST /bookings/{bookingId}/cancel`: Cancel a booking.
* `GET /bookings/{bookingId}/status`: Get the status of a booking.

### Webhooks

* `POST /webhook/payment`: Stripe webhook to handle payment events.

---

## 🚀 Getting Started

To run this project locally:

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/akshu8080/airbnbcloneproject.git](https://github.com/akshu8080/airbnbcloneproject.git)
    ```
2.  **Configure the application:**
    * Open `src/main/resources/application.properties`.
    * Update the database connection details (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`).
    * Set your JWT secret key (`jwt.secretKey`).
    * Add your Stripe API keys (`stripe.secret.key`, `stripe.webhook.secret`).
3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

The application will be accessible at `http://localhost:8080/api/v1`.
````
