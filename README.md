# GameZoneUnicesar 🎮

Commercial management and inventory control system developed for the **GameZone Unicesar** video game store. Project developed in Java following a clean 4-layer architecture with a lightweight flat-file persistence layer.

---

## 📋 Table of Contents
- [Features & Modules](#features--modules)
- [System Architecture](#system-architecture)
- [Project Structure](#project-structure)
- [System Requirements](#system-requirements)
- [Installation & Execution](#installation--execution)
- [Data Persistence](#data-persistence)

---

## 🚀 Features & Modules

The system is divided into three fully functional core modules:

1. **Product Module (`Product`):**
   - Multi-class management: **Games** (`Game`), **Consoles** (`Console`), and **Accessories** (`Accessory`).
   - Registration, querying, and product catalog listing.
   - Dynamic stock update functionality.

2. **Person Module (`Person`):**
   - Management of **Customers** (`Customer`) with loyalty levels and accumulated reward points.
   - Management of **Sellers** (`Seller`) with employee IDs and compensation details.

3. **Sales Module (`Sale`):**
   - Transaction registration linking customer, seller, and sold items.
   - Automatic calculation of totals and taxes.
   - Receipt generation and itemized line breakdown (`SaleDetail`).

---

## 🏗️ System Architecture

The project implements an Object-Oriented **4-Layer Architecture**:

1. **Layer 1: Domain/Model (`Model`)**
   - Contains primary entities, inheritance relationships (`Product` $\rightarrow$ `Game`/`Console`/`Accessory`, `Person` $\rightarrow$ `Customer`/`Seller`), and data encapsulation.

2. **Layer 2: Persistence (`Persistence`)**
   - Repositories handling direct reading and writing to flat files (`.txt`) via I/O Streams (`BufferedReader`, `BufferedWriter`).

3. **Layer 3: Service/Business Logic (`Service`)**
   - Service layer processing business logic, rule validations, and serving as an intermediary between UI and Persistence.

4. **Layer 4: Presentation/UI (`UI`)**
   - Interactive console-based menus for navigation and dependency injection management.

---

## 📁 Project Structure

```text
GameZoneUnicesar/
│
├── data/                       # Persistence files (.txt)
│   ├── persons.txt
│   ├── products.txt
│   └── sales.txt
│
├── src/
│   └── main/
│       └── java/
│           ├── Model/          # Domain / Model Layer
│           │   ├── Accessory.java
│           │   ├── Console.java
│           │   ├── Customer.java
│           │   ├── Game.java
│           │   ├── Person.java
│           │   ├── Product.java
│           │   ├── Sale.java
│           │   ├── SaleDetail.java
│           │   └── Seller.java
│           │
│           ├── Persistence/    # Persistence Layer (Flat-Files)
│           │   ├── PersonRepository.java
│           │   ├── ProductRepository.java
│           │   └── SaleRepository.java
│           │
│           ├── Service/        # Business Logic Layer
│           │   ├── PersonService.java
│           │   ├── ProductService.java
│           │   └── SalesService.java
│           │
│           ├── UI/             # Presentation Layer
│           │   ├── ConsoleMenu.java
│           │   └── ConsoleSubmenus.java
│           │
│           └── com/mycompany/gamezoneunicesar/
│               └── GameZoneUnicesar.java  # Entry point (Main)
│
└── README.md

💻 System Requirements

JDK: Java Development Kit 17 or higher.
Recommended IDE:
Apache NetBeans 15+
IntelliJ IDEA
Eclipse
Version Control: Git and GitHub.

🛠️ Installation & Execution

1. Clone the repository
git clone https://github.com/samueldrangel/GameZoneUnicesar.git
2. Open the project in your IDE
Open Apache NetBeans or your preferred Java IDE.
Select File → Open Project.
Select the cloned GameZoneUnicesar folder.
3. Run the application
Run the main project from your IDE.
In Apache NetBeans, you can use Shift + F6 or select Run Main Project.
The application will start through the GameZoneUnicesar main class.
Initial Data

On the first execution, the seedInitialData method populates the repositories with 10 initial records when the corresponding data files are empty.

💾 Data Persistence

The application does not require an external relational database.

All information is stored using semicolon-delimited (;) flat-text files located in the /data folder.

products.txt

Stores product information using the following structure:

TYPE;ID;TITLE;PRICE;STOCK;EXTRA_PARAM_1;EXTRA_PARAM_2
persons.txt

Stores customer and seller information.

sales.txt

Stores sales transactions, including transaction headers and line-item details.

👨‍💻 Technologies Used
Java
Object-Oriented Programming (OOP)
Java I/O
Flat-File Persistence
Git
GitHub
Apache NetBeans

📌 Project Purpose

GameZoneUnicesar was developed as an academic software project focused on applying Object-Oriented Programming, layered architecture, inheritance, encapsulation, business logic, and data persistence in a real-world video game store management scenario.