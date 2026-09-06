/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

/**
 *
 * @author PC
 */
import Model.Customer;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Model.Seller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles persistence operations for Sale entities using flat text file storage.
 * Synchronizes sale transactions and detailed line items to data/sales.txt.
 * 
 * @author Lead Developer
 * @version 1.0
 */
public class SaleRepository {

    private static final String FILE_PATH = "data/sales.txt";
    private static final String MAIN_DELIMITER = ";";
    private static final String ITEM_DELIMITER = ",";
    private static final String FIELD_DELIMITER = ":";

    /**
     * Constructor initializing storage environment.
     */
    public SaleRepository() {
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error initializing sales persistence file: " + e.getMessage());
        }
    }

    /**
     * Saves all sales to the text file format.
     * Format: saleId;date;customerId;sellerId;productId:quantity:unitPrice,productId:quantity:unitPrice
     * 
     * @param sales List of Sale instances to persist
     */
    public void saveAll(List<Sale> sales) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Sale sale : sales) {
                StringBuilder detailsBuilder = new StringBuilder();
                List<SaleDetail> details = sale.getDetails();

                for (int i = 0; i < details.size(); i++) {
                    SaleDetail detail = details.get(i);
                    detailsBuilder.append(detail.getProduct().getId())
                            .append(FIELD_DELIMITER)
                            .append(detail.getQuantity())
                            .append(FIELD_DELIMITER)
                            .append(detail.getUnitPrice());

                    if (i < details.size() - 1) {
                        detailsBuilder.append(ITEM_DELIMITER);
                    }
                }

                String line = String.join(MAIN_DELIMITER,
                        sale.getId(),
                        sale.getDate(),
                        sale.getCustomer() != null ? sale.getCustomer().getId() : "N/A",
                        sale.getSeller() != null ? sale.getSeller().getId() : "N/A",
                        detailsBuilder.toString()
                );

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving sales to file: " + e.getMessage());
        }
    }

    /**
     * Reads and parses sales records from text storage.
     * Note: Relies on PersonRepository and ProductRepository to re-link domain objects in Service layer.
     * 
     * @return Raw list of lines or pre-parsed sales data
     */
    public List<String> readRawSales() {
        List<String> lines = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return lines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading sales file: " + e.getMessage());
        }
        return lines;
    }
}
