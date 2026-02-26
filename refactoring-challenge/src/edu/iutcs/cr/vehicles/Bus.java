package edu.iutcs.cr.vehicles;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Bus extends Vehicle {

    private int passengerCapacity;

    public Bus(String registrationNumber, String make, String model, int year, double price, int passengerCapacity) {
        super(registrationNumber, make, model, year, price);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

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