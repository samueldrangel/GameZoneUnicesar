# Class Diagram — Promotion Module

```mermaid
classDiagram
    direction TD

    %% Clases del Módulo de Promociones
    class Promotion {
        <<abstract>>
        -String id
        -String name
        -LocalDate startDate
        -LocalDate endDate
        +Promotion(String id, String name, LocalDate startDate, LocalDate endDate)
        +getId() String
        +getName() String
        +getStartDate() LocalDate
        +getEndDate() LocalDate
        +isActive(LocalDate date) boolean
        +calculateDiscount(Sale sale)* double
    }

    class PercentageDiscount {
        -double percentage
        +PercentageDiscount(String id, String name, LocalDate startDate, LocalDate endDate, double percentage)
        +getPercentage() double
        +calculateDiscount(Sale sale) double
    }

    class CategoryDiscount {
        -double percentage
        -String targetCategory
        +CategoryDiscount(String id, String name, LocalDate startDate, LocalDate endDate, double percentage, String targetCategory)
        +getPercentage() double
        +getTargetCategory() String
        +calculateDiscount(Sale sale) double
    }

    class BulkPurchaseDiscount {
        -int minQuantity
        -double percentage
        +BulkPurchaseDiscount(String id, String name, LocalDate startDate, LocalDate endDate, int minQuantity, double percentage)
        +getMinQuantity() int
        +getPercentage() double
        +calculateDiscount(Sale sale) double
    }

    %% Relaciones de Herencia
    Promotion <|-- PercentageDiscount
    Promotion <|-- CategoryDiscount
    Promotion <|-- BulkPurchaseDiscount

    %% Persistencia y Servicios del Módulo
    class PromotionRepository {
        -String filePath
        +saveAll(List~Promotion~ promotions) void
        +loadAll() List~Promotion~
    }

    class PromotionService {
        -PromotionRepository repository
        +PromotionService(PromotionRepository repository)
        +registerPercentageDiscount(...) void
        +registerCategoryDiscount(...) void
        +registerBulkPurchaseDiscount(...) void
        +listAllPromotions() List~Promotion~
        +listActivePromotions() List~Promotion~
        +findBestPromotionFor(Sale sale) Promotion
        +findById(String id) Promotion
    }

    %% Integración con Venta y Servicios Existentes
    class Sale {
        -String saleId
        -LocalDate date
        -Customer customer
        -Seller seller
        -List~Product~ items
        -double subtotal
        -double discountAmount
        -String appliedPromotionName
        -double total
        +Sale(String saleId, LocalDate date, Customer customer, Seller seller, List~Product~ items)
        +calculateSubtotal() double
        +getDiscountAmount() double
        +setDiscountAmount(double discountAmount) void
        +getAppliedPromotionName() String
        +setAppliedPromotionName(String appliedPromotionName) void
        +getTotal() double
        +setTotal(double total) void
        +generateReceipt() String
    }

    class SaleService {
        -SaleRepository saleRepository
        -ProductService productService
        -PromotionService promotionService
        +registerSale(Sale sale) void
        +listAllSales() List~Sale~
        +getSaleById(String id) Sale
    }

    class Product {
        <<abstract>>
    }

    %% Relaciones de Integración
    PromotionService --> PromotionRepository : uses
    PromotionService ..> Sale : evaluates discount on
    SaleService --> PromotionService : requests best promotion
    CategoryDiscount ..> Product : inspects category
    Sale "*" --> "*" Product : contains