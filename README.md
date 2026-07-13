# 🏨 Hotel Reservation Management System

A desktop-based Hotel Reservation Management System developed using **Java Swing**, **JDBC**, and **MySQL**. The application helps hotel staff efficiently manage customers, rooms, reservations, payments, reports, and invoices through a modern graphical user interface.

---

# 📌 Project Overview

The Hotel Reservation Management System is designed to automate daily hotel operations.

The application provides a secure login system and allows hotel staff to:

- Manage Customers
- Manage Rooms
- Book Reservations
- Process Payments
- Generate PDF Invoices
- View Dashboard Reports

---

# 🚀 Features

## 🔐 Authentication

- Secure Login
- Username & Password Validation

## 👤 Customer Management

- Add Customer
- Update Customer
- Delete Customer
- Search Customer
- View All Customers

## 🛏 Room Management

- Add Room
- Update Room
- Delete Room
- Search Room
- Room Availability

## 📅 Reservation Management

- Book Room
- Update Reservation
- Search Reservation
- Automatic Room Status Update

## 💳 Payment Management

- Add Payment
- View Payment History
- Revenue Calculation

## 📄 Invoice

- Generate PDF Invoice

## 📊 Dashboard

- Customer Statistics
- Room Statistics
- Reservation Statistics
- Revenue Report

---

# 💻 Technology Stack

| Technology | Used |
|------------|------|
| Java | ✅ |
| Java Swing | ✅ |
| JDBC | ✅ |
| MySQL | ✅ |
| SQL | ✅ |
| FlatLaf | ✅ |
| OpenPDF | ✅ |
| Jakarta Mail | ✅ |
| IntelliJ IDEA | ✅ |
| Git | ✅ |
| GitHub | ✅ |

---

# 📂 Project Structure

```text
src
│
├── dao
├── email
├── exception
├── gui
├── invoice
├── model
├── resources
├── service
├── ui
├── util
└── validation
```

---

# ⚙ Installation

## Clone Repository

```bash
git clone https://github.com/ravigorai161-sketch/Hotel-Reservation-Management-System.git
```

---

## Open in IntelliJ IDEA

Open the project using IntelliJ IDEA.

---

## Configure Database

Create a MySQL database:

```sql
CREATE DATABASE hotel_management;
```

Import:

```
database/Hotel_Management.sql
```

---

## Update Database Credentials

Open:

```
src/util/DBConnection.java
```

Update:

```java
private static final String URL = "jdbc:mysql://localhost:3306/hotel_management";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

## Run

Run:

```
Main.java
```

---

# 🗄 Database

Database Name

```
hotel_management
```

Tables

- users
- customers
- rooms
- reservations
- payments
- checkouts

---

# 📸 Screenshots

## Login

![Login](images/login.png)

## Dashboard

![Dashboard](images/dashboard.png)

## Customer Management

![Customer](images/customer.png)

## Room Management

![Room](images/room.png)

## Reservation Management

![Reservation](images/reservation.png)

## Payment Management

![Payment](images/payment.png)

## Reports

![Reports](images/report.png)

---

# 🔮 Future Enhancements

- Online Booking
- QR Code Payments
- Email Notifications
- Dashboard Charts
- Backup & Restore
- Multi-user Authentication

---

# 👨‍💻 Author

**Ravilal Gorai**

GitHub:
https://github.com/ravigorai161-sketch

---

⭐ If you like this project, don't forget to star the repository!
