package edu.iutcs.cr.vehicles;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;
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
        setRegistrationNumber();
        setMake();
        setModel();
        setYear();
        setPrice();
        this.available = true;
    }

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber() {
        this.registrationNumber = InputHelper.readNonBlank("Enter registration number: ", "Registration number is mandatory!");
    }

    public String getMake() {
        return make;
    }

    public void setMake() {
        this.make = InputHelper.readNonBlank("Enter make: ", "Make is mandatory!");
    }

    public String getModel() {
        return model;
    }

    public void setModel() {
        this.model = InputHelper.readNonBlank("Enter model: ", "Model is mandatory!");
    }

    public String getYear() {
        return year;
    }

    public void setYear() {
        this.year = InputHelper.readNonBlank("Enter year: ", "Year is mandatory!");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter price: ");
        this.price = scanner.nextDouble();
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
