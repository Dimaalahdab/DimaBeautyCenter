# 💄 Dima Beauty Center

> **University Project** — Full-Stack Web Application

A complete beauty center management system built with Spring Boot and Java. This project is not just a technical exercise — it was designed with a real vision: to one day serve as the digital backbone of my own beauty center. By combining my passion for programming with my dream of entrepreneurship, I built a system that could genuinely run a business.

---

## ✨ What It Does

Dima Beauty Center is an admin panel that manages all aspects of a beauty center in one place:

- 🔐 **Secure Login** — Admin authentication with username and password
- 📅 **Appointments** — Schedule bookings between clients, employees, and services, with automatic conflict detection to prevent double-booking
- 👤 **Clients** — Add, update, and delete client records
- 👩‍💼 **Employees** — Manage staff details and their specialties
- 💅 **Services** — Maintain a catalog of beauty services with prices
- 🖼️ **Dashboard** — Central hub with a gallery and navigation to all modules

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java + Spring Boot |
| Frontend | HTML, CSS, JavaScript |
| Database | MySQL (via JPA / Hibernate) |
| API Style | REST (GET, POST, PUT, DELETE) |
| Architecture | MVC — Controllers, Entities, Repositories |

---

## 🗄️ Database Structure

The system is built around 5 entities:

- **User** — Admin accounts for login (independent, not linked to appointments)
- **Client** — Stores client name and phone number
- **Employee** — Stores staff name and specialty
- **Serv (Service)** — Stores service name and price
- **Appointment** — The central entity linking a client, employee, and service at a specific date and time

**Relationships:**
- One Client → Many Appointments
- One Employee → Many Appointments
- One Service → Many Appointments
- Each Appointment → belongs to one Client, one Employee, one Service

---

## 📄 Pages

| Page | Description |
|------|-------------|
| Login | Secure admin sign-in |
| Dashboard | Welcome page with gallery and navigation |
| Appointments | Create, view, and delete bookings with conflict detection |
| Clients | Add, edit, and delete client records |
| Employees | Manage staff names and specialties |
| Services | Manage service catalog with pricing |

---

## ⚙️ How to Run

### Prerequisites
- Java 17+
- Maven
- MySQL

### Steps

```bash
# 1. Clone the repo
git clone https://github.com/dimaalahdab/DimaBeautyCenter.git
cd DimaBeautyCenter

# 2. Set up your database in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/dimabeauty
spring.datasource.username=your_username
spring.datasource.password=your_password

# 3. Run the app
./mvnw spring-boot:run
```

Then open: [http://localhost:8088/login.html](http://localhost:8088/login.html)

---

## 💡 Key Feature — Conflict Detection

When creating an appointment, the backend automatically checks if the selected employee already has a booking at the same date and time. If a conflict exists, the system returns an error and the booking is blocked — no double-booking possible.

---

## 🌸 Built By

**Dima Alahdab** — *"It is not just code; it is the foundation of a dream."*
