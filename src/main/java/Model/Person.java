package Model;

/**
 * Abstract base class representing a generic person in the GameZone system.
 * Serves as the parent class for specific user roles like Customer and Admin.
 * 
 * @author Kevin Santiago Amaris Sánchez
 * @version 1.0
 */

public abstract class Person {

    private String id;
    private String name;
    private String email;
    private String phone;
    public Person() {

    }
 
    public Person(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;

    }
 
    // --- Getters and Setters ---
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a formatted summary of the person's details.
     * 
     * @return Formatted string with person details
     */

    public abstract String getDetails();
}
 
