/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

/**
 *
 * @author PC
 */
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
 * 
 * @author Lead Developer
 * @version 1.0
 */
public class SalesService {

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final List<Sale> sales;

    /**
     * Initializes SalesService with required repository and product service dependencies.
     * 
     * @param productService Instance of ProductService to handle stock updates
     */
    public SalesService(ProductService productService) {
        this.saleRepository = new SaleRepository();
        this.productService = productService;
        this.sales = new ArrayList<>();
    }

    /**
     * Registers a new sale transaction in the system.
     * Validates business rules: minimum one product, stock availability, and updates inventory.
     * 
     * @param saleId   Unique transaction ID
     * @param customer Purchasing Customer
     * @param seller   Processing Seller
     * @param details  List of line items
     * @return Processed Sale instance
     * @throws IllegalArgumentException if validation rules fail (empty items or stock shortage)
     */
    public Sale processSale(String saleId, Customer customer, Seller seller, List<SaleDetail> details) {
        // Validation Rule 1: Sale must contain at least one product line item
        if (details == null || details.isEmpty()) {
            throw new IllegalArgumentException("Validation Error: A sale must contain at least one product.");
        }

        if (customer == null || seller == null) {
            throw new IllegalArgumentException("Validation Error: Customer and Seller are required to process a sale.");
        }

        // Validation Rule 2: Verify stock availability for all products in the sale
        for (SaleDetail detail : details) {
            Product product = detail.getProduct();
            int requestedQty = detail.getQuantity();

            if (product.getStock() < requestedQty) {
                throw new IllegalArgumentException(String.format(
                        "Stock Error: Insufficient stock for product '%s'. Available: %d, Requested: %d",
                        product.getTitle(), product.getStock(), requestedQty
                ));
            }
        }

        // Action: Deduct stock from products and update inventory persistence (Point 8 in analysis)
        for (SaleDetail detail : details) {
            Product product = detail.getProduct();
            int newStock = product.getStock() - detail.getQuantity();
            productService.updateStock(product.getId(), newStock);
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
