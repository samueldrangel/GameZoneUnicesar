/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Samuel
 */
/**
 * Represents a video game product in the GameZone store.
 * Extends the abstract base class Product.
 * 
 * @author Samuel Rangel
 * @version 1.0
 */
public class Game extends Product {
    private String platform;
    private String genre;

    /**
     * Default constructor.
     */
    public Game() {
        super();
    }

    /**
     * Parameterized constructor to initialize a Game instance.
     * 
     * @param id       Unique identifier for the game
     * @param title    Title of the game
     * @param price    Price of the game
     * @param stock    Available inventory count
     * @param platform Target platform (e.g., PS5, Xbox, PC)
     * @param genre    Game genre (e.g., Action, RPG, Sports)
     */
    public Game(String id, String title, double price, int stock, String platform, String genre) {
        super(id, title, price, stock);
        this.platform = platform;
        this.genre = genre;
    }

    // --- Getters and Setters ---

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Provides a formatted description specific to a video game product.
     * 
     * @return Formatted string with game details
     */
    @Override
    public String getDescription() {
        return String.format("Game: %s | Platform: %s | Genre: %s | Price: $%.2f | Stock: %d",
                getTitle(), platform, genre, getPrice(), getStock());
    }
}