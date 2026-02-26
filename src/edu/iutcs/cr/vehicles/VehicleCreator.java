package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.system.SystemDatabase;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the type of vehicle [1-5]: ");
        System.out.println("1. Bus");
        System.out.println("2. Car");
        System.out.println("3. Hatchback");
        System.out.println("4. Sedan");
        System.out.println("5. SUV");

        int vehicleType = -1;
        while (vehicleType < 1 || vehicleType > 5) {
            System.out.print("Enter your choice: ");
            vehicleType = scanner.nextInt();

            if (vehicleType < 1 || vehicleType > 5) {
                System.out.println("Enter a valid vehicle type!");
            }
        }

        Vehicle newItem = createVehicle(vehicleType);
        database.getVehicles().add(newItem);
    }

    private Vehicle createVehicle(int vehicleType) {
        switch (vehicleType) {
            case 1:
                System.out.println("\n\nCreate new bus");
                return new Bus();
            case 2:
                System.out.println("\n\nCreate new car");
                return new Car();
            case 3:
                System.out.println("\n\nCreate new hatchback");
                return new Hatchback();
            case 4:
                System.out.println("\n\nCreate new sedan");
                return new Sedan();
            default:
                System.out.println("\n\nCreate new SUV");
                return new SUV();
        }
    }
}
