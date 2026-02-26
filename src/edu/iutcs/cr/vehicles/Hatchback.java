package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class Hatchback extends Vehicle {

    private boolean isCompact;

    public Hatchback() {
        super();
        promptCompact();
    }

    public boolean isCompact() {
        return isCompact;
    }

    /** Prompts the user and assigns the compact flag. */
    public void promptCompact() {
        setCompact(InputHelper.readBoolean("Is the hatchback compact? (true/false): "));
    }

    /** Pure setter — assigns the compact flag without any I/O. */
    public void setCompact(boolean isCompact) {
        this.isCompact = isCompact;
    }

    @Override
    public String toString() {
        return "Hatchback{" + super.toString() + ", " +
                "isCompact=" + isCompact() +
                "}";
    }
}
