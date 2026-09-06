/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author PC
 */

/**
 * Represents an individual line item within a sale transaction.
 * Links a specific Product with the purchased quantity and unit price.
 * 
 * @author Lead Developer
 * @version 1.0
 */
public class SaleDetail {

    private Product product;
    private int quantity;
    private double unitPrice;

    /**
     * Default constructor.
     */
    public SaleDetail() {
    }

    /**
     * Parameterized constructor for SaleDetail.
     * 
     * @param product  Product being purchased
     * @param quantity Quantity of the product
     */
    public SaleDetail(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
    }

    /**
     * Calculates the subtotal amount for this specific line item.
     * 
     * @return Subtotal (quantity * unitPrice)
     */
    public double calculateSubtotal() {
        return quantity * unitPrice;
    }

    // --- Getters and Setters ---

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
