package edu.iutcs.cr.system;

import edu.iutcs.cr.Invoice;
import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.vehicles.Vehicle;

import java.io.Serializable;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class SystemDatabase implements Serializable {

    private Set<Buyer> buyers;
    private Set<Seller> sellers;
    private Set<Vehicle> vehicles;
    private Set<Invoice> invoices;

    private static SystemDatabase instance;

    private SystemDatabase() {
        DataStore dataStore = new DataStore();

        buyers = dataStore.loadBuyers();
        sellers = dataStore.loadSellers();
        vehicles = dataStore.loadVehicles();
        invoices = dataStore.loadInvoices();
    }

    public static SystemDatabase getInstance() {
        if (isNull(instance)) {
            instance = new SystemDatabase();
        }

        return instance;
    }

    public void saveSystem() {
        DataStore dataStore = new DataStore();

        dataStore.saveBuyers(buyers);
        dataStore.saveSellers(sellers);
        dataStore.saveVehicles(vehicles);
        dataStore.saveInvoices(invoices);
    }

    public Set<Buyer> getBuyers() {
        return buyers;
    }

    public Set<Seller> getSellers() {
        return sellers;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
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

    private <T> T findInSet(Set<T> collection, T key) {
        for (T item : collection) {
            if (item.equals(key)) {
                return item;
            }
        }
        return null;
    }

    public void showInventory() {
        showCollection(vehicles, "No vehicles is present in system",
                vehicle -> System.out.println(vehicle.toString()));
    }

    public void showBuyerList() {
        showCollection(buyers, "No buyer is present in system",
                buyer -> System.out.println(buyer.toString()));
    }

    public void showSellerList() {
        showCollection(sellers, "No seller is present in system",
                seller -> System.out.println(seller.toString()));
    }

    public void showInvoices() {
        showCollection(invoices, "No invoice found in system", invoice -> {
            invoice.printInvoice();
            System.out.println("\n\n\n");
        });
    }

    public Vehicle findVehicleByRegistrationNumber(String registrationNumber) {
        return findInSet(vehicles, new Vehicle(registrationNumber));
    }

    public Buyer findBuyerById(String id) {
        return findInSet(buyers, new Buyer(id));
    }

    public Seller findSellerById(String id) {
        return findInSet(sellers, new Seller(id));
    }
}
