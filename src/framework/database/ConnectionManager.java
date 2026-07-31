package framework.database;

import config.Conexion;

import java.sql.Connection;

public final class ConnectionManager {

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        return Conexion.conectar();
    }

}