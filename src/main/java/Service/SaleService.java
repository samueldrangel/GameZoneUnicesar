/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Accessory;
import Model.Customer;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Model.Seller;
import Persistence.SaleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing sales business logic.
 * Orchestrates transaction validations, stock reduction, and sales persistence.
 * Adaptada para soportar tanto Productos (Juegos/Consolas) como Accesorios.
 * 
 * @author Lead Developer
 * @version 1.1
 */
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final AccessoryService accessoryService; // Adicionado para accesorios
    private final List<Sale> sales;

    /**
     * Initializes SaleService with required repositories and services.
     * 
     * @param productService Instance of ProductService to handle product stock updates
     * @param accessoryService Instance of AccessoryService to handle accessory stock updates
     */
    public SaleService(ProductService productService, AccessoryService accessoryService) {
        this.saleRepository = new SaleRepository();
        this.productService = productService;
        this.accessoryService = accessoryService;
        this.sales = new ArrayList<>();
    }

    /**
     * Registers a new sale transaction in the system.
     * Validates business rules: minimum one item, stock availability, and updates inventory.
     * 
     * @param saleId   Unique transaction ID
     * @param customer Purchasing Customer
     * @param seller   Processing Seller
     * @param details  List of line items
     * @return Processed Sale instance
     * @throws IllegalArgumentException if validation rules fail
     */
    public Sale processSale(String saleId, Customer customer, Seller seller, List<SaleDetail> details) {
        // Validation Rule 1: Sale must contain at least one product line item
        if (details == null || details.isEmpty()) {
            throw new IllegalArgumentException("Validation Error: A sale must contain at least one item.");
        }

        if (customer == null || seller == null) {
            throw new IllegalArgumentException("Validation Error: Customer and Seller are required to process a sale.");
        }

        // Validation Rule 2: Verify stock availability for all products/accessories in the sale
        for (SaleDetail detail : details) {
            Product item = detail.getProduct();
            int requestedQty = detail.getQuantity();

            if (item.getStock() < requestedQty) {
                throw new IllegalArgumentException(String.format(
                        "Stock Error: Insufficient stock for item '%s'. Available: %d, Requested: %d",
                        item.getTitle(), item.getStock(), requestedQty
                ));
            }
        }

        // Action: Deduct stock and update inventory persistence according to item type
        for (SaleDetail detail : details) {
            Product item = detail.getProduct();
            int newStock = item.getStock() - detail.getQuantity();
            
            // Si el item es un Accesorio, actualiza a través de AccessoryService (accessories.csv)
            if (item instanceof Accessory) {
                accessoryService.updateStock(item.getId(), newStock);
            } else {
                // Si es un Producto regular, actualiza mediante ProductService (products.csv)
                productService.updateStock(item.getId(), newStock);
            }
        }

        // Create and register sale transaction
        String currentDate = LocalDate.now().toString();
        Sale sale = new Sale(saleId, currentDate, customer, seller, details);
        
        sales.add(sale);
        saleRepository.saveAll(sales);

        return sale;
    }

    /**
     * Retrieves all recorded sales.
     * 
     * @return List of processed Sales
     */
    public List<Sale> getAllSales() {
        return new ArrayList<>(sales);
    }
}
