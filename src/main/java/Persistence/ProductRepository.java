/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

/**
 *
 * @author Samuel Angulo
 */
import Model.Console;
import Model.Game;
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
 * Manages reading and writing product records to plain text files in the data directory.
 * 
 * @author Samuel Angulo
 * @version 1.0
 */
public class ProductRepository {

    private final String filePath = "data/products.txt";

    public ProductRepository() {
        ensureFileExists();
    }

    /**
     * Ensures that the data directory and products.txt file exist.
     */
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

    /**
     * Saves a list of products to the text file, overwriting existing contents.
     * 
     * @param products List of Product instances to persist
     */
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
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving products to file: " + e.getMessage());
        }
    }

    /**
     * Reads and parses all products from the text file.
     * 
     * @return List of persisted Product instances
     */
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return products;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(";");
                String type = parts[0];

                if ("GAME".equalsIgnoreCase(type) && parts.length == 7) {
                    Game game = new Game(
                            parts[1],
                            parts[2],
                            Double.parseDouble(parts[3].replace(',', '.')),
                            Integer.parseInt(parts[4]),
                            parts[5],
                            parts[6]
                    );
                    products.add(game);
                } else if ("CONSOLE".equalsIgnoreCase(type) && parts.length == 7) {
                    Console console = new Console(
                            parts[1],
                            parts[2],
                            Double.parseDouble(parts[3].replace(',', '.')),
                            Integer.parseInt(parts[4]),
                            parts[5],
                            parts[6]
                    );
                    products.add(console);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading products from file: " + e.getMessage());
        }

        return products;
    }
}
