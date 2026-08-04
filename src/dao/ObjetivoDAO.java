package dao;

import framework.dao.BaseDAO;
import framework.exception.DatabaseException;
import model.Objetivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class ObjetivoDAO extends BaseDAO<Objetivo> {

    @Override
    public void guardar(Objetivo objetivo) {

    	String sql = """
    	        INSERT INTO objetivo
    	        (
    	            nombre,
    	            descripcion,
    	            activo
    	        )
    	        VALUES
    	        (?, ?, ?)
    	        """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

        	ps.setString(1, objetivo.getNombre());
        	ps.setString(2, objetivo.getDescripcion());
        	ps.setBoolean(3, objetivo.getActivo());

        	ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al guardar el objetivo.",
                    e
            );

        }

    }

    @Override
    public void actualizar(Objetivo objetivo) {

        String sql = """
                UPDATE objetivo
SET
    nombre = ?,
    descripcion = ?,
    activo = ?
WHERE id_objetivo = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

        	ps.setString(1, objetivo.getNombre());
        	ps.setString(2, objetivo.getDescripcion());
        	ps.setBoolean(3, objetivo.getActivo());
        	ps.setInt(4, objetivo.getIdObjetivo());

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al actualizar el objetivo.",
                    e
            );

        }

    }

    @Override
    public void eliminar(Integer id) {

        String sql = """
                UPDATE objetivo
                SET activo = FALSE
                WHERE id_objetivo = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al eliminar el objetivo.",
                    e
            );

        }

    }

    @Override
    public Objetivo buscarPorId(Integer id) {

        String sql = """
                SELECT *
                FROM objetivo
                WHERE id_objetivo = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Objetivo objetivo = new Objetivo();

                objetivo.setIdObjetivo(rs.getInt("id_objetivo"));
                objetivo.setNombre(rs.getString("nombre"));
                objetivo.setDescripcion(rs.getString("descripcion"));
                objetivo.setActivo(rs.getBoolean("activo"));

                return objetivo;

            }

            return null;

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al buscar el objetivo.",
                    e
            );

        }

    }

    @Override
    public List<Objetivo> listar() {

    	String sql = """
    	        SELECT *
    	        FROM objetivo
    	        WHERE activo = TRUE
    	        ORDER BY nombre
    	        """;

        List<Objetivo> objetivos = new ArrayList<>();

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Objetivo objetivo = new Objetivo();

                objetivo.setIdObjetivo(rs.getInt("id_objetivo"));
                objetivo.setNombre(rs.getString("nombre"));
                objetivo.setDescripcion(rs.getString("descripcion"));
                objetivo.setActivo(rs.getBoolean("activo"));

                objetivos.add(objetivo);

            }

            return objetivos;

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al listar objetivos.",
                    e
            );

        }

    }

}