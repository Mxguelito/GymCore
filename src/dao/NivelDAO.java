package dao;

import framework.dao.BaseDAO;
import framework.exception.DatabaseException;
import model.Nivel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class NivelDAO extends BaseDAO<Nivel> {

    public void guardar(Nivel nivel) {

        String sql = """
                INSERT INTO nivel
                (nombre, descripcion, activo)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, nivel.getNombre());
            ps.setString(2, nivel.getDescripcion());
            ps.setBoolean(3, nivel.getActivo());

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException("Error al guardar nivel", e);

        }

    }

    public void actualizar(Nivel nivel) {

        String sql = """
                UPDATE nivel
                SET nombre = ?,
                    descripcion = ?,
                    activo = ?
                WHERE id_nivel = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, nivel.getNombre());
            ps.setString(2, nivel.getDescripcion());
            ps.setBoolean(3, nivel.getActivo());
            ps.setInt(4, nivel.getIdNivel());

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException("Error al actualizar nivel", e);

        }

    }

    public void eliminar(Integer idNivel) {

        String sql = """
                UPDATE nivel
                SET activo = false
                WHERE id_nivel = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idNivel);

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException("Error al eliminar nivel", e);

        }

    }

    public Nivel buscarPorId(Integer idNivel) {

        String sql = """
                SELECT *
                FROM nivel
                WHERE id_nivel = ?
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idNivel);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapear(rs);

            }

            return null;

        } catch (Exception e) {

            throw new DatabaseException("Error al buscar nivel", e);

        }

    }

    public List<Nivel> listar() {

        List<Nivel> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM nivel
                WHERE activo = true
                ORDER BY nombre
                """;

        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                lista.add(mapear(rs));

            }

        } catch (Exception e) {

            throw new DatabaseException("Error al listar niveles", e);

        }

        return lista;

    }

    private Nivel mapear(ResultSet rs) throws Exception {

        Nivel nivel = new Nivel();

        nivel.setIdNivel(rs.getInt("id_nivel"));
        nivel.setNombre(rs.getString("nombre"));
        nivel.setDescripcion(rs.getString("descripcion"));
        nivel.setActivo(rs.getBoolean("activo"));

        if (rs.getTimestamp("fecha_creacion") != null) {
            nivel.setFechaCreacion(
                    rs.getTimestamp("fecha_creacion").toLocalDateTime()
            );
        }

        if (rs.getTimestamp("fecha_actualizacion") != null) {
            nivel.setFechaActualizacion(
                    rs.getTimestamp("fecha_actualizacion").toLocalDateTime()
            );
        }

        return nivel;

    }

}