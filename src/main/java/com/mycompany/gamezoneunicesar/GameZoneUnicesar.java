/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gamezoneunicesar;

import Model.Console;
import Model.Customer;
import Model.Game;
import Model.Seller;
import persistence.AccessoryRepository;
import Service.AccessoryService;
import Service.PersonService;
import Service.ProductService;
import Service.SaleService;
import UI.ConsoleMenu;

/**
 * Punto de entrada principal para el sistema GameZoneUnicesar.
 * Maneja la carga inicial de datos, inyección de dependencias y el inicio de la interfaz de usuario.
 * 
 * @author Desarrollador Principal
 * @version 1.2
 */
public class GameZoneUnicesar {

    public static void main(String[] args) {
        System.out.println("Inicializando Sistema GameZoneUnicesar...");

        // 1. Inyección de dependencias: Inicializar capas de persistencia y servicios
        ProductService productService = new ProductService();
        PersonService personService = new PersonService();
        
        // Inicializar el repositorio y servicio de accesorios
        AccessoryRepository accessoryRepository = new AccessoryRepository();
        AccessoryService accessoryService = new AccessoryService(accessoryRepository);
        
        SaleService saleService = new SaleService(productService, accessoryService);

        // 2. Carga inicial de datos: Poblar elementos por defecto si el catálogo o usuarios están vacíos
        seedInitialData(productService, personService, accessoryService);

        // 3. Inyectar servicios en la interfaz de usuario e iniciar la aplicación
        ConsoleMenu consoleMenu = new ConsoleMenu(productService, personService, saleService);
        consoleMenu.start();
    }

    /**
     * Poblar productos, accesorios, clientes y vendedores iniciales si los repositorios están vacíos.
     */
    private static void seedInitialData(ProductService productService, PersonService personService, AccessoryService accessoryService) {
        // Carga de productos regulares (Juegos y Consolas)
        if (productService.getAllProducts().isEmpty()) {
            productService.addProduct(new Game("P001", "The Legend of Zelda", 59.99, 10, "Nintendo Switch", "Acción"));
            productService.addProduct(new Game("P002", "God of War Ragnarok", 69.99, 8, "PlayStation 5", "Acción"));
            productService.addProduct(new Game("P003", "Halo Infinite", 49.99, 12, "Xbox Series X", "FPS"));
            productService.addProduct(new Game("P004", "Elden Ring", 59.99, 15, "PC", "RPG"));

            productService.addProduct(new Console("P005", "Consola PlayStation 5", 499.99, 5, "Sony", "825GB SSD"));
            productService.addProduct(new Console("P006", "Xbox Series X", 499.99, 4, "Microsoft", "1TB SSD"));
            productService.addProduct(new Console("P007", "Nintendo Switch OLED", 349.99, 7, "Nintendo", "64GB"));
        }

        // Carga de Accesorios
        if (accessoryService.listAllAccessories().isEmpty()) {
            // Control: (id, título, precio, stock, tipo, compatibilidad, tipoConexion)
            accessoryService.registerController("A001", "Control DualSense", 69.99, 20, "Control", "PS5", "Inalámbrico");
            
            // Cable: (id, título, precio, stock, tipo, compatibilidad, longitud, tipoConector)
            accessoryService.registerCable("A002", "Cable HDMI 2.1", 19.99, 30, "Cable", "PS5/Xbox/PC", 2.0, "HDMI");
            
            // Memoria: (id, título, precio, stock, tipo, compatibilidad, capacidadGb, tipoMemoria)
            accessoryService.registerMemory("A003", "Tarjeta de Expansión 1TB", 149.99, 10, "Memoria", "Xbox Series X", 1024, "NVMe SSD");
        }

        // Carga de Personas (Clientes y Vendedores)
        if (personService.getAllPersons().isEmpty()) {
            personService.registerPerson(new Customer("C001", "Juan Perez", "juan@gmail.com", "3001234567", "VIP", 150));
            personService.registerPerson(new Customer("C002", "Ana Martinez", "ana.martinez@gmail.com", "3112345678", "Regular", 40));
            personService.registerPerson(new Customer("C003", "Carlos Rodriguez", "carlos.r@gmail.com", "3203456789", "VIP", 300));
            personService.registerPerson(new Customer("C004", "Laura Gomez", "laura.g@gmail.com", "3014567890", "Regular", 10));

            personService.registerPerson(new Seller("S001", "Maria Gomez", "maria@gamezone.com", "3109876543", "EMP01", 1200.00, "Representante de Ventas", "2024-01-15"));
            personService.registerPerson(new Seller("S002", "David Lopez", "david@gamezone.com", "3158765432", "EMP02", 1350.00, "Supervisor de Tienda", "2023-06-01"));
        }
    }
}