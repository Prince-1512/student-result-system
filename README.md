# 🎓 Student Result Management System

A Java-based console application to manage student records and academic results using JDBC and MySQL.

---

## Features

- Add, view, update, and delete student records
- Add and manage subject-wise exam results
- Auto-calculate percentage and grade from marks
- Generate student result reports
- View top performers
- Search students by name

---

## Tech Stack

- **Java 17+**
- **JDBC** — database connectivity
- **MySQL** — relational database
- **OOP** — Model, DAO, Service, UI layers

---

## Project Structure

```
student-result-system/
├── src/com/studentresult/
│   ├── db/
│   │   └── DatabaseConnection.java   # Singleton JDBC connection
│   ├── model/
│   │   ├── Student.java              # Student entity
│   │   └── Result.java               # Result entity with grade logic
│   ├── dao/
│   │   ├── StudentDAO.java           # CRUD for students
│   │   └── ResultDAO.java            # CRUD for results
│   ├── service/
│   │   └── StudentService.java       # Business logic + validation
│   └── ui/
│       └── MainMenu.java             # Console menu (entry point)
├── database/
│   └── schema.sql                    # MySQL schema + sample data
└── README.md
```

---

## Setup & Run

### 1. Clone the repository

```bash
git clone https://github.com/Prince-1512/student-result-system.git
cd student-result-system
```

### 2. Setup MySQL database

```bash
mysql -u root -p < database/schema.sql
```

### 3. Update database credentials

Open `src/com/studentresult/db/DatabaseConnection.java` and update:

```java
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password";
```

### 4. Add MySQL JDBC Driver

Download `mysql-connector-java.jar` from [MySQL Downloads](https://dev.mysql.com/downloads/connector/j/) and add to classpath.

### 5. Compile and Run

```bash
# Compile
javac -cp .;mysql-connector-java-8.x.x.jar -d out src/com/studentresult/**/*.java

# Run
java -cp .;out;mysql-connector-java-8.x.x.jar com.studentresult.ui.MainMenu
```

---

## Grade System

| Percentage | Grade |
|------------|-------|
| 90 - 100   | A+    |
| 80 - 89    | A     |
| 70 - 79    | B+    |
| 60 - 69    | B     |
| 50 - 59    | C     |
| 40 - 49    | D     |
| Below 40   | F     |

---

## Author

**Prince Ranjan**  
MCA Final Year | School of Management Sciences, Varanasi  
[GitHub](https://github.com/Prince-1512) · [LinkedIn](https://linkedin.com/in/prince-ranjan-950840247)
