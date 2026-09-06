/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
* Concrete subclass representing a Seller (Employee) in the GameZone system.
* Extends Person by adding employment details, compensation, and store roles.
* 
* @author Kevin Santiago Amaris Sánchez
* @version 1.0
*/

public class Seller extends Person {
    private String employeeId;
    private double salary;
    private String role;
    private String hireDate;
 
    /**
     * Default constructor.
     */

    public Seller() {
        super();
    }
 
    /**
     * Parameterized constructor to initialize a Seller instance.
     * 
     * @param id         Unique identification number or national ID
     * @param name       Full name of the seller
     * @param email      Email address
     * @param phone      Contact phone number
     * @param employeeId Internal store employee identifier
     * @param salary     Base monthly salary
     * @param role       Role or position within the store (e.g., Sales Representative, Manager)
     * @param hireDate   Date of employment
     */

    public Seller(String id, String name, String email, String phone, String employeeId, double salary, String role, String hireDate) {
        super(id, name, email, phone);
        this.employeeId = employeeId;
        this.salary = salary;
        this.role = role;
        this.hireDate = hireDate;
    }
 
    // --- Getters and Setters ---
 
    public String getEmployeeId() {
        return employeeId;
    }
 
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
 
    public double getSalary() {
        return salary;
    }
 
    public void setSalary(double salary) {
        this.salary = salary;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }
 
    public String getHireDate() {
        return hireDate;
    }
 
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;

    }
 
    // --- Implementation of Abstract Method ---
 
    /**

     * Formats and returns the specific details of the seller.

     * 

     * @return Formatted string containing seller and employment information

     */

    @Override

    public String getDetails() {
        return String.format("Seller EmpID: %s | Name: %s | Role: %s | Salary: $%.2f | Hired: %s | Phone: %s",
                employeeId, getName(), role, salary, hireDate, getPhone());

    }

}
 
