package edu.iutcs.cr.system;

import edu.iutcs.cr.Invoice;
import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.vehicles.Vehicle;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class DataStore {
    private <T> void saveToFile(T data, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T loadFromFile(String filename, T defaultValue) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            saveToFile(defaultValue, filename);
            return defaultValue;
        }
    }

    public void saveInvoices(Set<Invoice> invoices) {
        saveToFile(invoices, "invoices.txt");
    }

    public Set<Invoice> loadInvoices() {
        return loadFromFile("invoices.txt", new HashSet<Invoice>());
    }

    public void saveBuyers(Set<Buyer> buyers) {
        saveToFile(buyers, "buyers.txt");
    }

    public Set<Buyer> loadBuyers() {
        return loadFromFile("buyers.txt", new HashSet<Buyer>());
    }

    public void saveSellers(Set<Seller> sellers) {
        saveToFile(sellers, "sellers.txt");
    }

    public Set<Seller> loadSellers() {
        return loadFromFile("sellers.txt", new HashSet<Seller>());
    }

    public void saveVehicles(Set<Vehicle> vehicles) {
        saveToFile(vehicles, "cars.txt");
    }

    public Set<Vehicle> loadVehicles() {
        return loadFromFile("cars.txt", new HashSet<Vehicle>());
    }
}
