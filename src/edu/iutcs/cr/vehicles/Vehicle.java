package edu.iutcs.cr.vehicles;

import java.io.Serializable;
import java.util.Objects;
import edu.iutcs.cr.util.InputHelper;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Vehicle implements Serializable {

    private String make;
    private String model;
    private String year;
    private double price;
    private boolean available;
    private String registrationNumber;

    public Vehicle() {
        promptRegistrationNumber();
        promptMake();
        promptModel();
        promptYear();
        promptPrice();
        this.available = true;
    }

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    /** Prompts the user and assigns the registration number. */
    public void promptRegistrationNumber() {
        setRegistrationNumber(InputHelper.readNonBlank("Enter registration number: ", "Registration number is mandatory!"));
    }

    /** Pure setter — assigns registration number without any I/O. */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    /** Prompts the user and assigns the make. */
    public void promptMake() {
        setMake(InputHelper.readNonBlank("Enter make: ", "Make is mandatory!"));
    }

    /** Pure setter — assigns make without any I/O. */
    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    /** Prompts the user and assigns the model. */
    public void promptModel() {
        setModel(InputHelper.readNonBlank("Enter model: ", "Model is mandatory!"));
    }

    /** Pure setter — assigns model without any I/O. */
    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    /** Prompts the user and assigns the year. */
    public void promptYear() {
        setYear(InputHelper.readNonBlank("Enter year: ", "Year is mandatory!"));
    }

    /** Pure setter — assigns year without any I/O. */
    public void setYear(String year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    /** Prompts the user and assigns the price. */
    public void promptPrice() {
        setPrice(InputHelper.readDouble("Enter price: "));
    }

    /** Pure setter — assigns price without any I/O. */
    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setUnavailable() {
        this.available = false;
    }

    @Override
    public String toString() {
        return "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", registrationNumber='" + registrationNumber + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(this.registrationNumber, vehicle.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}
