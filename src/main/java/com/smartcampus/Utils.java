package com.smartcampus;

import java.util.ArrayList;
import java.util.List;

/**
 * Utils Class - Utility functions for sorting and searching
 * Demonstrates PROCEDURAL PROGRAMMING approach
 */
public class Utils {
    
    /**
     * Merge Sort Algorithm - Sorts activities by date
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * 
     * Why Merge Sort?
     * - Guaranteed O(n log n) performance
     * - Stable sorting (maintains order of equal elements)
     * - Good for linked structures
     * - Better than Quick Sort for external sorting
     */
    
    public static List<Activity> sortActivitiesByDate(List<Activity> activities) {
        if (activities == null || activities.size() <= 1) {
            return new ArrayList<>(activities);
        }
        
        List<Activity> copy = new ArrayList<>(activities);
        mergeSort(copy, 0, copy.size() - 1);
        return copy;
    }
    
    private static void mergeSort(List<Activity> arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // Sort left half
            mergeSort(arr, left, mid);
            
            // Sort right half
            mergeSort(arr, mid + 1, right);
            
            // Merge both halves
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(List<Activity> arr, int left, int mid, int right) {
        List<Activity> leftArr = new ArrayList<>(arr.subList(left, mid + 1));
        List<Activity> rightArr = new ArrayList<>(arr.subList(mid + 1, right + 1));
        
        int i = 0, j = 0, k = left;
        
        while (i < leftArr.size() && j < rightArr.size()) {
            if (leftArr.get(i).getDateTime().compareTo(rightArr.get(j).getDateTime()) <= 0) {
                arr.set(k++, leftArr.get(i++));
            } else {
                arr.set(k++, rightArr.get(j++));
            }
        }
        
        while (i < leftArr.size()) {
            arr.set(k++, leftArr.get(i++));
        }
        
        while (j < rightArr.size()) {
            arr.set(k++, rightArr.get(j++));
        }
    }
    
    /**
     * Linear Search - Find activity by ID
     */
    public static Activity searchActivityById(List<Activity> activities, String id) {
        for (Activity activity : activities) {
            if (activity.getId().equals(id)) {
                return activity;
            }
        }
        return null;
    }
    
    /**
     * Check if activity ID already exists
     */
    public static boolean activityExists(List<Activity> activities, String id) {
        return searchActivityById(activities, id) != null;
    }
    
    /**
     * Generate unique activity ID
     */
    public static String generateActivityId(List<Activity> activities) {
        return "ACT_" + (activities.size() + 1);
    }
}
