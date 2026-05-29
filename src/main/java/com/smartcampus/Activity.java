package com.smartcampus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity Class - Represents a campus activity
 * Demonstrates OOP principles: Encapsulation
 */
public class Activity {
    private String id;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private int capacity;
    private List<String> registeredStudents;
    
    // Constructor
    public Activity(String id, String title, String description, 
                   LocalDateTime dateTime, String location, int capacity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
        this.registeredStudents = new ArrayList<>();
    }
    
    // Getters (Encapsulation)
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public List<String> getRegisteredStudents() { return new ArrayList<>(registeredStudents); }
    
    // Setters (Encapsulation)
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    
    // Business Logic Methods
    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }
    
    public boolean hasAvailableSlots() {
        return getAvailableSlots() > 0;
    }
    
    public boolean registerStudent(String studentId) {
        if (hasAvailableSlots() && !registeredStudents.contains(studentId)) {
            registeredStudents.add(studentId);
            return true;
        }
        return false;
    }
    
    public boolean unregisterStudent(String studentId) {
        return registeredStudents.remove(studentId);
    }
    
    public int getRegisteredCount() {
        return registeredStudents.size();
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format(
            "ID: %s | Title: %s | Date: %s | Location: %s | Capacity: %d | Available: %d",
            id, title, dateTime.format(formatter), location, capacity, getAvailableSlots()
        );
    }
}
