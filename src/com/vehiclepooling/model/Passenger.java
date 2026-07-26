package com.vehiclepooling.model;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends User {
    private List<Booking> bookings;
    private double walletBalance;

    public Passenger(String id, String n, String e, String p, String pass) {
        super(id, n, e, p, pass);
        this.walletBalance = 100.0;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking b) {
        bookings.add(b);
    }

    public double getWalletBalance() { return walletBalance; }

    public boolean deductAmount(double amount) {
        if (walletBalance >= amount) {
            walletBalance -= amount;
            return true;
        }
        return false;
    }

    public void addAmount(double amount) {
        walletBalance += amount;
    }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.printf("Wallet Balance: $%.2f%n", walletBalance);
        System.out.println("Total Bookings: " + bookings.size());
    }

    public List<Booking> getBookings() { return bookings; }
}