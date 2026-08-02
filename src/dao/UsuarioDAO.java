package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.Conexion;
import model.Usuario;

import model.Rol;





import model.Persona;

public class UsuarioDAO {

    public Usuario buscarPorUsername(String username) {

        try (Connection conexion = Conexion.conectar()) {

        	String sql = """
        			SELECT
        			    u.id_usuario,
        			    u.username,
        			    u.password_hash,
        			    u.estado,

        			    p.id_persona,
        			    p.nombre AS persona_nombre,
                        p.apellido,
                        p.email,

                        r.id_rol,
                        r.nombre AS rol_nombre,
                        r.descripcion

        			FROM usuario u

        			INNER JOIN persona p
        			    ON u.persona_id = p.id_persona

        			INNER JOIN rol r
        			    ON u.rol_id = r.id_rol

        			WHERE u.username = ?
        			""";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPasswordHash(rs.getString("password_hash"));
                usuario.setEstado(rs.getString("estado"));
                
                
                Persona persona = new Persona();

                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("persona_nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setEmail(rs.getString("email"));

                usuario.setPersona(persona);
                
                Rol rol = new Rol();

                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("rol_nombre"));
                rol.setDescripcion(rs.getString("descripcion"));

                usuario.setRol(rol);
                
                return usuario;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
    
  
    
    public boolean existeUsername(String username) {

        String sql = """
                SELECT COUNT(*)
                FROM usuario
                WHERE username = ?
                """;

        try (

            Connection conexion = Conexion.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    
    
    
    public void guardar(Usuario usuario) {

        String sql = """
                INSERT INTO usuario
                (
                    persona_id,
                    username,
                    password_hash,
                    estado,
                    rol_id
                )
                VALUES
                (?, ?, ?, ?, ?)
                """;

        try (

        		Connection conexion = Conexion.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setInt(
                    1,
                    usuario.getPersona().getIdPersona()
            );

            ps.setString(
                    2,
                    usuario.getUsername()
            );

            ps.setString(
                    3,
                    usuario.getPasswordHash()
            );

            ps.setString(
                    4,
                    usuario.getEstado()
            );

            ps.setInt(
                    5,
                    usuario.getRol().getIdRol()
            );

            ps.executeUpdate();

        } catch (Exception e) {

        	e.printStackTrace();

        }

    }
    
    
    public void actualizar(Usuario usuario) {

        String sqlPersona = """
                UPDATE persona
                SET
                    nombre = ?,
                    apellido = ?,
                    email = ?
                WHERE id_persona = ?
                """;

        String sqlUsuario = """
                UPDATE usuario
                SET
                    username = ?
                WHERE id_usuario = ?
                """;

        try (

            Connection conexion = Conexion.conectar()

        ) {

            PreparedStatement psPersona =
                    conexion.prepareStatement(sqlPersona);

            psPersona.setString(
                    1,
                    usuario.getPersona().getNombre()
            );

            psPersona.setString(
                    2,
                    usuario.getPersona().getApellido()
            );

            psPersona.setString(
                    3,
                    usuario.getPersona().getEmail()
            );

            psPersona.setInt(
                    4,
                    usuario.getPersona().getIdPersona()
            );

            psPersona.executeUpdate();

            PreparedStatement psUsuario =
                    conexion.prepareStatement(sqlUsuario);

            psUsuario.setString(
                    1,
                    usuario.getUsername()
            );

            psUsuario.setInt(
                    2,
                    usuario.getId()
            );

            psUsuario.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
  
    
    



}