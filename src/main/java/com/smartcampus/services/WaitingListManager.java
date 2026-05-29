package com.smartcampus.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * WaitingListManager Service Class
 * 
 * المسؤوليات:
 * - إدارة قائمة الانتظار
 * - FIFO - First In First Out
 * 
 * OOP Principles:
 * ✅ Single Responsibility: إدارة قائمة الانتظار
 * 
 * Data Structure:
 * ✅ Queue: أنسب هيكل لقائمة الانتظار
 */
public class WaitingListManager {
    
    private Queue<String> waitingList;
    private List<String> waitingListHistory;
    
    public WaitingListManager() {
        this.waitingList = new LinkedList<>();
        this.waitingListHistory = new ArrayList<>();
    }
    
    /**
     * إضافة طالب لقائمة الانتظار
     */
    public void addToWaitingList(String activityId, String studentId) {
        String entry = studentId + " for " + activityId;
        waitingList.add(entry);
        waitingListHistory.add(entry);
        System.out.println("✓ Student added to waiting list for: " + activityId);
    }
    
    /**
     * الحصول على الطالب التالي من قائمة الانتظار
     */
    public String getNextFromWaitingList() {
        return waitingList.poll();  // أول من دخل = أول من يخرج (FIFO)
    }
    
    /**
     * عرض قائمة الانتظار
     */
    public void displayWaitingList() {
        if (waitingList.isEmpty()) {
            System.out.println("Waiting list is empty!");
            return;
        }
        System.out.println("\n=== Waiting List (FIFO) ===");
        int position = 1;
        for (String entry : waitingList) {
            System.out.println(position + ". " + entry);
            position++;
        }
    }
    
    /**
     * حجم قائمة الانتظار
     */
    public int getWaitingListSize() {
        return waitingList.size();
    }
    
    /**
     * التحقق من أن قائمة الانتظار فارغة
     */
    public boolean isEmpty() {
        return waitingList.isEmpty();
    }
    
    /**
     * الحصول على سجل قائمة الانتظار
     */
    public List<String> getWaitingListHistory() {
        return new ArrayList<>(waitingListHistory);
    }
}
