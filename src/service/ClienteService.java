package service;

import dao.ClienteDAO;
import dao.PersonaDAO;
import model.Cliente;
import model.Persona;

import java.util.List;

import java.time.LocalDate;

public class ClienteService {

    private final ClienteDAO clienteDAO;
    private final PersonaDAO personaDAO;

    public ClienteService() {

        this.clienteDAO = new ClienteDAO();
        this.personaDAO = new PersonaDAO();

    }

    public void guardar(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        Persona persona = cliente.getPersona();

        if (persona == null) {
            throw new IllegalArgumentException("La persona es obligatoria.");
        }

        // Reglas de negocio
        if (cliente.getFechaIngreso() == null) {
            cliente.setFechaIngreso(LocalDate.now());
        }

        if (cliente.getEstado() == null || cliente.getEstado().isBlank()) {
            cliente.setEstado("ACTIVO");
        }

        // Primero guarda la persona
        personaDAO.guardar(persona);

        // Luego guarda el cliente
        clienteDAO.guardar(cliente);

    }

    public void actualizar(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        personaDAO.actualizar(cliente.getPersona());

        clienteDAO.actualizar(cliente);

    }

    public void eliminar(Integer id) {

        clienteDAO.eliminar(id);

    }

    public Cliente buscarPorId(Integer id) {

        return clienteDAO.buscarPorId(id);

    }

    public List<Cliente> listar() {

        return clienteDAO.listar();

    }

    
    public Cliente buscarPorPersona(Integer idPersona) {

        return clienteDAO.buscarPorPersona(idPersona);

    }
}