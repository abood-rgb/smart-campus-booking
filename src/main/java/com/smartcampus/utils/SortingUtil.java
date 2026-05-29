package com.smartcampus.utils;

import com.smartcampus.activity.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * SortingUtil Utility Class
 * 
 * المسؤوليات:
 * - ترتيب الأنشطة حسب التاريخ
 * - دوال مساعدة للترتيب
 * 
 * PROCEDURAL PROGRAMMING:
 * ✅ دوال مستقلة بدون state
 * 
 * ALGORITHM:
 * ✅ MERGE SORT: O(n log n) - الأفضل للبيانات المرتبة جزئياً
 */
public class SortingUtil {
    
    /**
     * ترتيب الأنشطة حسب التاريخ باستخدام Merge Sort
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * 
     * @param activities قائمة الأنشطة
     * @return قائمة مرتبة حسب التاريخ
     */
    public static List<Activity> sortActivitiesByDate(List<Activity> activities) {
        if (activities == null || activities.size() <= 1) {
            return new ArrayList<>(activities);
        }
        
        List<Activity> copy = new ArrayList<>(activities);
        mergeSort(copy, 0, copy.size() - 1);
        return copy;
    }
    
    /**
     * Merge Sort - Divide Phase
     */
    private static void mergeSort(List<Activity> arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // ترتيب النصف الأيسر
            mergeSort(arr, left, mid);
            
            // ترتيب النصف الأيمن
            mergeSort(arr, mid + 1, right);
            
            // دمج النصفين
            merge(arr, left, mid, right);
        }
    }
    
    /**
     * Merge Sort - Combine Phase
     */
    private static void merge(List<Activity> arr, int left, int mid, int right) {
        List<Activity> leftArr = new ArrayList<>(arr.subList(left, mid + 1));
        List<Activity> rightArr = new ArrayList<>(arr.subList(mid + 1, right + 1));
        
        int i = 0, j = 0, k = left;
        
        while (i < leftArr.size() && j < rightArr.size()) {
            if (leftArr.get(i).getDateTime().compareTo(
                    rightArr.get(j).getDateTime()) <= 0) {
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
}
