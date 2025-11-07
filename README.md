## BankPro — Modern Microservice-Based Banking System

## 🚀 Overview

BankPro is a comprehensive backend banking system designed using a microservice architecture pattern with Spring Boot. This project simulates real-world banking operations such as customer onboarding, account handling, fund management, and dashboard aggregation, built with scalable, secure, and testable service-oriented architecture.

The goal is to give developers hands-on experience with microservices, multithreading, concurrency, secure RESTful APIs, and asynchronous service communication.

---

## 📆 Project Phases

### ✅ Phase 1: Customer Service

* User Registration
* User Login (JWT Token Generation)
* Profile Retrieval
* JWT Integration
* Swagger Documentation

### ⏳ Phase 2: Account Service

* Create Account
* Check Balance
* List User Accounts
* Account-Customer Mapping

### ⏳ Phase 3: Transaction Service

* Deposit & Withdraw
* Fund Transfers
* Transaction History

### ⏳ Phase 4: Dashboard Service

* Asynchronous aggregation using `CompletableFuture`
* Fetch user, account, and transaction data concurrently

### ⏳ Phase 5: Core Enhancements

* Swagger 3 integration
* Constants and centralized config layer
* Global Exception Handling via `@ControllerAdvice`

### ⏳ Phase 6: Multithreading Implementation

* Async transaction logging with `ExecutorService`
* Balance validation via `Callable` + `Future`
* Dashboard async service with `CompletableFuture`

### ⏳ Phase 7: Optional Advanced Features

* Spring Cloud Gateway
* Eureka Service Discovery
* Dockerize microservices
* Redis caching or Kafka integration (optional)

---

## 🪧 Architecture Overview

### Microservice Structure

```
bankpro/
├── customer-service/
├── account-service/
├── transaction-service/
├── dashboard-service/
└── common-libs/ (optional shared modules)
```

### Standard Directory Structure (per service)

```
/src
  /controller
  /service
  /repository
  /entity
  /dto
  /config
  /exception
  /util
```

---

## 🔧 Tech Stack

| Layer            | Technology                         |
| ---------------- | ---------------------------------- |
| Language         | Java 21                            |
| Framework        | Spring Boot 3.x                    |
| Build Tool       | Maven                              |
| ORM              | Spring Data JPA (Hibernate)        |
| DB               | MySQL                              |
| Security         | Spring Security, JWT               |
| API Docs         | Swagger (SpringDoc OpenAPI 3)      |
| Dev Tools        | Lombok, SLF4J, Logback             |
| Testing          | JUnit, Mockito                     |
| Async & Threads  | ExecutorService, CompletableFuture |
| Optional Tools   | Eureka, Spring Cloud Gateway       |
| Containerization | Docker (optional)                  |

---

## 🔒 Security

* Password hashing with BCrypt
* Stateless authentication using JWT
* Role-based access control using `@PreAuthorize`
* Token-based API security

---

## 🔍 API Documentation

* Swagger UI: `http://localhost:8080/swagger-ui.html`
* OpenAPI Spec: `http://localhost:8080/v3/api-docs`

Each service exposes its own OpenAPI spec.

---

## 🎯 Features & Best Practices

* Clean Layered Architecture (Controller -> Service -> Repository)
* DTO-based request/response handling
* Strong validation using `javax.validation`
* Global exception handling using `@ControllerAdvice`
* Reusable `RequestResponse<T>` wrapper for all APIs
* Proper use of Java 21 features

---

## 🚪 Authentication & Authorization

* JWT tokens issued on login
* JWT parsed using filter in security config
* Token role-based authority check
* Only authenticated users can access protected routes

---

## 🪤 Concurrency & Performance

* Async operations using Java `CompletableFuture`
* Transaction logging using `ExecutorService`
* Balance checking logic using `Callable` + `Future`
* Parallel service calls in dashboard

---

## 🌐 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/bankpro.git
cd bankpro
```

### 2. Start MySQL

* Create databases: `bankpro_customer`, `bankpro_account`, etc.
* Update credentials in `application.properties`

### 3. Run Customer Service

```bash
cd customer-service
./mvnw spring-boot:run
```

### 4. Open Swagger UI

```
http://localhost:8080/swagger-ui.html
```

---

## 📒 Branching Strategy

| Branch Name           | Purpose                                 |
| --------------------- | --------------------------------------- |
| `main` / `master`     | Stable production code                  |
| `dev`                 | Development branch for merging features |
| `feature/customer`    | Customer microservice development       |
| `feature/account`     | Account microservice development        |
| `feature/transaction` | Transaction microservice development    |
| `feature/dashboard`   | Dashboard async service                 |
| `infra/docker`        | Docker-related setup                    |
| `feature/security`    | JWT and Spring Security configs         |

---

## 🙌 Author

**Aniket Kamlesh**
*Designed and built with industry standards, learning goals, and production-grade practices.*

---

## 📊 Outcomes

* Solid grasp on Spring Boot microservice development
* Hands-on with real-world concurrency in Java
* Swagger-driven, secure, documented APIs
* Ready for cloud-native enhancements

---

> “BankPro equips developers with enterprise-grade microservice practices, enabling you to build real-time, scalable, and secure banking systems like those in production fintech applications.”

---
