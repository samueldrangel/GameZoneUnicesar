# 📓 AI Usage Log - Developer 1 (Product & Integration Modules)

**Project:** GameZoneUnicesar  
**Developer:** Developer 1 (Model Layer Lead)  
**Role:** Backend Developer - Model Layer, Domain Rules, and Integration Fixes  
**Assigned Branches:** `feature/product-module`, `feature/accessory-category-discount`, `fix/return-discounted-refund`  
**File Location:** `docs/ai-usage/developer1-ai-log.md`  

---

## 📑 Module Responsibilities Summary
- Implementation of `Product` abstract base class and derived classes (`Game`, `Console`, `Accessory`).
- Implementation of `ProductRepository` for flat-file persistence (`data/products.txt`).
- Implementation of `ProductService` managing business rules (registration, catalog search, stock update).
- Polymorphic mapping, string parsing, and JavaDoc documentation in English.
- **Requirement 5 Integration (A1):** Extended `CategoryDiscount` model to support the `ACCESSORY` target category.
- **Requirement 5 Integration (A5):** Adjusted `Return` model to calculate proportional item refunds on discounted sales.

---

## 📌 Detailed AI Interaction Records

### Session 1: Tracking Product Serialization Logic in `ProductRepository`
- **Date:** 09/06/2026
- **Prompt / Query Asked to AI:**
  > *"Where is `parseProductToString` located?"*
- **Context & Problem:** While debugging file persistence, I needed to locate where the object-to-string transformation was taking place during file saving.
- **AI Response Summary:** Identified that line-by-line formatting was handled directly within the `saveAll()` method loop in `ProductRepository.java` using string concatenation and delimiters (`;`).
- **Developer Action & Validation:** Inspected `ProductRepository.java` in Apache NetBeans to verify how `Game`, `Console`, and `Accessory` attributes were formatted before writing to `data/products.txt`.

---

### Session 2: Fixing Polymorphic Persistence for `Accessory` Subclass
- **Date:** 09/06/2026
- **Prompt / Query Asked to AI:**
  > *"It showed the accessories but didn't save them to the file."*  
  > *"Missing console in `findAll`."*  
  > *"Give me more options on why accessories are not being listed."*
- **Context & Problem:** During integration testing, `ProductRepository` was failing to persist `Accessory` objects in `data/products.txt` upon application exit, and `findAll()` was missing type checks.
- **AI Response Summary:** Recommended adding an explicit `else if (product instanceof Accessory)` check inside the `saveAll()` method and updating `findAll()` to parse lines with type `ACCESSORY`.
- **Developer Action & Validation:** 
  1. Updated `ProductRepository.java` by adding the missing polymorphic condition for `Accessory`.
  2. Verified that `data/products.txt` successfully generated formatted records such as `ACCESSORY;P003;Headset;89.99;15;Wireless;USB-C`.
  3. Purged the `/data` directory to test clean auto-population and confirmed all product types loaded correctly.

---

### Session 3: Analyzing Initial Data Seeding in `ProductService`
- **Date:** 09/06/2026
- **Prompt / Query Asked to AI:**
  > *"What do you need to know why [accessories are not listing]?"*
- **Context & Problem:** Analyzing why the seed dataset was not reloading accessories into memory when the application restarted.
- **AI Response Summary:** Explained that `getAllProducts().isEmpty()` checks if the file already exists; if `products.txt` has old unformatted data, seeding is skipped. Recommended clearing the text file to force re-seeding.
- **Developer Action & Validation:** Deleted local data files, re-ran the main program (`Shift + F6`), and verified that all initial products were generated and readable via `ProductService`.

---

### Session 4: Integration A1 — Accessory Category Discount Support
- **Date:** 2026-09-22
- **Prompt / Query Asked to AI:**
  > *"How to update CategoryDiscount in Java so it checks if a Product is an instance of Accessory for category discount?"*
- **Context & Problem:** Requirement 5 Integration Adjustment A1 requires `CategoryDiscount` to support promotions targeting accessories in addition to videogames and consoles.
- **AI Response Summary:** Suggested validating `"ACCESSORY"` as a valid `targetCategory` inside constructor/setter and adding an `instanceof Accessory` check in `isProductInTargetCategory()`.
- **Developer Action & Validation:** 
  1. Updated `CategoryDiscount.java` in branch `feature/accessory-category-discount`.
  2. Verified constructor validation prevents invalid category strings while allowing `"ACCESSORY"`.
  3. Committed changes under commit `feat(model): extend CategoryDiscount to support ACCESSORY category`.

---

### Session 5: Integration A5 — Proportional Refund for Discounted Sales
- **Date:** 2026-09-24
- **Prompt / Query Asked to AI:**
  > *"How to calculate proportional item refunds in Java when the original sale had a total discount?"*
- **Context & Problem:** Requirement 5 Integration Adjustment A5 reported that refunding items using list price on discounted sales over-refunded customers.
- **AI Response Summary:** Proposed calculating `discountRatio = totalDiscount / originalSubtotal`, then evaluating each returned item's refund as `itemPrice * (1 - discountRatio)`.
- **Developer Action & Validation:**
  1. Refactored `Return.java` in branch `fix/return-discounted-refund` to update `calculateRefundAmount()` and `generateReturnReceipt()`.
  2. Tested receipt formatting to ensure list price, proportional discount, and net refund amount are clearly displayed per item.
  3. Committed changes under commit `fix(model): apply proportional discount in Return refund calculation`.

---

## 🛡️ Ethics & Compliance Declaration
1. **Self-Comprehension:** All code suggestions provided by the AI regarding `ProductRepository`, `CategoryDiscount`, and `Return` were reviewed, adapted, and compiled manually.
2. **Academic Integrity:** The AI was utilized solely as an assistant for debugging runtime errors, verifying file IO logic, refactoring polymorphic checks, and designing integration formulas in strict accordance with project guidelines.