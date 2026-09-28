/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.util.Scanner;
import Service.AccessoryService;
import Service.PersonService;
import Service.ProductService;
import Service.SaleService;
import persistence.AccessoryRepository;

/**
 * Main interactive console UI for GameZone Unicesar.
 * 
 * @author Lead Developer
 * @version 1.1
 */
public class ConsoleMenu {
    private final ProductService productService;
    private final PersonService personService;
    private final SaleService saleService;
    private final AccessoryService accessoryService;
    private final ConsoleSubmenus accessorySubmenu;
    private final Scanner scanner;

    public ConsoleMenu(ProductService productService, PersonService personService, SaleService saleService) {
        this.productService = productService;
        this.personService = personService;
        this.saleService = saleService;
        
        // Se instancia el servicio con su respectivo repositorio
        this.accessoryService = new AccessoryService(new AccessoryRepository());
        this.accessorySubmenu = new ConsoleSubmenus(this.accessoryService);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int option = -1;
        do {
            System.out.println("\n=================================");
            System.out.println("   GAMEZONE UNICESAR - MAIN MENU ");
            System.out.println("=================================");
            System.out.println("1. Product Management");
            System.out.println("2. Customer & Seller Management");
            System.out.println("3. Sales Module");
            System.out.println("4. Accessory Management");
            System.out.println("0. Exit Application");
            System.out.print("Select an option: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> showProductMenu();
                    case 2 -> showPersonMenu();
                    case 3 -> showSalesMenu();
                    case 4 -> accessorySubmenu.showAccessoryMenu();
                    case 0 -> System.out.println("\nThank you for using GameZone Unicesar. Goodbye!");
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Error: Please enter a valid number.");
                scanner.next();
            }
        } while (option != 0);
    }

    private void showProductMenu() {
        System.out.println("\n--- PRODUCT MANAGEMENT ---");
        System.out.println("1. List All Products (Games & Consoles)");
        System.out.println("2. Manage Accessories Submenu");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option: ");
        
        if (scanner.hasNextInt()) {
            int subOpt = scanner.nextInt();
            scanner.nextLine();
            if (subOpt == 1) {
                productService.getAllProducts().forEach(p -> 
                    System.out.println("[" + p.getId() + "] " + p.getTitle() + " - $" + p.getPrice() + " (Stock: " + p.getStock() + ")")
                );
            } else if (subOpt == 2) {
                accessorySubmenu.showAccessoryMenu();
            }
        }
    }

    private void showPersonMenu() {
        System.out.println("\n--- CUSTOMER & SELLER MANAGEMENT ---");
        personService.getAllPersons().forEach(p -> 
            System.out.println("[" + p.getId() + "] " + p.getName() + " - " + p.getEmail())
        );
    }

    private void showSalesMenu() {
        System.out.println("\n--- SALES MODULE ---");
        if (saleService.getAllSales().isEmpty()) {
            System.out.println("No recorded sales yet.");
        } else {
            saleService.getAllSales().forEach(s -> 
                System.out.println("Sale ID: " + s.getId()  + " | Total: $" + s.calculateTotal())
            );
        }
    }
}
