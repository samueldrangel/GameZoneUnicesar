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
 * Represents a memory storage accessory.
 * Extends Accessory to include capacity and memory type details.
 *
 * @author Samuel Angulo
 * @version 1.0
 */
public class Memory extends Accessory {

    private int capacityGb;
    private String memoryType;

    public Memory() {
        super();
    }

    public Memory(String id, String title, double price, int stock, String type, String compatibility, int capacityGb, String memoryType) {
        super(id, title, price, stock, type, compatibility);
        this.capacityGb = capacityGb;
        this.memoryType = memoryType;
    }

    public int getCapacityGb() {
        return capacityGb;
    }

    public void setCapacityGb(int capacityGb) {
        this.capacityGb = capacityGb;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    @Override
    public String getDescription() {
        return String.format("Memory: %s | Type: %s | Capacity: %dGB | Memory Type: %s | Compatibility: %s | Price: $%.2f | Stock: %d",
                getTitle(), getType(), capacityGb, memoryType, getCompatibility(), getPrice(), getStock());
    }
}
