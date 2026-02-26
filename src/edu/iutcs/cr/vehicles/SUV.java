package edu.iutcs.cr.vehicles;

import java.io.Serializable;
import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class SUV extends Vehicle implements Serializable {

    private boolean isOffRoad;

    // Constructor
    public SUV() {
        super();
        promptOffRoad();
    }

    // Getters and setters
    public boolean isOffRoad() {
        return isOffRoad;
    }

    /** Prompts the user and assigns the off-road flag. */
    public void promptOffRoad() {
        setOffRoad(InputHelper.readBoolean("Is the SUV for off-road use? (true/false): "));
    }

    /** Pure setter — assigns the off-road flag without any I/O. */
    public void setOffRoad(boolean isOffRoad) {
        this.isOffRoad = isOffRoad;
    }

    @Override
    public String toString() {
        return "SUV{" + super.toString() + ", " +
                "isOffRoad=" + isOffRoad() +
                "}";
    }
}
