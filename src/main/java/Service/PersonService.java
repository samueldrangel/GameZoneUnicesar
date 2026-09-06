package Service;
 
import Model.Customer;
import Model.Person;
import Model.Seller;
import Persistence.PersonRepository;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
/**
* Service class that manages business logic operations for Person entities.
* Handles registration, lookup, and filtering for Customers and Sellers.
* 
* @author Developer 2
* @version 1.0
*/

public class PersonService {
 
    private final PersonRepository personRepository;
    private final List<Person> persons;
 
    /**
     * Initializes the PersonService and loads existing registered persons from storage.
     */
    public PersonService() {
        this.personRepository = new PersonRepository();
        this.persons = personRepository.findAll();
    }
 
    /**
     * Registers a new person (Customer or Seller) if the identification number is unique.
     * 
     * @param person The Person instance to be registered
     * @return true if registered successfully, false if a person with the same ID already exists
     */
    public boolean registerPerson(Person person) {
        if (findById(person.getId()).isPresent()) {
            return false;
        }
 
        // Additional uniqueness check for Seller employeeId
        if (person instanceof Seller) {
            Seller newSeller = (Seller) person;
            boolean employeeIdExists = persons.stream()
                    .filter(p -> p instanceof Seller)
                    .map(p -> (Seller) p)
                    .anyMatch(s -> s.getEmployeeId().equalsIgnoreCase(newSeller.getEmployeeId()));
            if (employeeIdExists) {
                return false;
            }
        }
 
        persons.add(person);
        personRepository.saveAll(persons);
        return true;
    }
 
    /**
     * Retrieves all registered persons regardless of their specific role.
     * 
     * @return List of all Person instances
     */
    public List<Person> getAllPersons() {
        return new ArrayList<>(persons);
    }
 
    /**
     * Retrieves all registered customers.
     * 
     * @return List containing only Customer instances
     */
    public List<Customer> getAllCustomers() {
        return persons.stream()
                .filter(p -> p instanceof Customer)
                .map(p -> (Customer) p)
                .collect(Collectors.toList());
    }
 
    /**
     * Retrieves all registered sellers/employees.
     * 
     * @return List containing only Seller instances
     */
    public List<Seller> getAllSellers() {
        return persons.stream()
                .filter(p -> p instanceof Seller)
                .map(p -> (Seller) p)
                .collect(Collectors.toList());
    }
 
    /**
     * Searches for a person by their unique identification document.
     * 
     * @param id The unique ID/document number to search for
     * @return Optional containing the Person if found, or empty Optional otherwise
     */
    public Optional<Person> findById(String id) {
        return persons.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst();
    }
}
