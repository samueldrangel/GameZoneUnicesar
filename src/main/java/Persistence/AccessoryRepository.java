package persistence;

import Model.Accessory;
import Model.Cable;
import Model.Controller;
import Model.Memory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class responsible for persisting and retrieving Accessory entities
 * to and from a CSV file storage.
 */
public class AccessoryRepository {

    private final String filePath;

    /**
     * Constructs an AccessoryRepository with default file path "data/accessories.csv".
     */
    public AccessoryRepository() {
        this.filePath = "data/accessories.csv";
    }

    /**
     * Constructs an AccessoryRepository with a custom file path.
     *
     * @param filePath Relative or absolute path to the storage CSV file.
     */
    public AccessoryRepository(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of accessories to the CSV file.
     *
     * @param accessories List of Accessory objects to be persisted.
     */
    public void saveAll(List<Accessory> accessories) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Accessory accessory : accessories) {
                String line = serializeAccessory(accessory);
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to accessory file: " + e.getMessage());
        }
    }

    /**
     * Loads all accessories from the CSV file.
     *
     * @return List of retrieved Accessory objects, or empty list if file does not exist.
     */
    public List<Accessory> loadAll() {
        List<Accessory> accessories = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return accessories;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Accessory accessory = deserializeAccessory(line);
                if (accessory != null) {
                    accessories.add(accessory);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading accessory file: " + e.getMessage());
        }

        return accessories;
    }

    /**
     * Serializes an Accessory object into a CSV-formatted string.
     */
    private String serializeAccessory(Accessory accessory) {
        if (accessory instanceof Controller) {
            Controller controller = (Controller) accessory;
            return String.format("CONTROLLER,%s,%s,%.2f,%d,%s,%s,%s",
                    controller.getId(),
                    controller.getTitle(),
                    controller.getPrice(),
                    controller.getStock(),
                    controller.getType(),
                    controller.getCompatibility(),
                    controller.getConnectionType());
        } else if (accessory instanceof Cable) {
            Cable cable = (Cable) accessory;
            return String.format("CABLE,%s,%s,%.2f,%d,%s,%s,%.2f,%s",
                    cable.getId(),
                    cable.getTitle(),
                    cable.getPrice(),
                    cable.getStock(),
                    cable.getType(),
                    cable.getCompatibility(),
                    cable.getLength(),
                    cable.getConnectorType());
        } else if (accessory instanceof Memory) {
            Memory memory = (Memory) accessory;
            return String.format("MEMORY,%s,%s,%.2f,%d,%s,%s,%d,%s",
                    memory.getId(),
                    memory.getTitle(),
                    memory.getPrice(),
                    memory.getStock(),
                    memory.getType(),
                    memory.getCompatibility(),
                    memory.getCapacityGb(),
                    memory.getMemoryType());
        }

        return "";
    }

    /**
     * Deserializes a CSV formatted line into an Accessory instance.
     */
    private Accessory deserializeAccessory(String line) {
        String[] parts = line.split(",");
        if (parts.length < 7) {
            return null;
        }

        String classDiscriminator = parts[0];
        String id = parts[1];
        String title = parts[2];
        double price = Double.parseDouble(parts[3]);
        int stock = Integer.parseInt(parts[4]);
        String type = parts[5];
        String compatibility = parts[6];

        switch (classDiscriminator.toUpperCase()) {
            case "CONTROLLER":
                if (parts.length < 8) return null;
                String connectionType = parts[7];
                return new Controller(id, title, price, stock, type, compatibility, connectionType);

            case "CABLE":
                if (parts.length < 9) return null;
                double length = Double.parseDouble(parts[7]);
                String connectorType = parts[8];
                return new Cable(id, title, price, stock, type, compatibility, length, connectorType);

            case "MEMORY":
                if (parts.length < 9) return null;
                int capacityGb = Integer.parseInt(parts[7]);
                String memoryType = parts[8];
                return new Memory(id, title, price, stock, type, compatibility, capacityGb, memoryType);

            default:
                return null;
        }
    }
}