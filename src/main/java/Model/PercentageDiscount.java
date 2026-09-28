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
 * Represents a percentage-based promotion applied to the entire sale.
 *
 * @author Samuel Angulo
 * @version 1.0
 */
public class PercentageDiscount extends Promotion {

    private double percentage;

    public PercentageDiscount() {
        super();
    }

    public PercentageDiscount(String id, String name, LocalDate startDate, LocalDate endDate, double percentage) {
        super(id, name, startDate, endDate);
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

   @Override
    public double calculateDiscount(Sale sale) {
        if (sale == null) {
            return 0.0;
        }
        return sale.calculateTotal() * (percentage / 100.0);
    }
}
