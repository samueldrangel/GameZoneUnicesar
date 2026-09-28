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
import persistence.AccessoryRepository;

public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final AccessoryService accessoryService;
    private final List<Sale> sales;

    /**
     * Constructor compatible con GameZoneUnicesar
     */
    public SaleService(ProductService productService) {
        this(productService, new AccessoryService(new AccessoryRepository()));
    }

    public SaleService(ProductService productService, AccessoryService accessoryService) {
        this.saleRepository = new SaleRepository();
        this.productService = productService;
        this.accessoryService = accessoryService;
        this.sales = new ArrayList<>();
    }

    public Sale processSale(String saleId, Customer customer, Seller seller, List<SaleDetail> details) {
        if (details == null || details.isEmpty()) {
            throw new IllegalArgumentException("Validation Error: A sale must contain at least one item.");
        }

        if (customer == null || seller == null) {
            throw new IllegalArgumentException("Validation Error: Customer and Seller are required to process a sale.");
        }

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

        for (SaleDetail detail : details) {
            Product item = detail.getProduct();
            int newStock = item.getStock() - detail.getQuantity();
            
            if (item instanceof Accessory) {
                accessoryService.updateStock(item.getId(), newStock);
            } else {
                productService.updateStock(item.getId(), newStock);
            }
        }

        String currentDate = LocalDate.now().toString();
        Sale sale = new Sale(saleId, currentDate, customer, seller, details);
        
        sales.add(sale);
        saleRepository.saveAll(sales);

        return sale;
    }

    public List<Sale> getAllSales() {
        return new ArrayList<>(sales);
    }
}
