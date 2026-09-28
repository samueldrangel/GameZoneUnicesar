# Warranty Module Architecture Analysis

## 1. Class Hierarchy and Polymorphism
Common attributes (`id`, `product`, `sale`, `startDate`, `endDate`) and behaviors (`isActive`, `generateWarrantyCertificate`) are placed in the abstract base class `Warranty`. Subclasses override template methods (`getDurationInMonths`, `getWarrantyType`, `getAdditionalCost`) to supply specific rules without repeating core logic.

## 2. Product Warranty Rules and Runtime Checks
The decision of which products generate basic warranties resides in the **Service Layer** (`SaleService`/`WarrantyService`). Java's `instanceof` operator (e.g., `product instanceof Console`) is used to check the runtime type before assigning automatic coverage.

## 3. End Date Calculation
The end date calculation is performed inside the abstract `Warranty` constructor using `startDate.plusMonths(getDurationInMonths())`. Performing this in the constructor guarantees that every instantiated warranty object is initialized in a consistent state.

## 4. Extended Warranty Cost Evaluation
The additional cost (10% of product price) is calculated during sale registration in `SaleService.registerSale`. The cost returned by `ExtendedWarranty.getAdditionalCost()` is added to the total amount of the `Sale` object.

## 5. Expiring Soon Warranties Query
The `listWarrantiesExpiringSoon(int daysAhead)` method belongs to `WarrantyService`. It depends on `WarrantyRepository` to retrieve all warranties and filters them using date comparisons. This adheres to layered architecture guidelines by isolating business queries from the presentation and persistence layers.
