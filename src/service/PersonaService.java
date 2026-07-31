package service;

import java.util.List;

import dao.PersonaDAO;
import framework.service.BaseService;
import model.Persona;

public class PersonaService extends BaseService<Persona> {

    private final PersonaDAO personaDAO;

    public PersonaService() {
        this.personaDAO = new PersonaDAO();
    }

    @Override
    public void guardar(Persona persona) {
        personaDAO.guardar(persona);
    }

    @Override
    public void actualizar(Persona persona) {
        personaDAO.actualizar(persona);
    }

    @Override
    public void eliminar(Integer id) {
        personaDAO.eliminar(id);
    }

    @Override
    public Persona buscarPorId(Integer id) {
        return personaDAO.buscarPorId(id);
    }

    @Override
    public List<Persona> listar() {
        return personaDAO.listar();
    }

}