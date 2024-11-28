package com.melv.jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionFactory {
	//agrega tu propio user //Add your own user
	private String user = "root";
	//agrega tu propia contraseña //Add your own password
	private String password = "harsha";
	
	private DataSource dataSource;
	
	//Conection pool logic // logica para el pool de conexiones
	public ConnectionFactory(){
		var pooledDatraSource = new ComboPooledDataSource();
		pooledDatraSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock");
		pooledDatraSource.setUser(user);
		pooledDatraSource.setPassword(password);
		pooledDatraSource.setMaxPoolSize(10);
		this.dataSource = pooledDatraSource;
	}
	
	public Connection recoverConnection() {

		
		try {

			return this.dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}
	}

}
