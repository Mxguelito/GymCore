package service;

import java.util.List;

import dao.RutinaDAO;
import framework.service.BaseService;
import model.Rutina;

public class RutinaService extends BaseService<Rutina> {

    private final RutinaDAO rutinaDAO;

    public RutinaService() {

        rutinaDAO = new RutinaDAO();

    }

    @Override
    public void guardar(Rutina rutina) {

        rutinaDAO.guardar(rutina);

    }

    @Override
    public void actualizar(Rutina rutina) {

        rutinaDAO.actualizar(rutina);

    }

    @Override
    public void eliminar(Integer id) {

        rutinaDAO.eliminar(id);

    }

    @Override
    public Rutina buscarPorId(Integer id) {

        return rutinaDAO.buscarPorId(id);

    }

    @Override
    public List<Rutina> listar() {

        return rutinaDAO.listar();

    }

}