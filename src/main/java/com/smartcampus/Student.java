package com.smartcampus;

import java.util.ArrayList;
import java.util.List;

/**
 * Student Class - Represents a student user
 * Demonstrates OOP principles: Encapsulation and Data Hiding
 */
public class Student {
    private String studentId;
    private String name;
    private String email;
    private List<String> registeredActivities;
    
    // Constructor
    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.registeredActivities = new ArrayList<>();
    }
    
    // Getters (Encapsulation)
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getRegisteredActivities() {
        return new ArrayList<>(registeredActivities);
    }
    
    // Setters (Encapsulation)
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    
    // Business Logic
    public void registerActivity(String activityId) {
        if (!registeredActivities.contains(activityId)) {
            registeredActivities.add(activityId);
        }
    }
    
    public void unregisterActivity(String activityId) {
        registeredActivities.remove(activityId);
    }
    
    public boolean isRegisteredFor(String activityId) {
        return registeredActivities.contains(activityId);
    }
    
    public int getTotalActivities() {
        return registeredActivities.size();
    }
    
    @Override
    public String toString() {
        return String.format("Student ID: %s | Name: %s | Email: %s | Activities: %d",
                           studentId, name, email, registeredActivities.size());
    }
}
