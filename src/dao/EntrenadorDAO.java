package dao;

import framework.dao.BaseDAO;
import framework.exception.DatabaseException;
import model.Entrenador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class EntrenadorDAO extends BaseDAO<Entrenador> {

    @Override
    public void guardar(Entrenador entrenador) {

        String sql = """
                INSERT INTO entrenador
                (persona_id, especialidad, fecha_ingreso, estado)
                VALUES (?, ?, ?, ?)
                """;

        try (
        		Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, entrenador.getPersonaId());
            ps.setString(2, entrenador.getEspecialidad());
            ps.setDate(3, entrenador.getFechaIngreso());
            ps.setString(4, entrenador.getEstado());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Error al guardar entrenador.", e);
        }
    }

    @Override
    public void actualizar(Entrenador entrenador) {

        String sql = """
                UPDATE entrenador
                SET persona_id = ?,
                    especialidad = ?,
                    fecha_ingreso = ?,
                    estado = ?
                WHERE id_entrenador = ?
                """;

        try (
        		Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, entrenador.getPersonaId());
            ps.setString(2, entrenador.getEspecialidad());
            ps.setDate(3, entrenador.getFechaIngreso());
            ps.setString(4, entrenador.getEstado());
            ps.setInt(5, entrenador.getIdEntrenador());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Error al actualizar entrenador.", e);
        }
    }

    @Override
    public void eliminar(Integer id) {

        String sql = """
                UPDATE entrenador
                SET estado = 'INACTIVO'
                WHERE id_entrenador = ?
                """;

        try (
        		Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Error al eliminar entrenador.", e);
        }
    }

    @Override
    public Entrenador buscarPorId(Integer id) {

    	String sql = """
    	        SELECT

    	            e.*,

    	            p.nombre,

    	            p.apellido

    	        FROM entrenador e

    	        INNER JOIN persona p
    	            ON e.persona_id = p.id_persona

    	        WHERE e.id_entrenador = ?
    	        """;

        try (
        		Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }

            return null;

        } catch (Exception e) {
            throw new DatabaseException("Error al buscar entrenador.", e);
        }
    }

    @Override
    public List<Entrenador> listar() {

        List<Entrenador> lista = new ArrayList<>();

        String sql = """
                SELECT

                    e.*,

                    p.nombre,

                    p.apellido

                FROM entrenador e

                INNER JOIN persona p
                    ON e.persona_id = p.id_persona

                ORDER BY e.id_entrenador
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

            return lista;

        } catch (Exception e) {
            throw new DatabaseException("Error al listar entrenadores.", e);
        }
    }

    
    
    private Entrenador mapear(ResultSet rs) throws Exception {

        Entrenador entrenador = new Entrenador();

        entrenador.setIdEntrenador(rs.getInt("id_entrenador"));
        entrenador.setPersonaId(rs.getInt("persona_id"));
        entrenador.setNombrePersona(rs.getString("nombre"));
        entrenador.setApellidoPersona(rs.getString("apellido"));
        entrenador.setEspecialidad(rs.getString("especialidad"));
        entrenador.setFechaIngreso(rs.getDate("fecha_ingreso"));
        entrenador.setEstado(rs.getString("estado"));

        try {
            entrenador.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
            entrenador.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion"));
        } catch (Exception ignored) {
            // Compatible con versiones anteriores de la base de datos
        }

        return entrenador;
    }

}