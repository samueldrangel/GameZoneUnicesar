/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Samuel
 */
public class Control extends Accessory {
    private String conection;
    private String compatibility;

    public Control() {
    }

    public Control(String conection, String compatibility) {
        this.conection = conection;
        this.compatibility = compatibility;
    }

    public Control( String id, String title, double price, int stock, String type, String conection, String compatibility) {
        super(id, title, price, stock, type, compatibility);
        this.conection = conection;
        this.compatibility = compatibility;
    }

    public String getConection() {
        return conection;
    }

    public void setConection(String conection) {
        this.conection = conection;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    @Override
    public String getDescription() {
        return String.format("Accessory: %s | Type: %s | Compatibility: %s | Price: $%.2f | Stock: %d",
                getTitle(), conection, compatibility, getPrice(), getStock());
    }
    
     
    
}
