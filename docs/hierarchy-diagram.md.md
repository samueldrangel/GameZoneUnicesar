flowchart TB
    subgraph Model["Model Layer"]
        M1["Person hierarchy"]
        M2["Product hierarchy"]
        M3["Sale"]
    end

    subgraph Persistence["Persistence Layer"]
        P1["PersonRepository"]
        P2["ProductRepository"]
        P3["SaleRepository"]
        P4[("Application data files")]
    end

    subgraph Services["Service Layer"]
        S1["PersonService"]
        S2["ProductService"]
        S3["SaleService"]
    end

    subgraph UI["User Interface Layer"]
        U1["ConsoleMenu"]
        U2["Main"]
    end

    U2 --> U1
    U1 --> S1
    U1 --> S2
    U1 --> S3

    S1 --> M1
    S2 --> M2
    S3 --> M3
    S3 --> S2

    S1 --> P1
    S2 --> P2
    S3 --> P3

    P1 --> M1
    P2 --> M2
    P3 --> M3

    P1 --> P4
    P2 --> P4
    P3 --> P4

    classDef model fill:#eef2ff,stroke:#818cf8,color:#1e1b4b
    classDef persistence fill:#f0fdfa,stroke:#2dd4bf,color:#134e4a
    classDef services fill:#fff7ed,stroke:#fb923c,color:#7c2d12
    classDef ui fill:#fdf4ff,stroke:#e879f9,color:#701a75

    class M1,M2,M3 model
    class P1,P2,P3,P4 persistence
    class S1,S2,S3 services
    class U1,U2 ui