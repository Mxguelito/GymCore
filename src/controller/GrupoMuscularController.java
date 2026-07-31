package controller;

import java.util.List;

import model.GrupoMuscular;
import service.GrupoMuscularService;

public class GrupoMuscularController {

    private final GrupoMuscularService grupoMuscularService;

    public GrupoMuscularController() {

        this.grupoMuscularService = new GrupoMuscularService();

    }

    public void guardar(GrupoMuscular grupoMuscular) {

        grupoMuscularService.guardar(grupoMuscular);

    }

    public void actualizar(GrupoMuscular grupoMuscular) {

        grupoMuscularService.actualizar(grupoMuscular);

    }

    public void eliminar(Integer id) {

        grupoMuscularService.eliminar(id);

    }

    public GrupoMuscular buscarPorId(Integer id) {

        return grupoMuscularService.buscarPorId(id);

    }

    public List<GrupoMuscular> listar() {

        return grupoMuscularService.listar();

    }

}