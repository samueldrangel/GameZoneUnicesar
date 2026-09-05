# Team Structure and Module Distribution - GameZoneUnicesar

**Universidad Popular del Cesar**  
**Asignatura:** Programación III  
**Docente:** Ing. Esp. Alfredo Bautista  

---

## 1. Team Members & Roles Assignment

| Full Name | Student ID | Assigned Role | Assigned Module | Feature Branch |
| :--- | :--- | :--- | :--- | :--- |
| **Samuel David Rangel Martinez** | *[samueldrangel]* | Tech Lead (Líder Técnico) | Sales & Integration | `feature/sales-module` |
| **[Samuel Angulo Meza]** | *[sangulom]* | Developer 1 | Products | `feature/product-module` |
| **[Kevin Santiago Amaris Sanchez]** | *[ksamaris]* | Developer 2 | Persons | `feature/person-module` |

---

## 2. Vertical Slice Class Distribution

The class distribution adheres strictly to the vertical slicing criteria across the 4-layer architecture defined in the class diagram:

### 🛠️ Tech Lead — Sales & Integration Module (5 Classes)
* **Model Layer:** `Sale`
* **Persistence Layer:** `SaleRepository`
* **Service Layer:** `SaleService`
* **UI Layer:** `ConsoleMenu`
* **Application Root:** `Main`

### 🎮 Developer 1 — Product Module (5 Classes)
* **Model Layer (Abstract Base):** `Product`
* **Model Layer (Concrete 1):** `Game`
* **Model Layer (Concrete 2):** `Console`
* **Persistence Layer:** `ProductRepository`
* **Service Layer:** `ProductService`

### 👤 Developer 2 — Person Module (5 Classes)
* **Model Layer (Abstract Base):** `Person`
* **Model Layer (Concrete 1):** `Customer`
* **Model Layer (Concrete 2):** `Seller`
* **Persistence Layer:** `PersonRepository`
* **Service Layer:** `PersonService`

---

## 3. Committed Activities per Role

Each team member is committed to completing the following granular tasks through atomic commits in their respective feature branches:

### 👑 Tech Lead Activities (Samuel David Rangel Martinez)
1. Create and configure the initial GitHub repository (`README.md`, `.gitignore`, License).
2. Configure branch protection rules for `main` and `develop`.
3. Set up the Maven project (`pom.xml`) and establish the 4-layer package structure.
4. Draft and maintain the `TEAM.md` file with team assignment and class distribution.
5. Implement the `Sale` domain class (attributes, constructor, getters/setters).
6. Implement the `calculateTotal()` business logic method in `Sale`.
7. Implement `SaleRepository` for file persistence (saving/loading sales).
8. Implement `SaleService` with validation rules (stock verification, minimum items, inventory sync).
9. Implement the main structure for `ConsoleMenu` in the UI layer.
10. Implement interactive submenus for Persons, Products, and Sales in `ConsoleMenu`.
11. Implement `Main` class with dependency injection and application bootstrap.
12. Review, approve, and merge Pull Requests from developers into `develop`.
13. Finalize `README.md` with compilation and execution instructions.

### 📦 Developer 1 Activities ([Samuel Angulo Meza])
1. Create and switch to the `feature/product-module` branch.
2. Implement the abstract base class `Product` with common fields, constructor, and methods.
3. Declare the abstract method `getDescription()` in `Product`.
4. Implement the concrete class `Game` with specific attributes and `getDescription()` override.
5. Implement the concrete class `Console` with specific attributes and `getDescription()` override.
6. Implement `ProductRepository` for reading and writing product data files.
7. Implement `ProductService` handling business logic (registration, listing, stock update).
8. Write comprehensive JavaDoc documentation in English for all module classes.
9. Open Pull Requests targeting `develop` for Tech Lead review.

### 👥 Developer 2 Activities ([Kevin Santiago Amariz Sanchez])
1. Create and switch to the `feature/person-module` branch.
2. Implement the abstract base class `Person` with common fields, constructor, and methods.
3. Declare the abstract/business methods in `Person`.
4. Implement the concrete class `Customer` with specific attributes and purchase history logic.
5. Implement the concrete class `Seller` with specific attributes and work shift logic.
6. Implement `PersonRepository` for reading and writing person data files.
7. Implement `PersonService` handling business logic (registration and listing).
8. Write comprehensive JavaDoc documentation in English for all module classes.
9. Open Pull Requests targeting `develop` for Tech Lead review.