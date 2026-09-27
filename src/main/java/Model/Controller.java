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
 * Represents a game controller accessory.
 * Extends Accessory to include specific connection details.
 *
 * @author Samuel Angulo
 * @version 1.0
 */
public class Controller extends Accessory {

    private String connectionType;

    public Controller() {
        super();
    }

    public Controller(String id, String title, double price, int stock, String type, String compatibility, String connectionType) {
        super(id, title, price, stock, type, compatibility);
        this.connectionType = connectionType;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public String getDescription() {
        return String.format("Controller: %s | Type: %s | Connection: %s | Compatibility: %s | Price: $%.2f | Stock: %d",
                getTitle(), getType(), connectionType, getCompatibility(), getPrice(), getStock());
    }
}
