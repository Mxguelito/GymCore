package service;

import java.util.List;

import dao.GrupoMuscularDAO;
import framework.service.BaseService;
import model.GrupoMuscular;

public class GrupoMuscularService extends BaseService<GrupoMuscular> {

    private final GrupoMuscularDAO grupoMuscularDAO;

    public GrupoMuscularService() {

        this.grupoMuscularDAO = new GrupoMuscularDAO();

    }

    @Override
    public void guardar(GrupoMuscular grupoMuscular) {

        grupoMuscularDAO.guardar(grupoMuscular);

    }

    @Override
    public void actualizar(GrupoMuscular grupoMuscular) {

        grupoMuscularDAO.actualizar(grupoMuscular);

    }

    @Override
    public void eliminar(Integer id) {

        grupoMuscularDAO.eliminar(id);

    }

    @Override
    public GrupoMuscular buscarPorId(Integer id) {

        return grupoMuscularDAO.buscarPorId(id);

    }

    @Override
    public List<GrupoMuscular> listar() {

        return grupoMuscularDAO.listar();

    }

}