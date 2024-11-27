package com.melv.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.melv.jdbc.factory.ConnectionFactory;

public class PruebaPoolConexiones {
	
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		for (int i = 0; i < 20; i++) {
			Connection conexion = connectionFactory.recuperaConexion();
			System.out.println("Opening Connection Number" + (i+1));
			
		}
	}
}
