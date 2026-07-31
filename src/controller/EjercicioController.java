package controller;

import java.util.List;

import model.Ejercicio;
import service.EjercicioService;

public class EjercicioController {

    private final EjercicioService ejercicioService;

    public EjercicioController() {

        this.ejercicioService = new EjercicioService();

    }

    public void guardar(Ejercicio ejercicio) {

        ejercicioService.guardar(ejercicio);

    }

    public void actualizar(Ejercicio ejercicio) {

        ejercicioService.actualizar(ejercicio);

    }

    public void eliminar(Integer id) {

        ejercicioService.eliminar(id);

    }

    public Ejercicio buscarPorId(Integer id) {

        return ejercicioService.buscarPorId(id);

    }

    public List<Ejercicio> listar() {

        return ejercicioService.listar();

    }
    
    public List<Ejercicio> listarPorGrupo(Integer idGrupo) {

        return ejercicioService.listarPorGrupo(idGrupo);

    }

}