package com.shata.jdbc.databases_stuff_do_here;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class CreateDb {

    // Method to create database and table
    public static void createDatabaseAndTable() {
        // MySQL JDBC connection details
        String url = "jdbc:mysql://localhost:3306/";

        String username = "root"; // change this to your MySQL username
         // change this to your MySQL password
        String password = "harsha";
        // SQL queries
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS control_de_stock";
        String useDatabaseSQL = "USE control_de_stock";
        String createTableSQL = "CREATE TABLE IF NOT EXISTS PRODUCTO ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(255) NOT NULL, "
                + "descripcion VARCHAR(500), "
                + "cantidad INT NOT NULL"
                + ")";

        // Establishing connection and executing SQL
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            // 1. Create database
            System.out.println("Creating database...");
            stmt.executeUpdate(createDatabaseSQL);
            System.out.println("Database 'control_de_stock' created or already exists.");

            // 2. Use the created database
            stmt.executeUpdate(useDatabaseSQL);
            System.out.println("Using database 'control_de_stock'.");

            // 3. Create the PRODUCTO table
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'PRODUCTO' created or already exists.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
