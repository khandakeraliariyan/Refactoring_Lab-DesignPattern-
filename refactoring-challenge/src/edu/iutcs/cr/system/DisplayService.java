package edu.iutcs.cr.system;

import edu.iutcs.cr.Invoice;
import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.vehicles.Vehicle;

import java.util.Set;

/**
 * Responsible for displaying data to the console.
 * Extracted from SystemDatabase to adhere to Single Responsibility Principle.
 */
public class DisplayService {

    public void showInventory(Set<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles is present in system");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }

    public void showBuyerList(Set<Buyer> buyers) {
        if (buyers.isEmpty()) {
            System.out.println("No buyer is present in system");
            return;
        }

        for (Buyer buyer : buyers) {
            System.out.println(buyer.toString());
        }
    }

    public void showSellerList(Set<Seller> sellers) {
        if (sellers.isEmpty()) {
            System.out.println("No seller is present in system");
            return;
        }

        for (Seller seller : sellers) {
            System.out.println(seller.toString());
        }
    }

    public void showInvoices(Set<Invoice> invoices) {
        if (invoices.isEmpty()) {
            System.out.println("No invoice found in system");
            return;
        }

        for (Invoice invoice : invoices) {
            invoice.printInvoice();
            System.out.println("\n\n\n");
        }
    }
}