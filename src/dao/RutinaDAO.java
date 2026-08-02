package dao;

import framework.dao.BaseDAO;
import framework.exception.DatabaseException;

import model.Rutina;
import model.RutinaDetalle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Persona;
import model.Entrenador;
import java.sql.Date;

import model.Ejercicio;

public class RutinaDAO extends BaseDAO<Rutina> {

    public void guardar(Rutina rutina) {

        Connection connection = null;

        try {

            connection = getConnection();

            connection.setAutoCommit(false);

            guardarCabecera(connection, rutina);

            guardarDetalles(connection, rutina);

            connection.commit();

        } catch (Exception e) {

            try {

                if (connection != null) {

                    connection.rollback();

                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            throw new DatabaseException(
                    "Error al guardar la rutina.",
                    e
            );

        } finally {

            try {

                if (connection != null) {

                    connection.setAutoCommit(true);

                    connection.close();

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

    private void guardarCabecera(
            Connection connection,
            Rutina rutina
    ) {

        String sql = """

            INSERT INTO rutina (

                cliente_id,

                entrenador_id,

                nombre,

                descripcion,

                fecha_inicio,

                fecha_fin,

                estado,

                activo

            )

            VALUES (?,?,?,?,?,?,?,?)

            """;

        try (

                PreparedStatement ps =

                        connection.prepareStatement(

                                sql,

                                PreparedStatement.RETURN_GENERATED_KEYS

                        )

        ) {

            ps.setInt(

                    1,

                    rutina.getCliente().getIdCliente()

            );

            ps.setInt(

                    2,

                    rutina.getEntrenador().getIdEntrenador()

            );

            ps.setString(

                    3,

                    rutina.getNombre()

            );

            ps.setString(

                    4,

                    rutina.getDescripcion()

            );

            ps.setDate(
                    5,
                    Date.valueOf(
                            rutina.getFechaInicio()
                    )
            );

            ps.setDate(
                    6,
                    Date.valueOf(
                            rutina.getFechaFin()
                    )
            );

            ps.setString(

                    7,

                    rutina.getEstado()

            );

            ps.setBoolean(

                    8,

                    true

            );

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                rutina.setIdRutina(

                        rs.getInt(1)

                );

            }

        } catch (Exception e) {

            throw new DatabaseException(

                    "Error al guardar la rutina.",

                    e

            );

        }

    }

    private void actualizarCabecera(
            Connection connection,
            Rutina rutina
    ) {

        String sql = """

            UPDATE rutina

            SET

                cliente_id = ?,

                entrenador_id = ?,

                nombre = ?,

                descripcion = ?,

                fecha_inicio = ?,

                fecha_fin = ?,

                estado = ?

            WHERE id_rutina = ?

            """;

        try (

                PreparedStatement ps =
                        connection.prepareStatement(sql)

        ) {

            ps.setInt(
                    1,
                    rutina.getCliente().getIdCliente()
            );

            ps.setInt(
                    2,
                    rutina.getEntrenador().getIdEntrenador()
            );

            ps.setString(
                    3,
                    rutina.getNombre()
            );

            ps.setString(
                    4,
                    rutina.getDescripcion()
            );

            ps.setDate(
                    5,
                    java.sql.Date.valueOf(
                            rutina.getFechaInicio()
                    )
            );

            ps.setDate(
                    6,
                    java.sql.Date.valueOf(
                            rutina.getFechaFin()
                    )
            );

            ps.setString(
                    7,
                    rutina.getEstado()
            );

            ps.setInt(
                    8,
                    rutina.getIdRutina()
            );

            ps.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al actualizar la rutina.",
                    e
            );

        }

    }

 
   private void guardarDetalles(
            Connection connection,
            Rutina rutina
    ) {

        String sql = """

            INSERT INTO rutina_detalle (

                rutina_id,

                ejercicio_id,

                series,

                repeticiones,

                peso,

                descanso,

                orden

            )

            VALUES (?,?,?,?,?,?,?)

            """;

        try (

                PreparedStatement ps =
                        connection.prepareStatement(sql)

        ) {

            for (RutinaDetalle detalle : rutina.getDetalles()) {

                ps.setInt(
                        1,
                        rutina.getIdRutina()
                );

                ps.setInt(
                        2,
                        detalle.getEjercicio().getIdEjercicio()
                );

                ps.setInt(
                        3,
                        detalle.getSeries()
                );

                ps.setInt(
                        4,
                        detalle.getRepeticiones()
                );

                ps.setDouble(
                        5,
                        detalle.getPeso()
                );

                ps.setInt(
                        6,
                        detalle.getDescanso()
                );

                ps.setInt(
                        7,
                        detalle.getOrden()
                );

                ps.executeUpdate();

            }

        } catch (Exception e) {

            throw new DatabaseException(

                    "Error al guardar los ejercicios de la rutina.",

                    e

            );

        }

    }

   private void actualizarDetalles(
	        Connection connection,
	        Rutina rutina
	) {

	    try {

	        String eliminar = """

	            DELETE FROM rutina_detalle

	            WHERE rutina_id = ?

	            """;

	        PreparedStatement psEliminar =
	                connection.prepareStatement(eliminar);

	        psEliminar.setInt(
	                1,
	                rutina.getIdRutina()
	        );

	        psEliminar.executeUpdate();

	        guardarDetalles(
	                connection,
	                rutina
	        );

	    } catch (Exception e) {

	        throw new DatabaseException(
	                "Error al actualizar los ejercicios de la rutina.",
	                e
	        );

	    }

	}

   public void actualizar(Rutina rutina) {

        Connection connection = null;

        try {

            connection = getConnection();

            connection.setAutoCommit(false);

            actualizarCabecera(connection, rutina);

            actualizarDetalles(connection, rutina);

            connection.commit();

        } catch (Exception e) {

            try {

                if (connection != null) {

                    connection.rollback();

                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            throw new DatabaseException(
                    "Error al actualizar la rutina.",
                    e
            );

        } finally {

            try {

                if (connection != null) {

                    connection.setAutoCommit(true);

                    connection.close();

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

   public void eliminar(Integer id) {

	    String sql = """

	        UPDATE rutina

	        SET activo = FALSE

	        WHERE id_rutina = ?

	        """;

	    try (

	            Connection connection = getConnection();

	            PreparedStatement ps =
	                    connection.prepareStatement(sql)

	    ) {

	        ps.setInt(
	                1,
	                id
	        );

	        ps.executeUpdate();

	    } catch (Exception e) {

	        throw new DatabaseException(

	                "Error al eliminar la rutina.",

	                e

	        );

	    }

	}

    @Override
    public Rutina buscarPorId(Integer id) {

        Rutina rutina = null;

        String sql = """

            SELECT

                r.id_rutina,
                r.nombre,
                r.descripcion,
                r.fecha_inicio,
                r.fecha_fin,
                r.estado,

                c.id_cliente,

                pc.id_persona cliente_persona_id,
                pc.nombre cliente_nombre,
                pc.apellido cliente_apellido,

                e.id_entrenador,

                pe.id_persona entrenador_persona_id,
                pe.nombre entrenador_nombre,
                pe.apellido entrenador_apellido

            FROM rutina r

            INNER JOIN cliente c
                ON r.cliente_id = c.id_cliente

            INNER JOIN persona pc
                ON c.persona_id = pc.id_persona

            INNER JOIN entrenador e
                ON r.entrenador_id = e.id_entrenador

            INNER JOIN persona pe
                ON e.persona_id = pe.id_persona

            WHERE r.id_rutina = ?

            """;

        try (

                Connection connection = getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                rutina = new Rutina();

                rutina.setIdRutina(
                        rs.getInt("id_rutina")
                );

                rutina.setNombre(
                        rs.getString("nombre")
                );

                rutina.setDescripcion(
                        rs.getString("descripcion")
                );

                rutina.setFechaInicio(
                        rs.getDate("fecha_inicio").toLocalDate()
                );

                rutina.setFechaFin(
                        rs.getDate("fecha_fin").toLocalDate()
                );

                rutina.setEstado(
                        rs.getString("estado")
                );

                Cliente cliente = new Cliente();

                cliente.setIdCliente(
                        rs.getInt("id_cliente")
                );

                Persona personaCliente = new Persona();

                personaCliente.setIdPersona(
                        rs.getInt("cliente_persona_id")
                );

                personaCliente.setNombre(
                        rs.getString("cliente_nombre")
                );

                personaCliente.setApellido(
                        rs.getString("cliente_apellido")
                );

                cliente.setPersona(personaCliente);

                rutina.setCliente(cliente);

                Entrenador entrenador = new Entrenador();

                entrenador.setIdEntrenador(
                        rs.getInt("id_entrenador")
                );

                entrenador.setPersonaId(
                        rs.getInt("entrenador_persona_id")
                );

                entrenador.setNombrePersona(
                        rs.getString("entrenador_nombre")
                );

                entrenador.setApellidoPersona(
                        rs.getString("entrenador_apellido")
                );

                rutina.setEntrenador(entrenador);

            }

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al buscar rutina.",
                    e
            );

        }
        
        rutina.setDetalles(
                cargarDetalles(
                        rutina.getIdRutina()
                )
        );

        return rutina;

    }

   
    private List<RutinaDetalle> cargarDetalles(
            Integer idRutina
    ) {

        List<RutinaDetalle> detalles =
                new ArrayList<>();

        String sql = """

            SELECT

                rd.id_rutina_detalle,
                rd.series,
                rd.repeticiones,
                rd.peso,
                rd.descanso,
                rd.orden,

                e.id_ejercicio,
                e.nombre

            FROM rutina_detalle rd

            INNER JOIN ejercicio e
                ON rd.ejercicio_id = e.id_ejercicio

            WHERE rd.rutina_id = ?

            ORDER BY rd.orden

            """;

        try (

        		Connection connection = getConnection();

        		PreparedStatement ps =
        		        connection.prepareStatement(sql);

        ) {

            ps.setInt(
                    1,
                    idRutina
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                RutinaDetalle detalle =
                        new RutinaDetalle();

                detalle.setIdRutinaDetalle(
                        rs.getInt("id_rutina_detalle")
                );

                Ejercicio ejercicio =
                        new Ejercicio();

                ejercicio.setIdEjercicio(
                        rs.getInt("id_ejercicio")
                );

                ejercicio.setNombre(
                        rs.getString("nombre")
                );

                detalle.setEjercicio(
                        ejercicio
                );

                detalle.setSeries(
                        rs.getInt("series")
                );

                detalle.setRepeticiones(
                        rs.getInt("repeticiones")
                );

                detalle.setPeso(
                        rs.getDouble("peso")
                );

                detalle.setDescanso(
                        rs.getInt("descanso")
                );

                detalle.setOrden(
                        rs.getInt("orden")
                );

                detalles.add(detalle);

            }

        } catch (Exception e) {

            throw new DatabaseException(
                    "Error al cargar los ejercicios.",
                    e
            );

        }

        return detalles;

    }
    
    @Override
    public List<Rutina> listar() {

        List<Rutina> rutinas = new ArrayList<>();

        String sql = """

        		SELECT

    r.id_rutina,
    r.nombre,
    r.descripcion,
    r.fecha_inicio,
    r.fecha_fin,
    r.estado,
    r.activo,

    c.id_cliente,

    pc.id_persona AS cliente_persona_id,
    pc.nombre AS cliente_nombre,
    pc.apellido AS cliente_apellido,

    e.id_entrenador,

    pe.id_persona AS entrenador_persona_id,
    pe.nombre AS entrenador_nombre,
    pe.apellido AS entrenador_apellido

FROM rutina r

INNER JOIN cliente c
ON r.cliente_id = c.id_cliente

INNER JOIN persona pc
ON c.persona_id = pc.id_persona

INNER JOIN entrenador e
ON r.entrenador_id = e.id_entrenador

INNER JOIN persona pe
ON e.persona_id = pe.id_persona

WHERE r.activo = TRUE

ORDER BY r.nombre

        		""";

        try (

                Connection connection = getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()

        ) {

        	while (rs.next()) {

        	    Rutina rutina = new Rutina();

        	    rutina.setIdRutina(
        	            rs.getInt("id_rutina")
        	    );

        	    rutina.setNombre(
        	            rs.getString("nombre")
        	    );

        	    rutina.setDescripcion(
        	            rs.getString("descripcion")
        	    );

        	    rutina.setFechaInicio(
        	            rs.getDate("fecha_inicio").toLocalDate()
        	    );

        	    rutina.setFechaFin(
        	            rs.getDate("fecha_fin").toLocalDate()
        	    );

        	    rutina.setEstado(
        	            rs.getString("estado")
        	    );

        	    Cliente cliente = new Cliente();

        	    cliente.setIdCliente(
        	            rs.getInt("id_cliente")
        	    );

        	    Persona personaCliente = new Persona();

        	    personaCliente.setIdPersona(
        	    		rs.getInt("cliente_persona_id")
        	    );

        	    personaCliente.setNombre(
        	    		rs.getString("cliente_nombre")
        	    );

        	    personaCliente.setApellido(
        	    		rs.getString("cliente_apellido")
        	    );

        	    cliente.setPersona(personaCliente);

        	    rutina.setCliente(cliente);

        	    Entrenador entrenador = new Entrenador();

        	    entrenador.setIdEntrenador(
        	            rs.getInt("id_entrenador")
        	    );

        	    entrenador.setPersonaId(
        	    		rs.getInt("entrenador_persona_id")
        	    );

        	    entrenador.setNombrePersona(
        	    		rs.getString("entrenador_nombre")
        	    );

        	    entrenador.setApellidoPersona(
        	    		rs.getString("entrenador_apellido")
        	    );

        	    rutina.setEntrenador(entrenador);

        	    rutinas.add(rutina);

        	}

        } catch (Exception e) {

            throw new DatabaseException(

                    "Error al listar rutinas.",

                    e

            );

        }

        return rutinas;

    }

    
    
    public List<Rutina> listarPorCliente(Integer clienteId) {

        List<Rutina> todas = listar();

        List<Rutina> resultado = new ArrayList<>();

        for (Rutina rutina : todas) {

        	if (rutina.getCliente().getIdCliente() == clienteId) {

        	    resultado.add(rutina);

        	}

        }

        return resultado;

    }
}