/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

/**
 *
 * @author Samuel Angulo
 */


import Model.Cable;
import Model.Console;
import Model.Controller;
import Model.Game;
import Model.Memory;
import Model.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class handling persistence operations for Product entities.
 * Manages reading and writing product records to plain text files in the data
 * directory.

 * @version 1.0
 */
public class ProductRepository {

    private final String filePath = "data/products.txt";

    public ProductRepository() {
        ensureFileExists();
    }
    
    private void ensureFileExists() {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error creating persistence file: " + e.getMessage());
        }
    }

    public void saveAll(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                if (product instanceof Game) {
                    Game game = (Game) product;
                    writer.write(String.format("GAME;%s;%s;%.2f;%d;%s;%s%n",
                            game.getId(), game.getTitle(), game.getPrice(),
                            game.getStock(), game.getPlatform(), game.getGenre()));
                } else if (product instanceof Console) {
                    Console console = (Console) product;
                    writer.write(String.format("CONSOLE;%s;%s;%.2f;%d;%s;%s%n",
                            console.getId(), console.getTitle(), console.getPrice(),
                            console.getStock(), console.getBrand(), console.getStorageCapacity()));
                } else if (product instanceof Controller) {
                    Controller controller = (Controller) product;
                    writer.write(String.format("CONTROLLER;%s;%s;%.2f;%d;%s;%s;%s%n",
                            controller.getId(), controller.getTitle(), controller.getPrice(),
                            controller.getStock(), controller.getType(), controller.getCompatibility(),
                            controller.getConnectionType()));
                } else if (product instanceof Cable) {
                    Cable cable = (Cable) product;
                    writer.write(String.format("CABLE;%s;%s;%.2f;%d;%s;%s;%.2f;%s%n",
                            cable.getId(), cable.getTitle(), cable.getPrice(),
                            cable.getStock(), cable.getType(), cable.getCompatibility(),
                            cable.getLength(), cable.getConnectorType()));
                } else if (product instanceof Memory) {
                    Memory memory = (Memory) product;
                    writer.write(String.format("MEMORY;%s;%s;%.2f;%d;%s;%s;%d;%s%n",
                            memory.getId(), memory.getTitle(), memory.getPrice(),
                            memory.getStock(), memory.getType(), memory.getCompatibility(),
                            memory.getCapacityGb(), memory.getMemoryType()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving products to file: " + e.getMessage());
        }
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return products;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(";");
                String type = parts[0];

                if (type.equalsIgnoreCase("GAME")) {
                    products.add(new Game(parts[1], parts[2], Double.parseDouble(parts[3].replace(",", ".")),
                            Integer.parseInt(parts[4]), parts[5], parts[6]));

                } else if (type.equalsIgnoreCase("CONSOLE")) {
                    products.add(new Console(parts[1], parts[2], Double.parseDouble(parts[3].replace(",", ".")),
                            Integer.parseInt(parts[4]), parts[5], parts[6]));

                } else if (type.equalsIgnoreCase("CONTROLLER")) {
                    products.add(new Controller(parts[1], parts[2], Double.parseDouble(parts[3].replace(",", ".")),
                            Integer.parseInt(parts[4]), parts[5], parts[6], parts[7]));

                } else if (type.equalsIgnoreCase("CABLE")) {
                    products.add(new Cable(parts[1], parts[2], Double.parseDouble(parts[3].replace(",", ".")),
                            Integer.parseInt(parts[4]), parts[5], parts[6], Double.parseDouble(parts[7].replace(",", ".")), parts[8]));

                } else if (type.equalsIgnoreCase("MEMORY")) {
                    products.add(new Memory(parts[1], parts[2], Double.parseDouble(parts[3].replace(",", ".")),
                            Integer.parseInt(parts[4]), parts[5], parts[6], Integer.parseInt(parts[7]), parts[8]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading products from file: " + e.getMessage());
        }

        return products;
    }
}
