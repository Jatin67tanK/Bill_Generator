
---

# Billing & Stock Management System – Spring Boot

This project is a Spring Boot–based Billing and Inventory Management System designed for small shops and businesses. It provides features for product management, stock updates, order processing, billing, threshold alerts, and SMS notifications using Twilio. The system supports both administrators (managing stock) and customers (placing orders).

---

## Key Features

### 1. Add New Product

* Admin can add products with fields:
  Name, Price, GST, Quantity, Threshold Value
* Saves product details in the database using Spring Data JPA.

### 2. Update Stock (by product ID)

* Automatically updates the product quantity in the database.

### 3. Customer Order Placement

* Customer submits:

  * Name
  * Mobile No
  * Product ID
  * Product Quantity
* System:

  * Validates product availability
  * Generates a bill
  * Calculates subtotal and GST
  * Deducts stock
  * Sends SMS confirmation

### 4. Payment Simulation

* Mock payment method (`isPayment()`)
* Random 80% success rate
* On payment failure, customer receives an SMS notification.

### 5. Threshold Alerts

* If ordered quantity exceeds the threshold:

  * Sends Twilio SMS alert
  * Prevents order placement if stock is insufficient

### 6. Daily Stock Report

* Generates stock data in CSV format:

  ```
  id,productName,Quantity,price,gst,threshold
  ```
* Can be downloaded or sent to admin.

### 7. Twilio SMS Notifications

* Payment status notifications
* Threshold warnings
* Order confirmation messages

---

## Core Business Logic Includes

* GST calculation
* Bill generation algorithm
* Payment confirmation flow
* Stock deduction and threshold validation
* Automated notifications

---

## Technologies Used

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Twilio SMS API
* REST API Architecture

