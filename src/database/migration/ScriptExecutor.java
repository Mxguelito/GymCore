package database.migration;

import config.Conexion;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.sql.Connection;
import java.sql.Statement;

public class ScriptExecutor {
	
	
	
	public boolean ejecutar(String archivo) {

	    try {
	    	
	    	Connection conexion = Conexion.conectar();
	    	String sql = Files.readString(Paths.get(archivo));
	    	Statement statement = conexion.createStatement();
	    	statement.execute(sql);
	    	statement.close();

	    	conexion.close();
	    	System.out.println("✔ Ejecutado: " + archivo);
	    	
	    	return true;

	    } catch (Exception e) {

	        e.printStackTrace();
	        
	        return false;

	    }

}

}
