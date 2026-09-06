/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author PC
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sale transaction in the GameZone system.
 * Connects a Customer, a Seller, and a collection of SaleDetail items.
 * Guarantees that a sale cannot exist without at least one product line item.
 * 
 * @author Lead Developer
 * @version 1.0
 */
public class Sale {

    private String id;
    private String date;
    private Customer customer;
    private Seller seller;
    private List<SaleDetail> details;

    /**
     * Default constructor initializing an empty details list.
     */
    public Sale() {
        this.details = new ArrayList<>();
    }

    /**
     * Parameterized constructor to create a complete Sale instance.
     * Enforces the domain rule that a sale must contain at least one detail item.
     * 
     * @param id       Unique identifier for the sale transaction
     * @param date     Date of the transaction
     * @param customer Customer making the purchase
     * @param seller   Seller processing the transaction
     * @param details  List of line items included in the sale
     * @throws IllegalArgumentException if the details list is null or empty
     */
    public Sale(String id, String date, Customer customer, Seller seller, List<SaleDetail> details) {
        if (details == null || details.isEmpty()) {
            throw new IllegalArgumentException("A sale cannot be registered without at least one product.");
        }
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.seller = seller;
        this.details = new ArrayList<>(details);
    }

    /**
     * Adds a detail item to the sale transaction.
     * 
     * @param detail SaleDetail line item to add
     */
    public void addDetail(SaleDetail detail) {
        if (detail != null) {
            this.details.add(detail);
        }
    }

    /**
     * Calculates the aggregate total of the sale by accumulating the subtotals of all line items.
     * Implements Activity #6.
     * 
     * @return Total monetary amount of the sale
     */
    public double calculateTotal() {
        double total = 0.0;
        if (details != null) {
            for (SaleDetail detail : details) {
                total += detail.calculateSubtotal();
            }
        }
        return total;
    }

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<SaleDetail> getDetails() {
        return new ArrayList<>(details);
    }

    public void setDetails(List<SaleDetail> details) {
        if (details == null || details.isEmpty()) {
            throw new IllegalArgumentException("A sale must contain at least one detail item.");
        }
        this.details = new ArrayList<>(details);
    }
}
