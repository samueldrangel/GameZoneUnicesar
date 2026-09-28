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
 * Submenús interactivos para la gestión de accesorios en la consola.
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
            System.out.println("      GESTIÓN DE ACCESORIOS      ");
            System.out.println("---------------------------------");
            System.out.println("1. Listar todos los accesorios");
            System.out.println("2. Registrar control");
            System.out.println("3. Registrar cable");
            System.out.println("4. Registrar memoria");
            System.out.println("5. Filtrar accesorios por tipo");
            System.out.println("6. Buscar accesorios compatibles por consola");
            System.out.println("7. Buscar accesorio por ID");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

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
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción inválida. Por favor, intente de nuevo.");
                }
            } else {
                System.out.println("Error: Por favor, ingrese un número válido.");
                scanner.next();
            }
        } while (option != 0);
    }

    private void listAllAccessories() {
        System.out.println("\n--- TODOS LOS ACCESORIOS ---");
        List<Accessory> list = accessoryService.listAllAccessories();
        if (list.isEmpty()) {
            System.out.println("No hay accesorios registrados actualmente.");
        } else {
            list.forEach(this::displayAccessoryDetails);
        }
    }

    private void registerControllerMenu() {
        System.out.println("\n--- REGISTRAR NUEVO CONTROL ---");
        try {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Título / Nombre: ");
            String title = scanner.nextLine();

            System.out.print("Precio: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock (Inventario): ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Tipo (ej., Gamepad, Arcade Stick): ");
            String type = scanner.nextLine();

            System.out.print("Compatibilidad (ej., PS5, Xbox Series, PC): ");
            String compatibility = scanner.nextLine();

            System.out.print("Tipo de conexión (ej., Inalámbrico Bluetooth, USB-C): ");
            String connectionType = scanner.nextLine();

            Controller controller = accessoryService.registerController(id, title, price, stock, type, compatibility, connectionType);
            System.out.println("Control registrado exitosamente con el ID: " + controller.getId());

        } catch (NumberFormatException e) {
            System.out.println("Error: El precio y el stock deben ser valores numéricos válidos.");
        } catch (Exception e) {
            System.out.println("Error al registrar el control: " + e.getMessage());
        }
    }

    private void registerCableMenu() {
        System.out.println("\n--- REGISTRAR NUEVO CABLE ---");
        try {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Título / Nombre: ");
            String title = scanner.nextLine();

            System.out.print("Precio: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock (Inventario): ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Tipo (ej., HDMI 2.1, Cable de Poder): ");
            String type = scanner.nextLine();

            System.out.print("Compatibilidad (ej., PS5, Switch): ");
            String compatibility = scanner.nextLine();

            System.out.print("Longitud en metros (ej., 2.0): ");
            double length = Double.parseDouble(scanner.nextLine());

            System.out.print("Tipo de conector (ej., HDMI a HDMI, USB-C): ");
            String connectorType = scanner.nextLine();

            Cable cable = accessoryService.registerCable(id, title, price, stock, type, compatibility, length, connectorType);
            System.out.println("Cable registrado exitosamente con el ID: " + cable.getId());

        } catch (NumberFormatException e) {
            System.out.println("Error: El precio, el stock y la longitud deben ser valores numéricos válidos.");
        } catch (Exception e) {
            System.out.println("Error al registrar el cable: " + e.getMessage());
        }
    }

    private void registerMemoryMenu() {
        System.out.println("\n--- REGISTRAR NUEVA MEMORIA ---");
        try {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Título / Nombre: ");
            String title = scanner.nextLine();

            System.out.print("Precio: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Stock (Inventario): ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Tipo (ej., Expansión SSD, MicroSD): ");
            String type = scanner.nextLine();

            System.out.print("Compatibilidad (ej., PS5, Switch): ");
            String compatibility = scanner.nextLine();

            System.out.print("Capacidad en GB (ej., 1024): ");
            int capacityGb = Integer.parseInt(scanner.nextLine());

            System.out.print("Tipo de memoria (ej., NVMe M.2, SDXC): ");
            String memoryType = scanner.nextLine();

            Memory memory = accessoryService.registerMemory(id, title, price, stock, type, compatibility, capacityGb, memoryType);
            System.out.println("Memoria registrada exitosamente con el ID: " + memory.getId());

        } catch (NumberFormatException e) {
            System.out.println("Error: El precio, el stock y la capacidad deben ser números válidos.");
        } catch (Exception e) {
            System.out.println("Error al registrar la memoria: " + e.getMessage());
        }
    }

    private void filterByTypeMenu() {
        System.out.print("\nIngrese el Tipo de Accesorio a filtrar (ej. Controller, Cable, Memory): ");
        String type = scanner.nextLine();

        List<Accessory> filtered = accessoryService.listAccessoriesByType(type);
        if (filtered.isEmpty()) {
            System.out.println("No se encontraron accesorios que coincidan con el tipo: " + type);
        } else {
            System.out.println("\n--- ACCESORIOS DEL TIPO: " + type + " ---");
            filtered.forEach(this::displayAccessoryDetails);
        }
    }

    private void findByCompatibilityMenu() {
        System.out.print("\nIngrese el ID de la consola o Nombre de la Plataforma (ej., PS5, Switch): ");
        String consoleId = scanner.nextLine();

        List<Accessory> compatible = accessoryService.findAccessoriesCompatibleWith(consoleId);
        if (compatible.isEmpty()) {
            System.out.println("No se encontraron accesorios compatibles para: " + consoleId);
        } else {
            System.out.println("\n--- ACCESORIOS COMPATIBLES CON: " + consoleId + " ---");
            compatible.forEach(this::displayAccessoryDetails);
        }
    }

    private void findByIdMenu() {
        System.out.print("\nIngrese el ID del Accesorio: ");
        String id = scanner.nextLine();

        Accessory acc = accessoryService.findById(id);
        if (acc != null) {
            System.out.println("\n--- ACCESORIO ENCONTRADO ---");
            displayAccessoryDetails(acc);
        } else {
            System.out.println("No se encontró ningún accesorio con el ID: " + id);
        }
    }

    private void displayAccessoryDetails(Accessory a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(a.getId()).append("] ").append(a.getTitle())
          .append(" | Precio: $").append(a.getPrice())
          .append(" | Stock: ").append(a.getStock())
          .append(" | Tipo: ").append(a.getType())
          .append(" | Compatibilidad: ").append(a.getCompatibility());

        if (a instanceof Controller c) {
            sb.append(" | Conexión: ").append(c.getConnectionType());
        } else if (a instanceof Cable cb) {
            sb.append(" | Longitud: ").append(cb.getLength()).append("m")
              .append(" | Conector: ").append(cb.getConnectorType());
        } else if (a instanceof Memory m) {
            sb.append(" | Capacidad: ").append(m.getCapacityGb()).append("GB")
              .append(" | Tipo de Memoria: ").append(m.getMemoryType());
        }

        System.out.println(sb.toString());
    }
}
