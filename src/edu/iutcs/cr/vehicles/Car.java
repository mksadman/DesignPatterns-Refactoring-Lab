package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Car extends Vehicle {

    private static final long serialVersionUID = 1L;

    int seatingCapacity;

    public Car() {
        super();
        promptSeatingCapacity();
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    /** Prompts the user and assigns the seating capacity. */
    public void promptSeatingCapacity() {
        setSeatingCapacity(InputHelper.readInt("Enter new seating capacity: "));
    }

    /** Pure setter — assigns seating capacity without any I/O. */
    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String toString() {
        return "Car{" + super.toString() + ", " +
                "seatingCapacity=" + getSeatingCapacity() +
                "}";
    }
}
