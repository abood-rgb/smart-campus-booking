package com.smartcampus.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Organizer Class - Extends Member
 * 
 * المسؤوليات:
 * - تمثيل منظم أنشطة
 * - تتبع الأنشطة المنظمة
 * 
 * OOP Principles:
 * ✅ Inheritance: يرث من Member
 * ✅ Specialization: متخصص في تنظيم الأنشطة
 */
public class Organizer extends Member {
    
    private String department;
    private List<String> organizedActivities;
    private int experienceYears;
    
    // ========== Constructor ==========
    public Organizer(String id, String name, String email, String phone,
                    String department, int experienceYears) {
        super(id, name, email, phone);
        this.department = department;
        this.experienceYears = experienceYears;
        this.organizedActivities = new ArrayList<>();
    }
    
    // ========== Getters ==========
    public String getDepartment() { return department; }
    public int getExperienceYears() { return experienceYears; }
    public List<String> getOrganizedActivities() { return new ArrayList<>(organizedActivities); }
    
    // ========== Setters ==========
    public void setDepartment(String department) { this.department = department; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
    
    // ========== Business Logic ==========
    public void addOrganizedActivity(String activityId) {
        if (!organizedActivities.contains(activityId)) {
            organizedActivities.add(activityId);
        }
    }
    
    public void removeOrganizedActivity(String activityId) {
        organizedActivities.remove(activityId);
    }
    
    public int getTotalOrganizedActivities() {
        return organizedActivities.size();
    }
    
    @Override
    public String toString() {
        return String.format(
            "Organizer - ID: %s | Name: %s | Department: %s | Experience: %d years | Activities: %d",
            id, name, department, experienceYears, organizedActivities.size()
        );
    }
}
