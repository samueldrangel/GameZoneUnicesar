# Class Diagram - GameZoneUnicesar

```mermaid
classDiagram
    direction TB

    namespace Model {
        class Person {
            <<abstract>>
            -String name
            -String identification
            -String phone
        }

        class Customer {
            <<concrete>>
            -String email
            -List~Sale~ purchaseHistory
        }

        class Seller {
            <<concrete>>
            -String employeeCode
            -String workShift
        }

        class Product {
            <<abstract>>
            -String id
            -String title
            -double price
            -int availableQuantity
            +String getDescription()*
        }

        class Game {
            <<concrete>>
            -String platform
            -String genre
            -String ageRating
            +String getDescription()
        }

        class Console {
            <<concrete>>
            -String brand
            -String model
            -String generation
            +String getDescription()
        }

        class Sale {
            -String id
            -LocalDate date
            -Customer customer
            -Seller seller
            -List~Product~ products
            +double calculateTotal()
        }
    }

    namespace Persistence {
        class PersonRepository {
            -String filePath
            +List~Person~ load()
            +void save(List~Person~ people)
        }

        class ProductRepository {
            -String filePath
            +List~Product~ load()
            +void save(List~Product~ products)
        }

        class SaleRepository {
            -String filePath
            +List~Sale~ load()
            +void save(List~Sale~ sales)
        }
    }

    namespace Services {
        class PersonService {
            -PersonRepository personRepository
            +void registerCustomer(Customer customer)
            +List~Customer~ listCustomers()
            +List~Seller~ listSellers()
        }

        class ProductService {
            -ProductRepository productRepository
            +void registerProduct(Product product)
            +List~Product~ listProducts()
            +void updateStock(Product product, int quantity)
        }

        class SaleService {
            -SaleRepository saleRepository
            -ProductService productService
            +void registerSale(Sale sale)
            +List~Sale~ listSales()
            +List~Sale~ findByCustomer(Customer customer)
            +List~Sale~ findBySeller(Seller seller)
        }
    }

    namespace UserInterface {
        class ConsoleMenu {
            -PersonService personService
            -ProductService productService
            -SaleService saleService
            +void start()
            +void showMainMenu()
        }
    }

    %% RELACIONES
    Person <|-- Customer
    Person <|-- Seller

    Product <|-- Game
    Product <|-- Console

    Customer "1" --> "0..*" Sale : purchases
    Seller "1" --> "0..*" Sale : attends
    Sale "1" *-- "1..*" Product : contains

    PersonRepository ..> Person : persists
    ProductRepository ..> Product : persists
    SaleRepository ..> Sale : persists

    PersonService --> PersonRepository : uses
    ProductService --> ProductRepository : uses
    SaleService --> SaleRepository : uses
    SaleService --> ProductService : updates inventory

    ConsoleMenu --> PersonService : uses
    ConsoleMenu --> ProductService : uses
    ConsoleMenu --> SaleService : uses