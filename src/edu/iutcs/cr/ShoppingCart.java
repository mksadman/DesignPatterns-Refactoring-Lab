package edu.iutcs.cr;

import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.Vehicle;
import edu.iutcs.cr.util.InputHelper;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class ShoppingCart implements Serializable {

    private final Set<Vehicle> vehicles;
    private final SystemDatabase database;

    public ShoppingCart() {
        this.vehicles = new HashSet<>();
        database = SystemDatabase.getInstance();
    }

    public Set<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public void addItem() {
        String registrationNumber = InputHelper.readNonBlank(
                "Enter registration number of vehicle: ", "Registration number is mandatory!");

        Vehicle vehicle = database.findVehicleByRegistrationNumber(registrationNumber);

        if (isNull(vehicle) || !vehicle.isAvailable()) {
            System.out.println("Vehicle not available");
            return;
        }

        vehicles.add(vehicle);
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
