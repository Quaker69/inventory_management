package com.melv.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.melv.jdbc.factory.ConnectionFactory;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
    	Connection con = new ConnectionFactory().recuperaConexion();

        System.out.println("Closing the connection");

        con.close();
    }

}
