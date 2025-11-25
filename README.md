📌 Personal Finance and Task Management System

A Java JSP-based Web Application

📖 Overview

The Personal Finance and Task Management System is a web-based application designed to help users manage their expenses, budgets, and financial goals effectively.
It also includes a Task Manager module (to be added later).

This system allows a user to:

Register / Login

Add, View, and Delete Expenses

Add, View, and Delete Budgets

Add, View, and Delete Goals

View an overview (dashboard) of their financial summary

Securely maintain session and logout

Use a styled, clean, responsive UI

This README covers everything completed for Review-1.

🎯 Project Objectives (Review-1)

✔ Build a functional finance management system
✔ Implement full CRUD for Expenses, Budgets, Goals (partial update optional later)
✔ Proper session handling + authentication
✔ Dashboard summary
✔ Basic frontend UI with CSS
✔ MVC architecture using DAO + Model + JSP
✔ Functional database integration

🛠️ Tech Stack

Frontend

JSP

HTML5

CSS3

Bootstrap (optional, used lightly)

Backend

Java (Servlets + JSP)

JDBC

DAO Pattern

MVC Architecture

Database

MySQL

Server

Apache Tomcat

📂 Project Structure
PersonalFinanceManager/
│
├── src/main/java/
│   ├── com.finance.dao/        # DAO classes
│   ├── com.finance.model/      # Java model classes
│   ├── com.finance.servlet/    # All servlets
│
├── src/main/webapp/
│   ├── css/style.css           # Global styles
│   ├── index.jsp               # Login
│   ├── register.jsp            # Register
│   ├── dashboard.jsp           # Dashboard summary
│   ├── expenses.jsp            # Add + View + Delete expenses
│   ├── budgets.jsp             # Add + View + Delete budgets
│   ├── goals.jsp               # Add + View + Delete goals
│   ├── logout.jsp              # Logout session
│
└── README.md

🗄️ Database Structure
1. users
Column	Type
userId	INT (PK)
name	VARCHAR
email	VARCHAR
password	VARCHAR

2. expenses
Column	Type
id	INT (PK)
userId	INT (FK)
amount	DOUBLE
category	VARCHAR
expenseDate	DATE
description	VARCHAR

3. budget
Column	Type
id	INT (PK)
userId	INT (FK)
category	VARCHAR
amount	DOUBLE
startDate	DATE
endDate	DATE

4. goals
Column	Type
id	INT (PK)
userId	INT (FK)
title	VARCHAR
targetAmount	DOUBLE
targetDate	DATE

🚀 How to Run the Project
1. Import into IDE

Open Eclipse / IntelliJ

Import as Maven or Dynamic Web Project

2. Configure Tomcat

Add Apache Tomcat

Set project to run on server

3. Create MySQL Database

Run the SQL script:

CREATE DATABASE financeapp;

USE financeapp;

4. Update DB Connection in DAO

Inside each DAO:
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/financeapp", "root", "yourpassword"
);

5. Run on Server

Right-click → Run on Server → Tomcat
Visit:
        http://localhost:8080/PersonalFinanceManager/


✔️ Completed in Review-1
✅ User Authentication

Login

Register

Logout

Session handling

✅ Expense Management

Add expense

View all expenses

Delete expense

✅ Budget Management

Add budget

View all budgets

Delete budget

✅ Goal Management

Add goal

View all goals

Delete goal

✅ Dashboard Summary

Total expenses

Total budget

Total goals

✅ Frontend Styling

Clean UI

Reusable CSS

Navigation

🎯 Upcoming (For Review-2 or Final Submission)

🔜 Task Manager Module (Add/Edit/Delete tasks)
🔜 Notifications for budget overuse
🔜 Charts & Visual Reports
🔜 Profile settings
🔜 Export reports (PDF/PPt)
🔜 Admin Functionalities


📸 Screenshots

<img width="978" height="1391" alt="image" src="https://github.com/user-attachments/assets/38ed4884-7f33-4284-afcd-5fedc879fd56" />
<img width="1659" height="666" alt="image" src="https://github.com/user-attachments/assets/c53b9a11-5ab2-4bc5-83a3-170e2b4f2b32" />
<img width="1589" height="496" alt="image" src="https://github.com/user-attachments/assets/7f581bd6-a9a9-459e-bdc6-3a5e759bab3d" />
<img width="2201" height="965" alt="image" src="https://github.com/user-attachments/assets/04e66697-964e-44f9-b1dd-cfe6fb632613" />
<img width="2166" height="956" alt="image" src="https://github.com/user-attachments/assets/0993e405-0ee5-41c9-a093-299851b41705" />
<img width="1701" height="844" alt="image" src="https://github.com/user-attachments/assets/125f548e-a316-4d77-b0e5-96622a27149d" />
