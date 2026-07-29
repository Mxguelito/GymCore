package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.Conexion;
import model.Usuario;

public class UsuarioDAO {

    public Usuario buscarPorUsername(String username) {

        try (Connection conexion = Conexion.conectar()) {

            String sql = "SELECT * FROM usuario WHERE username = ?";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPasswordHash(rs.getString("password_hash"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setRolId(rs.getInt("rol_id"));
                
                return usuario;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

}