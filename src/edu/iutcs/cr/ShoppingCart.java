package edu.iutcs.cr;

import edu.iutcs.cr.vehicles.Vehicle;
import edu.iutcs.cr.util.InputHelper;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Set<Vehicle> vehicles;

    public ShoppingCart() {
        this.vehicles = new HashSet<>();
    }

    public Set<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
     * Adds a pre-validated vehicle to the cart.
     * The caller is responsible for obtaining and validating the {@link Vehicle} instance.
     */
    public void addItem(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    /** Marks every vehicle in the cart as unavailable after a confirmed purchase. */
    public void markVehiclesUnavailable() {
        for (Vehicle vehicle : vehicles) {
            vehicle.setUnavailable();
        }
    }

    public void removeItem() {
        String registrationNumber = InputHelper.readNonBlank(
                "Enter the registration number of the vehicle: ", "Registration number is mandatory!");
        vehicles.remove(new Vehicle(registrationNumber));
    }

    public void viewCart() {
        System.out.println("\n\nShopping cart\n\n");

        if(vehicles.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }
}
