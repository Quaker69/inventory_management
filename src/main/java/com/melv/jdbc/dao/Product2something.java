package com.melv.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.melv.jdbc.model.Product;

public class Product2something {

    private Connection con;

    public Product2something(Connection con) {
        this.con = con;
    }
    
    public void keep_it(Product product) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO PRODUCTO "
                        + "(nombre, descripcion, cantidad)"
                        + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setString(1, product.getName());
                statement.setString(2, product.getDescription());
                statement.setInt(3, product.getQuatityy());
    
                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        product.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("The product was inserted: %s", product));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> listar() {
        List<Product> result_is = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
    
            try (statement) {
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        result_is.add(new Product(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result_is;
    }

    public int elemniateeee(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount(); //how many rows where afected by this query // cuantas filas fueron afectadas con este query

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificationsssss(String nombre, String descripcion, Integer cantidad, Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE PRODUCTO SET "
                    + " NOMBRE = ?, "
                    + " DESCRIPCION = ?,"
                    + " CANTIDAD = ?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

