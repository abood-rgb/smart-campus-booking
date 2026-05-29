package com.smartcampus.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Student Class - Extends Member
 * 
 * المسؤوليات:
 * - تمثيل طالب جامعي
 * - تتبع الأنشطة المسجل فيها
 * 
 * OOP Principles:
 * ✅ Inheritance: يرث من Member
 * ✅ Method Override: toString مخصص للطالب
 */
public class Student extends Member {
    
    private List<String> registeredActivities;
    private String studentNumber;
    private String department;
    
    // ========== Constructor ==========
    public Student(String id, String name, String email, String phone,
                  String studentNumber, String department) {
        super(id, name, email, phone);
        this.studentNumber = studentNumber;
        this.department = department;
        this.registeredActivities = new ArrayList<>();
    }
    
    // ========== Getters ==========
    public String getStudentNumber() { return studentNumber; }
    public String getDepartment() { return department; }
    public List<String> getRegisteredActivities() { return new ArrayList<>(registeredActivities); }
    
    // ========== Setters ==========
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
    public void setDepartment(String department) { this.department = department; }
    
    // ========== Business Logic ==========
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
        return String.format(
            "Student - ID: %s | Name: %s | Number: %s | Department: %s | Email: %s | Activities: %d",
            id, name, studentNumber, department, email, registeredActivities.size()
        );
    }
}
