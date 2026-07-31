package dao;

import framework.dao.BaseDAO;
import framework.exception.DatabaseException;

import model.Ejercicio;
import model.GrupoMuscular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class EjercicioDAO extends BaseDAO<Ejercicio> {

    @Override
    public void guardar(Ejercicio ejercicio) {

        String sql = """
                INSERT INTO ejercicio
                (
                    grupo_muscular_id,
                    nombre,
                    descripcion,
                    video_url,
                    activo
                )
                VALUES
                (?, ?, ?, ?, ?)
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(
                        sql,
                        PreparedStatement.RETURN_GENERATED_KEYS
                )

        ) {

            prepararEjercicio(ps, ejercicio);

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {

                ejercicio.setIdEjercicio(keys.getInt(1));

            }

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al guardar el ejercicio.",
                    e
            );

        }

    }

    @Override
    public void actualizar(Ejercicio ejercicio) {

        String sql = """
                UPDATE ejercicio
                SET
                    grupo_muscular_id = ?,
                    nombre = ?,
                    descripcion = ?,
                    video_url = ?,
                    activo = ?
                WHERE id_ejercicio = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            prepararEjercicio(ps, ejercicio);

            ps.setInt(6, ejercicio.getIdEjercicio());

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al actualizar el ejercicio.",
                    e
            );

        }

    }

    @Override
    public void eliminar(Integer id) {

        String sql = """
                UPDATE ejercicio
                SET activo = FALSE
                WHERE id_ejercicio = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al eliminar el ejercicio.",
                    e
            );

        }

    }

    @Override
    public Ejercicio buscarPorId(Integer id) {

        String sql = """
                SELECT

                    e.*,

                    g.id_grupo_muscular,

                    g.nombre AS grupo_nombre

                FROM ejercicio e

                INNER JOIN grupo_muscular g
                    ON e.grupo_muscular_id = g.id_grupo_muscular

                WHERE e.id_ejercicio = ?

                AND e.activo = TRUE
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapearEjercicio(rs);

            }

            return null;

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al buscar el ejercicio.",
                    e
            );

        }

    }

    @Override
    public List<Ejercicio> listar() {

        String sql = """
                SELECT

                    e.*,

                    g.id_grupo_muscular,

                    g.nombre AS grupo_nombre

                FROM ejercicio e

                INNER JOIN grupo_muscular g
                    ON e.grupo_muscular_id = g.id_grupo_muscular

                WHERE e.activo = TRUE

                ORDER BY g.nombre,
                         e.nombre
                """;

        List<Ejercicio> ejercicios = new ArrayList<>();

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ejercicios.add(mapearEjercicio(rs));

            }

            return ejercicios;

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al listar los ejercicios.",
                    e
            );

        }

    }
    
    public List<Ejercicio> listarPorGrupo(Integer idGrupo) {

        String sql = """
                SELECT

                    e.*,

                    g.id_grupo_muscular,

                    g.nombre AS grupo_nombre

                FROM ejercicio e

                INNER JOIN grupo_muscular g
                    ON e.grupo_muscular_id = g.id_grupo_muscular

                WHERE e.activo = TRUE

                AND g.id_grupo_muscular = ?

                ORDER BY e.nombre
                """;

        List<Ejercicio> ejercicios = new ArrayList<>();

        try (

                Connection conexion = getConnection();

                PreparedStatement ps =
                        conexion.prepareStatement(sql);

        ) {

            ps.setInt(1, idGrupo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ejercicios.add(
                        mapearEjercicio(rs)
                );

            }

            return ejercicios;

        } catch (Exception e) {

            throw new DatabaseException(

                    "Error al listar ejercicios por grupo.",

                    e

            );

        }

    }

    private Ejercicio mapearEjercicio(ResultSet rs) throws Exception {

        GrupoMuscular grupo = new GrupoMuscular();

        grupo.setIdGrupoMuscular(
                rs.getInt("id_grupo_muscular")
        );

        grupo.setNombre(
                rs.getString("grupo_nombre")
        );

        Ejercicio ejercicio = new Ejercicio();

        ejercicio.setIdEjercicio(
                rs.getInt("id_ejercicio")
        );

        ejercicio.setGrupoMuscular(grupo);

        ejercicio.setNombre(
                rs.getString("nombre")
        );

        ejercicio.setDescripcion(
                rs.getString("descripcion")
        );

        ejercicio.setVideoUrl(
                rs.getString("video_url")
        );

        ejercicio.setActivo(
                rs.getBoolean("activo")
        );

        Timestamp fechaCreacion =
                rs.getTimestamp("fecha_creacion");

        if (fechaCreacion != null) {

            ejercicio.setFechaCreacion(
                    fechaCreacion.toLocalDateTime()
            );

        }

        Timestamp fechaActualizacion =
                rs.getTimestamp("fecha_actualizacion");

        if (fechaActualizacion != null) {

            ejercicio.setFechaActualizacion(
                    fechaActualizacion.toLocalDateTime()
            );

        }

        return ejercicio;

    }

    private void prepararEjercicio(
            PreparedStatement ps,
            Ejercicio ejercicio
    ) throws Exception {

        ps.setInt(
                1,
                ejercicio.getGrupoMuscular()
                        .getIdGrupoMuscular()
        );

        ps.setString(
                2,
                ejercicio.getNombre()
        );

        ps.setString(
                3,
                ejercicio.getDescripcion()
        );

        ps.setString(
                4,
                ejercicio.getVideoUrl()
        );

        ps.setBoolean(
                5,
                ejercicio.getActivo() != null
                        ? ejercicio.getActivo()
                        : true
        );

    }

}