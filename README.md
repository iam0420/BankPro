# BankPro — Enterprise-Grade Banking Backend System

### Author: **Aniket Kamlesh**

### Version: `v1.0.0`

---

## 🚀 Project Overview

**BankPro** is a secure, modular, and extensible banking platform backend developed using modern Java and Spring Boot technologies. It provides robust customer registration, authentication, role-based access control, and scalable architecture designed to evolve into a full microservices system.

> **Objective:** Build a professional-grade banking backend that adheres to industry standards followed by large-scale organizations like Google, Microsoft, and enterprise banking platforms.

---

## 🔄 System Architecture (Current & Future)

### Phase 1: Monolithic Module

```
+--------------------------+
|     Spring Boot App      |
|--------------------------|
| - Customer Module        |
| - Auth (JWT)             |
| - Swagger Docs           |
| - Role Based Access      |
| - Global Exception       |
| - MySQL DB Integration   |
+--------------------------+
```

### Phase 2: Microservices (Planned)

```
+--------------+     +-------------------+     +-------------------+
|  API Gateway | --> |  Customer Service | --> |  MySQL            |
+--------------+     +-------------------+     +-------------------+
        |                    |
        v                    v
  Auth Service         Transaction Service
        |                    |
        v                    v
    Notification         Reporting Engine
```

---

## 🌐 Technologies & Tools

| Layer           | Technology / Tool               |
| --------------- | ------------------------------- |
| Language        | Java 21                         |
| Framework       | Spring Boot 3.5                 |
| API Docs        | Springdoc OpenAPI 2.x + Swagger |
| Security        | Spring Security, JWT            |
| Database        | MySQL 8                         |
| ORM             | Hibernate (JPA)                 |
| Validation      | Jakarta Validation              |
| Build Tool      | Maven                           |
| Dev Tools       | IntelliJ, Docker (planned)      |
| Logging         | SLF4J, Logback                  |
| Version Control | GitHub                          |

---

## 🚧 Core Modules Implemented

### 1. Customer Module

* Register customer with validation
* Login customer with encrypted credentials
* Fetch profile (authenticated)
* Admin APIs to list all customers
* Admin API to promote user

### 2. Security Module

* JWT-based stateless authentication
* Role-based access control (`ROLE_USER`, `ROLE_ADMIN`)
* Password encryption (BCrypt)

### 3. Documentation

* Swagger UI at `/swagger-ui.html`
* Fully annotated DTOs and endpoints

### 4. Error Handling

* Centralized exception handling
* Custom error response model
* Custom business exceptions with error codes

---

## 🔍 Sample API Endpoints

| Method | Endpoint                                  | Description                    | Role       |
| ------ | ----------------------------------------- | ------------------------------ | ---------- |
| POST   | `/api/v1/customers/register`              | Register a new user            | Public     |
| POST   | `/api/v1/customers/login`                 | Login with credentials         | Public     |
| GET    | `/api/v1/customers/profile`               | Get current user profile       | User/Admin |
| GET    | `/api/v1/customers/admin/customers`       | Fetch all registered customers | Admin Only |
| PUT    | `/api/v1/customers/admin/promote/{email}` | Promote user to admin          | Admin Only |

---

## 📃 API Docs (Swagger)

* Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* OpenAPI Docs: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📝 Setup & Run Locally

```bash
# Clone repository
git clone https://github.com/iam0420/BankPro.git
cd BankPro

# Configure MySQL DB in application.properties or .env
spring.datasource.url=jdbc:mysql://localhost:3306/bankpro_customer
spring.datasource.username=root
spring.datasource.password=your_password

# Run the application
./mvnw spring-boot:run

# Swagger Docs
Visit: http://localhost:8080/swagger-ui.html
```

---

## 🌎 Environment Configuration (application.properties)

```properties
spring.application.name=customer-service
server.port=8080
spring.profiles.active=dev
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/bankpro_customer
```

---

## 🌐 Planned Features & Roadmap

| Feature                         | Status    |
| ------------------------------- | --------- |
| Customer microservice           | ✅ Done    |
| Authentication service          | ⏳ Planned |
| Account & transaction service   | ⏳ Planned |
| Email/OTP verification          | ⏳ Planned |
| Dockerization                   | ⏳ Planned |
| API Gateway                     | ⏳ Planned |
| Monitoring (Prometheus/Grafana) | ⏳ Planned |

---

## 📊 Contribution Standards

* Follow **Clean Code** and SOLID principles
* Add Javadoc & Swagger annotations to all endpoints
* Exceptions must extend `BaseException`
* Use DTOs with Jakarta validation annotations
* Never expose raw passwords or sensitive tokens

---

## 👥 Maintainer & Credits

Developed & maintained by **Aniket Kamlesh**
GitHub: [iam0420](https://github.com/iam0420)

---

## 📖 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
