# Smart Campus Activity Booking System

## Overview
A comprehensive system for managing and booking campus activities at the university. This system allows students to discover, register, and manage their participation in various campus events such as workshops, sports sessions, seminars, and club events.

## Features

### 1. Display Upcoming Activities
- View all available activities
- Activities sorted by date (using Merge Sort algorithm)
- Shows activity details: title, date, time, location, capacity, and available slots

### 2. Register/Unregister for Activities
- Students can register for activities
- Students can unregister from activities
- Automatic prevention of overbooking
- Real-time capacity management

### 3. Manage Activity Details
- Create and update activity information
- Set activity title, schedule, location, and capacity
- Track current registrations
- Monitor available slots

## Technology Stack
- **Language:** Java
- **IDE:** IntelliJ IDEA / Eclipse / NetBeans
- **Architecture:** 
  - Procedural Programming (Utility functions)
  - Object-Oriented Programming (Classes, Inheritance, Encapsulation)
  - Event-Driven Programming (Event listeners and handlers)

## Algorithm
**Merge Sort** is used to sort activities by date in ascending order for optimal time complexity O(n log n).

## Project Structure
```
src/
├── Activity.java           - Core activity entity
├── ActivityManager.java    - Business logic for activity management
├── Student.java            - Student entity
├── BookingSystem.java      - Main system with event handling
└── Utils.java              - Utility functions
```

## How to Run
1. Compile all Java files
2. Run `BookingSystem.java`
3. Follow the menu prompts to interact with the system

## Report
See `REPORT.md` for detailed documentation on:
- Algorithm design and analysis
- Implementation details
- Programming paradigm explanations
- Debugging process
- Coding standards applied

## Author
Abood RGB - Junior Developer
