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
 * Represents a cable accessory.
 * Extends Accessory to include cable length and connector type details.
 *
 * @author Samuel Angulo
 * @version 1.0
 */
public class Cable extends Accessory {

    private double length;
    private String connectorType;

    public Cable() {
        super();
    }

    public Cable(String id, String title, double price, int stock, String type, String compatibility, double length, String connectorType) {
        super(id, title, price, stock, type, compatibility);
        this.length = length;
        this.connectorType = connectorType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    @Override
    public String getDescription() {
        return String.format("Cable: %s | Type: %s | Length: %.1fm | Connector: %s | Compatibility: %s | Price: $%.2f | Stock: %d",
                getTitle(), getType(), length, connectorType, getCompatibility(), getPrice(), getStock());
    }
}
