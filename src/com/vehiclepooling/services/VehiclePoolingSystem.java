package com.vehiclepooling.services;

import com.vehiclepooling.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VehiclePoolingSystem {
    private List<Driver> drivers;
    private List<Passenger> passengers;
    private List<Vehicle> vehicles;
    private List<Ride> rides;
    private List<Booking> bookings;
    private List<Payment> payments;

    private int nextUserId;
    private int nextVehicleId;
    private int nextRideId;
    private int nextBookingId;
    private int nextPaymentId;

    public VehiclePoolingSystem() {
        drivers = new ArrayList<>();
        passengers = new ArrayList<>();
        vehicles = new ArrayList<>();
        rides = new ArrayList<>();
        bookings = new ArrayList<>();
        payments = new ArrayList<>();
        
        nextUserId = 1;
        nextVehicleId = 1;
        nextRideId = 1;
        nextBookingId = 1;
        nextPaymentId = 1;
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss yyyy");
        return now.format(formatter);
    }

    public void registerDriver(String name, String email, String phone, String password, String license) {
        String id = "D" + (nextUserId++);
        Driver driver = new Driver(id, name, email, phone, password, license);
        drivers.add(driver);
        System.out.println("\nDriver registered successfully! Driver ID: " + id);
    }

    public void registerPassenger(String name, String email, String phone, String password) {
        String id = "P" + (nextUserId++);
        Passenger passenger = new Passenger(id, name, email, phone, password);
        passengers.add(passenger);
        System.out.println("\nPassenger registered successfully! Passenger ID: " + id);
    }

    public void addVehicle(String driverId, String model, String licensePlate, int capacity) {
        Driver driver = findDriver(driverId);
        if (driver != null) {
            String id = "V" + (nextVehicleId++);
            Vehicle vehicle = new Vehicle(id, model, licensePlate, capacity, driverId);
            vehicles.add(vehicle);
            driver.addVehicle(vehicle);
            System.out.println("\nVehicle added successfully! Vehicle ID: " + id);
        } else {
            System.out.println("\nDriver not found!");
        }
    }

    public void createRide(String driverId, String source, String destination, String time, int seats, double price) {
        Driver driver = findDriver(driverId);
        if (driver != null) {
            String id = "R" + (nextRideId++);
            Ride ride = new Ride(id, driverId, source, destination, time, seats, price);
            rides.add(ride);
            driver.createRide(ride);
            System.out.println("\nRide created successfully! Ride ID: " + id);
        } else {
            System.out.println("\nDriver not found!");
        }
    }

    public void searchRides(String source, String destination) {
        System.out.println("\n=== Available Rides ===");
        boolean found = false;
        for (Ride ride : rides) {
            if (ride.getSource().equals(source) && ride.getDestination().equals(destination) &&
                ride.getStatus().equals("Available") && ride.getAvailableSeats() > 0) {
                ride.displayRide();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rides available for this route.");
        }
    }

    public void bookRide(String passengerId, String rideId, int seatsRequested) {
        Passenger passenger = findPassenger(passengerId);
        Ride ride = findRide(rideId);

        if (passenger == null) {
            System.out.println("\nPassenger not found!");
            return;
        }
        if (ride == null) {
            System.out.println("\nRide not found!");
            return;
        }

        if (ride.getAvailableSeats() < seatsRequested) {
            System.out.println("\nNot enough seats available!");
            return;
        }

        double totalAmount = ride.getPricePerSeat() * seatsRequested;
        if (!passenger.deductAmount(totalAmount)) {
            System.out.println("\nInsufficient wallet balance!");
            return;
        }

        String bookingId = "B" + (nextBookingId++);
        Booking booking = new Booking(bookingId, passengerId, rideId, getCurrentTime(), seatsRequested, totalAmount);
        bookings.add(booking);
        passenger.addBooking(booking);

        for (int i = 0; i < seatsRequested; i++) {
            ride.bookSeat(passengerId);
        }

        String paymentId = "PAY" + (nextPaymentId++);
        Payment payment = new Payment(paymentId, bookingId, totalAmount, "Wallet", getCurrentTime());
        payment.processPayment();
        payments.add(payment);

        System.out.println("\nRide booked successfully! Booking ID: " + bookingId);
        booking.displayBooking();
    }

    public void cancelBooking(String bookingId) {
        Booking booking = findBooking(bookingId);
        if (booking == null) {
            System.out.println("\nBooking not found!");
            return;
        }

        if (!booking.getStatus().equals("Confirmed")) {
            System.out.println("\nBooking cannot be cancelled!");
            return;
        }

        Ride ride = findRide(booking.getRideId());
        Passenger passenger = findPassenger(booking.getPassengerId());

        if (ride != null && passenger != null) {
            ride.cancelSeat(booking.getPassengerId());
            passenger.addAmount(booking.getTotalAmount());
            booking.cancelBooking();
            System.out.println("\nBooking cancelled successfully! Amount refunded to wallet.");
        }
    }

    public void viewProfile(String userId) {
        Driver driver = findDriver(userId);
        if (driver != null) {
            driver.displayProfile();
            return;
        }

        Passenger passenger = findPassenger(userId);
        if (passenger != null) {
            passenger.displayProfile();
            return;
        }

        System.out.println("\nUser not found!");
    }

    public void listDriverRides(String driverId) {
        Driver driver = findDriver(driverId);
        if (driver == null) {
            System.out.println("\nDriver not found!");
            return;
        }

        System.out.println("\n=== Your Rides ===");
        List<Ride> driverRides = driver.getRides();
        if (driverRides.isEmpty()) {
            System.out.println("No rides created yet.");
        } else {
            for (Ride ride : driverRides) {
                ride.displayRide();
            }
        }
    }

    public void listPassengerBookings(String passengerId) {
        Passenger passenger = findPassenger(passengerId);
        if (passenger == null) {
            System.out.println("\nPassenger not found!");
            return;
        }

        System.out.println("\n=== Your Bookings ===");
        List<Booking> passengerBookings = passenger.getBookings();
        if (passengerBookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking booking : passengerBookings) {
                booking.displayBooking();
            }
        }
    }

    private Driver findDriver(String id) {
        for (Driver d : drivers) {
            if (d.getUserId().equals(id)) return d;
        }
        return null;
    }

    private Passenger findPassenger(String id) {
        for (Passenger p : passengers) {
            if (p.getUserId().equals(id)) return p;
        }
        return null;
    }

    private Ride findRide(String id) {
        for (Ride r : rides) {
            if (r.getRideId().equals(id)) return r;
        }
        return null;
    }

    private Booking findBooking(String id) {
        for (Booking b : bookings) {
            if (b.getBookingId().equals(id)) return b;
        }
        return null;
    }
}