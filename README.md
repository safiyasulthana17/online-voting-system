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
```
---

## 🛠️ Technology Stack Matrix

| Layer | Technologies | Description |
| :--- | :--- | :--- |
| **Frontend** | HTML5, CSS3, Bootstrap 5, JavaScript | Responsive layout Engine |
| **Backend Framework** | Java 21, Spring Boot 3.2.3 | Core Controller Runtime API Engine |
| **Data Layers** | Spring Data JPA, Hibernate 6 | Object-Relational Mapping Framework |
| **Database Engine** | MySQL Server 8.0, HikariCP Connection Pool | Secure, structured Relational storage |
| **Template Engine** | Thymeleaf 3 | Reactive Server-Side HTML Rendering |

---

## 🧮 Core Business System Algorithms

### 1️⃣ Identity Security Authentication Flow
* Voters input their unique email address credentials.
* Backend systems check for pre-existing records via the database access layer (`UserRepository`).
* Active sessions lock verified security identifiers into the container environment (`HttpSession`).

### 2️⃣ "One Person – One Vote" Constraint
* Each `User` model tracks a unique target collection tracking reference: `Set<Long> votedElectionIds`.
* When attempting to post a vote, the tracking logic checks if the user's ID is already in the election set.
* If a record exists, execution blocks instantly, throwing an error and preventing duplicate voting.

### 3️⃣ Real-Time Vote Compilation Logic
* Votes trigger atomic counter field shifts directly inside the corresponding database records.
* Evaluation algorithms gather active entity data lists from the database, sort the results by count, and instantly display the winner on the public dashboard.

---

# ⚙️ Local Development Machine Configuration

To run this project on your local machine, follow the steps below.

## 1. Database Creation

Open **MySQL Workbench** (or any MySQL SQL client) and create the database:

```sql
CREATE DATABASE online_voting_system;
```

---

## 2. Configure Environment Properties

Open the following file:

```text
src/main/resources/application.properties
```

Update it with your MySQL database credentials:

```properties
server.port=9091

spring.datasource.url=jdbc:mysql://localhost:3306/online_voting_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 3. Build & Run the Application

Open a terminal in the project's root directory and execute:

```bash
mvn spring-boot:run
```

The application will start on **Port 9091**.

Open your browser and visit:

**👉 http://localhost:9091/register**

---

# 📈 Practical Applications & Uses

### 🏫 Educational Institutions
- Conduct **Student Council Elections**
- Elect **Class Representatives (CRs)**
- Organize **College & University Elections**

### 🏢 Corporate Organizations
- Conduct employee voting for internal initiatives
- Collect feedback through secure polling
- Elect employee committee representatives

### 🏘️ Community Associations
- Organize transparent elections for:
  - Housing Society Boards
  - Local Clubs
  - Resident Welfare Associations (RWAs)

### 🗳️ Local Governance
- Conduct secure elections for:
  - Panchayat Elections
  - Village Committees
  - Small Civic Bodies
  - Community Decision-Making

---

## 🚀 Access the Application

Once the server is running, open:

```
http://localhost:9091/register
```

to begin registration and use the Online Voting System.
