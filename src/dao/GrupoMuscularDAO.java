package dao;

import framework.dao.BaseDAO;
import framework.exception.DatabaseException;
import model.GrupoMuscular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class GrupoMuscularDAO extends BaseDAO<GrupoMuscular> {

    @Override
    public void guardar(GrupoMuscular grupo) {

        String sql = """
                INSERT INTO grupo_muscular
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
                PreparedStatement ps = conexion.prepareStatement(
                        sql,
                        PreparedStatement.RETURN_GENERATED_KEYS
                )

        ) {

            prepararGrupo(ps, grupo);

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {

                grupo.setIdGrupoMuscular(keys.getInt(1));

            }

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al guardar el grupo muscular.",
                    e
            );

        }

    }

    @Override
    public void actualizar(GrupoMuscular grupo) {

        String sql = """
                UPDATE grupo_muscular
                SET
                    nombre = ?,
                    descripcion = ?,
                    activo = ?
                WHERE id_grupo_muscular = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            prepararGrupo(ps, grupo);

            ps.setInt(4, grupo.getIdGrupoMuscular());

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al actualizar el grupo muscular.",
                    e
            );

        }

    }

    @Override
    public void eliminar(Integer id) {

        String sql = """
                UPDATE grupo_muscular
                SET activo = FALSE
                WHERE id_grupo_muscular = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al eliminar el grupo muscular.",
                    e
            );

        }

    }

    @Override
    public GrupoMuscular buscarPorId(Integer id) {

        String sql = """
                SELECT *
                FROM grupo_muscular
                WHERE id_grupo_muscular = ?
                AND activo = TRUE
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return mapearGrupo(rs);

            }

            return null;

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al buscar el grupo muscular.",
                    e
            );

        }

    }

    @Override
    public List<GrupoMuscular> listar() {

        String sql = """
                SELECT *
                FROM grupo_muscular
                WHERE activo = TRUE
                ORDER BY nombre
                """;

        List<GrupoMuscular> grupos = new ArrayList<>();

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                grupos.add(mapearGrupo(rs));

            }

            return grupos;

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al listar los grupos musculares.",
                    e
            );

        }

    }

    private GrupoMuscular mapearGrupo(ResultSet rs) throws Exception {

        GrupoMuscular grupo = new GrupoMuscular();

        grupo.setIdGrupoMuscular(rs.getInt("id_grupo_muscular"));
        grupo.setNombre(rs.getString("nombre"));
        grupo.setDescripcion(rs.getString("descripcion"));
        grupo.setActivo(rs.getBoolean("activo"));

        Timestamp fechaCreacion = rs.getTimestamp("fecha_creacion");

        if (fechaCreacion != null) {

            grupo.setFechaCreacion(
                    fechaCreacion.toLocalDateTime()
            );

        }

        Timestamp fechaActualizacion = rs.getTimestamp("fecha_actualizacion");

        if (fechaActualizacion != null) {

            grupo.setFechaActualizacion(
                    fechaActualizacion.toLocalDateTime()
            );

        }

        return grupo;

    }

    private void prepararGrupo(
            PreparedStatement ps,
            GrupoMuscular grupo
    ) throws Exception {

        ps.setString(1, grupo.getNombre());

        ps.setString(2, grupo.getDescripcion());

        ps.setBoolean(
                3,
                grupo.getActivo() != null
                        ? grupo.getActivo()
                        : true
        );

    }

}