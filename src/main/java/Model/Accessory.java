/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Samuel Angulo
 */
/**
 * Represents an accessory product in the GameZone store.
 * Extends the abstract base class Product.
 * 
 * @author Samuel Angulo
 * @version 1.0
 */
public class Accessory extends Product {
    private String type;
    private String compatibility;

    /**
     * Default constructor.
     */
    public Accessory() {
        super();
    }

    /**
     * Parameterized constructor to initialize an Accessory instance.
     * 
     * @param id            Unique identifier for the accessory
     * @param title         Title or name of the accessory
     * @param price         Price of the accessory
     * @param stock         Available inventory count
     * @param type          Type of accessory (e.g., Controller, Headset, Cable)
     * @param compatibility Compatible devices/platforms (e.g., PS5, Xbox, Universal)
     */
    public Accessory(String id, String title, double price, int stock, String type, String compatibility) {
        super(id, title, price, stock);
        this.type = type;
        this.compatibility = compatibility;
    }

    // --- Getters and Setters ---

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    /**
     * Provides a formatted description specific to an accessory product.
     * 
     * @return Formatted string with accessory details
     */
    @Override
    public String getDescription() {
        return String.format("Accessory: %s | Type: %s | Compatibility: %s | Price: $%.2f | Stock: %d",
                getTitle(), type, compatibility, getPrice(), getStock());
    }
}
