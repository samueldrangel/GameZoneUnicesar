/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.util.List;
import java.util.Scanner;
import Model.Accessory;
import Service.AccessoryService;

/**
 * Submenu class responsible for handling accessory management interactions in the console.
 * Integrates with AccessoryService for business operations.
 * 
 * @author Lead Developer
 * @version 1.0
 */
public class ConsoleSubmenus {

    private final AccessoryService accessoryService;
    private final Scanner scanner;

    /**
     * Initializes the accessory submenu with the required AccessoryService dependency.
     * 
     * @param accessoryService Injected instance of AccessoryService
     */
    public ConsoleSubmenus(AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays and manages the accessory options loop in the interactive console.
     */
    public void showAccessoryMenu() {
        int option = -1;
        do {
            System.out.println("\n=========================================");
            System.out.println("      MÓDULO DE GESTIÓN DE ACCESORIOS   ");
            System.out.println("=========================================");
            System.out.println("1. Listar todos los accesorios");
            System.out.println("2. Filtrar accesorios por tipo (Controller, Cable, Memory)");
            System.out.println("3. Buscar por compatibilidad de consola");
            System.out.println("4. Consultar accesorio por ID");
            System.out.println("5. Registrar un nuevo accesorio");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el búfer

                switch (option) {
                    case 1 -> handleListAll();
                    case 2 -> handleFilterByType();
                    case 3 -> handleFilterByCompatibility();
                    case 4 -> handleFindById();
                    case 5 -> handleRegisterAccessory();
                    case 6 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Error: Por favor ingrese un número entero válido.");
                scanner.next(); // Descartar entrada inválida
            }
        } while (option != 6);
    }

    private void handleListAll() {
        System.out.println("\n--- Lista de Todos los Accesorios ---");
        List<Accessory> accessories = accessoryService.listAllAccessories();
        if (accessories.isEmpty()) {
            System.out.println("No hay accesorios registrados en el inventario.");
        } else {
            accessories.forEach(a -> 
                System.out.printf("ID: %-8s | Título: %-25s | Tipo: %-12s | Precio: $%-8.2f | Stock: %d%n",
                        a.getId(), a.getTitle(), a.getType(), a.getPrice(), a.getStock())
            );
        }
    }

    private void handleFilterByType() {
        System.out.print("\nIngrese el tipo a filtrar (e.g. Controller, Cable, Memory): ");
        String type = scanner.nextLine();
        List<Accessory> filtered = accessoryService.listAccessoriesByType(type);

        if (filtered.isEmpty()) {
            System.out.println("No se encontraron accesorios del tipo: " + type);
        } else {
            System.out.println("\n--- Accesorios del tipo '" + type + "' ---");
            filtered.forEach(a -> 
                System.out.printf("ID: %-8s | Título: %-25s | Precio: $%-8.2f | Stock: %d%n",
                        a.getId(), a.getTitle(), a.getPrice(), a.getStock())
            );
        }
    }

    private void handleFilterByCompatibility() {
        System.out.print("\nIngrese el nombre o ID de la consola (e.g. PS5, Xbox, Switch): ");
        String consoleId = scanner.nextLine();
        List<Accessory> compatible = accessoryService.findAccessoriesCompatibleWith(consoleId);

        if (compatible.isEmpty()) {
            System.out.println("No se encontraron accesorios compatibles con: " + consoleId);
        } else {
            System.out.println("\n--- Accesorios compatibles con '" + consoleId + "' ---");
            compatible.forEach(a -> 
                System.out.printf("ID: %-8s | Título: %-25s | Compatibilidad: %-15s | Stock: %d%n",
                        a.getId(), a.getTitle(), a.getCompatibility(), a.getStock())
            );
        }
    }

    private void handleFindById() {
        System.out.print("\nIngrese el ID del accesorio a buscar: ");
        String id = scanner.nextLine();
        Accessory accessory = accessoryService.findById(id);

        if (accessory != null) {
            System.out.println("\n--- Accesorio Encontrado ---");
            System.out.println("ID:             " + accessory.getId());
            System.out.println("Título:         " + accessory.getTitle());
            System.out.println("Tipo:           " + accessory.getType());
            System.out.println("Precio:         $" + accessory.getPrice());
            System.out.println("Stock:          " + accessory.getStock());
            System.out.println("Compatibilidad: " + accessory.getCompatibility());
        } else {
            System.out.println("Error: No existe ningún accesorio con el ID: " + id);
        }
    }

    private void handleRegisterAccessory() {
        System.out.println("\n--- Registrar Nuevo Accesorio ---");
        System.out.println("1. Registrar Control (Controller)");
        System.out.println("2. Registrar Cable");
        System.out.println("3. Registrar Memoria (Memory)");
        System.out.print("Seleccione la categoría: ");

        if (scanner.hasNextInt()) {
            int category = scanner.nextInt();
            scanner.nextLine();

            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Título/Nombre: ");
            String title = scanner.nextLine();
            System.out.print("Precio: ");
            double price = scanner.nextDouble();
            System.out.print("Stock inicial: ");
            int stock = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Compatibilidad: ");
            String compatibility = scanner.nextLine();

            switch (category) {
                case 1 -> {
                    System.out.print("Tipo de conexión (e.g., Wireless/Bluetooth/USB): ");
                    String connectionType = scanner.nextLine();
                    accessoryService.registerController(id, title, price, stock, "Controller", compatibility, connectionType);
                    System.out.println("¡Control registrado exitosamente!");
                }
                case 2 -> {
                    System.out.print("Longitud en metros: ");
                    double length = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Tipo de conector (e.g., HDMI 2.1 / USB-C): ");
                    String connectorType = scanner.nextLine();
                    accessoryService.registerCable(id, title, price, stock, "Cable", compatibility, length, connectorType);
                    System.out.println("¡Cable registrado exitosamente!");
                }
                case 3 -> {
                    System.out.print("Capacidad en GB: ");
                    int capacityGb = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tipo de memoria (e.g., NVMe SSD / MicroSD): ");
                    String memoryType = scanner.nextLine();
                    accessoryService.registerMemory(id, title, price, stock, "Memory", compatibility, capacityGb, memoryType);
                    System.out.println("¡Memoria registrada exitosamente!");
                }
                default -> System.out.println("Categoría no válida.");
            }
        } else {
            System.out.println("Entrada no válida.");
            scanner.next();
        }
    }
}
