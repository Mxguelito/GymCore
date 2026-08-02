package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.Conexion;

public class DashboardDAO {

    private int contar(String tabla) {

        String sql = "SELECT COUNT(*) FROM " + tabla;

        try (
        		Connection conn = Conexion.conectar();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }

    public int contarClientes() {
        return contar("cliente");
    }

    public int contarEntrenadores() {
        return contar("entrenador");
    }

    public int contarRutinas() {
        return contar("rutina");
    }

    public int contarObjetivos() {
        return contar("objetivo");
    }

    public int contarGruposMusculares() {
        return contar("grupo_muscular");
    }

    public int contarNiveles() {
        return contar("nivel");
    }
    
    public int contarPagos() {
        return contar("pago");
    }

}