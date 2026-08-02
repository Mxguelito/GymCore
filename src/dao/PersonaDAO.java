package dao;

import framework.dao.BaseDAO;
import framework.exception.DatabaseException;
import model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;

public class PersonaDAO extends BaseDAO<Persona> {

    @Override
    public void guardar(Persona persona) {

        String sql = """
                INSERT INTO persona
                (
                    nombre,
                    apellido,
                    dni,
                    telefono,
                    email,
                    fecha_nacimiento,
                    sexo,
                    activo
                )
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        
        try (

        	    Connection conexion = getConnection();

        	    PreparedStatement ps = conexion.prepareStatement(
        	            sql,
        	            PreparedStatement.RETURN_GENERATED_KEYS
        	    )

        	) {

        	    prepararPersona(ps, persona);

        	    int filas = ps.executeUpdate();

        	    ResultSet keys = ps.getGeneratedKeys();

        	    if (keys.next()) {

        	        persona.setIdPersona(keys.getInt(1));

        	    }

        	    System.out.println();
        	    System.out.println("====================================");
        	    System.out.println(" PERSONA GUARDADA CORRECTAMENTE");
        	    System.out.println(" Filas afectadas: " + filas);
        	    System.out.println("====================================");

        	} catch (Exception e) {

        	    throw new DatabaseException(
        	            "Error al guardar la persona.",
        	            e
        	    );

        	}

    }

    
    @Override
    public void actualizar(Persona persona) {

        String sql = """
                UPDATE persona
                SET
                    nombre = ?,
                    apellido = ?,
                    dni = ?,
                    telefono = ?,
                    email = ?,
                    fecha_nacimiento = ?,
                    sexo = ?,
                    activo = ?
                WHERE id_persona = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);

        ) {

        	prepararPersona(ps, persona);

        	ps.setInt(9, persona.getIdPersona());

        	int filas = ps.executeUpdate();

            System.out.println();
            System.out.println("====================================");
            System.out.println(" PERSONA ACTUALIZADA");
            System.out.println(" Filas afectadas: " + filas);
            System.out.println("====================================");

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al actualizar la persona.",
                    e
            );

        }

    }

    
    @Override
    public void eliminar(Integer id) {

        String sql = """
                UPDATE persona
                SET activo = FALSE
                WHERE id_persona = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);

        ) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            System.out.println();
            System.out.println("====================================");
            System.out.println(" PERSONA ELIMINADA");
            System.out.println(" Filas afectadas: " + filas);
            System.out.println("====================================");

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al eliminar la persona.",
                    e
            );

        }

    }

    
    @Override
    public Persona buscarPorId(Integer id) {

        String sql = """
                SELECT *
                FROM persona
                WHERE id_persona = ?
                AND activo = TRUE
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);

        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

            	return mapearPersona(rs);

            }

            return null;

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al buscar la persona.",
                    e
            );

        }

    }

    
    @Override
    public List<Persona> listar() {

        String sql = """
                SELECT *
                FROM persona
                WHERE activo = TRUE
                ORDER BY apellido, nombre
                """;

        List<Persona> personas = new ArrayList<>();

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);

        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

            	personas.add(mapearPersona(rs));

            }

            return personas;

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al listar personas.",
                    e
            );

        }

    }

    
    private Persona mapearPersona(ResultSet rs) throws Exception {

        Persona persona = new Persona();

        persona.setIdPersona(rs.getInt("id_persona"));
        persona.setNombre(rs.getString("nombre"));
        persona.setApellido(rs.getString("apellido"));
        persona.setDni(rs.getString("dni"));
        persona.setTelefono(rs.getString("telefono"));
        persona.setEmail(rs.getString("email"));

        Date fechaNacimiento = rs.getDate("fecha_nacimiento");

        if (fechaNacimiento != null) {
            persona.setFechaNacimiento(fechaNacimiento.toLocalDate());
        }

        persona.setSexo(rs.getString("sexo"));
        persona.setActivo(rs.getBoolean("activo"));

        Timestamp fechaCreacion = rs.getTimestamp("fecha_creacion");

        if (fechaCreacion != null) {
            persona.setFechaCreacion(fechaCreacion.toLocalDateTime());
        }

        Timestamp fechaActualizacion = rs.getTimestamp("fecha_actualizacion");

        if (fechaActualizacion != null) {
            persona.setFechaActualizacion(fechaActualizacion.toLocalDateTime());
        }

        return persona;

    }


    private void prepararPersona(
            PreparedStatement ps,
            Persona persona
    ) throws Exception {

        ps.setString(1, persona.getNombre());
        ps.setString(2, persona.getApellido());
        ps.setString(3, persona.getDni());
        ps.setString(4, persona.getTelefono());
        ps.setString(5, persona.getEmail());

        if (persona.getFechaNacimiento() != null) {
            ps.setDate(6, Date.valueOf(persona.getFechaNacimiento()));
        } else {
            ps.setNull(6, java.sql.Types.DATE);
        }

        ps.setString(7, persona.getSexo());

        ps.setBoolean(
                8,
                persona.getActivo() != null
                        ? persona.getActivo()
                        : true
        );

    }
    
    public boolean existeEmail(String email) {

        String sql = """
                SELECT COUNT(*)
                FROM persona
                WHERE email = ?
                """;

        try (

            Connection conexion = getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;

            }

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al verificar email.",
                    e
            );

        }

        return false;

    }

}