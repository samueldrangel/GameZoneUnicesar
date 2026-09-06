/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

/**
 *
 * @author PC
 */
import Service.PersonService;
import Service.ProductService;
import Service.SalesService;

import java.util.Scanner;

/**
 * Main Console User Interface class for GameZone system.
 * Implements the primary menu routing and navigation loop.
 * 
 * @author Lead Developer
 * @version 1.0
 */
public class ConsoleMenu {

    private final ConsoleSubmenus submenus;
    private final Scanner scanner;

    /**
     * Constructs the main menu and initializes module submenus via dependency injection.
     * 
     * @param productService Service handling product inventory logic
     * @param personService  Service handling customer and seller records
     * @param salesService   Service orchestrating sales transactions
     */
    public ConsoleMenu(ProductService productService, PersonService personService, SalesService salesService) {
        this.scanner = new Scanner(System.in);
        this.submenus = new ConsoleSubmenus(productService, personService, salesService, this.scanner);
    }

    /**
     * Starts the main menu event loop.
     */
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=================================");
            System.out.println("   GAMEZONE UNICESAR - MAIN MENU ");
            System.out.println("=================================");
            System.out.println("1. Product Management");
            System.out.println("2. Customer & Seller Management");
            System.out.println("3. Sales Module");
            System.out.println("0. Exit Application");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    submenus.showProductSubmenu();
                    break;
                case "2":
                    submenus.showPersonSubmenu();
                    break;
                case "3":
                    submenus.showSalesSubmenu();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting GameZone System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
