package database.migration;

import config.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class SchemaVersionDAO {
	
	
	
	public List<String> obtenerVersionesEjecutadas() {

	    List<String> versiones = new ArrayList<>();

	    try {

	        Connection conexion = Conexion.conectar();

	        String sql = "SELECT version FROM schema_version";

	        PreparedStatement ps = conexion.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            versiones.add(rs.getString("version"));

	        }

	        rs.close();
	        ps.close();
	        conexion.close();

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return versiones;

	}
	
	public boolean existeTabla() {

	    try {

	        Connection conexion = Conexion.conectar();

	        String sql =
	                "SHOW TABLES LIKE 'schema_version'";

	        PreparedStatement ps =
	                conexion.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        boolean existe = rs.next();

	        rs.close();
	        ps.close();
	        conexion.close();

	        return existe;

	    } catch (Exception e) {

	        e.printStackTrace();

	        return false;

	    }

	}
	public void registrar(Migration migration) {

	    try {

	        Connection conexion = Conexion.conectar();

	        String sql =
	        		"INSERT INTO schema_version(version, nombre, archivo) VALUES (?, ?, ?)";

	        PreparedStatement ps = conexion.prepareStatement(sql);

	        ps.setString(1, migration.getVersion());
	        ps.setString(2, migration.getNombre());
	        ps.setString(3, migration.getArchivo());

	        ps.executeUpdate();

	        ps.close();
	        conexion.close();

	        System.out.println("✔ Registrada: " + migration.getVersion());

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	}

}
