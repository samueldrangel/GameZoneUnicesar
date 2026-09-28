# Class Diagram — Warranty Module

```mermaid
classDiagram
    direction TD

    class Warranty {
        <<abstract>>
        -String id
        -Product product
        -Sale sale
        -LocalDate startDate
        -LocalDate endDate
        +Warranty(String id, Product product, Sale sale, LocalDate startDate)
        +getId() String
        +getProduct() Product
        +getSale() Sale
        +getStartDate() LocalDate
        +getEndDate() LocalDate
        +isActive(LocalDate date) boolean
        +generateWarrantyCertificate() String
        +getDurationInMonths()* int
        +getWarrantyType()* String
        +getAdditionalCost()* double
    }

    class BasicWarranty {
        +BasicWarranty(String id, Product product, Sale sale, LocalDate startDate)
        +getDurationInMonths() int
        +getWarrantyType() String
        +getAdditionalCost() double
    }

    class ExtendedWarranty {
        +ExtendedWarranty(String id, Product product, Sale sale, LocalDate startDate)
        +getDurationInMonths() int
        +getWarrantyType() String
        +getAdditionalCost() double
    }

    Warranty <|-- BasicWarranty
    Warranty <|-- ExtendedWarranty

    class WarrantyRepository {
        -String filePath
        +saveAll(List~Warranty~ warranties) void
        +loadAll() List~Warranty~
    }

    class WarrantyService {
        -WarrantyRepository repository
        +assignBasicWarranty(Product product, Sale sale, LocalDate startDate) BasicWarranty
        +assignExtendedWarranty(Product product, Sale sale, LocalDate startDate) ExtendedWarranty
        +findWarrantyByProduct(String productId, String saleId) Warranty
        +listAllWarranties() List~Warranty~
        +listActiveWarranties() List~Warranty~
        +listWarrantiesExpiringSoon(int daysAhead) List~Warranty~
    }

    class Sale {
        -String id
        -LocalDate date
        -double total
    }

    class Product {
        <<abstract>>
        -String id
        -double price
    }

    WarrantyService --> WarrantyRepository : uses
    WarrantyService ..> Warranty : manages
    Warranty "*" --> "1" Product : references
    Warranty "*" --> "1" Sale : references
