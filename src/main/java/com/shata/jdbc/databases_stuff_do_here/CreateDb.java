package com.shata.jdbc.databases_stuff_do_here;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class CreateDb {


    public static void createDatabaseAndTable() {
        // MySQL JDBC connection details
        String url = "jdbc:mysql://localhost:3306/";

        String username = "root"; 
        
        String password = "harsha";
   
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS control_de_stock";
        String useDatabaseSQL = "USE control_de_stock";
        String createTableSQL = "CREATE TABLE IF NOT EXISTS PRODUCTO ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(255) NOT NULL, "
                + "descripcion VARCHAR(500), "
                + "cantidad INT NOT NULL"
                + ")";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            System.out.println("[*] Creating database...");
            stmt.executeUpdate(createDatabaseSQL);
            System.out.println("[*] Database 'control_de_stock' created or already exists.");

 
            stmt.executeUpdate(useDatabaseSQL);
            System.out.println("[*] Using database 'control_de_stock'.");

    
            stmt.executeUpdate(createTableSQL);
            System.out.println("[*] Table 'PRODUCTO' created or already exists.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
