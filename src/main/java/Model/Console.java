/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Samuel
 */
/**
 * Represents a gaming console product in the GameZone store.
 * Extends the abstract base class Product.
 * 
 * @author Samuel Rangel
 * @version 1.0
 */
public class Console extends Product {
    private String brand;
    private String storageCapacity;

    /**
     * Default constructor.
     */
    public Console() {
        super();
    }

    /**
     * Parameterized constructor to initialize a Console instance.
     * 
     * @param id              Unique identifier for the console
     * @param title           Title or name of the console
     * @param price           Price of the console
     * @param stock           Available inventory count
     * @param brand           Brand/Manufacturer (e.g., Sony, Microsoft, Nintendo)
     * @param storageCapacity Internal storage capacity (e.g., 512GB, 1TB)
     */
    public Console(String id, String title, double price, int stock, String brand, String storageCapacity) {
        super(id, title, price, stock);
        this.brand = brand;
        this.storageCapacity = storageCapacity;
    }

    // --- Getters and Setters ---

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(String storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    /**
     * Provides a formatted description specific to a console product.
     * 
     * @return Formatted string with console details
     */
    @Override
    public String getDescription() {
        return String.format("Console: %s | Brand: %s | Storage: %s | Price: $%.2f | Stock: %d",
                getTitle(), brand, storageCapacity, getPrice(), getStock());
    }
}
