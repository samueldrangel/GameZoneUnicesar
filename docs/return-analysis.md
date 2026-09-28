# Return Module Architecture Analysis

## 1. Entity Relationship
The relationship between `Return` and `Sale` is an **Association** (specifically, a unidirectional reference). A `Return` references an existing `Sale` transaction to verify product inclusion and purchase dates, but both entities maintain independent lifecycles.

## 2. Product Inclusion Representation
A return contains a `List<Product> returnedProducts` attribute. This list stores only the specific `Product` instances that the customer is actually returning, rather than all products from the original sale.

## 3. Date Boundary Validation Layer
The 30-day return policy validation is located in the **Service Layer** (`ReturnService`) and supported by domain helpers (`Sale.canBeReturned`). Java's `java.time.temporal.ChronoUnit.DAYS.between(saleDate, currentDate)` mechanism is used to compute the difference in days.

## 4. Stock Updating and Code Reuse
Stock restoration reuses the `restoreStock` method from `ProductService` (or `ProductRepository`). It is invoked from `ReturnService` upon a successful return. Reusing this method prevents code duplication and maintains data consistency in inventory storage.

## 5. Monthly Balance Report Architecture
The monthly balance report is placed in `ReturnService` (or a dedicated reporting service). It depends on both `SaleService` and `ReturnRepository`/`ReturnService` to aggregate gross sales, subtract total refunds, and calculate the net balance for layered architecture coherence.
