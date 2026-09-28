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
 * Represents a volume discount promotion that applies a percentage discount 
 * if the total quantity of items in the sale reaches or exceeds a minimum threshold.
 *
 * @author Samuel Angulo
 * @version 1.0
 */
public class BulkPurchaseDiscount extends Promotion {

    private int minQuantity;
    private double percentage;

    public BulkPurchaseDiscount() {
        super();
    }

    public BulkPurchaseDiscount(String id, String name, LocalDate startDate, LocalDate endDate, int minQuantity, double percentage) {
        super(id, name, startDate, endDate);
        this.minQuantity = minQuantity;
        this.percentage = percentage;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double calculateDiscount(Sale sale) {
        if (sale == null || sale.getDetails() == null) {
            return 0.0;
        }

        // Calculamos la cantidad total de ítems acumulados en el detalle de la venta
        int totalItems = 0;
        for (SaleDetail detail : sale.getDetails()) {
            if (detail != null) {
                totalItems += detail.getQuantity(); // O el método correspondiente en SaleDetail
            }
        }

        if (totalItems >= minQuantity) {
            return sale.calculateTotal() * (percentage / 100.0);
        }

        return 0.0;
    }
}