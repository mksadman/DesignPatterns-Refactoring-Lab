package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.util.InputHelper;

/**
 * Responsible solely for prompting the user to select a vehicle type,
 * constructing the chosen {@link Vehicle} subtype, and adding it to the
 * database inventory.
 *
 * <p>Extracted from {@code SystemFlowRunner} to resolve the God Class smell:
 * vehicle-creation is a distinct responsibility and should not live in the
 * application-flow coordinator.</p>
 */
public class VehicleCreator {

    private final SystemDatabase database;

    public VehicleCreator(SystemDatabase database) {
        this.database = database;
    }

    /** Guides the user through selecting and creating a new vehicle. */
    public void addVehicle() {
        System.out.println("Please enter the type of vehicle [1-" + VehicleType.values().length + "]: ");
        for (VehicleType type : VehicleType.values()) {
            System.out.println(type.getCode() + ". " + type.getLabel());
        }

        int vehicleType = InputHelper.readIntInRange(
                "Enter your choice: ",
                "Enter a valid vehicle type!\nEnter your choice: ",
                1, VehicleType.values().length);

        Vehicle newItem = VehicleType.fromCode(vehicleType).create();
        database.getVehicles().add(newItem);
    }
}
