# 📓 AI Usage Log - Developer 2 (Person Module)

**Project:** GameZoneUnicesar  
**Developer:** Developer 2 (Person Module Lead)  
**Role:** Backend Developer - Model, Persistence, and Service Layers for Persons  
**Assigned Branch:** `feature/person-module`  
**File Location:** `ai-usage/developer2-ai-log.md`  

---

## 📑 Module Responsibilities Summary
- Implementation of `Person` abstract base class and derived classes (`Customer`, `Seller`).
- Implementation of `PersonRepository` for flat-file persistence (`data/persons.txt`).
- Implementation of `PersonService` managing business rules (registration and listing of customers and sellers).
- Polymorphic mapping, string parsing, and JavaDoc documentation in English.

---

## 📌 Detailed AI Interaction Records

### Session 1: Structuring Abstract Class `Person` and Inheritance
- **Date:** 09/06/2026
- **Prompt / Query Asked to AI:**
  > *"How should I declare the abstract method in `Person` so that `Customer` and `Seller` override it properly without violating OOP principles?"*
- **Context & Problem:** Defining the polymorphic display behavior for common attributes (`id`, `name`, `phone`) alongside specialized fields like `email` for customers and `employeeCode`/`shift` for sellers.
- **AI Response Summary:** Recommended declaring an abstract method `getRoleDescription()` or overriding `toString()` in `Person` and forcing specialized implementations in `Customer` and `Seller`.
- **Developer Action & Validation:** Implemented `@Override` methods in both `Customer.java` and `Seller.java` ensuring full encapsulation with private attributes.

---

### Session 2: Polymorphic File Parsing in `PersonRepository`
- **Date:** 09/06/2026
- **Prompt / Query Asked to AI:**
  > *"How can I differentiate between a `Customer` and a `Seller` when reading line by line from `persons.txt`?"*
- **Context & Problem:** `PersonRepository` needed to deserialize text lines back into specific Java objects (`Customer` vs. `Seller`) during application initialization.
- **AI Response Summary:** Suggested using a prefix identifier in each line (e.g., `CUSTOMER;101;John Doe;...` vs `SELLER;202;Jane Smith;...`) and parsing fields with a `switch` or `if-else` block in `findAll()`.
- **Developer Action & Validation:** 
  1. Updated `PersonRepository.java` to format output lines with `CUSTOMER` or `SELLER` tags in `saveAll()`.
  2. Confirmed that pre-loaded sellers and new registered customers are read correctly back into memory upon restart.

---

### Session 3: Validating Preloaded Sellers Data Seeding
- **Date:** 09/06/2026
- **Prompt / Query Asked to AI:**
  > *"What is the best way to handle initial data seeding for preloaded sellers if the file does not exist yet?"*
- **Context & Problem:** The system requirement states that at least 3 sellers must be pre-loaded on first startup.
- **AI Response Summary:** Explained how to check if `persons.txt` is missing or empty inside `PersonService` initializer, triggering an automatic default list insertion for sellers.
- **Developer Action & Validation:** Verified that 3 default seller instances are automatically saved to `data/persons.txt` on the initial execution.

---

## 🛡️ Ethics & Compliance Declaration
1. **Self-Comprehension:** All code suggestions regarding `PersonRepository` and `PersonService` were reviewed, tested, and implemented manually in Apache NetBeans.
2. **Academic Integrity:** The AI was utilized exclusively for conceptual clarification on OOP inheritance, file IO parsing logic, and debugging, adhering strictly to sections 16 & 18 of the project guidelines.