package service;

import dao.EntrenadorDAO;
import model.Entrenador;

import java.util.List;

public class EntrenadorService {

    private final EntrenadorDAO entrenadorDAO;

    public EntrenadorService() {
        this.entrenadorDAO = new EntrenadorDAO();
    }

    public void guardar(Entrenador entrenador) {
        entrenadorDAO.guardar(entrenador);
    }

    public void actualizar(Entrenador entrenador) {
        entrenadorDAO.actualizar(entrenador);
    }

    public void eliminar(Integer id) {
        entrenadorDAO.eliminar(id);
    }

    public Entrenador buscarPorId(Integer id) {
        return entrenadorDAO.buscarPorId(id);
    }

    public List<Entrenador> listar() {
        return entrenadorDAO.listar();
    }

}