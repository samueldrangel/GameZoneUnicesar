/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a product return transaction associated with a previous Sale.
 *
 * @author Samuel Angulo
 * @version 1.0
 */
public class Return {

    private String id;
    private LocalDate returnDate;
    private Sale originalSale;
    private List<Product> returnedProducts;
    private String reason;
    private double refundedAmount;

    /**
     * Default constructor initializing default values.
     */
    public Return() {
        this.returnedProducts = new ArrayList<>();
        this.returnDate = LocalDate.now();
    }

    /**
     * Parameterized constructor for Return instance.
     * Enforces domain integrity rules for original sale and returned products list.
     *
     * @param id               Unique return identifier
     * @param returnDate       Date when the return is processed
     * @param originalSale     Reference to the original sale
     * @param returnedProducts List of specific products being returned
     * @param reason           Reason for the return
     */
    public Return(String id, LocalDate returnDate, Sale originalSale, List<Product> returnedProducts, String reason) {
        if (originalSale == null) {
            throw new IllegalArgumentException("Original sale reference cannot be null.");
        }
        if (returnedProducts == null || returnedProducts.isEmpty()) {
            throw new IllegalArgumentException("Returned products list cannot be null or empty.");
        }
        this.id = id;
        this.returnDate = returnDate != null ? returnDate : LocalDate.now();
        this.originalSale = originalSale;
        this.returnedProducts = new ArrayList<>(returnedProducts);
        this.reason = reason;
        this.refundedAmount = calculateRefundAmount();
    }

    /**
     * Calculates the total monetary amount to be refunded by summing prices
     * of all returned products.
     *
     * @return Total refunded amount
     */
    public double calculateRefundAmount() {
        double total = 0.0;
        if (returnedProducts != null) {
            for (Product product : returnedProducts) {
                if (product != null) {
                    total += product.getPrice();
                }
            }
        }
        this.refundedAmount = total;
        return total;
    }

    /**
     * Generates a formatted return receipt string in Spanish detailing transaction info.
     *
     * @return Formatted return receipt string
     */
    public String generateReturnReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== COMPROBANTE DE DEVOLUCIÓN ==========\n");
        sb.append("ID Devolución: ").append(id).append("\n");
        sb.append("Fecha: ").append(returnDate).append("\n");
        sb.append("Venta Referenciada ID: ").append(originalSale != null ? originalSale.getId() : "N/A").append("\n");
        sb.append("Motivo: ").append(reason != null ? reason : "Sin especificar").append("\n");
        sb.append("-----------------------------------------------\n");
        sb.append("Productos Devueltos:\n");
        if (returnedProducts != null && !returnedProducts.isEmpty()) {
            for (Product p : returnedProducts) {
                if (p != null) {
                    sb.append(" - ").append(p.getTitle())
                      .append(" ($").append(String.format("%.2f", p.getPrice())).append(")\n");
                }
            }
        }
        sb.append("-----------------------------------------------\n");
        sb.append("Monto Total Reembolsado: $").append(String.format("%.2f", refundedAmount)).append("\n");
        sb.append("===============================================");
        return sb.toString();
    }

    // --- Getters (Strict encapsulation, no setters for immutable references) ---

    public String getId() {
        return id;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Sale getOriginalSale() {
        return originalSale;
    }

    public List<Product> getReturnedProducts() {
        return new ArrayList<>(returnedProducts);
    }

    public String getReason() {
        return reason;
    }

    public double getRefundedAmount() {
        return refundedAmount;
    }
}
