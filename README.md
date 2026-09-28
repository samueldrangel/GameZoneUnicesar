# GameZoneUnicesar 🎮

Sistema de gestión comercial y control de inventarios desarrollado para la tienda de videojuegos **GameZone Unicesar**. Proyecto en Java implementado con una arquitectura limpia de 4 capas y una capa ligera de persistencia basada en archivos de texto plano.

---

## 📋 Tabla de Contenidos

1. [Características y Módulos](#-características-y-módulos)
2. [Arquitectura del Sistema](#-arquitectura-del-sistema)
3. [Estructura del Proyecto](#-estructuración-del-proyecto)
4. [Requisitos del Sistema](#-requisitos-del-sistema)
5. [Instalación y Ejecución](#-instalación-y-ejecución)
6. [Persistencia de Datos](#-persistencia-de-datos)
7. [Tecnologías Utilizadas](#-tecnologías-utilizadas)
8. [Propósito del Proyecto](#-propósito-del-proyecto)

---

## 🚀 Características y Módulos

El sistema está dividido en módulos principales completamente funcionales:

* **Módulo de Productos y Accesorios (`Product` / `Accessory`):**
  * Gestión multiclase: Juegos (`Game`), Consolas (`Console`) y Accesorios específicos (`Accessory` → `Controller`, `Cable`, `Memory`).
  * Registro, consulta, filtrado por tipo y búsqueda de compatibilidad con consolas.
  * Funcionalidad de actualización dinámica de inventario (stock).
* **Módulo de Personas (`Person`):**
  * Gestión de Clientes (`Customer`) con niveles de fidelización y puntos acumulados de recompensa.
  * Gestión de Vendedores (`Seller`) con ID de empleado y detalles de compensación/salario.
* **Módulo de Ventas (`Sale`):**
  * Registro de transacciones vinculando cliente, vendedor y artículos/accesorios vendidos.
  * Cálculo automático de totales e impuestos.
  * Generación de comprobantes y desglose detallado por ítem (`SaleDetail`).

---

## 🏗️ Arquitectura del Sistema

El proyecto implementa una **Arquitectura de 4 Capas** orientada a objetos:

* **Capa 1: Dominio / Modelo (`Model`)**  
  Contiene las entidades principales, relaciones de herencia (`Product` → `Game`/`Console`, `Accessory` → `Controller`/`Cable`/`Memory`, `Person` → `Customer`/`Seller`) y encapsulamiento de datos.
* **Capa 2: Persistencia (`Persistence`)**  
  Repositorios encargados de la lectura y escritura directa en archivos planos (`.txt`) mediante flujos de E/S (`BufferedReader`, `BufferedWriter`).
* **Capa 3: Lógica de Negocio / Servicios (`Service`)**  
  Capa de servicios que procesa la lógica del negocio, validación de reglas y actúa como intermediaria entre la UI y la capa de persistencia.
* **Capa 4: Presentación / Interfaz (`UI`)**  
  Menús e interactivos submenús en consola (`ConsoleMenu`, `ConsoleSubmenus`) para la navegación e inyección de dependencias.

---

## 📁 Estructura del Proyecto

```text
GameZoneUnicesar/
│
├── data/                       # Archivos de persistencia (.txt)
│   ├── accessories.txt
│   ├── persons.txt
│   ├── products.txt
│   └── sales.txt
│
├── src/
│   └── main/
│       └── java/
│           ├── Model/          # Capa de Dominio / Modelo
│           │   ├── Accessory.java
│           │   ├── Cable.java
│           │   ├── Console.java
│           │   ├── Controller.java
│           │   ├── Customer.java
│           │   ├── Game.java
│           │   ├── Memory.java
│           │   ├── Person.java
│           │   ├── Product.java
│           │   ├── Sale.java
│           │   ├── SaleDetail.java
│           │   └── Seller.java
│           │
│           ├── Persistence/    # Capa de Persistencia (Archivos Planos)
│           │   ├── AccessoryRepository.java
│           │   ├── PersonRepository.java
│           │   ├── ProductRepository.java
│           │   └── SaleRepository.java
│           │
│           ├── Service/        # Capa de Lógica de Negocio
│           │   ├── AccessoryService.java
│           │   ├── PersonService.java
│           │   ├── ProductService.java
│           │   └── SalesService.java
│           │
│           ├── UI/             # Capa de Presentación
│           │   ├── ConsoleMenu.java
│           │   └── ConsoleSubmenus.java
│           │
│           └── com/mycompany/gamezoneunicesar/
│               └── GameZoneUnicesar.java  # Punto de entrada principal (Main)
│
└── README.md
💻 Requisitos del Sistema
JDK: Java Development Kit 17 o superior.

IDE Recomendado:

Apache NetBeans 15+

IntelliJ IDEA

Eclipse

Control de Versiones: Git y GitHub.

🛠️ Instalación y Ejecución
Clonar el repositorio:

Bash
git clone [https://github.com/samueldrangel/GameZoneUnicesar.git](https://github.com/samueldrangel/GameZoneUnicesar.git)
Abrir el proyecto en tu IDE:

Abre Apache NetBeans o tu IDE de Java preferido.

Selecciona File → Open Project.

Elige la carpeta clonada GameZoneUnicesar.

Ejecutar la aplicación:

Ejecuta el proyecto desde la clase principal.

En Apache NetBeans, puedes usar Shift + F6 o seleccionar Run Main Project.

La aplicación iniciará mediante la clase GameZoneUnicesar.

Datos Iniciales: En la primera ejecución, el método seedInitialData poblará automáticamente los repositorios con registros iniciales de prueba para productos, accesorios y usuarios si los archivos de datos están vacíos.

💾 Persistencia de Datos
Toda la información se almacena en archivos de texto plano delimitados por punto y coma (;), ubicados dentro de la carpeta /data:

products.txt: Almacena el catálogo de videojuegos y consolas.

accessories.txt: Almacena la información detallada de controles, cables y memorias.

persons.txt: Almacena la información de clientes y vendedores.

sales.txt: Registra las transacciones de ventas con sus correspondientes detalles.

👨‍💻 Tecnologías Utilizadas
Java 17+

Programación Orientada a Objetos (POO)

E/S de Java (Java I/O)

Persistencia en Archivos Planos

Git & GitHub

Apache NetBeans

📌 Propósito del Proyecto
GameZoneUnicesar fue desarrollado como un proyecto académico enfocado en la aplicación de principios de Programación Orientada a Objetos, arquitectura en capas, herencia, encapsulamiento, lógica de negocio y persistencia de datos en un contexto real de gestión comercial para una tienda de videojuegos.