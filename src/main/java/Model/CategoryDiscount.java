/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Samuel
 */
import java.time.LocalDate;

/**
 * Represents a category-specific promotion that applies a percentage discount
 * only to items matching a target category.
 *
 * @author Samuel Angulo
 * @version 1.0
 */
public class CategoryDiscount extends Promotion {

    private double percentage;
    private String targetCategory;

    public CategoryDiscount() {
        super();
    }

    public CategoryDiscount(String id, String name, LocalDate startDate, LocalDate endDate, double percentage, String targetCategory) {
        super(id, name, startDate, endDate);
        this.percentage = percentage;
        this.targetCategory = targetCategory;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getTargetCategory() {
        return targetCategory;
    }

    public void setTargetCategory(String targetCategory) {
        this.targetCategory = targetCategory;
    }

   @Override
    public double calculateDiscount(Sale sale) {
        if (sale == null || sale.getDetails() == null) {
            return 0.0;
        }

        double eligibleTotal = 0.0;
        for (SaleDetail detail : sale.getDetails()) {
            if (detail != null && detail.getProduct() != null) {
                Product product = detail.getProduct();
                String productCategory = product.getClass().getSimpleName(); // Ej: "Game", "Console", "Accessory"
                
                if (productCategory.equalsIgnoreCase(targetCategory)) {
                    eligibleTotal += detail.calculateSubtotal();
                }
            }
        }

        return eligibleTotal * (percentage / 100.0);
    }
}
