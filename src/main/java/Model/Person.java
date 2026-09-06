package Model;

/**
 * Abstract base class representing a generic person in the GameZone system.
 * Serves as the parent class for specific user roles like Customer and Admin.
 * 
 * @author Developer 2
 * @version 1.0
 */

public abstract class Person {
    private String id;
    private String name;
    private String email;

    /**
     * Default constructor.
     */
    public Person() {
    }

    /**
     * Parameterized constructor to initialize a Person instance.
     * 
     * @param id    Unique identification number or document
     * @param name  Full name of the person
     * @param email Email address for contact and account management
     */
    
    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    /**
     * Abstract method to be implemented by concrete subclasses 
     * to return the specific role or user details.
     * 
     * @return Formatted details of the person
     */
    public abstract String getDetails();
}
