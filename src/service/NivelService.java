package service;

import dao.NivelDAO;
import model.Nivel;

import java.util.List;

public class NivelService {

    private final NivelDAO nivelDAO;

    public NivelService() {

        nivelDAO = new NivelDAO();

    }

    public void guardar(Nivel nivel) {

        nivelDAO.guardar(nivel);

    }

    public void actualizar(Nivel nivel) {

        nivelDAO.actualizar(nivel);

    }

    public void eliminar(Integer idNivel) {

        nivelDAO.eliminar(idNivel);

    }

    public Nivel buscarPorId(Integer idNivel) {

        return nivelDAO.buscarPorId(idNivel);

    }

    public List<Nivel> listar() {

        return nivelDAO.listar();

    }

}