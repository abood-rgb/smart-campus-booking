# Smart Campus Activity Booking System - Setup Guide

## تشغيل البرنامج على Eclipse IDE

### الخطوة 1: تحميل الملفات
كل الملفات موجودة في المشروع:
```
smart-campus-booking/
├── src/main/java/com/smartcampus/
│   ├── Activity.java           ✅
│   ├── ActivityManager.java    ✅
│   ├── Student.java            ✅
│   ├── Utils.java              ✅
│   └── BookingSystem.java      ✅
```

### الخطوة 2: استيراد المشروع في Eclipse
1. افتح Eclipse
2. اختر File → Import → Existing Projects into Workspace
3. اختر مجلد `smart-campus-booking`
4. اضغط Finish

### الخطوة 3: تشغيل البرنامج
1. اضغط كليك يمين على `BookingSystem.java`
2. اختر Run As → Java Application
3. البرنامج سيبدأ وسيظهر القائمة الرئيسية

### الخطوة 4: التفاعل مع البرنامج

#### المميزات الأساسية:

**1. عرض الأنشطة القادمة (مرتبة حسب التاريخ)**
- اختر الخيار 1 من القائمة
- سيتم ترتيب الأنشطة باستخدام Merge Sort algorithm
- الأنشطة تظهر من الأقرب للأبعد

**2. التسجيل للأنشطة**
- اختر الخيار 4
- أدخل Student ID (مثال: STU_001)
- أدخل Activity ID (مثال: ACT_1)
- النظام يحمي من الزيادة عن السعة تلقائياً
- يظهر حدث (Event) عند التسجيل الناجح

**3. إلغاء التسجيل**
- اختر الخيار 5
- أدخل Student ID و Activity ID
- سيتم إلغاء التسجيل وحدث Event يظهر

**4. إدارة الأنشطة (Admin)**
- اختر الخيار 6
- يمكنك:
  - إضافة نشاط جديد
  - تحديث تفاصيل النشاط
  - حذف النشاط

**5. عرض المشاركين**
- اختر الخيار 7
- أدخل Activity ID لرؤية قائمة الطلاب المسجلين

**6. تقرير الحضور**
- اختر الخيار 8
- يظهر تقرير شامل عن:
  - إجمالي الأنشطة
  - إجمالي الحضور
  - نسبة الاستخدام

## أمثلة على الاستخدام

### مثال 1: عرض الأنشطة القادمة
```
Select an option (1-9): 1
=== Upcoming Activities (Sorted by Date) ===
ID: ACT_4 | Title: Debate Club Meeting | Date: 2026-06-01 15:00 | ...
ID: ACT_2 | Title: Football Match | Date: 2026-06-03 16:00 | ...
ID: ACT_1 | Title: Java Workshop | Date: 2026-06-05 14:00 | ...
ID: ACT_3 | Title: AI & Machine Learning Seminar | Date: 2026-06-10 10:00 | ...
```

### مثال 2: التسجيل للنشاط
```
Select an option (1-9): 4
--- Register for Activity ---
Enter your Student ID: STU_001
Enter Activity ID: ACT_1
✓ Registration successful for: Java Workshop
📢 EVENT: Student STU_001 registered for: Java Workshop
```

### مثال 3: إضافة نشاط جديد
```
Select an option (1-9): 6
--- ADMIN MENU ---
Select an option (1-4): 1
--- Add New Activity ---
Activity ID (auto-generated): ACT_5
Enter activity title: Web Development Workshop
Enter activity description: Learn modern web development
Enter date and time (yyyy-MM-dd HH:mm): 2026-06-15 11:00
Enter location: Building D - Lab 1
Enter capacity (number of students): 25
✓ Activity added successfully: Web Development Workshop
```

## Debugging في Eclipse

### استخدام Breakpoints:
1. اضغط Ctrl+Shift+B لإضافة breakpoint
2. اضغط F11 لتشغيل Debugger
3. استخدم F6 (Step Over) أو F5 (Step Into) للتنقل

### Console Output:
- تظهر جميع الرسائل في Eclipse Console
- ✓ للعمليات الناجحة
- ✗ للأخطاء
- 📢 للأحداث (Events)

## الخوارزمية المستخدمة: Merge Sort

### الميزات:
- **Time Complexity**: O(n log n)
- **Space Complexity**: O(n)
- **مستقرة**: تحافظ على ترتيب العناصر المتساوية
- **مثالية للبيانات الكبيرة**

### الاستخدام:
```java
List<Activity> sortedActivities = Utils.sortActivitiesByDate(activities);
```

## Programming Paradigms المستخدمة

### 1. Procedural Programming (Utils.java)
- دوال للترتيب والبحث
- Merge Sort algorithm
- معالجة البيانات خطية

### 2. Object-Oriented Programming
- Classes: Activity, Student, ActivityManager
- Encapsulation و Getter/Setter
- Inheritance patterns

### 3. Event-Driven Programming (BookingSystem.java)
- Event Listeners (OnActivityRegisteredListener)
- Fire events عند التسجيل/الغاء التسجيل
- Functional Interfaces للأحداث

## معايير الكود المطبقة

✅ **Naming Conventions**: camelCase للمتغيرات
✅ **Code Comments**: توثيق شامل للفئات والدوال
✅ **Error Handling**: معالجة الأخطاء والاستثناءات
✅ **Encapsulation**: Private fields مع public getters
✅ **Single Responsibility**: كل class له مسؤولية واحدة

## الملفات المطلوبة

كل الملفات موجودة في:
`src/main/java/com/smartcampus/`

فقط انسخها في Eclipse وشغل `BookingSystem.java`!
