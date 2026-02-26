package edu.iutcs.cr.vehicles;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Enumerates every concrete {@link Vehicle} subtype known to the system,
 * together with its display label and a no-argument factory.
 *
 * <p>Replaces the integer-keyed switch in {@code VehicleCreator} (Long
 * if-else Chain / Switch Statements smell): adding a new vehicle type now
 * only requires adding a new enum constant here, with zero changes to any
 * dispatch logic.</p>
 */
public enum VehicleType {

    BUS(1,       "Bus",       Bus::new),
    CAR(2,       "Car",       Car::new),
    HATCHBACK(3, "Hatchback", Hatchback::new),
    SEDAN(4,     "Sedan",     Sedan::new),
    SUV(5,       "SUV",       SUV::new);

    private final int code;
    private final String label;
    private final Supplier<Vehicle> factory;

    VehicleType(int code, String label, Supplier<Vehicle> factory) {
        this.code    = code;
        this.label   = label;
        this.factory = factory;
    }

    public int getCode()    { return code; }
    public String getLabel(){ return label; }

    /** Constructs and returns a fresh instance of this vehicle type. */
    public Vehicle create() {
        System.out.println("\n\nCreate new " + label.toLowerCase());
        return factory.get();
    }

    /**
     * Looks up a {@link VehicleType} by its menu code.
     *
     * @throws IllegalArgumentException if {@code code} does not match any type
     */
    public static VehicleType fromCode(int code) {
        return Arrays.stream(values())
                .filter(t -> t.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown vehicle type code: " + code));
    }
}
