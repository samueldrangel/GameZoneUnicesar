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

        class Accessory {
            <<abstract>>
            -List~String~ compatibleConsoleIds
            +addCompatibleConsoleId(String consoleId) void
            +isCompatibleWith(String consoleId) boolean
            +String getDescription()*
        }

        class Controller {
            <<concrete>>
            -String connectionType
            +String getDescription()
        }

        class Cable {
            <<concrete>>
            -double lengthMeters
            -String connectorType
            +String getDescription()
        }

        class Memory {
            <<concrete>>
            -int capacityGb
            -String memoryType
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

        class AccessoryRepository {
            -String filePath
            +List~Accessory~ loadAll()
            +void saveAll(List~Accessory~ accessories)
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

        class AccessoryService {
            -AccessoryRepository accessoryRepository
            +void registerController(Controller controller)
            +void registerCable(Cable cable)
            +void registerMemory(Memory memory)
            +List~Accessory~ listAllAccessories()
            +List~Accessory~ listAccessoriesByType(String type)
            +List~Accessory~ findAccessoriesCompatibleWith(String consoleId)
            +void updateStock(String accessoryId, int quantity)
        }

        class SaleService {
            -SaleRepository saleRepository
            -ProductService productService
            -AccessoryService accessoryService
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
            -AccessoryService accessoryService
            -SaleService saleService
            +void start()
            +void showMainMenu()
            +void showAccessorySubmenu()
        }
    }

    %% RELACIONES DE HERENCIA
    Person <|-- Customer
    Person <|-- Seller

    Product <|-- Game
    Product <|-- Console
    Product <|-- Accessory : Inherits

    Accessory <|-- Controller
    Accessory <|-- Cable
    Accessory <|-- Memory

    %% RELACIONES DE ASOCIACIÓN Y COMPOSICIÓN
    Customer "1" --> "0..*" Sale : purchases
    Seller "1" --> "0..*" Sale : attends
    Sale "1" *-- "1..*" Product : contains

    %% RELACIONES DE PERSISTENCIA
    PersonRepository ..> Person : persists
    ProductRepository ..> Product : persists
    AccessoryRepository ..> Accessory : persists
    SaleRepository ..> Sale : persists

    %% RELACIONES DE SERVICIO
    PersonService --> PersonRepository : uses
    ProductService --> ProductRepository : uses
    AccessoryService --> AccessoryRepository : uses
    SaleService --> SaleRepository : uses
    SaleService --> ProductService : updates product inventory
    SaleService --> AccessoryService : updates accessory inventory

    %% RELACIONES DE INTERFAZ DE USUARIO
    ConsoleMenu --> PersonService : uses
    ConsoleMenu --> ProductService : uses
    ConsoleMenu --> AccessoryService : uses
    ConsoleMenu --> SaleService : uses