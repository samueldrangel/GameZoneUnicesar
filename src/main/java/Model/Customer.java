/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
* Concrete subclass representing a Customer in the GameZone system.
* Extends Person by adding loyalty points and customer categorization.
* 
* @author Developer 2
* @version 1.0
*/
public class Customer extends Person {
 
    private String customerType;
    private int accumulatedPoints;
 
    /**
     * Default constructor.
     */
    public Customer() {
        super();
    }
 
    /**
     * Parameterized constructor to initialize a Customer instance.
     * 
     * @param id                Unique identification number or document
     * @param name              Full name of the customer
     * @param email             Email address
     * @param phone             Contact phone number
     * @param customerType      Category/tier of the customer (e.g., Regular, VIP)
     * @param accumulatedPoints Total loyalty points earned
     */
    public Customer(String id, String name, String email, String phone, String customerType, int accumulatedPoints) {
        super(id, name, email, phone);
        this.customerType = customerType;
        this.accumulatedPoints = accumulatedPoints;
    }
 
    // --- Getters and Setters ---
 
    public String getCustomerType() {
        return customerType;
    }
 
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
 
    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }
 
    public void setAccumulatedPoints(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }
 
    // --- Implementation of Abstract Method ---
 
    /**
     * Formats and returns the specific details of the customer.
     * 
     * @return Formatted string containing customer information
     */
    @Override
    public String getDetails() {
        return String.format("Customer ID: %s | Name: %s | Email: %s | Phone: %s | Type: %s | Points: %d",
                getId(), getName(), getEmail(), getPhone(), customerType, accumulatedPoints);
    }
}
