# Inventory Management System

A simple **Inventory Management System** developed using **Java**, **Swing**, **JDBC**, and **MySQL** for managing products, with features like adding, removing, and modifying stock. The system also includes a **user login** and the ability to generate **bar graph reports** based on the inventory.

## Features

- **Login System**: Secure user login functionality.
- **Inventory Management**:
  - Add new products to the inventory.
  - Remove products from the inventory.
  - Modify product details (name, quantity, and price).
- **Reports**:
  - Generate bar graph reports of the inventory, displaying product quantities.

## Technologies Used

- **Java**: For application development.
- **Swing**: For the graphical user interface (GUI).
- **JDBC**: To connect and interact with the MySQL database.
- **MySQL**: For storing inventory data and user credentials.
- **JFreeChart**: Used for generating and displaying bar graphs.

## Database Setup

Create a MySQL database named `inventory_db` and use the following SQL scripts to set up the necessary tables.

### SQL Scripts:

#### `products` Table:
```sql
CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    quantity INT,
    price DECIMAL(10, 2)
);
