package dao;

import framework.dao.BaseDAO;
import framework.exception.DatabaseException;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import model.Persona;
public class ClienteDAO extends BaseDAO<Cliente> {

    @Override
    public void guardar(Cliente cliente) {

        String sql = """
                INSERT INTO cliente
                (
                    persona_id,
                    fecha_ingreso,
                    estado
                )
                VALUES
                (?, ?, ?)
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setInt(
                    1,
                    cliente.getPersona().getIdPersona()
            );

            if (cliente.getFechaIngreso() != null) {

                ps.setDate(
                        2,
                        Date.valueOf(cliente.getFechaIngreso())
                );

            } else {

                ps.setNull(2, java.sql.Types.DATE);

            }

            ps.setString(
                    3,
                    cliente.getEstado()
            );

            int filas = ps.executeUpdate();

            System.out.println();
            System.out.println("====================================");
            System.out.println(" CLIENTE GUARDADO CORRECTAMENTE");
            System.out.println(" Filas afectadas: " + filas);
            System.out.println("====================================");

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Error al guardar el cliente.",
                    e
            );

        }

    }

    @Override
    public void actualizar(Cliente cliente) {

        String sql = """
                UPDATE cliente
                SET
                    estado = ?
                WHERE id_cliente = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);

        ) {

            ps.setString(
                    1,
                    cliente.getEstado()
            );

            ps.setInt(
                    2,
                    cliente.getIdCliente()
            );

            int filas = ps.executeUpdate();

            System.out.println();
            System.out.println("====================================");
            System.out.println(" CLIENTE ACTUALIZADO");
            System.out.println(" Filas afectadas: " + filas);
            System.out.println("====================================");

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al actualizar el cliente.",
                    e
            );

        }

    }
    @Override
    public void eliminar(Integer id) {

        String sql = """
                UPDATE cliente
                SET estado = ?
                WHERE id_cliente = ?
                """;

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)

        ) {

            ps.setString(1, "INACTIVO");
            ps.setInt(2, id);
            
            System.out.println("[DAO] Ejecutando UPDATE cliente...");
            System.out.println("[DAO] ID = " + id);

            int filas = ps.executeUpdate();
            
            System.out.println("[DAO] Filas afectadas: " + filas);

            System.out.println();
            System.out.println("====================================");
            System.out.println(" CLIENTE DESACTIVADO");
            System.out.println(" Filas afectadas: " + filas);
            System.out.println("====================================");

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Error al eliminar el cliente.",
                    e
            );

        }

    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Cliente> listar() {

        String sql = """
                SELECT
                    c.id_cliente,
                    c.fecha_ingreso,
                    c.estado,

                    p.id_persona,
                    p.nombre,
                    p.apellido,
                    p.email

                FROM cliente c

                INNER JOIN persona p
                        ON c.persona_id = p.id_persona
                        
                        WHERE c.estado = 'ACTIVO'

                ORDER BY p.apellido, p.nombre
                """;

        List<Cliente> clientes = new ArrayList<>();

        try (

                Connection conexion = getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);

        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Persona persona = new Persona();

                persona.setIdPersona(rs.getInt("id_persona"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setEmail(rs.getString("email"));

                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setPersona(persona);

                Date fechaIngreso = rs.getDate("fecha_ingreso");

                if (fechaIngreso != null) {
                    cliente.setFechaIngreso(fechaIngreso.toLocalDate());
                }

                cliente.setEstado(rs.getString("estado"));

                clientes.add(cliente);

            }

            return clientes;

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al listar clientes.",
                    e
            );

        }

    }

    
    
    public Cliente buscarPorPersona(Integer idPersona) {

        String sql = """

            SELECT

                c.id_cliente,
                c.fecha_ingreso,
                c.estado,

                p.id_persona,
                p.nombre,
                p.apellido,
                p.email

            FROM cliente c

            INNER JOIN persona p
                ON c.persona_id = p.id_persona

            WHERE p.id_persona = ?

            """;

        try (

            Connection connection = getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(sql)

        ) {

            ps.setInt(1, idPersona);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdCliente(
                        rs.getInt("id_cliente")
                );

                cliente.setFechaIngreso(
                        rs.getDate("fecha_ingreso").toLocalDate()
                );

                cliente.setEstado(
                        rs.getString("estado")
                );

                Persona persona = new Persona();

                persona.setIdPersona(
                        rs.getInt("id_persona")
                );

                persona.setNombre(
                        rs.getString("nombre")
                );

                persona.setApellido(
                        rs.getString("apellido")
                );

                persona.setEmail(
                        rs.getString("email")
                );

                cliente.setPersona(persona);

                return cliente;

            }

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al buscar cliente.",
                    e
            );

        }

        return null;

    }
}