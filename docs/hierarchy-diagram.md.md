# Hierarchy Diagram - GameZoneUnicesar

```mermaid
classDiagram
    direction TB

    %% Person hierarchy
    class Person {
        <<abstract>>
    }
    class Customer {
        <<concrete>>
    }
    class Employee {
        <<concrete>>
    }

    Person <|-- Customer
    Person <|-- Employee

    %% Product hierarchy
    class Product {
        <<abstract>>
    }
    class Game {
        <<concrete>>
    }
    class Accessory {
        <<concrete>>
    }
    class Console {
        <<concrete>>
    }

    Product <|-- Game
    Product <|-- Accessory
    Product <|-- Console