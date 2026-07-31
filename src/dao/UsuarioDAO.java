package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.Conexion;
import model.Usuario;

import model.Rol;

public class UsuarioDAO {

    public Usuario buscarPorUsername(String username) {

        try (Connection conexion = Conexion.conectar()) {

        	String sql = """
        			SELECT
        			    u.id_usuario,
        			    u.username,
        			    u.password_hash,
        			    u.estado,
        			    r.id_rol,
        			    r.nombre,
        			    r.descripcion
        			FROM usuario u
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
                Rol rol = new Rol();

                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombre(rs.getString("nombre"));
                rol.setDescripcion(rs.getString("descripcion"));

                usuario.setRol(rol);
                
                return usuario;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}