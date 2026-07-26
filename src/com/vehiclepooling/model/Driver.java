package com.vehiclepooling.model;

import java.util.ArrayList;
import java.util.List;

public class Driver extends User {
    private String licenseNumber;
    private double rating;
    private int totalRides;
    private List<Vehicle> vehicles;
    private List<Ride> rides;

    public Driver(String id, String n, String e, String p, String pass, String license) {
        super(id, n, e, p, pass);
        this.licenseNumber = license;
        this.rating = 5.0;
        this.totalRides = 0;
        this.vehicles = new ArrayList<>();
        this.rides = new ArrayList<>();
    }

    public String getLicenseNumber() { return licenseNumber; }
    public double getRating() { return rating; }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void createRide(Ride r) {
        rides.add(r);
    }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("License Number: " + licenseNumber);
        System.out.printf("Rating: %.1f/5.0%n", rating);
        System.out.println("Total Rides: " + totalRides);
        System.out.println("Vehicles: " + vehicles.size());
    }

    public void updateRating(double newRating) {
        totalRides++;
        rating = ((rating * (totalRides - 1)) + newRating) / totalRides;
    }

    public List<Ride> getRides() { return rides; }
}