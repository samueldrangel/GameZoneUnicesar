/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

/**
 *
 * @author PC
 */


import Model.Customer;
import Model.Person;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Model.Seller;
import Service.PersonService;
import Service.ProductService;
import Service.SalesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Handles submenus and input prompts for Product, Person, and Sales modules.
 * 
 * @author Lead Developer
 * @version 1.0
 */
public class ConsoleSubmenus {

    private final ProductService productService;
    private final PersonService personService;
    private final SalesService salesService;
    private final Scanner scanner;

    public ConsoleSubmenus(ProductService productService, PersonService personService, SalesService salesService, Scanner scanner) {
        this.productService = productService;
        this.personService = personService;
        this.salesService = salesService;
        this.scanner = scanner;
    }

    // --- Submenu 1: Products ---
    public void showProductSubmenu() {
        System.out.println("\n--- Product Management ---");
        System.out.println("1. List All Products");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            List<Product> products = productService.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("No products registered in system.");
            } else {
                products.forEach(p -> System.out.println(" - " + p.getDescription()));
            }
        }
    }

    // --- Submenu 2: Persons (Customers & Sellers) ---
    public void showPersonSubmenu() {
        System.out.println("\n--- Person Management ---");
        System.out.println("1. List All Customers");
        System.out.println("2. List All Sellers");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            List<Customer> customers = personService.getAllCustomers();
            if (customers.isEmpty()) {
                System.out.println("No customers found.");
            } else {
                customers.forEach(c -> System.out.println(" - " + c.getDetails()));
            }
        } else if ("2".equals(choice)) {
            List<Seller> sellers = personService.getAllSellers();
            if (sellers.isEmpty()) {
                System.out.println("No sellers found.");
            } else {
                sellers.forEach(s -> System.out.println(" - " + s.getDetails()));
            }
        }
    }

    // --- Submenu 3: Sales ---
    public void showSalesSubmenu() {
        System.out.println("\n--- Sales Module ---");
        System.out.println("1. Register New Sale");
        System.out.println("2. List All Sales");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            processNewSaleUI();
        } else if ("2".equals(choice)) {
            List<Sale> sales = salesService.getAllSales();
            if (sales.isEmpty()) {
                System.out.println("No sales registered.");
            } else {
                sales.forEach(s -> System.out.println(String.format("Sale #%s | Customer: %s | Total: $%.2f",
                        s.getId(),
                        s.getCustomer() != null ? s.getCustomer().getName() : "N/A",
                        s.calculateTotal())));
            }
        }
    }

    private void processNewSaleUI() {
        try {
            System.out.println("\n--- Processing New Sale ---");
            System.out.print("Enter Sale ID: ");
            String saleId = scanner.nextLine();

            System.out.print("Enter Customer ID: ");
            String customerId = scanner.nextLine();
            Optional<Person> customerOpt = personService.findById(customerId);
            if (!customerOpt.isPresent() || !(customerOpt.get() instanceof Customer)) {
                System.out.println("Error: Registered Customer not found with ID " + customerId);
                return;
            }

            System.out.print("Enter Seller ID: ");
            String sellerId = scanner.nextLine();
            Optional<Person> sellerOpt = personService.findById(sellerId);
            if (!sellerOpt.isPresent() || !(sellerOpt.get() instanceof Seller)) {
                System.out.println("Error: Registered Seller not found with ID " + sellerId);
                return;
            }

            List<SaleDetail> details = new ArrayList<>();
            boolean addingProducts = true;

            while (addingProducts) {
                System.out.print("Enter Product ID to add (or type 'done' to finish): ");
                String productId = scanner.nextLine();

                if ("done".equalsIgnoreCase(productId)) {
                    break;
                }

                Optional<Product> productOpt = productService.findById(productId);
                if (!productOpt.isPresent()) {
                    System.out.println("Error: Product ID not found in catalog.");
                    continue;
                }

                System.out.print("Enter Quantity: ");
                int qty = Integer.parseInt(scanner.nextLine());

                details.add(new SaleDetail(productOpt.get(), qty));
                System.out.println("Product added to cart.");
            }

            Sale sale = salesService.processSale(saleId, (Customer) customerOpt.get(), (Seller) sellerOpt.get(), details);
            System.out.println(String.format("Sale registered successfully! Total Amount: $%.2f", sale.calculateTotal()));

        } catch (IllegalArgumentException e) {
            System.out.println("Business Rule Violation: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Execution Error: " + e.getMessage());
        }
    }
}
