package com.smartcampus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * BookingSystem Class - Main application with Event-Driven Programming
 * Demonstrates EVENT-DRIVEN PROGRAMMING with user interactions
 */
public class BookingSystem {
    private ActivityManager activityManager;
    private Scanner scanner;
    private boolean running;
    
    // Event listeners
    private OnActivityRegisteredListener onActivityRegistered;
    private OnActivityUnregisteredListener onActivityUnregistered;
    
    // Event interfaces (Event-Driven Programming)
    @FunctionalInterface
    public interface OnActivityRegisteredListener {
        void onRegistered(String studentId, String activityId, String activityTitle);
    }
    
    @FunctionalInterface
    public interface OnActivityUnregisteredListener {
        void onUnregistered(String studentId, String activityId, String activityTitle);
    }
    
    // Constructor
    public BookingSystem() {
        this.activityManager = new ActivityManager();
        this.scanner = new Scanner(System.in);
        this.running = true;
        
        // Register default event listeners
        registerDefaultListeners();
    }
    
    /**
     * Register default event listeners
     */
    private void registerDefaultListeners() {
        // Event: When student registers for activity
        this.onActivityRegistered = (studentId, activityId, activityTitle) -> {
            System.out.println("📢 EVENT: Student " + studentId + " registered for: " + activityTitle);
        };
        
        // Event: When student unregisters from activity
        this.onActivityUnregistered = (studentId, activityId, activityTitle) -> {
            System.out.println("📢 EVENT: Student " + studentId + " unregistered from: " + activityTitle);
        };
    }
    
    /**
     * Set custom event listener for registration
     */
    public void setOnActivityRegisteredListener(OnActivityRegisteredListener listener) {
        this.onActivityRegistered = listener;
    }
    
    /**
     * Set custom event listener for unregistration
     */
    public void setOnActivityUnregisteredListener(OnActivityUnregisteredListener listener) {
        this.onActivityUnregistered = listener;
    }
    
    /**
     * Fire registration event
     */
    private void fireRegistrationEvent(String studentId, String activityId, String activityTitle) {
        if (onActivityRegistered != null) {
            onActivityRegistered.onRegistered(studentId, activityId, activityTitle);
        }
    }
    
    /**
     * Fire unregistration event
     */
    private void fireUnregistrationEvent(String studentId, String activityId, String activityTitle) {
        if (onActivityUnregistered != null) {
            onActivityUnregistered.onUnregistered(studentId, activityId, activityTitle);
        }
    }
    
    /**
     * Display main menu
     */
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
    
    /**
     * Display admin menu
     */
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
    
    /**
     * Add new activity (with user input)
     */
    public void addNewActivity() {
        System.out.println("\n--- Add New Activity ---");
        
        try {
            String id = Utils.generateActivityId(activityManager.getAllActivities());
            System.out.println("Activity ID (auto-generated): " + id);
            
            System.out.print("Enter activity title: ");
            String title = scanner.nextLine();
            
            System.out.print("Enter activity description: ");
            String description = scanner.nextLine();
            
            System.out.print("Enter date and time (yyyy-MM-dd HH:mm): ");
            String dateTimeStr = scanner.nextLine();
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, 
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            
            System.out.print("Enter location: ");
            String location = scanner.nextLine();
            
            System.out.print("Enter capacity (number of students): ");
            int capacity = Integer.parseInt(scanner.nextLine());
            
            if (capacity <= 0) {
                System.out.println("✗ Capacity must be greater than 0!");
                return;
            }
            
            Activity activity = new Activity(id, title, description, dateTime, location, capacity);
            activityManager.addActivity(activity);
            
        } catch (DateTimeParseException e) {
            System.out.println("✗ Invalid date/time format! Use: yyyy-MM-dd HH:mm");
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid capacity! Please enter a valid number.");
        }
    }
    
    /**
     * Update activity details (with user input)
     */
    public void updateActivityDetails() {
        System.out.println("\n--- Update Activity Details ---");
        System.out.print("Enter activity ID to update: ");
        String activityId = scanner.nextLine();
        
        Activity activity = activityManager.getActivity(activityId);
        if (activity == null) {
            System.out.println("✗ Activity not found!");
            return;
        }
        
        System.out.print("Enter new title (current: " + activity.getTitle() + "): ");
        String title = scanner.nextLine();
        
        System.out.print("Enter new description (current: " + activity.getDescription() + "): ");
        String description = scanner.nextLine();
        
        System.out.print("Enter new location (current: " + activity.getLocation() + "): ");
        String location = scanner.nextLine();
        
        System.out.print("Enter new capacity (current: " + activity.getCapacity() + "): ");
        int capacity = Integer.parseInt(scanner.nextLine());
        
        if (capacity <= 0) {
            System.out.println("✗ Capacity must be greater than 0!");
            return;
        }
        
        activityManager.updateActivityDetails(activityId, title, description, location, capacity);
    }
    
    /**
     * Remove activity (with user input)
     */
    public void removeActivity() {
        System.out.println("\n--- Remove Activity ---");
        System.out.print("Enter activity ID to remove: ");
        String activityId = scanner.nextLine();
        activityManager.removeActivity(activityId);
    }
    
    /**
     * Register for activity (with user input)
     */
    public void registerForActivity() {
        System.out.println("\n--- Register for Activity ---");
        System.out.print("Enter your Student ID: ");
        String studentId = scanner.nextLine();
        
        System.out.print("Enter Activity ID: ");
        String activityId = scanner.nextLine();
        
        Activity activity = activityManager.getActivity(activityId);
        if (activity == null) {
            System.out.println("✗ Activity not found!");
            return;
        }
        
        if (activityManager.registerStudentForActivity(activityId, studentId)) {
            // Fire registration event
            fireRegistrationEvent(studentId, activityId, activity.getTitle());
        }
    }
    
    /**
     * Unregister from activity (with user input)
     */
    public void unregisterFromActivity() {
        System.out.println("\n--- Unregister from Activity ---");
        System.out.print("Enter your Student ID: ");
        String studentId = scanner.nextLine();
        
        System.out.print("Enter Activity ID: ");
        String activityId = scanner.nextLine();
        
        Activity activity = activityManager.getActivity(activityId);
        if (activity == null) {
            System.out.println("✗ Activity not found!");
            return;
        }
        
        if (activityManager.unregisterStudentFromActivity(activityId, studentId)) {
            // Fire unregistration event
            fireUnregistrationEvent(studentId, activityId, activity.getTitle());
        }
    }
    
    /**
     * View activity details (with user input)
     */
    public void viewActivityDetails() {
        System.out.println("\n--- View Activity Details ---");
        System.out.print("Enter Activity ID: ");
        String activityId = scanner.nextLine();
        activityManager.displayActivityDetails(activityId);
    }
    
    /**
     * View activity participants (with user input)
     */
    public void viewActivityParticipants() {
        System.out.println("\n--- View Activity Participants ---");
        System.out.print("Enter Activity ID: ");
        String activityId = scanner.nextLine();
        activityManager.displayActivityParticipants(activityId);
    }
    
    /**
     * Main event loop
     */
    public void run() {
        // Add sample activities
        addSampleActivities();
        
        while (running) {
            displayMainMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        activityManager.displayUpcomingActivities();
                        break;
                    case 2:
                        activityManager.displayAllActivities();
                        break;
                    case 3:
                        viewActivityDetails();
                        break;
                    case 4:
                        registerForActivity();
                        break;
                    case 5:
                        unregisterFromActivity();
                        break;
                    case 6:
                        handleAdminMenu();
                        break;
                    case 7:
                        viewActivityParticipants();
                        break;
                    case 8:
                        activityManager.generateReport();
                        break;
                    case 9:
                        System.out.println("\n👋 Thank you for using Smart Campus Booking System!");
                        running = false;
                        break;
                    default:
                        System.out.println("✗ Invalid option! Please select 1-9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Handle admin menu
     */
    private void handleAdminMenu() {
        boolean adminRunning = true;
        
        while (adminRunning) {
            displayAdminMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        addNewActivity();
                        break;
                    case 2:
                        updateActivityDetails();
                        break;
                    case 3:
                        removeActivity();
                        break;
                    case 4:
                        adminRunning = false;
                        break;
                    default:
                        System.out.println("✗ Invalid option! Please select 1-4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
    }
    
    /**
     * Add sample activities for testing
     */
    private void addSampleActivities() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        Activity act1 = new Activity("ACT_1", "Java Workshop",
            "Learn advanced Java programming", 
            LocalDateTime.parse("2026-06-05 14:00", formatter),
            "Building A - Room 101", 30);
        
        Activity act2 = new Activity("ACT_2", "Football Match",
            "Friendly football game", 
            LocalDateTime.parse("2026-06-03 16:00", formatter),
            "Sports Field", 50);
        
        Activity act3 = new Activity("ACT_3", "AI & Machine Learning Seminar",
            "Latest trends in AI and ML", 
            LocalDateTime.parse("2026-06-10 10:00", formatter),
            "Building B - Auditorium", 100);
        
        Activity act4 = new Activity("ACT_4", "Debate Club Meeting",
            "Weekly debate competition", 
            LocalDateTime.parse("2026-06-01 15:00", formatter),
            "Building C - Hall 2", 40);
        
        activityManager.addActivity(act1);
        activityManager.addActivity(act2);
        activityManager.addActivity(act3);
        activityManager.addActivity(act4);
        
        System.out.println("✓ Sample activities loaded!");
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        System.out.println("🚀 Starting Smart Campus Activity Booking System...\n");
        BookingSystem system = new BookingSystem();
        system.run();
    }
}
