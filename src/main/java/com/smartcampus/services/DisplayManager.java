package com.smartcampus.services;

import com.smartcampus.activity.Activity;

import java.util.List;

/**
 * DisplayManager Service Class
 * 
 * المسؤوليات:
 * - عرض المعلومات للمستخدم
 * - تنسيق البيانات للعرض
 * 
 * OOP Principles:
 * ✅ Single Responsibility: عرض البيانات فقط
 */
public class DisplayManager {
    
    private ActivityManager activityManager;
    
    public DisplayManager(ActivityManager activityManager) {
        this.activityManager = activityManager;
    }
    
    // ========== Display Methods ==========
    
    public void displayAllActivities() {
        List<Activity> activities = activityManager.getAllActivities();
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
        List<Activity> sorted = activityManager.getUpcomingActivities();
        if (sorted.isEmpty()) {
            System.out.println("No upcoming activities.");
            return;
        }
        System.out.println("\n=== Upcoming Activities (Sorted by Date - Merge Sort) ===");
        for (Activity activity : sorted) {
            System.out.println(activity);
        }
    }
    
    public void displayActivityDetails(String activityId) {
        Activity activity = activityManager.getActivity(activityId);
        if (activity != null) {
            System.out.println("\n=== Activity Details ===");
            System.out.println("ID: " + activity.getId());
            System.out.println("Title: " + activity.getTitle());
            System.out.println("Description: " + activity.getDescription());
            System.out.println("Date & Time: " + activity.getDateTime());
            System.out.println("Location: " + activity.getLocation());
            System.out.println("Capacity: " + activity.getCapacity());
            System.out.println("Registered: " + activity.getRegisteredCount());
            System.out.println("Available: " + activity.getAvailableSlots());
        } else {
            System.out.println("Activity not found!");
        }
    }
    
    public void displayActivityParticipants(String activityId) {
        Activity activity = activityManager.getActivity(activityId);
        if (activity != null && !activity.getRegisteredStudents().isEmpty()) {
            System.out.println("\n=== Participants for " + activity.getTitle() + " ===");
            int counter = 1;
            for (String studentId : activity.getRegisteredStudents()) {
                System.out.println(counter + ". " + studentId);
                counter++;
            }
        } else {
            System.out.println("No participants found!");
        }
    }
    
    public void generateReport() {
        List<Activity> activities = activityManager.getAllActivities();
        System.out.println("\n" + "=".repeat(50));
        System.out.println("    📊 CAMPUS ACTIVITIES REPORT");
        System.out.println("=".repeat(50));
        
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
            int util = (activity.getCapacity() > 0) ?
                (activity.getRegisteredCount() * 100 / activity.getCapacity()) : 0;
            System.out.println("  - " + activity.getTitle() + ": " +
                activity.getRegisteredCount() + "/" + activity.getCapacity() +
                " (" + util + "%)");
        }
        System.out.println("=".repeat(50));
    }
    
    // ========== Menu Display ==========
    
    public void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("    🎓 SMART CAMPUS ACTIVITY BOOKING SYSTEM 🎓");
        System.out.println("=".repeat(50));
        System.out.println("1. View Upcoming Activities (Sorted by Date)");
        System.out.println("2. View All Activities");
        System.out.println("3. View Activity Details");
        System.out.println("4. Register for an Activity");
        System.out.println("5. Unregister from an Activity");
        System.out.println("6. Manage Activity (Admin)");
        System.out.println("7. View Activity Participants");
        System.out.println("8. Generate Report");
        System.out.println("9. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Select an option (1-9): ");
    }
    
    public void displayAdminMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("    📋 ADMIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Add New Activity");
        System.out.println("2. Update Activity Details");
        System.out.println("3. Remove Activity");
        System.out.println("4. Back to Main Menu");
        System.out.println("=".repeat(50));
        System.out.print("Select an option (1-4): ");
    }
}
