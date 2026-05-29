# Smart Campus Activity Booking System - Professional Report

## جدول المحتويات
1. [مقدمة عن الخوارزميات](#مقدمة-عن-الخوارزميات)
2. [نظرة عامة على المشروع](#نظرة-عامة-على-المشروع)
3. [تحليل الخوارزمية](#تحليل-الخوارزمية)
4. [التطبيق](#التطبيق)
5. [البرمجة الإجرائية](#البرمجة-الإجرائية)
6. [البرمجة الموجهة للكائنات](#البرمجة-الموجهة-للكائنات)
7. [البرمجة المدفوعة بالأحداث](#البرمجة-المدفوعة-بالأحداث)
8. [عملية التصحيح والمعايير](#عملية-التصحيح-والمعايير)

---

## مقدمة عن الخوارزميات

### ما هي الخوارزمية؟
الخوارزمية هي مجموعة من الخطوات المتسلسلة والمنطقية لحل مشكلة معينة أو إنجاز مهمة محددة. 
كل خطوة واضحة ومحددة، والنتيجة النهائية مضمونة إذا تم اتباع الخطوات بشكل صحيح.

### خصائص الخوارزمية الجيدة:
1. **الوضوح**: يجب أن تكون سهلة الفهم
2. **الكفاءة**: تنجز المهمة بسرعة
3. **الاستخدام الأمثل للموارد**: تستخدم ذاكرة قليلة
4. **الصحة**: تعطي النتيجة الصحيحة دائماً

### خطوات تطوير التطبيق:
```
1. التحليل والتخطيط
   ↓
2. كتابة الكود (Implementation)
   ↓
3. التجميع (Compilation)
   ↓
4. التشغيل (Execution)
   ↓
5. الاختبار (Testing)
   ↓
6. التصحيح (Debugging)
```

---

## نظرة عامة على المشروع

### الهدف:
بناء نظام لحجز الأنشطة الجامعية يسمح للطلاب بـ:
- اكتشاف الأنشطة القادمة
- التسجيل والغاء التسجيل
- إدارة تفاصيل الأنشطة

### الميزات الثلاث المنفذة:
1. ✅ **عرض الأنشطة القادمة** (مع الترتيب حسب التاريخ)
2. ✅ **التسجيل والغاء التسجيل**
3. ✅ **إدارة تفاصيل الأنشطة**

### البرمجة المستخدمة:
- **Procedural**: معالجة البيانات بدوال
- **OOP**: استخدام الكائنات والفئات
- **Event-Driven**: التفاعل مع أحداث المستخدم

---

## تحليل الخوارزمية

### الخوارزمية المختارة: Merge Sort

#### لماذا Merge Sort؟

| المعيار | Quick Sort | Bubble Sort | Merge Sort |
|--------|-----------|------------|-----------|
| أفضل حالة | O(n log n) | O(n²) | O(n log n) |
| حالة عادية | O(n log n) | O(n²) | O(n log n) |
| أسوأ حالة | O(n²) | O(n²) | O(n log n) |
| الاستقرار | غير مستقر | مستقر | مستقر |

**Merge Sort هو الأفضل لأنه:**
- ✅ محمي من أسوأ الحالات O(n log n)
- ✅ مستقر (يحافظ على ترتيب العناصر المتساوية)
- ✅ مناسب للبيانات الكبيرة
- ✅ قابل للتوازي

#### الخطوات:

**1. تقسيم (Divide):**
```
[ACT_3, ACT_1, ACT_4, ACT_2]
        ↓
    [ACT_3, ACT_1] | [ACT_4, ACT_2]
```

**2. ترتيب (Sort):**
```
[ACT_3, ACT_1] → [ACT_1, ACT_3]
[ACT_4, ACT_2] → [ACT_2, ACT_4]
```

**3. دمج (Merge):**
```
[ACT_1, ACT_3] + [ACT_2, ACT_4]
        ↓
[ACT_1, ACT_2, ACT_3, ACT_4]  (مرتبة حسب التاريخ)
```

#### Pseudocode:
```pseudocode
FUNCTION mergeSort(array, left, right)
    IF left < right THEN
        mid = (left + right) / 2
        mergeSort(array, left, mid)
        mergeSort(array, mid+1, right)
        merge(array, left, mid, right)
    END IF
END FUNCTION

FUNCTION merge(array, left, mid, right)
    Create leftArr from array[left...mid]
    Create rightArr from array[mid+1...right]
    
    i = 0, j = 0, k = left
    
    WHILE i < leftArr.length AND j < rightArr.length DO
        IF leftArr[i] <= rightArr[j] THEN
            array[k] = leftArr[i]
            i = i + 1
        ELSE
            array[k] = rightArr[j]
            j = j + 1
        END IF
        k = k + 1
    END WHILE
    
    Copy remaining elements from leftArr and rightArr
END FUNCTION
```

#### التحليل الزمني:
- **Time Complexity**: O(n log n)
  - تقسيم: log n مستويات
  - دمج: n عملية في كل مستوى
  - المجموع: n × log n

- **Space Complexity**: O(n)
  - نحتاج مصفوفات مؤقتة للدمج

---

## التطبيق

### البنية الكاملة للمشروع:

```
src/main/java/com/smartcampus/
├── Activity.java          (Entity Class)
├── Student.java           (Entity Class)
├── Utils.java             (Procedural Functions)
├── ActivityManager.java   (Manager Class - OOP)
└── BookingSystem.java     (Main - Event-Driven)
```

### تفاصيل كل ملف:

#### 1. Activity.java (OOP)
```java
public class Activity {
    private String id;
    private String title;
    private LocalDateTime dateTime;
    private List<String> registeredStudents;
    
    // Encapsulation
    public boolean registerStudent(String studentId) {
        if (hasAvailableSlots() && !registeredStudents.contains(studentId)) {
            registeredStudents.add(studentId);
            return true;
        }
        return false;
    }
}
```

**المبادئ:**
- ✅ **Encapsulation**: بيانات خاصة مع getter/setter
- ✅ **Data Hiding**: المستخدم لا يرى التفاصيل الداخلية

#### 2. Utils.java (Procedural)
```java
public static List<Activity> sortActivitiesByDate(List<Activity> activities) {
    List<Activity> copy = new ArrayList<>(activities);
    mergeSort(copy, 0, copy.size() - 1);
    return copy;
}

private static void mergeSort(List<Activity> arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}
```

**الميزات:**
- ✅ **دوال مستقلة**: بدون كائنات
- ✅ **معالجة خطية**: خطوة خطوة
- ✅ **إعادة استخدام**: يمكن استخدام الدوال في أي مكان

#### 3. ActivityManager.java (OOP)
```java
public class ActivityManager {
    private List<Activity> activities;
    
    public boolean registerStudentForActivity(String activityId, String studentId) {
        Activity activity = getActivity(activityId);
        if (activity != null) {
            return activity.registerStudent(studentId);
        }
        return false;
    }
}
```

**المبادئ:**
- ✅ **Single Responsibility**: مسؤول فقط عن إدارة الأنشطة
- ✅ **Encapsulation**: قائمة الأنشطة خاصة

#### 4. BookingSystem.java (Event-Driven)
```java
@FunctionalInterface
public interface OnActivityRegisteredListener {
    void onRegistered(String studentId, String activityId, String activityTitle);
}

private void fireRegistrationEvent(String studentId, String activityId, String title) {
    if (onActivityRegistered != null) {
        onActivityRegistered.onRegistered(studentId, activityId, title);
    }
}
```

**الميزات:**
- ✅ **Listeners**: للاستماع للأحداث
- ✅ **Callbacks**: تنفيذ الكود عند حدوث حدث
- ✅ **Decoupling**: الكود غير مرتبط مباشرة

---

## البرمجة الإجرائية

### التعريف:
البرمجة الإجرائية تركز على **خطوات الحل** وليس **الكائنات**. 
البرنامج عبارة عن سلسلة من الدوال والإجراءات.

### الخصائص:
```
✅ دوال مستقلة
✅ معالجة خطية
✅ سهلة الفهم للمبتدئين
❌ صعبة الصيانة في المشاريع الكبيرة
❌ إعادة كود كثيرة
```

### التطبيق في المشروع:

**File: Utils.java**

```java
// دالة مستقلة لترتيب الأنشطة
public static List<Activity> sortActivitiesByDate(List<Activity> activities) {
    if (activities == null || activities.size() <= 1) {
        return new ArrayList<>(activities);
    }
    
    List<Activity> copy = new ArrayList<>(activities);
    mergeSort(copy, 0, copy.size() - 1);
    return copy;
}

// دالة مساعدة للبحث
public static Activity searchActivityById(List<Activity> activities, String id) {
    for (Activity activity : activities) {
        if (activity.getId().equals(id)) {
            return activity;
        }
    }
    return null;
}

// دالة مساعدة للتحقق
public static boolean activityExists(List<Activity> activities, String id) {
    return searchActivityById(activities, id) != null;
}
```

### المميزات:
- ✅ **وضوح**: الدوال واضحة وسهلة الفهم
- ✅ **استقلالية**: يمكن استخدام الدوال بشكل منفصل
- ✅ **قابلية الاختبار**: سهل اختبار كل دالة

### العيوب في هذا التطبيق:
- ❌ عدم وجود حالة (state) للبيانات
- ❌ يجب نقل البيانات يدوياً بين الدوال
- ❌ صعوبة إضافة ميزات جديدة

---

## البرمجة الموجهة للكائنات

### التعريف:
البرمجة الموجهة للكائنات تركز على **الكائنات** التي تحتوي على **بيانات** و**سلوك**.

### المبادئ الأربعة:
```
1. Encapsulation (التغليف)
   - بيانات خاصة
   - طرق عامة للوصول
   
2. Inheritance (الوراثة)
   - أب وأبناء
   - مشاركة الكود
   
3. Polymorphism (التعدد الشكلي)
   - نفس الاسم، سلوك مختلف
   
4. Abstraction (التجريد)
   - إخفاء التعقيد
```

### التطبيق في المشروع:

**File: Activity.java**

```java
public class Activity {
    // Encapsulation: بيانات خاصة
    private String id;
    private String title;
    private LocalDateTime dateTime;
    private String location;
    private int capacity;
    private List<String> registeredStudents;
    
    // Constructor
    public Activity(String id, String title, String description,
                   LocalDateTime dateTime, String location, int capacity) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
        this.registeredStudents = new ArrayList<>();
    }
    
    // Getters - Controlled Access
    public String getId() { return id; }
    public String getTitle() { return title; }
    public LocalDateTime getDateTime() { return dateTime; }
    
    // Business Logic
    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }
    
    public boolean hasAvailableSlots() {
        return getAvailableSlots() > 0;
    }
    
    public boolean registerStudent(String studentId) {
        if (hasAvailableSlots() && !registeredStudents.contains(studentId)) {
            registeredStudents.add(studentId);
            return true;
        }
        return false;
    }
}
```

**File: ActivityManager.java**

```java
public class ActivityManager {
    private List<Activity> activities;  // Encapsulation
    
    public ActivityManager() {
        this.activities = new ArrayList<>();
    }
    
    // Business Logic
    public void addActivity(Activity activity) {
        if (activity != null && !Utils.activityExists(activities, activity.getId())) {
            activities.add(activity);
            System.out.println("✓ Activity added: " + activity.getTitle());
        }
    }
    
    public boolean registerStudentForActivity(String activityId, String studentId) {
        Activity activity = getActivity(activityId);
        if (activity != null && activity.registerStudent(studentId)) {
            System.out.println("✓ Registration successful!");
            return true;
        }
        return false;
    }
    
    public List<Activity> getUpcomingActivities() {
        return Utils.sortActivitiesByDate(activities);
    }
}
```

### المميزات:
- ✅ **تجميع البيانات والسلوك**: كل شيء في مكان واحد
- ✅ **إعادة استخدام**: يمكن إنشاء عدة Activity objects
- ✅ **صيانة سهلة**: تغيير الكود في مكان واحد
- ✅ **تناسب المشاريع الكبيرة**: سهل التوسع

### مثال عملي:

```java
// إنشاء نشاط
Activity javaWorkshop = new Activity(
    "ACT_1",
    "Java Workshop",
    "Learn Java",
    LocalDateTime.parse("2026-06-05 14:00", formatter),
    "Room 101",
    30
);

// تسجيل طالب
javaWorkshop.registerStudent("STU_001");
javaWorkshop.registerStudent("STU_002");

// الحصول على المعلومات
System.out.println("المقاعد المتاحة: " + javaWorkshop.getAvailableSlots());
// النتيجة: 28
```

---

## البرمجة المدفوعة بالأحداث

### التعريف:
البرمجة المدفوعة بالأحداث تركز على **الاستجابة للأحداث** (مثل نقرات الفأرة أو إدخال البيانات).
البرنامج ينتظر حدثاً ثم يرد على هذا الحدث.

### آلية العمل:
```
المستخدم يدخل بيانات
        ↓
يحدث حدث (Event)
        ↓
يتم البحث عن Listeners
        ↓
تنفيذ الكود (Callback)
```

### التطبيق في المشروع:

**File: BookingSystem.java**

```java
// 1. تعريف Listener Interface (Event)
@FunctionalInterface
public interface OnActivityRegisteredListener {
    void onRegistered(String studentId, String activityId, String activityTitle);
}

@FunctionalInterface
public interface OnActivityUnregisteredListener {
    void onUnregistered(String studentId, String activityId, String activityTitle);
}

// 2. تخزين الـ Listeners
private OnActivityRegisteredListener onActivityRegistered;
private OnActivityUnregisteredListener onActivityUnregistered;

// 3. تسجيل الـ Listeners (Registration)
public void setOnActivityRegisteredListener(OnActivityRegisteredListener listener) {
    this.onActivityRegistered = listener;
}

// 4. تفعيل الحدث (Fire Event)
private void fireRegistrationEvent(String studentId, String activityId, String title) {
    if (onActivityRegistered != null) {
        onActivityRegistered.onRegistered(studentId, activityId, title);
    }
}

// 5. استخدام الحدث عند التسجيل
public void registerForActivity() {
    // ... كود التسجيل ...
    if (activityManager.registerStudentForActivity(activityId, studentId)) {
        // تفعيل الحدث
        fireRegistrationEvent(studentId, activityId, activity.getTitle());
    }
}

// 6. تسجيل Listener افتراضي
private void registerDefaultListeners() {
    this.onActivityRegistered = (studentId, activityId, activityTitle) -> {
        System.out.println("📢 EVENT: Student " + studentId + 
                          " registered for: " + activityTitle);
    };
}
```

### مثال عملي:

```java
// إنشاء النظام
BookingSystem system = new BookingSystem();

// تسجيل Listener مخصص
system.setOnActivityRegisteredListener((studentId, activityId, title) -> {
    System.out.println("✅ New Registration!");
    System.out.println("Student: " + studentId);
    System.out.println("Activity: " + title);
    // يمكن حفظ في قاعدة البيانات أو إرسال بريد إلكتروني
});

// عند التسجيل:
system.registerForActivity();
// النتيجة:
// ✅ New Registration!
// Student: STU_001
// Activity: Java Workshop
```

### المميزات:
- ✅ **تفاعل مباشر**: البرنامج يستجيب فوراً
- ✅ **Loose Coupling**: كود غير مرتبط مباشرة
- ✅ **سهل الإضافة**: يمكن إضافة Listeners جديدة
- ✅ **مرن**: يمكن تغيير السلوك في الوقت الفعلي

### العيوب:
- ❌ أصعب في الفهم للمبتدئين
- ❌ يصعب Debugging الأحداث المعقدة
- ❌ قد تكون بطيئة مع عدد كبير من الأحداث

---

## عملية التصحيح والمعايير

### 1. عملية Debugging

#### الأخطاء المحتملة:

**أ) Syntax Errors** (أخطاء صيغة)
```java
// ❌ خطأ: نسيان النقطة والفاصلة
String name = "Activity"

// ✅ صحيح:
String name = "Activity";
```

**ب) Logic Errors** (أخطاء منطقية)
```java
// ❌ خطأ: الشرط مقلوب
if (capacity > registeredStudents.size()) {
    // سيسمح بالزيادة عن السعة!
}

// ✅ صحيح:
if (registeredStudents.size() < capacity) {
    // فقط إذا كان هناك مقاعد متاحة
}
```

**ج) Runtime Errors** (أخطاء التشغيل)
```java
// ❌ خطأ: Null Pointer Exception
Activity activity = getActivity("WRONG_ID");
System.out.println(activity.getTitle());  // crash!

// ✅ صحيح:
Activity activity = getActivity("ACT_1");
if (activity != null) {
    System.out.println(activity.getTitle());
}
```

#### أدوات Debugging في Eclipse:

**1. Breakpoints:**
```
- Ctrl+Shift+B: إضافة breakpoint
- F11: تشغيل Debugger
- F6: خطوة عادية (Step Over)
- F5: خطوة داخل الدالة (Step Into)
```

**2. Variables View:**
- مشاهدة قيم المتغيرات
- تتبع التغييرات

**3. Console View:**
```java
System.out.println("Debug: " + variable);
```

### 2. معايير الكود المطبقة

#### أ) Naming Conventions

```java
// ✅ Classes - PascalCase
public class ActivityManager { }
public class BookingSystem { }

// ✅ Methods - camelCase
public void registerForActivity() { }
public Activity getActivity(String id) { }

// ✅ Variables - camelCase
String studentId = "STU_001";
int capacity = 30;
boolean hasSlots = true;

// ✅ Constants - UPPER_CASE
private static final String SYSTEM_NAME = "Smart Campus";
```

#### ب) Code Comments

```java
/**
 * Merge Sort Algorithm - Sorts activities by date
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */
public static List<Activity> sortActivitiesByDate(List<Activity> activities) {
    // Implementation...
}

// Check if activity is full
if (!activity.hasAvailableSlots()) {
    System.out.println("Activity is full!");
}
```

#### ج) Encapsulation

```java
public class Activity {
    // ✅ Private fields
    private String id;
    private String title;
    private int capacity;
    
    // ✅ Public getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    
    // ✅ Controlled setters
    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
    }
}
```

#### د) Error Handling

```java
try {
    int capacity = Integer.parseInt(scanner.nextLine());
    if (capacity <= 0) {
        System.out.println("✗ Capacity must be positive!");
        return;
    }
} catch (NumberFormatException e) {
    System.out.println("✗ Invalid input! Enter a number.");
}
```

#### هـ) Single Responsibility

```java
// ✅ كل class له مسؤولية واحدة

// Activity: تخزين بيانات النشاط
public class Activity { }

// ActivityManager: إدارة الأنشطة
public class ActivityManager { }

// BookingSystem: واجهة المستخدم والأحداث
public class BookingSystem { }

// Utils: دوال مساعدة (ترتيب، بحث)
public class Utils { }
```

### 3. الفوائد من اتباع المعايير

#### للعمل الفردي:
- ✅ كود سهل الفهم
- ✅ سهولة البحث عن الأخطاء
- ✅ سهولة الصيانة

#### للعمل الجماعي:
- ✅ توحيد الكود
- ✅ تقليل الأخطاء
- ✅ سهولة التعاون
- ✅ توثيق أفضل

#### المنظمات المهنية:
- ✅ جودة أفضل
- ✅ أمان أفضل
- ✅ أداء أفضل
- ✅ توفير المال والوقت

---

## الخلاصة

### ما تم إنجازه:

| المهمة | الحالة | الملاحظات |
|-------|--------|----------|
| تحليل الخوارزمية | ✅ اكتمل | Merge Sort بـ O(n log n) |
| البرمجة الإجرائية | ✅ اكتمل | Utils.java مع دوال مستقلة |
| OOP | ✅ اكتمل | Activity, Manager مع Encapsulation |
| Event-Driven | ✅ اكتمل | Listeners و Callbacks |
| Debugging | ✅ اكتمل | أخطاء محتملة تم معالجتها |
| المعايير | ✅ اكتمل | Naming, Comments, Error Handling |

### المهارات المكتسبة:

1. **تحليل المشاكل**: تقسيم المشكلة إلى أجزاء
2. **اختيار الخوارزمية**: معرفة متى استخدام Merge Sort
3. **البرمجة المرنة**: استخدام paradigms مختلفة
4. **كود احترافي**: اتباع معايير عالية
5. **Debugging**: اكتشاف وحل الأخطاء

---

## المراجع والموارد

- Oracle Java Documentation
- Data Structures and Algorithms (CLRS)
- Clean Code by Robert C. Martin
- Design Patterns

---

**تاريخ الإنجاز:** 29 مايو 2026  
**المطور:** Abood RGB  
**الحالة:** جاهز للاستخدام ✅
