package com.vehiclepooling;

import com.vehiclepooling.services.VehiclePoolingSystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VehiclePoolingSystem system = new VehiclePoolingSystem();
        Scanner scanner = new Scanner(System.in);

        // Seed Sample Data
        system.registerDriver("John Driver", "john@email.com", "1234567890", "pass123", "DL12345");
        system.registerPassenger("Alice Passenger", "alice@email.com", "9876543210", "pass456");

        system.addVehicle("D1", "Toyota Camry", "ABC-1234", 4);
        system.createRide("D1", "Delhi", "Agra", "2025-11-15 09:00 AM", 3, 500.0);
        system.createRide("D1", "Delhi", "Jaipur", "2025-11-16 08:00 AM", 3, 800.0);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("   VEHICLE POOLING SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Register Driver");
            System.out.println("2. Register Passenger");
            System.out.println("3. Add Vehicle (Driver)");
            System.out.println("4. Create Ride (Driver)");
            System.out.println("5. Search Rides");
            System.out.println("6. Book Ride (Passenger)");
            System.out.println("7. Cancel Booking");
            System.out.println("8. View Profile");
            System.out.println("9. View Driver Rides");
            System.out.println("10. View Passenger Bookings");
            System.out.println("0. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input! Please enter a number.");
                continue;
            }

            if (choice == 0) {
                System.out.println("\nThank you for using Vehicle Pooling System!");
                break;
            }

            String name, email, phone, password, license, id, model, licensePlate;
            String source, dest, time, rideId, bookingId;
            int capacity, seats;
            double price;

            switch (choice) {
                case 1:
                    System.out.print("\nEnter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    phone = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    System.out.print("Enter license number: ");
                    license = scanner.nextLine();
                    system.registerDriver(name, email, phone, password, license);
                    break;

                case 2:
                    System.out.print("\nEnter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    phone = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    system.registerPassenger(name, email, phone, password);
                    break;

                case 3:
                    System.out.print("\nEnter driver ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter vehicle model: ");
                    model = scanner.nextLine();
                    System.out.print("Enter license plate: ");
                    licensePlate = scanner.nextLine();
                    System.out.print("Enter capacity: ");
                    capacity = Integer.parseInt(scanner.nextLine());
                    system.addVehicle(id, model, licensePlate, capacity);
                    break;

                case 4:
                    System.out.print("\nEnter driver ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter source: ");
                    source = scanner.nextLine();
                    System.out.print("Enter destination: ");
                    dest = scanner.nextLine();
                    System.out.print("Enter departure time: ");
                    time = scanner.nextLine();
                    System.out.print("Enter available seats: ");
                    seats = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter price per seat: ");
                    price = Double.parseDouble(scanner.nextLine());
                    system.createRide(id, source, dest, time, seats, price);
                    break;

                case 5:
                    System.out.print("\nEnter source: ");
                    source = scanner.nextLine();
                    System.out.print("Enter destination: ");
                    dest = scanner.nextLine();
                    system.searchRides(source, dest);
                    break;

                case 6:
                    System.out.print("\nEnter passenger ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter ride ID: ");
                    rideId = scanner.nextLine();
                    System.out.print("Enter number of seats: ");
                    seats = Integer.parseInt(scanner.nextLine());
                    system.bookRide(id, rideId, seats);
                    break;

                case 7:
                    System.out.print("\nEnter booking ID: ");
                    bookingId = scanner.nextLine();
                    system.cancelBooking(bookingId);
                    break;

                case 8:
                    System.out.print("\nEnter user ID: ");
                    id = scanner.nextLine();
                    system.viewProfile(id);
                    break;

                case 9:
                    System.out.print("\nEnter driver ID: ");
                    id = scanner.nextLine();
                    system.listDriverRides(id);
                    break;

                case 10:
                    System.out.print("\nEnter passenger ID: ");
                    id = scanner.nextLine();
                    system.listPassengerBookings(id);
                    break;

                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}