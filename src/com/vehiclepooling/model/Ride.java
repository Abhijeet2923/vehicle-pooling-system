package com.vehiclepooling.model;

import java.util.ArrayList;
import java.util.List;

public class Ride {
    private String rideId;
    private String driverId;
    private String source;
    private String destination;
    private String departureTime;
    private int availableSeats;
    private double pricePerSeat;
    private String status; // "Available", "In Progress", "Completed", "Cancelled"
    private List<String> passengerIds;

    public Ride(String id, String did, String src, String dest, String time, int seats, double price) {
        this.rideId = id;
        this.driverId = did;
        this.source = src;
        this.destination = dest;
        this.departureTime = time;
        this.availableSeats = seats;
        this.pricePerSeat = price;
        this.status = "Available";
        this.passengerIds = new ArrayList<>();
    }

    public String getRideId() { return rideId; }
    public String getDriverId() { return driverId; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public int getAvailableSeats() { return availableSeats; }
    public double getPricePerSeat() { return pricePerSeat; }
    public String getStatus() { return status; }

    public boolean bookSeat(String passengerId) {
        if (availableSeats > 0 && status.equals("Available")) {
            availableSeats--;
            passengerIds.add(passengerId);
            return true;
        }
        return false;
    }

    public void cancelSeat(String passengerId) {
        if (passengerIds.remove(passengerId)) {
            availableSeats++;
        }
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void displayRide() {
        System.out.println("\n--- Ride Details ---");
        System.out.println("Ride ID: " + rideId);
        System.out.println("From: " + source + " To: " + destination);
        System.out.println("Departure: " + departureTime);
        System.out.println("Available Seats: " + availableSeats);
        System.out.printf("Price per Seat: $%.2f%n", pricePerSeat);
        System.out.println("Status: " + status);
    }

    public List<String> getPassengerIds() { return passengerIds; }
}