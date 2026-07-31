package controller;

import model.Entrenador;
import service.EntrenadorService;

import java.util.List;

public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController() {
        this.entrenadorService = new EntrenadorService();
    }

    public void guardar(Entrenador entrenador) {
        entrenadorService.guardar(entrenador);
    }

    public void actualizar(Entrenador entrenador) {
        entrenadorService.actualizar(entrenador);
    }

    public void eliminar(Integer id) {
        entrenadorService.eliminar(id);
    }

    public Entrenador buscarPorId(Integer id) {
        return entrenadorService.buscarPorId(id);
    }

    public List<Entrenador> listar() {
        return entrenadorService.listar();
    }

}