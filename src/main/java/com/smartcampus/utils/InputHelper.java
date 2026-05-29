package com.smartcampus.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * InputHelper Utility Class
 * 
 * المسؤوليات:
 * - معالجة مدخلات المستخدم
 * - التحقق من صحة البيانات
 * - تحويل البيانات للأنواع المطلوبة
 * 
 * PROCEDURAL PROGRAMMING: دوال مساعدة مستقلة
 */
public class InputHelper {
    
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * الحصول على نص من المستخدم
     */
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * الحصول على رقم صحيح من المستخدم
     */
    public static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.");
            }
        }
    }
    
    /**
     * الحصول على تاريخ ووقت من المستخدم
     */
    public static LocalDateTime getDateTimeInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return LocalDateTime.parse(input,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("✗ Invalid date/time format! Use: yyyy-MM-dd HH:mm");
            }
        }
    }
    
    /**
     * الحصول على اختيار من المستخدم (yes/no)
     */
    public static boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) return true;
            if (input.equals("n") || input.equals("no")) return false;
            System.out.println("✗ Please enter 'y' or 'n'");
        }
    }
    
    /**
     * التحقق من أن النص ليس فارغاً
     */
    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }
    
    /**
     * التحقق من أن الرقم موجب
     */
    public static boolean isValidPositiveNumber(int num) {
        return num > 0;
    }
    
    /**
     * التحقق من صيغة البريد الإلكتروني
     */
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
