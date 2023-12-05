package com.example.new_orvba;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String userId;
    public String email;
    public String firstName;
    public String lastName;
    public List<BreakdownRequest> breakdownRequests; // New field

    // Required default constructor for Firebase
    public User() {
        breakdownRequests = new ArrayList<>();
    }

    public User(String userId, String email, String firstName, String lastName) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.breakdownRequests = new ArrayList<>();
    }
}
