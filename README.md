# 🗳️ SmartVote Hub: Online Voting System

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java Version](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.x-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)](https://www.thymeleaf.org/)

A secure, robust, and full-stack **Online Voting System** designed to handle user registrations, secure password authentication, election management, and real-time live voting updates. Built using modern enterprise standards with **Spring Boot MVC**, **Spring Data JPA**, and **Thymeleaf HTML templates**.

---

## 🚀 Key Features

* **🔒 Secure Authentication:** Custom registration and dynamic login system validation.
* **👥 Dual-Layered Schema Architecture:** Prevents duplicate user accounts and filters database registration issues seamlessly.
* **📊 Election Dashboard:** Allows authenticated voters to view active election details and choose their candidate.
* **⏱️ Real-time Processing:** Live data interaction handling user inputs instantly using safe JPA repository transactions.
* **💎 Clean UI:** Sleek frontend design crafted natively via Thymeleaf templates.

---

## 📂 System Architecture & Project Structure

The codebase strictly adheres to the standard **Model-View-Controller (MVC)** layered pattern:

```text
online-voting-system/
│
├── src/main/java/com/voting/
│   ├── VotingApplication.java       # System Core Launcher Engine
│   ├── controller/                  # HTTP Request Layer (Web Endpoints)
│   │   ├── AuthController.java      # Handles Login, Registration & Session Logout
│   │   └── ElectionController.java  # Manages Ballot Displays & Voting Processing
│   ├── model/                       # Data Domain Entities (MySQL Maps)
│   │   ├── User.java                # Voter Records Schema
│   │   ├── Election.java            # Contests Data Configuration
│   │   └── Candidate.java           # Nominees & vote tally tracks
│   ├── repository/                  # Spring Data JPA Database Connectors
│   │   ├── UserRepository.java
│   │   └── ElectionRepository.java
│   └── service/                     # Core Business Logic Layer
│       └── VotingService.java       # Validations & Duplicate Voter Protections
│
├── src/main/resources/
│   ├── templates/                   # Frontend UI Templates Layer (Thymeleaf)
│   │   ├── login.html               # Authentication Entryway
│   │   ├── register.html            # Profile Creation Form
│   │   ├── dashboard.html           # Main Control Center
│   │   └── live-results.html        # Voting Analytics Display Screen
│   └── application.properties       # Core Environmental Configuration
│
└── pom.xml                          # Maven Dependencies & Build Blueprint
