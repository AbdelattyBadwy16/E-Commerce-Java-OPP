# Advanced E-Commerce System Simulation

## 1. Project Overview

This project is a console-based e-commerce simulation that demonstrates core Object-Oriented Programming (OOP) principles. It is designed to be a flexible and scalable system for handling various product types, managing a shopping cart, and processing checkouts with robust validation and error handling.

---

## 2. Core Features

### Product Management
- **Base Products:** All products have a name, price, and available quantity.
- **Expirable Products:** Special products like "Cheese" and "Biscuits" have an expiration date. The system prevents the checkout of expired items.
- **Shippable Products:** Products that require shipping, like "TV" or "Cheese", have an associated weight.
- **Complex Types:** The system supports products that are both shippable and expirable (e.g., Cheese).

### Cart & Checkout
- **Add to Cart:** Customers can add products to their cart, with quantity validation to ensure it does not exceed available stock.
- **Checkout Process:**
    - Calculates and displays the order subtotal, shipping fees, and total amount to be paid.
    - Updates and displays the customer's new balance after a successful purchase.
- **Robust Validation:** The checkout process fails with a clear error message if:
    - The shopping cart is empty.
    - The customer's balance is insufficient.
    - A product in the cart is out of stock or has expired.

### Shipping
- **Shipment Handling:** After a successful checkout, the system compiles a list of all shippable items.
- **Service Integration:** This list is then sent to a simulated `ShippingService`, which processes items based on their name and weight, adhering to a predefined `Shippable` interface.

---

## 3. Design Decisions & Concepts

The core of this project is a polymorphic design that allows for easy extension and maintenance.

- **Interfaces for Flexibility:** Instead of a rigid class hierarchy, I used interfaces (`Shippable`, `Expirable`) to define product behaviors. This approach allows a single product class to implement multiple behaviors (e.g., `Cheese` can be both `Shippable` and `Expirable`) without the limitations of multiple inheritance.

- **Encapsulation:** Each class (`Customer`, `Product`, `Cart`) encapsulates its own data and logic, promoting modularity and reducing complexity.

- **Error Handling:** The system is designed to handle all specified corner cases gracefully, providing clear, user-friendly error messages to explain why a transaction cannot be completed.

---

## 4. Tech Stack

- **Language:** Java
