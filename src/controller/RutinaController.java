package controller;

import java.util.List;

import model.Rutina;
import service.RutinaService;

public class RutinaController {

    private final RutinaService rutinaService;

    public RutinaController() {

        rutinaService = new RutinaService();

    }

    public void guardar(Rutina rutina) {

        rutinaService.guardar(rutina);

    }

    public void actualizar(Rutina rutina) {

        rutinaService.actualizar(rutina);

    }

    public void eliminar(Integer id) {

        rutinaService.eliminar(id);

    }

    public Rutina buscarPorId(Integer id) {

        return rutinaService.buscarPorId(id);

    }

    public List<Rutina> listar() {

        return rutinaService.listar();

    }

}