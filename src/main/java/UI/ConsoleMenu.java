/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.util.List;
import java.util.Scanner;
import Model.Accessory;
import Service.AccessoryService;
import Service.SaleService;

public class ConsoleMenu {
    private final AccessoryService accessoryService;
    private final SaleService saleService;
    private final Scanner scanner;

    public ConsoleMenu(AccessoryService accessoryService, SaleService saleService) {
        this.accessoryService = accessoryService;
        this.saleService = saleService;
        this.scanner = new Scanner(System.in);
    }

    public void showAccessorySubMenu() {
        int option = -1;
        do {
            System.out.println("\n=================================");
            System.out.println("     GESTIÓN DE ACCESORIOS       ");
            System.out.println("=================================");
            System.out.println("1. Listar todos los accesorios");
            System.out.println("2. Filtrar por tipo (Controller, Cable, Memory)");
            System.out.println("3. Buscar por compatibilidad de consola");
            System.out.println("4. Consultar por ID");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar búfer

                switch (option) {
                    case 1 -> {
                        List<Accessory> list = accessoryService.listAllAccessories();
                        if (list.isEmpty()) {
                            System.out.println("No hay accesorios registrados.");
                        } else {
                            list.forEach(a -> System.out.println(a.getId() + " - " + a.getTitle() + " | Stock: " + a.getStock() + " | Price: $" + a.getPrice()));
                        }
                    }
                    case 2 -> {
                        System.out.print("Ingrese el tipo (Controller/Cable/Memory): ");
                        String type = scanner.nextLine();
                        List<Accessory> filtered = accessoryService.listAccessoriesByType(type);
                        filtered.forEach(a -> System.out.println(a.getId() + " - " + a.getTitle()));
                    }
                    case 3 -> {
                        System.out.print("Ingrese ID o nombre de la consola compatible: ");
                        String console = scanner.nextLine();
                        List<Accessory> compatible = accessoryService.findAccessoriesCompatibleWith(console);
                        compatible.forEach(a -> System.out.println(a.getId() + " - " + a.getTitle()));
                    }
                    case 4 -> {
                        System.out.print("Ingrese ID del accesorio: ");
                        String id = scanner.nextLine();
                        Accessory acc = accessoryService.findById(id);
                        if (acc != null) {
                            System.out.println("Encontrado: " + acc.getTitle() + " | Stock: " + acc.getStock());
                        } else {
                            System.out.println("Accesorio no encontrado.");
                        }
                    }
                    case 5 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Entrada inválida. Ingrese un número.");
                scanner.next();
            }
        } while (option != 5);
    }
}
