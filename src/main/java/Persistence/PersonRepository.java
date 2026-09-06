package Persistence;
 
import Model.Customer;
import Model.Person;
import Model.Seller;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
/**
* Handles read and write operations for Person entities using flat text file storage.
* Supports persistence for both Customer and Seller concrete subclasses.
* 
* @author Kevin Santiago Amaris Sánchez
* @version 1.0
*/
public class PersonRepository {
 
    private static final String FILE_PATH = "data/persons.txt";
    private static final String DELIMITER = ";";
 
    /**
     * Initializes the repository and ensures the data directory and file exist.
     */
    public PersonRepository() {
        ensureFileExists();
    }
 
    /**
     * Verifies if the storage file exists, creating directories and the file if missing.
     */
    private void ensureFileExists() {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error initializing persons persistence file: " + e.getMessage());
        }
    }
 
    /**
     * Saves the complete list of persons to the text file.
     * Overwrites existing content to sync current state.
     * 
     * @param persons List of Person instances (Customer / Seller) to persist
     */
    public void saveAll(List<Person> persons) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Person person : persons) {
                if (person instanceof Customer) {
                    Customer c = (Customer) person;
                    // Format: CUSTOMER;id;name;email;phone;customerType;accumulatedPoints
                    writer.write(String.join(DELIMITER,
                            "CUSTOMER",
                            c.getId(),
                            c.getName(),
                            c.getEmail(),
                            c.getPhone(),
                            c.getCustomerType(),
                            String.valueOf(c.getAccumulatedPoints())
                    ));
                } else if (person instanceof Seller) {
                    Seller s = (Seller) person;
                    // Format: SELLER;id;name;email;phone;employeeId;salary;role;hireDate
                    writer.write(String.join(DELIMITER,
                            "SELLER",
                            s.getId(),
                            s.getName(),
                            s.getEmail(),
                            s.getPhone(),
                            s.getEmployeeId(),
                            String.valueOf(s.getSalary()),
                            s.getRole(),
                            s.getHireDate()
                    ));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving persons to file: " + e.getMessage());
        }
    }
 
    /**
     * Reads all persisted persons from the text file and restores Customer and Seller objects.
     * 
     * @return List of restored Person instances
     */
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        File file = new File(FILE_PATH);
 
        if (!file.exists()) {
            return persons;
        }
 
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(DELIMITER);
                String type = parts[0];
 
                if ("CUSTOMER".equalsIgnoreCase(type) && parts.length == 7) {
                    Customer customer = new Customer(
                            parts[1], // id
                            parts[2], // name
                            parts[3], // email
                            parts[4], // phone
                            parts[5], // customerType
                            Integer.parseInt(parts[6]) // accumulatedPoints
                    );
                    persons.add(customer);
                } else if ("SELLER".equalsIgnoreCase(type) && parts.length == 9) {
                    Seller seller = new Seller(
                            parts[1], // id
                            parts[2], // name
                            parts[3], // email
                            parts[4], // phone
                            parts[5], // employeeId
                            Double.parseDouble(parts[6]), // salary
                            parts[7], // role
                            parts[8]  // hireDate
                    );
                    persons.add(seller);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading persons from file: " + e.getMessage());
        }
 
        return persons;
    }
}