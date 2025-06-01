# IT-Master-BackEnd-REST

This repository contains the **backend REST API** for the IT Master online course management system. It is built using **Spring Boot** and provides services for user management, course handling, and admin functionalities. It is designed to interact with the frontend counterpart: [IT-Master-FrontEnd-React](https://github.com/Yugal-kosamshile/IT-Master-FrontEnd-React).

---

## 🌐 Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 In-Memory Database
- RESTful APIs
- Spring Web
- Spring Security (if extended)
- Maven

---

## 🚀 Getting Started

### Prerequisites

- JDK 17 or later
- Maven
- Git

### Clone the Repository

```bash
git clone https://github.com/Yugal-kosamshile/IT-Master-BackEnd-REST.git
cd IT-Master-BackEnd-REST
````

### Run the Application

```bash
./mvnw spring-boot:run
```

Or from your IDE, run `ITMasterApplication.java`.

The backend will start on `http://localhost:8082`.

---

## ⚙️ Configuration

Located in `application.properties`:

```properties
server.port=8082
spring.datasource.url=jdbc:h2:mem:~/test
spring.datasource.username=sa
spring.datasource.password=pass@123
spring.h2.console.path=/h2-console
```

> Access the H2 console at: `http://localhost:8082/h2-console`
> JDBC URL: `jdbc:h2:mem:~/test`

---

## 🧩 Modules & Features

### 👤 User Management

* `UserController.java`

  * Create user
  * Get user(s)
  * Update user info
  * Delete user

### 📚 Course Management

* `Course.java`

  * Entity representing courses
* CRUD endpoints for course operations (assumed in service/controller)

### 🔐 Admin Management

* `AdminController.java`

  * Admin-level operations (e.g., approving users or managing courses)

---

## 🔗 Frontend Integration

This backend is designed to work with:
📦 [IT-Master-FrontEnd-React](https://github.com/Yugal-kosamshile/IT-Master-FrontEnd-React)

* Ensure both frontend and backend are running simultaneously.
* API base URL in frontend should be set to: `http://localhost:8082`

---

## 🧪 Testing

You can test the endpoints using:

* Postman / Swagger
* Browser (for GETs)
* Frontend app interaction

---

## 📬 Contact

For inquiries or support: [yugalkosamshile2002@gmail.com](yugalkosamshile2002@gmail.com)
