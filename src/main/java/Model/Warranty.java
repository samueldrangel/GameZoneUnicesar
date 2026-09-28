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
 * Represents an abstract warranty associated with a product sale.
 */
public abstract class Warranty {
    private final String id;
    private final Product product;
    private final Sale sale;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a Warranty instance and automatically calculates the end date.
     *
     * @param id        the unique warranty identifier
     * @param product   the product associated with the warranty
     * @param sale      the sale associated with the warranty
     * @param startDate the start date of the warranty
     */
    public Warranty(String id, Product product, Sale sale, LocalDate startDate) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Warranty ID cannot be null or empty.");
        }
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (sale == null) {
            throw new IllegalArgumentException("Sale cannot be null.");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null.");
        }

        this.id = id;
        this.product = product;
        this.sale = sale;
        this.startDate = startDate;
        this.endDate = startDate.plusMonths(getDurationInMonths());
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Sale getSale() {
        return sale;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Gets the duration of the warranty in months.
     *
     * @return duration in months
     */
    public abstract int getDurationInMonths();

    /**
     * Gets the type name of the warranty.
     *
     * @return warranty type display name
     */
    public abstract String getWarrantyType();

    /**
     * Gets the additional cost added to the sale for this warranty.
     *
     * @return additional cost
     */
    public abstract double getAdditionalCost();

    /**
     * Checks if the warranty is active on the given date.
     *
     * @param date the date to check
     * @return true if active, false otherwise
     */
    public boolean isActive(LocalDate date) {
        if (date == null) {
            return false;
        }
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    /**
     * Generates a Spanish formatted certificate for the warranty.
     *
     * @return formatted string certificate
     */
    public String generateWarrantyCertificate() {
    return String.format(
        "=== COMPROBANTE DE GARANTÍA ===\n" +
        "ID Garantía    : %s\n" +
        "Tipo           : %s\n" +
        "Producto       : %s\n" +
        "ID Venta       : %s\n" +
        "Fecha Inicio   : %s\n" +
        "Fecha Vencimiento: %s\n" +
        "Costo Adicional: $%.2f\n" +
        "===============================",
        id, getWarrantyType(), product.getTitle(), sale.getId(), startDate, endDate, getAdditionalCost()
    );
}
}
