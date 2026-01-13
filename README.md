# Food Delivery Management System

## A Java Console Application

---

## Overview

The Food Delivery Management System is a Java-based console application that simulates the core workflow of a food delivery platform. It allows users to browse a menu, place orders, process payments, assign delivery personnel, and generate bills.

This project was developed as a mini project to demonstrate strong Object-Oriented Programming principles, structured program design, and file handling in Java.

---

## Key Highlights

* Modular and well-structured Java code
* Strong use of Object-Oriented Programming concepts
* Custom exception handling for realistic error scenarios
* File-based input and output using CSV and TXT files
* Easy-to-run console application

---

## Tech Stack

**Language**
Java

**Programming Paradigm**
Object-Oriented Programming

**Data Handling**

* CSV files for input
* Text files for bill generation

**Interface**
Command-line / Console based

---

## Features

* Menu management with multiple food categories:

  * Pizza
  * Burger
  * Pasta
  * Beverages
  * Desserts

* Order creation and processing

* Automatic delivery person assignment

* Bill generation after successful order placement

* Custom exception handling for:

  * Invalid orders
  * Payment failures

* Persistent data handling using files

---

## Project Structure

```
src/
├── FoodDeliveryApp.java
└── menu/
    ├── MenuItem.java
    ├── Pizza.java
    ├── Burger.java
    ├── Pasta.java
    ├── Beverage.java
    └── Dessert.java

data/
└── delivery_people.csv

output/
└── bill.txt
```

---

## How to Run the Project

1. Clone the repository:

   ```
   git clone https://github.com/your-username/Food-Delivery-Management-System.git
   ```

2. Navigate to the source directory:

   ```
   cd src
   ```

3. Compile the application:

   ```
   javac FoodDeliveryApp.java
   ```

4. Run the program:

   ```
   java FoodDeliveryApp
   ```

---

## Concepts Demonstrated

* Classes and Objects
* Inheritance and Polymorphism
* Encapsulation and Abstraction
* Custom exception handling
* File handling using CSV and TXT files
* Modular application design

---

## Future Enhancements

* Database integration instead of file-based storage
* Graphical User Interface
* User authentication and roles
* Online payment simulation
* Order history and analytics

---

## Author

**Shreedevi Balaji**

Computer Science Engineering
Mini Project – Food Delivery Management System

---

