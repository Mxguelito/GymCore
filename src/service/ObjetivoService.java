package service;

import dao.ObjetivoDAO;
import model.Objetivo;

import java.util.List;

public class ObjetivoService {

    private final ObjetivoDAO objetivoDAO;

    public ObjetivoService() {

        objetivoDAO = new ObjetivoDAO();

    }

    public void guardar(Objetivo objetivo) {

        if (objetivo == null) {
            throw new IllegalArgumentException("El objetivo no puede ser nulo.");
        }

        if (objetivo.getNombre() == null || objetivo.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        objetivoDAO.guardar(objetivo);

    }

    public void actualizar(Objetivo objetivo) {

        if (objetivo == null) {
            throw new IllegalArgumentException("El objetivo no puede ser nulo.");
        }

        objetivoDAO.actualizar(objetivo);

    }

    public void eliminar(Integer id) {

        objetivoDAO.eliminar(id);

    }

    public Objetivo buscarPorId(Integer id) {

        return objetivoDAO.buscarPorId(id);

    }

    public List<Objetivo> listar() {

        return objetivoDAO.listar();

    }

}