/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gamezoneunicesar;

/**
 *
 * @author PC
 */

import Model.Accessory;
import Model.Console;
import Model.Customer;
import Model.Game;
import Model.Seller;
import Service.PersonService;
import Service.ProductService;
import Service.SalesService;
import UI.ConsoleMenu;

/**
 * Main application entry point for GameZoneUnicesar system.
 * Handles initial data seeding, dependency injection, and UI startup.
 * 
 * @author Lead Developer
 * @version 1.0
 */
public class GameZoneUnicesar {

    public static void main(String[] args) {
        System.out.println("Initializing GameZone Unicesar System...");

        // 1. Dependency Injection: Initialize Service Layer
        ProductService productService = new ProductService();
        PersonService personService = new PersonService();
        SalesService salesService = new SalesService(productService);

        // 2. Data Seeding: Populate default items if catalog/users are empty
        seedInitialData(productService, personService);

        // 3. Inject Services into UI and Start Application
        ConsoleMenu consoleMenu = new ConsoleMenu(productService, personService, salesService);
        consoleMenu.start();
    }

    /**
     * Seeds initial products, customers, and sellers if repositories are empty.
     */
    /**
     * Seeds initial products, customers, and sellers if repositories are empty.
     */
    /**
     * Seeds initial products, customers, and sellers if repositories are empty.
     */
    private static void seedInitialData(ProductService productService, PersonService personService) {
        if (productService.getAllProducts().isEmpty()) {
            // Games (id, title, price, stock, platform, genre)
            productService.addProduct(new Game("P001", "The Legend of Zelda", 59.99, 10, "Nintendo Switch", "Action"));
            productService.addProduct(new Game("P002", "God of War Ragnarok", 69.99, 8, "PlayStation 5", "Action"));
            productService.addProduct(new Game("P003", "Halo Infinite", 49.99, 12, "Xbox Series X", "FPS"));
            productService.addProduct(new Game("P004", "Elden Ring", 59.99, 15, "PC", "RPG"));

            // Consoles (id, title, price, stock, brand, storageCapacity)
            productService.addProduct(new Console("P005", "PlayStation 5 Console", 499.99, 5, "Sony", "825GB SSD"));
            productService.addProduct(new Console("P006", "Xbox Series X", 499.99, 4, "Microsoft", "1TB SSD"));
            productService.addProduct(new Console("P007", "Nintendo Switch OLED", 349.99, 7, "Nintendo", "64GB"));

            // Accessories (id, title, price, stock, type, compatibility)
            productService.addProduct(new Accessory("P008", "DualSense Controller", 69.99, 20, "Controller", "PS5"));
            productService.addProduct(new Accessory("P009", "Xbox Headset", 99.99, 10, "Headset", "Xbox/PC"));
            productService.addProduct(new Accessory("P010", "Switch Pro Controller", 69.99, 14, "Controller", "Switch"));
        }

        if (personService.getAllPersons().isEmpty()) {
            // Customers
            personService.registerPerson(new Customer("C001", "Juan Perez", "juan@gmail.com", "3001234567", "VIP", 150));
            personService.registerPerson(new Customer("C002", "Ana Martinez", "ana.martinez@gmail.com", "3112345678", "Regular", 40));
            personService.registerPerson(new Customer("C003", "Carlos Rodriguez", "carlos.r@gmail.com", "3203456789", "VIP", 300));
            personService.registerPerson(new Customer("C004", "Laura Gomez", "laura.g@gmail.com", "3014567890", "Regular", 10));

            // Sellers
            personService.registerPerson(new Seller("S001", "Maria Gomez", "maria@gamezone.com", "3109876543", "EMP01", 1200.00, "Sales Rep", "2024-01-15"));
            personService.registerPerson(new Seller("S002", "David Lopez", "david@gamezone.com", "3158765432", "EMP02", 1350.00, "Store Supervisor", "2023-06-01"));
        }
    }
}