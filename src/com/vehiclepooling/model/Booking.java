package com.vehiclepooling.model;

public class Booking {
    private String bookingId;
    private String passengerId;
    private String rideId;
    private String bookingTime;
    private int seatsBooked;
    private double totalAmount;
    private String status; // "Confirmed", "Cancelled", "Completed"

    public Booking(String bid, String pid, String rid, String time, int seats, double amount) {
        this.bookingId = bid;
        this.passengerId = pid;
        this.rideId = rid;
        this.bookingTime = time;
        this.seatsBooked = seats;
        this.totalAmount = amount;
        this.status = "Confirmed";
    }

    public String getBookingId() { return bookingId; }
    public String getPassengerId() { return passengerId; }
    public String getRideId() { return rideId; }
    public String getStatus() { return status; }
    public double getTotalAmount() { return totalAmount; }

    public void cancelBooking() {
        this.status = "Cancelled";
    }

    public void completeBooking() {
        this.status = "Completed";
    }

    public void displayBooking() {
        System.out.println("\n--- Booking Details ---");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Ride ID: " + rideId);
        System.out.println("Booking Time: " + bookingTime);
        System.out.println("Seats Booked: " + seatsBooked);
        System.out.printf("Total Amount: $%.2f%n", totalAmount);
        System.out.println("Status: " + status);
    }
}