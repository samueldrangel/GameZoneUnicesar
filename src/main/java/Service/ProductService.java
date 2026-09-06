/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

/**
 *
 * @author Samuel Angulo
 */
import Model.Product;
import Persistence.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class that manages business logic operations for Product entities.
 * Coordinates data operations between the UI and the persistence layer.
 * 
 * @author Samuel Angulo
 * @version 1.0
 */
public class ProductService {

    private final ProductRepository productRepository;
    private final List<Product> products;

    /**
     * Initializes the ProductService, loading existing products from storage.
     */
    public ProductService() {
        this.productRepository = new ProductRepository();
        this.products = productRepository.findAll();
    }

    /**
     * Adds a new product to the catalog if its ID is unique.
     * 
     * @param product The product instance to be registered
     * @return true if added successfully, false if a product with the same ID exists
     */
    public boolean addProduct(Product product) {
        if (findById(product.getId()).isPresent()) {
            return false;
        }
        products.add(product);
        productRepository.saveAll(products);
        return true;
    }

    /**
     * Retrieves all products currently registered.
     * 
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    /**
     * Searches for a product by its unique identifier.
     * 
     * @param id Unique ID of the product
     * @return Optional containing the product if found, or empty Optional otherwise
     */
    public Optional<Product> findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    /**
     * Updates the stock count of a specific product.
     * 
     * @param id Unique ID of the product
     * @param newStock New stock quantity (must be non-negative)
     * @return true if updated successfully, false if product not found or invalid stock
     */
    public boolean updateStock(String id, int newStock) {
        if (newStock < 0) {
            return false;
        }
        Optional<Product> optionalProduct = findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setStock(newStock);
            productRepository.saveAll(products);
            return true;
        }
        return false;
    }
}