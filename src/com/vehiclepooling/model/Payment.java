package com.vehiclepooling.model;

public class Payment {
    private String paymentId;
    private String bookingId;
    private double amount;
    private String paymentMethod;
    private String paymentTime;
    private String status; // "Pending", "Completed", "Failed", "Refunded"

    public Payment(String pid, String bid, double amt, String method, String time) {
        this.paymentId = pid;
        this.bookingId = bid;
        this.amount = amt;
        this.paymentMethod = method;
        this.paymentTime = time;
        this.status = "Pending";
    }

    public String getPaymentId() { return paymentId; }
    public String getStatus() { return status; }

    public void processPayment() {
        this.status = "Completed";
        System.out.println("Payment processed successfully!");
    }

    public void refundPayment() {
        this.status = "Refunded";
        System.out.println("Payment refunded successfully!");
    }

    public void displayPayment() {
        System.out.println("\n--- Payment Details ---");
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Booking ID: " + bookingId);
        System.out.printf("Amount: $%.2f%n", amount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Status: " + status);
    }
}