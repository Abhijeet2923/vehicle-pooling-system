package com.vehiclepooling.model;

public class Vehicle {
    private String vehicleId;
    private String model;
    private String licensePlate;
    private int capacity;
    private String driverId;

    public Vehicle(String id, String m, String lp, int cap, String did) {
        this.vehicleId = id;
        this.model = m;
        this.licensePlate = lp;
        this.capacity = cap;
        this.driverId = did;
    }

    public String getVehicleId() { return vehicleId; }
    public String getModel() { return model; }
    public String getLicensePlate() { return licensePlate; }
    public int getCapacity() { return capacity; }
    public String getDriverId() { return driverId; }

    public void displayVehicle() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Model: " + model);
        System.out.println("License Plate: " + licensePlate);
        System.out.println("Capacity: " + capacity + " seats");
    }
}