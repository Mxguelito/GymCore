package dao;

import framework.dao.BaseDAO;

import framework.exception.DatabaseException;

import model.Pago;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Cliente;
import model.Persona;

public class PagoDAO extends BaseDAO<Pago> {

	@Override
	public void guardar(Pago pago) {

	    String sql = """

	        INSERT INTO pago (

	            cliente_id,

	            fecha_pago,

	            periodo,

	            importe,

	            metodo_pago,

	            estado,

	            observaciones,

	            activo

	        )

	        VALUES (?,?,?,?,?,?,?,?)

	        """;

	    try (

	            Connection connection = getConnection();

	            PreparedStatement ps =
	                    connection.prepareStatement(sql)

	    ) {

	        ps.setInt(
	                1,
	                pago.getCliente().getIdCliente()
	        );

	        ps.setDate(
	                2,
	                java.sql.Date.valueOf(
	                        pago.getFechaPago()
	                )
	        );

	        ps.setString(
	                3,
	                pago.getPeriodo()
	        );

	        ps.setDouble(
	                4,
	                pago.getImporte()
	        );

	        ps.setString(
	                5,
	                pago.getMetodoPago()
	        );

	        ps.setString(
	                6,
	                pago.getEstado()
	        );

	        ps.setString(
	                7,
	                pago.getObservaciones()
	        );

	        ps.setBoolean(
	                8,
	                true
	        );

	        ps.executeUpdate();

	    } catch (Exception e) {

	        throw new DatabaseException(
	                "Error al guardar el pago.",
	                e
	        );

	    }

	}

	@Override
	public void actualizar(Pago pago) {

	    String sql = """

	        UPDATE pago

	        SET

	            cliente_id = ?,
	            fecha_pago = ?,
	            periodo = ?,
	            importe = ?,
	            metodo_pago = ?,
	            estado = ?,
	            observaciones = ?

	        WHERE id_pago = ?

	        """;

	    try (

	            Connection connection = getConnection();

	            PreparedStatement ps =
	                    connection.prepareStatement(sql)

	    ) {

	        ps.setInt(
	                1,
	                pago.getCliente().getIdCliente()
	        );

	        ps.setDate(
	                2,
	                java.sql.Date.valueOf(
	                        pago.getFechaPago()
	                )
	        );

	        ps.setString(
	                3,
	                pago.getPeriodo()
	        );

	        ps.setDouble(
	                4,
	                pago.getImporte()
	        );

	        ps.setString(
	                5,
	                pago.getMetodoPago()
	        );

	        ps.setString(
	                6,
	                pago.getEstado()
	        );

	        ps.setString(
	                7,
	                pago.getObservaciones()
	        );

	        ps.setInt(
	                8,
	                pago.getIdPago()
	        );

	        ps.executeUpdate();

	    } catch (Exception e) {

	        throw new DatabaseException(
	                "Error al actualizar el pago.",
	                e
	        );

	    }

	}

	@Override
	public void eliminar(Integer id) {

	    String sql = """

	        UPDATE pago

	        SET activo = FALSE

	        WHERE id_pago = ?

	        """;

	    try (

	            Connection connection = getConnection();

	            PreparedStatement ps =
	                    connection.prepareStatement(sql)

	    ) {

	        ps.setInt(1, id);

	        ps.executeUpdate();

	    } catch (Exception e) {

	        throw new DatabaseException(
	                "Error al eliminar el pago.",
	                e
	        );

	    }

	}

    @Override
    public Pago buscarPorId(Integer id) {

        String sql = """

            SELECT

                p.id_pago,
                p.fecha_pago,
                p.periodo,
                p.importe,
                p.metodo_pago,
                p.estado,
                p.observaciones,

                c.id_cliente,

                pe.id_persona,
                pe.nombre,
                pe.apellido

            FROM pago p

            INNER JOIN cliente c
                ON p.cliente_id = c.id_cliente

            INNER JOIN persona pe
                ON c.persona_id = pe.id_persona

            WHERE p.id_pago = ?

            """;

        try (

                Connection connection = getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Pago pago = new Pago();

                pago.setIdPago(
                        rs.getInt("id_pago")
                );

                pago.setFechaPago(
                        rs.getDate("fecha_pago").toLocalDate()
                );

                pago.setPeriodo(
                        rs.getString("periodo")
                );

                pago.setImporte(
                        rs.getDouble("importe")
                );

                pago.setMetodoPago(
                        rs.getString("metodo_pago")
                );

                pago.setEstado(
                        rs.getString("estado")
                );

                pago.setObservaciones(
                        rs.getString("observaciones")
                );

                Cliente cliente = new Cliente();

                cliente.setIdCliente(
                        rs.getInt("id_cliente")
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

                cliente.setPersona(persona);

                pago.setCliente(cliente);

                return pago;

            }

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al buscar el pago.",
                    e
            );

        }

        return null;

    }

    @Override
    public List<Pago> listar() {

        List<Pago> pagos = new ArrayList<>();

        String sql = """

            SELECT

                p.id_pago,

                p.fecha_pago,

                p.periodo,

                p.importe,

                p.metodo_pago,

                p.estado,

                p.observaciones,

                c.id_cliente,

                pe.id_persona,

                pe.nombre,

                pe.apellido

            FROM pago p

            INNER JOIN cliente c

                ON p.cliente_id = c.id_cliente

            INNER JOIN persona pe

                ON c.persona_id = pe.id_persona

            WHERE p.activo = TRUE

            ORDER BY p.fecha_pago DESC

            """;

        try (

                Connection connection = getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()

        ) {

            while (rs.next()) {

                Pago pago = new Pago();

                pago.setIdPago(
                        rs.getInt("id_pago")
                );

                pago.setFechaPago(
                        rs.getDate("fecha_pago").toLocalDate()
                );

                pago.setPeriodo(
                        rs.getString("periodo")
                );

                pago.setImporte(
                        rs.getDouble("importe")
                );

                pago.setMetodoPago(
                        rs.getString("metodo_pago")
                );

                pago.setEstado(
                        rs.getString("estado")
                );

                pago.setObservaciones(
                        rs.getString("observaciones")
                );

                Cliente cliente = new Cliente();

                cliente.setIdCliente(
                        rs.getInt("id_cliente")
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

                cliente.setPersona(persona);

                pago.setCliente(cliente);

                pagos.add(pago);

            }

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al listar pagos.",
                    e
            );

        }

        return pagos;

    }

}