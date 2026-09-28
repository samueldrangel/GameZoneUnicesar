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
 * Interfaz de consola interactiva principal para GameZone Unicesar.
 * 
 * @author Desarrollador Principal
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
            System.out.println("   GAMEZONE UNICESAR - MENÚ PRINCIPAL ");
            System.out.println("=================================");
            System.out.println("1. Gestión de Productos");
            System.out.println("2. Gestión de Clientes y Vendedores");
            System.out.println("3. Módulo de Ventas");
            System.out.println("4. Gestión de Accesorios");
            System.out.println("0. Salir de la Aplicación");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> showProductMenu();
                    case 2 -> showPersonMenu();
                    case 3 -> showSalesMenu();
                    case 4 -> accessorySubmenu.showAccessoryMenu();
                    case 0 -> System.out.println("\n¡Gracias por usar GameZone Unicesar. Hasta luego!");
                    default -> System.out.println("Opción inválida. Por favor, intente de nuevo.");
                }
            } else {
                System.out.println("Error: Por favor, ingrese un número válido.");
                scanner.next();
            }
        } while (option != 0);
    }

    private void showProductMenu() {
        System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
        System.out.println("1. Listar todos los productos (Juegos y Consolas)");
        System.out.println("2. Submenú de Gestión de Accesorios");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        
        if (scanner.hasNextInt()) {
            int subOpt = scanner.nextInt();
            scanner.nextLine();
            if (subOpt == 1) {
                productService.getAllProducts().forEach(p -> 
                    System.out.println("[" + p.getId() + "] " + p.getTitle() + " - $" + p.getPrice() + " (Inventario: " + p.getStock() + ")")
                );
            } else if (subOpt == 2) {
                accessorySubmenu.showAccessoryMenu();
            }
        }
    }

    private void showPersonMenu() {
        System.out.println("\n--- GESTIÓN DE CLIENTES Y VENDEDORES ---");
        personService.getAllPersons().forEach(p -> 
            System.out.println("[" + p.getId() + "] " + p.getName() + " - " + p.getEmail())
        );
    }

    private void showSalesMenu() {
        System.out.println("\n--- MÓDULO DE VENTAS ---");
        if (saleService.getAllSales().isEmpty()) {
            System.out.println("Aún no hay ventas registradas.");
        } else {
            saleService.getAllSales().forEach(s -> 
                System.out.println("ID Venta: " + s.getId() + " | Total: $" + s.calculateTotal())
            );
        }
    }
}
