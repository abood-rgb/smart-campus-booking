package com.smartcampus.person;

/**
 * Member Class - Base Entity for all persons
 * 
 * المسؤوليات:
 * - تمثيل عضو في النظام
 * - بيانات أساسية للشخص
 * 
 * OOP Principles:
 * ✅ Base Class: للوراثة (Inheritance)
 * ✅ Encapsulation: بيانات محمية
 */
public class Member {
    
    // ========== Protected Fields ==========
    protected String id;
    protected String name;
    protected String email;
    protected String phone;
    
    // ========== Constructor ==========
    public Member(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // ========== Getters ==========
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    
    // ========== Setters ==========
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    
    @Override
    public String toString() {
        return String.format("Member - ID: %s | Name: %s | Email: %s | Phone: %s",
                           id, name, email, phone);
    }
}
