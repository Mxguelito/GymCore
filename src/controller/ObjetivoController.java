package controller;

import model.Objetivo;
import service.ObjetivoService;

import java.util.List;

public class ObjetivoController {

    private final ObjetivoService objetivoService;

    public ObjetivoController() {

        objetivoService = new ObjetivoService();

    }

    public void guardar(Objetivo objetivo) {

        objetivoService.guardar(objetivo);

    }

    public void actualizar(Objetivo objetivo) {

        objetivoService.actualizar(objetivo);

    }

    public void eliminar(Integer id) {

        objetivoService.eliminar(id);

    }

    public Objetivo buscarPorId(Integer id) {

        return objetivoService.buscarPorId(id);

    }

    public List<Objetivo> listar() {

        return objetivoService.listar();

    }

}