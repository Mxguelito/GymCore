package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/gymcore_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {

        try {

        	Class.forName("com.mysql.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("======================================");
            System.out.println(" CONEXION EXITOSA A MYSQL ");
            System.out.println("======================================");

            return conexion;

        } catch (Exception e) {

            System.out.println("Error al conectar.");

            e.printStackTrace();

            return null;

        }

    }

}