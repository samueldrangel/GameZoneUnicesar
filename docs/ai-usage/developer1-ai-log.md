\# 📓 AI Usage Log - Developer 1 (Product Module)



\*\*Project:\*\* GameZoneUnicesar  

\*\*Developer:\*\* Developer 1 (Product Module Lead)  

\*\*Role:\*\* Backend Developer - Model, Persistence, and Service Layers for Products  

\*\*Assigned Branch:\*\* `feature/product-module`  

\*\*File Location:\*\* `ai-usage/developer1-ai-log.md`  



\---



\## 📑 Module Responsibilities Summary

\- Implementation of `Product` abstract base class and derived classes (`Game`, `Console`, `Accessory`).

\- Implementation of `ProductRepository` for flat-file persistence (`data/products.txt`).

\- Implementation of `ProductService` managing business rules (registration, catalog search, stock update).

\- Polymorphic mapping, string parsing, and JavaDoc documentation in English.



\---



\## 📌 Detailed AI Interaction Records



\### Session 1: Tracking Product Serialization Logic in `ProductRepository`

\- \*\*Date:\*\* 09/06/2026

\- \*\*Prompt / Query Asked to AI:\*\*

&#x20; > \*"Where is `parseProductToString` located?"\*

\- \*\*Context \& Problem:\*\* While debugging file persistence, I needed to locate where the object-to-string transformation was taking place during file saving.

\- \*\*AI Response Summary:\*\* Identified that line-by-line formatting was handled directly within the `saveAll()` method loop in `ProductRepository.java` using string concatenation and delimiters (`;`).

\- \*\*Developer Action \& Validation:\*\* Inspected `ProductRepository.java` in Apache NetBeans to verify how `Game`, `Console`, and `Accessory` attributes were formatted before writing to `data/products.txt`.



\---



\### Session 2: Fixing Polymorphic Persistence for `Accessory` Subclass

\- \*\*Date:\*\* 09/06/2026

\- \*\*Prompt / Query Asked to AI:\*\*

&#x20; > \*"It showed the accessories but didn't save them to the file."\*  

&#x20; > \*"Missing console in `findAll`."\*  

&#x20; > \*"Give me more options on why accessories are not being listed."\*

\- \*\*Context \& Problem:\*\* During integration testing, `ProductRepository` was failing to persist `Accessory` objects in `data/products.txt` upon application exit, and `findAll()` was missing type checks.

\- \*\*AI Response Summary:\*\* Recommended adding an explicit `else if (product instanceof Accessory)` check inside the `saveAll()` method and updating `findAll()` to parse lines with type `ACCESSORY`.

\- \*\*Developer Action \& Validation:\*\* 

&#x20; 1. Updated `ProductRepository.java` by adding the missing polymorphic condition for `Accessory`.

&#x20; 2. Verified that `data/products.txt` successfully generated formatted records such as `ACCESSORY;P003;Headset;89.99;15;Wireless;USB-C`.

&#x20; 3. Purged the `/data` directory to test clean auto-population and confirmed all product types loaded correctly.



\---



\### Session 3: Analyzing Initial Data Seeding in `ProductService`

\- \*\*Date:\*\* 09/06/2026

\- \*\*Prompt / Query Asked to AI:\*\*

&#x20; > \*"What do you need to know why \[accessories are not listing]?"\*

\- \*\*Context \& Problem:\*\* Analyzing why the seed dataset was not reloading accessories into memory when the application restarted.

\- \*\*AI Response Summary:\*\* Explained that `getAllProducts().isEmpty()` checks if the file already exists; if `products.txt` has old unformatted data, seeding is skipped. Recommended clearing the text file to force re-seeding.

\- \*\*Developer Action \& Validation:\*\* Deleted local data files, re-ran the main program (`Shift + F6`), and verified that all initial products were generated and readable via `ProductService`.



\---



\## 🛡️ Ethics \& Compliance Declaration

1\. \*\*Self-Comprehension:\*\* All code suggestions provided by the AI regarding `ProductRepository` and `ProductService` were reviewed, adapted, and compiled manually.

2\. \*\*Academic Integrity:\*\* The AI was utilized solely as an assistant for debugging runtime errors, verifying file IO logic, and refactoring polymorphic checks, in strict accordance with section 16 \& 18 of the project guidelines.

