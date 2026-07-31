package controller;

import model.Nivel;
import service.NivelService;

import java.util.List;

public class NivelController {

    private final NivelService nivelService;

    public NivelController() {

        nivelService = new NivelService();

    }

    public void guardar(Nivel nivel) {

        nivelService.guardar(nivel);

    }

    public void actualizar(Nivel nivel) {

        nivelService.actualizar(nivel);

    }

    public void eliminar(Integer idNivel) {

        nivelService.eliminar(idNivel);

    }

    public Nivel buscarPorId(Integer idNivel) {

        return nivelService.buscarPorId(idNivel);

    }

    public List<Nivel> listar() {

        return nivelService.listar();

    }

}