package com.smartcampus;

import java.util.ArrayList;
import java.util.List;

/**
 * ActivityManager Class - Manages all activities
 * Demonstrates OOP principles: Encapsulation, Single Responsibility
 */
public class ActivityManager {
    private List<Activity> activities;
    
    // Constructor
    public ActivityManager() {
        this.activities = new ArrayList<>();
    }
    
    // Activity Management Methods
    public void addActivity(Activity activity) {
        if (activity != null && !Utils.activityExists(activities, activity.getId())) {
            activities.add(activity);
            System.out.println("✓ Activity added successfully: " + activity.getTitle());
        } else {
            System.out.println("✗ Activity already exists or is invalid!");
        }
    }
    
    public Activity getActivity(String activityId) {
        return Utils.searchActivityById(activities, activityId);
    }
    
    public List<Activity> getAllActivities() {
        return new ArrayList<>(activities);
    }
    
    public List<Activity> getUpcomingActivities() {
        return Utils.sortActivitiesByDate(activities);
    }
    
    public void updateActivityDetails(String activityId, String title, 
                                     String description, String location, int capacity) {
        Activity activity = getActivity(activityId);
        if (activity != null) {
            activity.setTitle(title);
            activity.setDescription(description);
            activity.setLocation(location);
            activity.setCapacity(capacity);
            System.out.println("✓ Activity updated: " + title);
        } else {
            System.out.println("✗ Activity not found!");
        }
    }
    
    public void removeActivity(String activityId) {
        Activity activity = getActivity(activityId);
        if (activity != null) {
            activities.remove(activity);
            System.out.println("✓ Activity removed: " + activity.getTitle());
        } else {
            System.out.println("✗ Activity not found!");
        }
    }
    
    // Registration Methods
    public boolean registerStudentForActivity(String activityId, String studentId) {
        Activity activity = getActivity(activityId);
        if (activity != null) {
            if (activity.registerStudent(studentId)) {
                System.out.println("✓ Registration successful for: " + activity.getTitle());
                return true;
            } else if (!activity.hasAvailableSlots()) {
                System.out.println("✗ Activity is full!");
                return false;
            } else {
                System.out.println("✗ Student already registered for this activity!");
                return false;
            }
        } else {
            System.out.println("✗ Activity not found!");
            return false;
        }
    }
    
    public boolean unregisterStudentFromActivity(String activityId, String studentId) {
        Activity activity = getActivity(activityId);
        if (activity != null && activity.unregisterStudent(studentId)) {
            System.out.println("✓ Unregistration successful from: " + activity.getTitle());
            return true;
        } else {
            System.out.println("✗ Student not registered for this activity!");
            return false;
        }
    }
    
    // Reporting Methods
    public void displayAllActivities() {
        if (activities.isEmpty()) {
            System.out.println("No activities available.");
            return;
        }
        System.out.println("\n=== All Activities ===");
        for (Activity activity : activities) {
            System.out.println(activity);
        }
    }
    
    public void displayUpcomingActivities() {
        List<Activity> sorted = getUpcomingActivities();
        if (sorted.isEmpty()) {
            System.out.println("No upcoming activities.");
            return;
        }
        System.out.println("\n=== Upcoming Activities (Sorted by Date) ===");
        for (Activity activity : sorted) {
            System.out.println(activity);
        }
    }
    
    public void displayActivityDetails(String activityId) {
        Activity activity = getActivity(activityId);
        if (activity != null) {
            System.out.println("\n=== Activity Details ===");
            System.out.println("Title: " + activity.getTitle());
            System.out.println("Description: " + activity.getDescription());
            System.out.println("Date & Time: " + activity.getDateTime());
            System.out.println("Location: " + activity.getLocation());
            System.out.println("Capacity: " + activity.getCapacity());
            System.out.println("Registered: " + activity.getRegisteredCount());
            System.out.println("Available Slots: " + activity.getAvailableSlots());
        } else {
            System.out.println("Activity not found!");
        }
    }
    
    public void displayActivityParticipants(String activityId) {
        Activity activity = getActivity(activityId);
        if (activity != null && !activity.getRegisteredStudents().isEmpty()) {
            System.out.println("\n=== Participants for " + activity.getTitle() + " ===");
            for (String studentId : activity.getRegisteredStudents()) {
                System.out.println("- " + studentId);
            }
        } else {
            System.out.println("No participants found or activity not found!");
        }
    }
    
    public void generateReport() {
        System.out.println("\n=== Campus Activities Report ===");
        System.out.println("Total Activities: " + activities.size());
        
        int totalCapacity = 0;
        int totalRegistered = 0;
        
        for (Activity activity : activities) {
            totalCapacity += activity.getCapacity();
            totalRegistered += activity.getRegisteredCount();
        }
        
        System.out.println("Total Capacity: " + totalCapacity);
        System.out.println("Total Registrations: " + totalRegistered);
        System.out.println("Overall Utilization: " + 
            (totalCapacity > 0 ? (totalRegistered * 100 / totalCapacity) + "%" : "N/A"));
        
        System.out.println("\nActivity Breakdown:");
        for (Activity activity : activities) {
            System.out.println("  - " + activity.getTitle() + ": " + 
                activity.getRegisteredCount() + "/" + activity.getCapacity() + " (" +
                ((activity.getCapacity() > 0) ? 
                    (activity.getRegisteredCount() * 100 / activity.getCapacity()) : 0) + "%)");
        }
    }
}
