# 🪑 Furniture Management System

### 🚀 Java | JDBC | MySQL | DAO Pattern

A **role-based backend application** that simulates a real-world furniture ordering system with persistent cart, order processing, and admin management features.

---

# 📌 Overview

This project demonstrates how a real backend system works by implementing:

* User authentication (Admin & Customer)
* Persistent cart management
* Order processing workflow
* Relational database design using MySQL
* Clean architecture using DAO pattern

---

# 🎯 Key Features

## 👨‍💼 Admin

* Add / Delete Products
* View Product Catalog
* View Customers
* View Orders with details

## 👤 Customer

* Login authentication
* Browse products
* Add to cart (persistent)
* Remove / update cart items
* Place orders

---

# 🧠 What Makes This Project Strong

✔ Real-world workflow (Cart → Order → Storage)
✔ Database-driven persistence (no in-memory data loss)
✔ Proper use of SQL JOINs and relationships
✔ Clean separation using DAO pattern
✔ Transaction handling for order processing

---

# 🏗️ Architecture

```text
Presentation Layer → MainApp (UI)
        ↓
Service Layer → Business Logic
        ↓
DAO Layer → Database Operations
        ↓
Database → MySQL Tables
```

---

# 🗄️ Database Design

| Table       | Purpose                       |
| ----------- | ----------------------------- |
| users       | Stores admin & customer login |
| products    | Product catalog               |
| cart        | User-specific cart            |
| orders      | Order summary                 |
| order_items | Order details                 |

---

# 🔄 Application Workflow

## 👤 Customer Flow

1. Login
2. View products
3. Add items to cart (stored in DB)
4. View / modify cart
5. Place order
6. Cart → Orders + Order Items
7. Cart is cleared

---

## 👨‍💼 Admin Flow

1. Login
2. Manage products
3. View customers
4. View orders
5. View order items

---

# ⚙️ Setup Instructions

## 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/furniture-management-system.git
```

---

## 2️⃣ Create Database

```sql
CREATE DATABASE furniture_app;
USE furniture_app;
```

---

## 3️⃣ Configure DB Credentials

Create file:

📁 `db.properties` (not pushed to GitHub)

```properties
db.url=jdbc:mysql://localhost:3306/furniture_app
db.user=root
db.password=your_password
```

---

## ⚠️ Security Note

* `db.properties` is ignored via `.gitignore`
* Credentials are NOT hardcoded in source code

---

## 4️⃣ Run Application

Run:

```
MainApp.java
```

---

# 🧩 Tech Stack

* Java (Core)
* JDBC
* MySQL
* SQL
* Eclipse IDE

---

# ⚠️ Limitations

* Console-based UI
* No password encryption
* No REST API

---

# 🚀 Future Improvements

* Convert to Spring Boot
* Build REST APIs
* Add frontend (React)
* Implement JWT authentication

---

# 💬 Interview Talking Points

* Explain DAO pattern usage
* Describe cart persistence logic
* Explain order transaction workflow
* Discuss database relationships

---

# 👨‍💻 Author

**Nani**
Java Full-Stack Developer (Fresher)

---

# ⭐ Support

If you found this project useful, give it a ⭐ on GitHub!
