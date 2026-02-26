package edu.iutcs.cr.system;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Responsible solely for displaying the contents of the system database
 * to the console.
 *
 * <p>Extracted from {@link SystemDatabase} to resolve the God Class smell:
 * display/UI logic is a distinct responsibility and should not live inside the
 * data-store class.</p>
 */
public class DatabasePrinter {

    private final SystemDatabase database;

    public DatabasePrinter(SystemDatabase database) {
        this.database = database;
    }

    public void showInventory() {
        showCollection(database.getVehicles(), "No vehicles is present in system",
                vehicle -> System.out.println(vehicle.toString()));
    }

    public void showBuyerList() {
        showCollection(database.getBuyers(), "No buyer is present in system",
                buyer -> System.out.println(buyer.toString()));
    }

    public void showSellerList() {
        showCollection(database.getSellers(), "No seller is present in system",
                seller -> System.out.println(seller.toString()));
    }

    public void showInvoices() {
        showCollection(database.getInvoices(), "No invoice found in system", invoice -> {
            invoice.printInvoice();
            System.out.println("\n\n\n");
        });
    }

    private <T> void showCollection(Set<T> collection, String emptyMessage, Consumer<T> printer) {
        if (collection.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (T item : collection) {
            printer.accept(item);
        }
    }
}
