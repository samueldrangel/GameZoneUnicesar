/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Samuel
 */
import java.time.LocalDate;

/**
 * Represents a basic factory warranty covering manufacturing defects for 6 months at no extra cost.
 */
public class BasicWarranty extends Warranty {

    public BasicWarranty(String id, Product product, Sale sale, LocalDate startDate) {
        super(id, product, sale, startDate);
    }

    @Override
    public int getDurationInMonths() {
        return 6;
    }

    @Override
    public String getWarrantyType() {
        return "Garantía Básica";
    }

    @Override
    public double getAdditionalCost() {
        return 0.0;
    }
}
