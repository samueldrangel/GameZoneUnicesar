```mermaid
classDiagram

&#x20;   direction TB



&#x20;   namespace Model {

&#x20;       class Person {

&#x20;           <<abstract>>

&#x20;           -String name

&#x20;           -String identification

&#x20;           -String phone

&#x20;       }



&#x20;       class Customer {

&#x20;           <<concrete>>

&#x20;           -String email

&#x20;           -List\~Sale\~ purchaseHistory

&#x20;       }



&#x20;       class Seller {

&#x20;           <<concrete>>

&#x20;           -String employeeCode

&#x20;           -String workShift

&#x20;       }



&#x20;       class Product {

&#x20;           <<abstract>>

&#x20;           -String id

&#x20;           -String title

&#x20;           -double price

&#x20;           -int availableQuantity

&#x20;           +String getDescription()\*

&#x20;       }



&#x20;       class Game {

&#x20;           <<concrete>>

&#x20;           -String platform

&#x20;           -String genre

&#x20;           -String ageRating

&#x20;           +String getDescription()

&#x20;       }



&#x20;       class Console {

&#x20;           <<concrete>>

&#x20;           -String brand

&#x20;           -String model

&#x20;           -String generation

&#x20;           +String getDescription()

&#x20;       }



&#x20;       class Accessories {

&#x20;           <<abstract>>

&#x20;           -String id

&#x20;           -String title

&#x20;           -String price

&#x20;           -String Stock

&#x20;           +String getDescription()

&#x20;       }



&#x20;       class Control {

&#x20;           <<concrete>>

&#x20;           -String conection

&#x20;           -String compatibility

&#x20;           +String getDescription()

&#x20;       }



&#x20;       class Cables {

&#x20;           <<concrete>>

&#x20;           -String lenght

&#x20;           -String conectorType

&#x20;           +String getDescription()

&#x20;       }



&#x20;       class Memories {

&#x20;           <<concrete>>

&#x20;           -String capacity

&#x20;           -String Type

&#x20;           +String getDescription()

&#x20;       }



&#x20;       class Sale {

&#x20;           -String id

&#x20;           -LocalDate date

&#x20;           -Customer customer

&#x20;           -Seller seller

&#x20;           -List\~Product\~ products

&#x20;           +double calculateTotal()

&#x20;       }

&#x20;   }



&#x20;   namespace Persistence {

&#x20;       class PersonRepository {

&#x20;           -String filePath

&#x20;           +List\~Person\~ load()

&#x20;           +void save(List\~Person\~ people)

&#x20;       }



&#x20;       class ProductRepository {

&#x20;           -String filePath

&#x20;           +List\~Product\~ load()

&#x20;           +void save(List\~Product\~ products)

&#x20;       }



&#x20;       class SaleRepository {

&#x20;           -String filePath

&#x20;           +List\~Sale\~ load()

&#x20;           +void save(List\~Sale\~ sales)

&#x20;       }

&#x20;   }



&#x20;   namespace Services {

&#x20;       class PersonService {

&#x20;           -PersonRepository personRepository

&#x20;           +void registerCustomer(Customer customer)

&#x20;           +List\~Customer\~ listCustomers()

&#x20;           +List\~Seller\~ listSellers()

&#x20;       }



&#x20;       class ProductService {

&#x20;           -ProductRepository productRepository

&#x20;           +void registerProduct(Product product)

&#x20;           +List\~Product\~ listProducts()

&#x20;           +void updateStock(Product product, int quantity)

&#x20;       }



&#x20;       class SaleService {

&#x20;           -SaleRepository saleRepository

&#x20;           -ProductService productService

&#x20;           +void registerSale(Sale sale)

&#x20;           +List\~Sale\~ listSales()

&#x20;           +List\~Sale\~ findByCustomer(Customer customer)

&#x20;           +List\~Sale\~ findBySeller(Seller seller)

&#x20;       }

&#x20;   }



&#x20;   namespace UserInterface {

&#x20;       class ConsoleMenu {

&#x20;           -PersonService personService

&#x20;           -ProductService productService

&#x20;           -SaleService saleService

&#x20;           +void start()

&#x20;           +void showMainMenu()

&#x20;       }

&#x20;   }



&#x20;   %% RELACIONES

&#x20;   Person <|-- Customer

&#x20;   Person <|-- Seller



&#x20;   Product <|-- Game

&#x20;   Product <|-- Console



&#x20;   Accessories <|-- Control

&#x20;   Accessories <|-- Cables

&#x20;   Accessories <|-- Memories

&#x20;   Sale <|-- Accessories

&#x20;   Product <|-- Accessories





&#x20;   Customer "1" --> "0..\*" Sale : purchases

&#x20;   Seller "1" --> "0..\*" Sale : attends

&#x20;   Sale "1" \*-- "1..\*" Product : contains



&#x20;   PersonRepository ..> Person : persists

&#x20;   ProductRepository ..> Product : persists

&#x20;   SaleRepository ..> Sale : persists



&#x20;   PersonService --> PersonRepository : uses

&#x20;   ProductService --> ProductRepository : uses

&#x20;   SaleService --> SaleRepository : uses

&#x20;   SaleService --> ProductService : updates inventory



&#x20;   ConsoleMenu --> PersonService : uses

&#x20;   ConsoleMenu --> ProductService : uses

&#x20;   ConsoleMenu --> SaleService : uses

