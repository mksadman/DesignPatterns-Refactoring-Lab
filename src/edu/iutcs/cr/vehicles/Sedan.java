package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class Sedan extends Vehicle {

    private static final long serialVersionUID = 1L;

    private boolean hasSunroof;

    // Constructor
    public Sedan() {
        super();
        promptHasSunroof();
    }

    // Getters and setters
    public boolean hasSunroof() {
        return hasSunroof;
    }

    /** Prompts the user and assigns the sunroof flag. */
    public void promptHasSunroof() {
        setHasSunroof(InputHelper.readBoolean("Does the sedan have a sunroof? (true/false): "));
    }

    /** Pure setter — assigns the sunroof flag without any I/O. */
    public void setHasSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
    }

    @Override
    public String toString() {
        return "Sedan{" + super.toString() + ", " +
                "hasSunroof=" + hasSunroof() +
                "}";
    }
}
