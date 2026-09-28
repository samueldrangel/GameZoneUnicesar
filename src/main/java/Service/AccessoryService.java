package Service;

import Model.Accessory;
import Model.Cable;
import Model.Controller;
import Model.Memory;
import persistence.AccessoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class managing business operations related to accessories.
 */
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final List<Accessory> accessories;

    /**
     * Constructs an AccessoryService with the injected repository.
     *
     * @param accessoryRepository Repository for accessory persistence operations.
     */
    public AccessoryService(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
        this.accessories = this.accessoryRepository.loadAll();
    }

    /**
     * Registers a new controller in the system.
     */
    public Controller registerController(String id, String title, double price, int stock,
                                         String type, String compatibility, String connectionType) {
        Controller controller = new Controller(id, title, price, stock, type, compatibility, connectionType);
        accessories.add(controller);
        accessoryRepository.saveAll(accessories);
        return controller;
    }

    /**
     * Registers a new cable in the system.
     */
    public Cable registerCable(String id, String title, double price, int stock,
                                String type, String compatibility, double length, String connectorType) {
        Cable cable = new Cable(id, title, price, stock, type, compatibility, length, connectorType);
        accessories.add(cable);
        accessoryRepository.saveAll(accessories);
        return cable;
    }

    /**
     * Registers a new memory accessory in the system.
     */
    public Memory registerMemory(String id, String title, double price, int stock,
                                  String type, String compatibility, int capacityGb, String memoryType) {
        Memory memory = new Memory(id, title, price, stock, type, compatibility, capacityGb, memoryType);
        accessories.add(memory);
        accessoryRepository.saveAll(accessories);
        return memory;
    }

    /**
     * Retrieves all accessories registered in the inventory.
     *
     * @return A list containing all accessories.
     */
    public List<Accessory> listAllAccessories() {
        return new ArrayList<>(accessories);
    }

    /**
     * Filters accessories by their type attribute.
     *
     * @param type Category or type name (e.g. Controller, Cable, Memory).
     * @return List of accessories matching the specified type.
     */
    public List<Accessory> listAccessoriesByType(String type) {
        if (type == null) {
            return new ArrayList<>();
        }
        return accessories.stream()
                .filter(a -> a.getType() != null && a.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Finds all accessories compatible with a specific console ID or platform name.
     *
     * @param consoleId Unique identifier or platform name of the target console.
     * @return List of compatible accessories.
     */
    public List<Accessory> findAccessoriesCompatibleWith(String consoleId) {
        if (consoleId == null) {
            return new ArrayList<>();
        }
        return accessories.stream()
                .filter(a -> a.getCompatibility() != null && 
                        a.getCompatibility().toLowerCase().contains(consoleId.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Searches for an accessory by its unique identifier.
     *
     * @param id Unique identifier.
     * @return The matching Accessory instance, or null if not found.
     */
    public Accessory findById(String id) {
        if (id == null) {
            return null;
        }
        return accessories.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates the stock count for a given accessory and persists the change.
     *
     * @param accessoryId Unique identifier of the accessory.
     * @param quantity New available stock quantity.
     * @throws IllegalArgumentException if quantity is negative or accessory is not found.
     */
    public void updateStock(String accessoryId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
        Accessory accessory = findById(accessoryId);
        if (accessory == null) {
            throw new IllegalArgumentException("Accessory not found with ID: " + accessoryId);
        }
        accessory.setStock(quantity);
        accessoryRepository.saveAll(accessories);
    }
}
