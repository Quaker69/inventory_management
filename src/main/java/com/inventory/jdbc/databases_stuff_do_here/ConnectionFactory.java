package com.inventory.jdbc.databases_stuff_do_here;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private String user = "root";
    private String password = "harsha";
    private DataSource dataSource;

    // Static block to disable all logging
    static {
        Logger mlogLogger = Logger.getLogger("com.mchange.v2.log.MLog");
        mlogLogger.setLevel(Level.OFF); // Disable logging for MLog

        Logger c3p0Logger = Logger.getLogger("com.mchange.v2.c3p0");
        c3p0Logger.setLevel(Level.OFF); // Disable logging for c3p0
    }

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
