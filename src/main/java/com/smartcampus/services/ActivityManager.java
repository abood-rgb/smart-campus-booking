package com.smartcampus.services;

import com.smartcampus.activity.Activity;
import com.smartcampus.utils.SortingUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ActivityManager Service Class
 * 
 * المسؤوليات:
 * - إدارة جميع الأنشطة (CRUD)
 * - التسجيل والغاء التسجيل
 * 
 * OOP Principles:
 * ✅ Encapsulation: قائمة الأنشطة خاصة
 * ✅ Single Responsibility: إدارة الأنشطة
 */
public class ActivityManager {
    
    private List<Activity> activities;
    
    public ActivityManager() {
        this.activities = new ArrayList<>();
    }
    
    // ========== CRUD Operations ==========
    
    public void addActivity(Activity activity) {
        if (activity != null && !activityExists(activity.getId())) {
            activities.add(activity);
            System.out.println("✓ Activity added: " + activity.getTitle());
        } else {
            System.out.println("✗ Activity already exists or is invalid!");
        }
    }
    
    public Activity getActivity(String activityId) {
        for (Activity activity : activities) {
            if (activity.getId().equals(activityId)) {
                return activity;
            }
        }
        return null;
    }
    
    public List<Activity> getAllActivities() {
        return new ArrayList<>(activities);
    }
    
    public List<Activity> getUpcomingActivities() {
        return SortingUtil.sortActivitiesByDate(activities);
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
    
    // ========== Registration Management ==========
    
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
                System.out.println("✗ Student already registered!");
                return false;
            }
        }
        System.out.println("✗ Activity not found!");
        return false;
    }
    
    public boolean unregisterStudentFromActivity(String activityId, String studentId) {
        Activity activity = getActivity(activityId);
        if (activity != null && activity.unregisterStudent(studentId)) {
            System.out.println("✓ Unregistration successful from: " + activity.getTitle());
            return true;
        }
        System.out.println("✗ Student not registered for this activity!");
        return false;
    }
    
    // ========== Helper Methods ==========
    
    private boolean activityExists(String id) {
        return getActivity(id) != null;
    }
    
    public String generateActivityId() {
        return "ACT_" + (activities.size() + 1);
    }
}
