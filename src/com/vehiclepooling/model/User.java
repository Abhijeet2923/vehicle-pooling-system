package com.vehiclepooling.model;

public class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String phone;
    protected String password;

    public User(String id, String n, String e, String p, String pass) {
        this.userId = id;
        this.name = n;
        this.email = e;
        this.phone = p;
        this.password = pass;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void displayProfile() {
        System.out.println("\n--- User Profile ---");
        System.out.println("ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
    }

    public boolean authenticate(String pass) {
        return password.equals(pass);
    }
}