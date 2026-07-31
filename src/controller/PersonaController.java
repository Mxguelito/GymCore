package controller;

import java.util.List;

import model.Persona;
import service.PersonaService;

public class PersonaController {

    private PersonaService personaService;

    public PersonaController() {

        personaService = new PersonaService();

    }

    public void guardar(Persona persona) {

        personaService.guardar(persona);

    }

    public void actualizar(Persona persona) {

        personaService.actualizar(persona);

    }

    public void eliminar(Integer id) {

        personaService.eliminar(id);

    }

    public Persona buscarPorId(Integer id) {

        return personaService.buscarPorId(id);

    }

    public List<Persona> listar() {

        return personaService.listar();

    }

}