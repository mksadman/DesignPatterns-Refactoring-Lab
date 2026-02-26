package edu.iutcs.cr.vehicles;

import java.io.Serializable;
import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Bus extends Vehicle implements Serializable {

    int passengerCapacity;

    public Bus() {
        super();
        promptPassengerCapacity();
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    /** Prompts the user and assigns the passenger capacity. */
    public void promptPassengerCapacity() {
        setPassengerCapacity(InputHelper.readInt("Enter new passenger capacity: "));
    }

    /** Pure setter — assigns passenger capacity without any I/O. */
    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public String toString() {
        return "Bus{" + super.toString() + ", " +
                "passengerCapacity=" + getPassengerCapacity() +
                "}";
    }
}
