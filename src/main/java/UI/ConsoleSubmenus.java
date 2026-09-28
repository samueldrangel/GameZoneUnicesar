/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.util.List;
import java.util.Scanner;
import Model.Accessory;
import Model.Cable;
import Model.Controller;
import Model.Memory;
import Service.AccessoryService;

/**
 * Submenu implementation for managing specific accessory subtypes and operations.
 * 
 * @author Lead Developer
 * @version 1.1
 */
public class ConsoleSubmenus {

    private final AccessoryService accessoryService;
    private final Scanner scanner;

    public ConsoleSubmenus(AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
        this.scanner = new Scanner(System.in);
    }

    public void showAccessoryMenu() {
        int option = -1;
        do {
            System.out.println("\n---------------------------------");
            System.out.println("      ACCESSORY MANAGEMENT       ");
            System.out.println("---------------------------------");
            System.out.println("1. List All Accessories");
            System.out.println("2. Register Controller");
            System.out.println("3. Register Cable");
            System.out.println("4. Register Memory");
            System.out.println("5. Filter Accessories by Type");
            System.out.println("6. Find Compatible Accessories by Console");
            System.out.println("7. Find Accessory by ID");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> listAllAccessories();
                    case 2 -> registerControllerMenu();
                    case 3 -> registerCableMenu();
                    case 4 -> registerMemoryMenu();
                    case 5 -> filterByTypeMenu();
                    case 6 -> findByCompatibilityMenu();
                    case 7 -> findByIdMenu();
                    case 0 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Error: Please enter a valid number.");
                scanner.next();
            }
        } while (option != 0);
    }

    private void listAllAccessories() {
        System.out.println("\n--- ALL ACCESSORIES ---");
        List<Accessory> list = accessoryService.listAllAccessories();
        if (list.isEmpty()) {
            System.out.println("No accessories currently registered.");
        } else {
            list.forEach(this::displayAccessoryDetails);
        }
    }

    private void registerControllerMenu() {
        System.out.println("\n--- REGISTER NEW CONTROLLER ---");
        try {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Type (e.g., Gamepad, Arcade Stick): ");
            String type = scanner.nextLine();

            System.out.print("Compatibility (e.g., PS5, Xbox Series, PC): ");
            String compatibility = scanner.nextLine();

            System.out.print("Connection Type (e.g., Wireless Bluetooth, USB-C): ");
            String connectionType = scanner.nextLine();

            Controller controller = accessoryService.registerController(id, title, price, stock, type, compatibility, connectionType);
            System.out.println("Controller registered successfully with ID: " + controller.getId());

        } catch (NumberFormatException e) {
            System.out.println("Error: Price and stock must be valid numeric values.");
        } catch (Exception e) {
            System.out.println("Error registering controller: " + e.getMessage());
        }
    }

    private void registerCableMenu() {
        System.out.println("\n--- REGISTER NEW CABLE ---");
        try {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Type (e.g., HDMI 2.1, Power Cable): ");
            String type = scanner.nextLine();

            System.out.print("Compatibility (e.g., PS5, Switch): ");
            String compatibility = scanner.nextLine();

            System.out.print("Length in meters (e.g., 2.0): ");
            double length = Double.parseDouble(scanner.nextLine());

            System.out.print("Connector Type (e.g., HDMI to HDMI, USB-C): ");
            String connectorType = scanner.nextLine();

            Cable cable = accessoryService.registerCable(id, title, price, stock, type, compatibility, length, connectorType);
            System.out.println("Cable registered successfully with ID: " + cable.getId());

        } catch (NumberFormatException e) {
            System.out.println("Error: Price, stock, and length must be valid numeric values.");
        } catch (Exception e) {
            System.out.println("Error registering cable: " + e.getMessage());
        }
    }

    private void registerMemoryMenu() {
        System.out.println("\n--- REGISTER NEW MEMORY ACCESSORY ---");
        try {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Type (e.g., SSD Expansion, MicroSD): ");
            String type = scanner.nextLine();

            System.out.print("Compatibility (e.g., PS5, Switch): ");
            String compatibility = scanner.nextLine();

            System.out.print("Capacity in GB (e.g., 1024): ");
            int capacityGb = Integer.parseInt(scanner.nextLine());

            System.out.print("Memory Type (e.g., NVMe M.2, SDXC): ");
            String memoryType = scanner.nextLine();

            Memory memory = accessoryService.registerMemory(id, title, price, stock, type, compatibility, capacityGb, memoryType);
            System.out.println("Memory registered successfully with ID: " + memory.getId());

        } catch (NumberFormatException e) {
            System.out.println("Error: Price, stock, and capacity must be valid numbers.");
        } catch (Exception e) {
            System.out.println("Error registering memory: " + e.getMessage());
        }
    }

    private void filterByTypeMenu() {
        System.out.print("\nEnter Accessory Type to filter (e.g. Controller, Cable, Memory): ");
        String type = scanner.nextLine();

        List<Accessory> filtered = accessoryService.listAccessoriesByType(type);
        if (filtered.isEmpty()) {
            System.out.println("No accessories found matching type: " + type);
        } else {
            System.out.println("\n--- ACCESSORIES OF TYPE: " + type + " ---");
            filtered.forEach(this::displayAccessoryDetails);
        }
    }

    private void findByCompatibilityMenu() {
        System.out.print("\nEnter Console ID or Platform Name (e.g., PS5, Switch): ");
        String consoleId = scanner.nextLine();

        List<Accessory> compatible = accessoryService.findAccessoriesCompatibleWith(consoleId);
        if (compatible.isEmpty()) {
            System.out.println("No compatible accessories found for: " + consoleId);
        } else {
            System.out.println("\n--- COMPATIBLE ACCESSORIES FOR: " + consoleId + " ---");
            compatible.forEach(this::displayAccessoryDetails);
        }
    }

    private void findByIdMenu() {
        System.out.print("\nEnter Accessory ID: ");
        String id = scanner.nextLine();

        Accessory acc = accessoryService.findById(id);
        if (acc != null) {
            System.out.println("\n--- ACCESSORY FOUND ---");
            displayAccessoryDetails(acc);
        } else {
            System.out.println("Accessory not found with ID: " + id);
        }
    }

    private void displayAccessoryDetails(Accessory a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(a.getId()).append("] ").append(a.getTitle())
          .append(" | Price: $").append(a.getPrice())
          .append(" | Stock: ").append(a.getStock())
          .append(" | Type: ").append(a.getType())
          .append(" | Compatibility: ").append(a.getCompatibility());

        if (a instanceof Controller c) {
            sb.append(" | Connection: ").append(c.getConnectionType());
        } else if (a instanceof Cable cb) {
            sb.append(" | Length: ").append(cb.getLength()).append("m")
              .append(" | Connector: ").append(cb.getConnectorType());
        } else if (a instanceof Memory m) {
            sb.append(" | Capacity: ").append(m.getCapacityGb()).append("GB")
              .append(" | Memory Type: ").append(m.getMemoryType());
        }

        System.out.println(sb.toString());
    }
}
